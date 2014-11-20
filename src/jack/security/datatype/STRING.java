package jack.security.datatype;

import jack.security.MappingRule;
import jack.security.confuser.Function;
import jack.security.exception.UndefinedFunction;
import jack.utility.StringTools;

import java.util.ArrayList;
import java.util.List;

public class STRING extends SuperType {
	public static final String MASK="MASK";
//	public static final String REPLACE="REPL";
	
	public STRING(String columnName){
		super(columnName);
	}
	public STRING(String columnName,MappingRule rule){
		super(columnName);
		this.rule = rule;
	}

	/**
	 * 对输入数据进行加密
	 * @param in	输入数据
	 * @param func	加密函数标识
	 * @param params	加密函数所需参数
	 * @return
	 * @throws UndefinedFunction 
	 */
	ArrayList<String> obfuscate(String in,String func,List<String> params) throws UndefinedFunction {
		ArrayList<String> ret = new ArrayList<String>();
		
		String out = "";
		switch(func){
		case MASK:
			out = mask(in,params);
			break;
		case DEL:
			out = delete(in,params);
			break;
//		case xxxxx:
		default: // 不加密
			throw new UndefinedFunction();
		}
		ret.add(out);
		
		//add others
//		ret.add(yyy);
		
		return ret;
	}
	
	
	public static String mask(String str,List<String> params){
		int[] pos = new int[params.size()];
		for(int i=0;i<params.size();i++){
			pos[i]=Integer.parseInt(params.get(i));
		}
		return StringTools.replace(str, '*', pos);
	}
	
//	public static String replace(String str,List<String> params){
//		//TODO
//	}


	@Override
	public String getTypeName() {
		return SupportedType.STRING;
	}
}
