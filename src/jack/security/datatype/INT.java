package jack.security.datatype;

import jack.security.MappingRule;
import jack.security.confuser.Function;

import java.util.ArrayList;
import java.util.List;

public class INT extends SuperType {
	public static final String STDIZE="STDIZE";

	
	public INT(String n,MappingRule rule){
		super(n);
		this.rule = rule;
	}
	
	@Override
	public ArrayList<String> confuse(String in) {
		ArrayList<String> ret = new ArrayList<String>();
		
		String func = rule.getFuncname();
		List<String> params = rule.getParams();
		
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

	@Override
	public MappingRule getRule() {
		return rule;
	}

	public static int standardize(String in,List<String> params){
		int input = Integer.parseInt(in);
		float mean = Float.parseFloat(params.get(0));
		float var = Float.parseFloat( params.get(1) );
		return (int)(Function.standardize(mean, var, input));
	}
}
