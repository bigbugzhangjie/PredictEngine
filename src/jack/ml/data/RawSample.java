package jack.ml.data;

import java.util.ArrayList;
import java.util.List;

public class RawSample {
	//按顺序声明原始样本中每个feature的数据类型
	ArrayList<String> types = new ArrayList<String>();
	//以String类型存储原始信息
	ArrayList<String> values = new ArrayList<String>();
	
	public RawSample(){
		
	}
	
	public void addType(String t){
		types.add(t);
	}
	
	public void addAllType(List<String> ts){
		types.addAll(ts);
	}
	
	public void addValue(String v){
		values.add(v);
	}
	
	public ArrayList<String> getTypes() {
		return types;
	}
	public void setTypes(ArrayList<String> types) {
		this.types = types;
	}
	public ArrayList<String> getValues() {
		return values;
	}
	public void setValues(ArrayList<String> values) {
		this.values = values;
	}

	
}
