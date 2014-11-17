package jack.security.datatype;

import java.util.ArrayList;
import java.util.List;

public class NOMINAL extends SuperType{
	public static final String EXTEND="EXTD";
	
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
	public String getTypeName() {
		return SupportedType.NOMINAL;
	}
	@Override
	ArrayList<String> obfuscate(String in, String func, List<String> params) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
