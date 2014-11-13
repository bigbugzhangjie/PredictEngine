package jack.security.datatype;

import jack.security.MappingRule;

import java.util.ArrayList;

public class NOMINAL extends SuperType{

	ArrayList<String> list = new ArrayList<String>();//该枚举类型里支持的实例
	int size;
	
	public NOMINAL(String col){
		super(col);
	}
	public NOMINAL(String col,ArrayList<String> list){
		super(col);
		this.list = list;
	}
	
//	public void add(String t){		
//	}
	
	public void setList(ArrayList<String> list) {
		this.list = list;
	}
	
	public int getSize(){
		size = list.size();
		return size;
	}
	
	
	public int getIndex(String s){
		if(list.contains(s)){
			return list.indexOf(s);
		}else{
			return -1;
		}
	}
	@Override
	public ArrayList<String> confuse(String in) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public MappingRule getRule() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
