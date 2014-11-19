package jack.security;

import jack.exception.FileFormatException;
import jack.exception.UndefinedException;
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
	
	/**
	 * 从文件读取映射规则：
	 * 以#开头的为注释行
	 * @param file
	 * @throws IOException
	 * @throws FileFormatException
	 */
	public void loadRules(File file) throws IOException, FileFormatException{
		List<String> lines = FileTools.getLineList(file);
		ArrayList<MappingRule> ret = new ArrayList<MappingRule>();
		for(String line : lines){
			if(line.startsWith("#")){
				continue;
			}
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
	
	public void obfuscate(RawCorpus corpus) throws UndefinedException{
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
	
	public static void main(String[] args) throws Exception {
		String casename = "test";
		String depart = "test-dept";
		String party = "test-3rdParty";
		
		File datafile = new File("test/security/车险test.data");//文本文件
		File deffile = new File("test/security/车险test.def");//各列的数据类型定义
		File rulefile = new File("test/security/安融征信.rule");//各列的转换规则定义
		
		File outfile = new File("test/security/安融征信.out"); //转换后的结果文件
		
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
