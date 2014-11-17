package jack.security;

import jack.exception.FileFormatException;
import jack.ml.data.RawCorpus;
import jack.security.datatype.SuperType;
import jack.utility.FileTools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Converter {

	String casename = "";
	ArrayList<MappingRule> rules = new ArrayList<MappingRule>();
	Definer definer;
	
	ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>(); 
	
	public Converter(String name){
		this.casename = name;
	}
	
	public void loadRules(File file) throws IOException, FileFormatException{
		List<String> lines = FileTools.getLineList(file);
		ArrayList<MappingRule> ret = new ArrayList<MappingRule>();
		for(String line : lines){
			MappingRule rule = MappingRule.load(line);
			ret.add(rule);
		}		
		this.rules = ret;
	}
	
	public void loadDefiner(File file) throws IOException, FileFormatException{
		Definer def = new Definer();
		def.setName(casename);
		def.loadTypeList(file);
		this.definer = def;
	}
	
	public void obfuscate(RawCorpus corpus){
		System.out.println("obfuscating corpus ...");
		if(corpus==null || corpus.getData()==null || corpus.getSize()==0){
			System.err.println("non-input corpus...");
			return;
		}

		result = new ArrayList<ArrayList<String>>();
		int error_line = 0;
		
		for(ArrayList<String> line : corpus.getData()){
			if(line.size()!= rules.size()){
				error_line++;
				System.err.println("size mismatch error in line: "+line);
				continue;
			}
			ArrayList<String> out = new ArrayList<String>();
			for(int i=0; i<line.size();i++){
				String col = line.get(i);
				MappingRule rule = rules.get(i);
				SuperType type = definer.getType(i);
				type.setRule(rule);				
				out.addAll( type.obfuscate(col) );				
			}
			result.add(out);
		}
		System.out.println("Found "+error_line+" error lines");
	}
	
	public void write(File file) throws IOException{
		FileWriter w = new FileWriter(file);
		for(ArrayList<String> l : result){
			String nl = "";
			for(String c : l){
				nl += c+"\t";
			}
			w.write(nl+"\n");
		}
		w.close();
	}
	
	public static void main(String[] args) throws IOException, FileFormatException {
		String casename = "";
		String depart = "";
		String party = "";
		
		File datafile = new File("");//文本文件
		File deffile = new File("");//各列的数据类型定义
		File rulefile = new File("");//各列的转换规则定义
		
		File outfile = new File(""); //转换后的结果文件
		
		Case mycase = new Case(casename,depart,party);
		mycase.fetch(datafile);
		
		Converter converter = new Converter(mycase.getName());
		converter.loadDefiner(deffile);
		converter.loadRules(rulefile);
		converter.obfuscate(mycase.getCorpus());
		converter.write(outfile);

		System.out.println("==========Finished!==========");
	}



}
