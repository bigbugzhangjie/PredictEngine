package jack.security.datatype;

import jack.security.MappingRule;
import jack.security.confuser.Function;

import java.util.ArrayList;
import java.util.List;

public class STRING extends SuperType {
	public static final String MASK="MASK";
	
	public STRING(String n,MappingRule rule){
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
	ArrayList<String> obfuscate(String in,String func,List<String> params) {
		ArrayList<String> ret = new ArrayList<String>();
		
		String out = "";
		switch(func){
		case MASK:
			out = String.valueOf(standardize(in,params));
			break;
//		case xxxxx:
		}
		ret.add(out);
		
		//add others
//		ret.add(yyy);
		
		return ret;
	}

	@Override
	public MappingRule getRule() {
		return rule;
	}

	public static String standardize(String in,List<String> params){
		int input = Integer.parseInt(in);
		float mean = Float.parseFloat(params.get(0));
		float var = Float.parseFloat( params.get(1) );
		return (int)(Function.standardize(mean, var, input));
	}
}
