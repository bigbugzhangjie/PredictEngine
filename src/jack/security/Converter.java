package jack.security;

import jack.exception.FileFormatException;
import jack.exception.UndefinedException;
import jack.ml.data.RawCorpus;
import jack.security.datatype.SuperType;
import jack.security.exception.UndefinedType;
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
	
	public void loadDefiner(File file) throws IOException, FileFormatException, UndefinedType{
		Definer def = new Definer();
		def.setName(casename);
		def.loadTypeList(file);
		this.definer = def;
	}
	
	/**
	 * 对corpus进行加密
	 * @param corpus
	 * @throws UndefinedException
	 */
	public void obfuscate(RawCorpus corpus) throws UndefinedException{
		ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
		System.out.println("obfuscating corpus ...");
		if(corpus==null || corpus.getData()==null || corpus.getSize()==0){
			System.err.println("non-input corpus...");
			return ;
		}

		ret = new ArrayList<ArrayList<String>>();
		int error_line = 0;
		
		for(ArrayList<String> sample : corpus.getData()){ //取出数据集中的每条样本
			if(sample.size()!= rules.size()){ //字段数目不对
				error_line++;
				System.err.println("size mismatch error in line: "+sample);
				continue;
			}
			ArrayList<String> out = new ArrayList<String>();
			for(int i=0; i<sample.size();i++){	// 处理样本中的每个字段
				String col = sample.get(i);
				MappingRule rule = rules.get(i);
				SuperType type = definer.getType(i);
				type.setRule(rule);		
				
				//加密函数名为空时，不加密
				if(rule.getFuncname()==null || rule.getFuncname().length()==0){
					ArrayList<String> orig = new ArrayList<String>();
					orig.add(col);
					out.addAll(orig);
				}else{
					out.addAll( type.obfuscate(col) );
				}
			}
			ret.add(out);
		}
		System.out.println("Found "+error_line+" error lines");
		result = ret;
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
		String casename = "1124";
		String depart = "雪域之光";
		String party = "雪域之光";		//"安融"	"百分点"
		
//		File datafile = new File("test/security/车险test.data");//文本文件
//		File deffile = new File("test/security/车险test.def");//各列的数据类型定义
//		File rulefile = new File("test/security/安融征信.rule");//各列的转换规则定义
	
		File datafile = new File("test/security/车险-10000.data");//文本文件
		File deffile = new File("test/security/车险-10000.def");//各列的数据类型定义
		File rulefile = new File("test/security/雪域之光.rule");//安融征信 百分点
		
		File outfile = new File("test/security/"+casename+"-"+depart+"-"+party+".out"); //转换后的结果文件
		
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
