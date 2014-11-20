package jack.security.datatype;

import jack.security.exception.UndefinedFunction;
import jack.security.exception.UndefinedNOMINAL;

import java.util.ArrayList;
import java.util.List;

public class NOMINAL extends SuperType{
	public static final String EXTEND="EXTD";
	
	ArrayList<String> candidates = new ArrayList<String>();//该枚举类型里支持的实例
	int size;
	
	public NOMINAL(String col){
		super(col);
	}
	
	/**
	 * 
	 * @param col	该列的字段名
	 * @param list	此NOMINAL可选的所有枚举值
	 */
	public NOMINAL(String col,ArrayList<String> list){
		super(col);
		this.candidates = list;
	}
	
//	public void add(String t){		
//	}
	
	public void setCandidates(ArrayList<String> list) {
		this.candidates = list;
	}
	
	public int getSize(){
		size = candidates.size();
		return size;
	}
	
	
	public int getIndex(String s){
		if(candidates.contains(s)){
			return candidates.indexOf(s);
		}else{
			return -1;
		}
	}
	@Override
	public String getTypeName() {
		return SupportedType.NOMINAL;
	}
	@Override
	ArrayList<String> obfuscate(String in, String func, List<String> params) throws UndefinedNOMINAL, UndefinedFunction  {
		// TODO Auto-generated method stub
		ArrayList<String> ret = new ArrayList<String>();
		
		String out = "";
		switch(func){
		case EXTEND:
			out = extend(in,params);
			break;
		case DEL:
			out = delete(in,params);
			break;
		default:
			throw new UndefinedFunction();
		}
		ret.add(out);
		
		//add others
//		ret.add(yyy);
		
		return ret;
	}
	
	/**
	 * 在candidates列表中的第pos[i]列之前加干扰项；
	 * 如：  原candidates为 [3,6,1,9,8,2],   pos=[2,4]
	 * 则输出为： [3,6,x,1,9,x,8,2]
	 * @param in
	 * @param pos	candidates中的目标列的index	
	 * @return
	 * @throws UndefinedNOMINAL 
	 */
	private String extend(String in, List<String> pos) throws UndefinedNOMINAL{
		String ret = "";
		if(!candidates.contains(in)){
			throw new UndefinedNOMINAL();
		}
		//TODO
		return ret;
	}
}
