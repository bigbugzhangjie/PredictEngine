package jack.security.datatype;

import jack.security.MappingRule;
import jack.security.exception.UndefinedFunction;

import java.util.ArrayList;
import java.util.List;

public class ORDINAL extends NOMINAL{

	public ORDINAL(String columnName){
		super(columnName);
	}
	/**
	 * 
	 * @param col	该列的字段名
	 * @param list	此ORDINAL可选的所有值,要求从小到大排列
	 */
	public ORDINAL(String col,ArrayList<String> list){
		super(col);
		this.candidates = list;
	}
	
	@Override
	public String getTypeName() {
		return SupportedType.ORDINAL;
	}

	@Override
	ArrayList<String> obfuscate(String in, String func, List<String> params) throws UndefinedFunction {
		// TODO Auto-generated method stub
		ArrayList<String> ret = new ArrayList<String>();
		
		String out = "";
		switch(func){
//		case STDIZE:
//			out = String.valueOf(standardize(in,params));
//			break;
		case DEL:
			out = delete(in,params);
			break;
//		case xxxxx:
		default:
			throw new UndefinedFunction();
		}
		ret.add(out);
		
		//add others
//		ret.add(yyy);
		
		return ret;
	}

}
