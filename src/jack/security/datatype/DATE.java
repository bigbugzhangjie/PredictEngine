package jack.security.datatype;

import jack.security.MappingRule;

import java.util.ArrayList;

public class DATE extends AbstractType {
	String format="";
	public DATE(String format){
		this.format = format;
	}
	@Override
	public ArrayList<String> confuse(String in) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public MappingRule getRule() {
		// TODO Auto-generated method stub
		return null;
	}

}
