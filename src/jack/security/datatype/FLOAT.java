package jack.security.datatype;

import jack.security.MappingRule;
import jack.security.confuser.Function;

import java.util.ArrayList;
import java.util.List;

public class FLOAT extends SuperType {
	public static final String STDIZE="STDIZE";

	
	public FLOAT(String n,MappingRule rule){
		super(n);
		this.rule = rule;
	}
	

	/**
	 * 对输入数据进行加密
	 * @param in	输入数据
	 * @param func	加密函数标识
	 * @param params	加密函数所需参数
	 * @return
	 */
	public ArrayList<String> obfuscate(String in,String func,List<String> params) {
		ArrayList<String> ret = new ArrayList<String>();
		
		String out = "";
		switch(func){
		case STDIZE:
			out = String.valueOf(standardize(in,params));
			break;
//		case xxxxx:
		}
		ret.add(out);
		
		//add others
//		ret.add(yyy);
		
		return ret;
	}


	public static int standardize(String in,List<String> params){
		int input = Integer.parseInt(in);
		float mean = Float.parseFloat(params.get(0));
		float var = Float.parseFloat( params.get(1) );
		return (int)(Function.standardize(mean, var, input));
	}


	@Override
	public String getTypeName() {
		return SupportedType.FLOAT;
	}
	
	
}
