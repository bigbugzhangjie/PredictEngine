package jack.security.datatype;

import jack.exception.UndefinedFunction;
import jack.security.MappingRule;

import java.util.ArrayList;
import java.util.List;

public class ORDINAL extends SuperType{

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
