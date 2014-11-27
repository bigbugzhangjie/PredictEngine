package jack.ml.data;

import jack.utility.FileTools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ArrayList<ArrayList<String>>,很多行，每行很多String
 * @author bigbug
 *
 */
public class RawCorpus {
	String name = "";
	ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
	
//	public RawCorpus(String name){
//		this.name = name;
//	}
	public RawCorpus(String name,File f) throws IOException{
		this.name = name;
		load(f);
	}
	public RawCorpus(File f) throws IOException{
		load(f);
	}
	
	/**
	 * 从文件中读取每行，每行中用 \t 分割，读取各个字段并以String存储
	 * 注意：跳过以“#”开头的行，认为是注释
	 * @param f
	 * @throws IOException
	 */	
	public void load(File f) throws IOException{
		List<String> lines = FileTools.getLineList(f);
		int cnt = 0;
		for(String line : lines){
			if(line.startsWith("#")){
				continue;
			}
			String[] ts = line.split("\t");
			ArrayList<String> cols = new ArrayList<String>(Arrays.asList(ts));
			data.add(cols);
			cnt++;
		}
		System.out.println("Loaded "+cnt +" valid lines from file:"+f.getName());
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setData(ArrayList<ArrayList<String>> data) {
		this.data = data;
	}

	public ArrayList<ArrayList<String>> getData(){
		return this.data;
	}
	
	public int getSize(){
		return data.size();
	}
	
	/**
	 * 返回指定的列
	 * @param idx
	 * @return
	 */
	public ArrayList<String> getColumn(int idx){
		ArrayList<String> ret = new ArrayList<String>();
		for(ArrayList<String> line : data){
			ret.add(line.get(idx));
		}
		return ret;
	}
}
