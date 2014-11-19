package jack.security.datatype;

import jack.exception.UndefinedFunction;
import jack.security.MappingRule;

import java.util.ArrayList;
import java.util.List;

public class DATE extends SuperType {
	
	String format="";
	public DATE(String format){
		this.format = format;
	}
	
	/**
	 * 对输入数据进行加密
	 * @param in	输入数据
	 * @param func	加密函数标识
	 * @param params	加密函数所需参数
	 * @return
	 * @throws UndefinedFunction 
	 */
	public ArrayList<String> obfuscate(String in,String func,List<String> params) throws UndefinedFunction {
		ArrayList<String> ret = new ArrayList<String>();
		
		String out = "";
		switch(func){
//TODO
		case DEL:
			out = delete(in,params);
			break;
		default:
			throw new UndefinedFunction();
		}
		ret.add(out);
		//TODO		
		//add others
//		ret.add(yyy);
		
		return ret;
	}

	@Override
	public String getTypeName() {
		return SupportedType.DATE;
	} 


}
