package jack.security.datatype;

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
	 */
	ArrayList<String> obfuscate(String in,String func,List<String> params) {
	//TODO
		return null;
	}

	@Override
	public String getTypeName() {
		return SupportedType.DATE;
	} 


}
