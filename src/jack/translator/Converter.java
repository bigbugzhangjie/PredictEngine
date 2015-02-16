package jack.translator;

import jack.exception.FileFormatException;
import jack.exception.UndefinedException;
import jack.ml.data.RawCorpus;
import jack.translator.datatype.SuperType;
import jack.translator.exception.UndefinedType;
import jack.utility.FileTools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Converter {

	String casename = "";
	HashMap<String,MappingRule> rules = new HashMap<String,MappingRule>();
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
		HashMap<String,MappingRule> ret = new HashMap<String,MappingRule>();
		for(String line : lines){
			if(line.startsWith("#")){
				continue;
			}
			MappingRule rule = MappingRule.load(line);
			ret.put(rule.getRulename(),rule);
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
//			if(sample.size()!= rules.size()){ //字段数目不对
//				error_line++;
//				System.err.println("size mismatch error in line: "+sample);
//				continue;
//			}
			ArrayList<String> out = new ArrayList<String>();
			for(int i=0; i<sample.size();i++){	// 处理样本中的每个字段
				String col = sample.get(i);
				
				SuperType type = definer.getType(i);
				String colname = type.getColumnName();
				MappingRule rule = rules.get(colname);
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
		String casename = "1127";
		String depart = "雪域之光";
		String party = "安融征信";		//"安融征信"  	"百分点"   "雪域之光"
		
//		File datafile = new File("test/security/车险test.data");//文本文件
//		File deffile = new File("test/security/车险test.def");//各列的数据类型定义
//		File rulefile = new File("test/security/安融征信.rule");//各列的转换规则定义
	
		File datafile = new File("test/security/车险19k-v2.data");//车险19k-v2.data   车险-10000.data
		File deffile = new File("test/security/车险19k-v2.def");//车险-10000.def
		File rulefile = new File("test/security/"+party+"-v2.rule");//安融征信 百分点
		
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
