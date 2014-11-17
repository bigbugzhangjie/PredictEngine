package jack.security.datatype;

import jack.security.MappingRule;

import java.util.ArrayList;
import java.util.List;

public class ORDINAL extends SuperType{

	@Override
	public String getTypeName() {
		return SupportedType.ORDINAL;
	}

	@Override
	ArrayList<String> obfuscate(String in, String func, List<String> params) {
		// TODO Auto-generated method stub
		return null;
	}

}
