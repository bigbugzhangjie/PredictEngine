package jack.security.datatype;

import java.util.ArrayList;

import jack.security.Customer;
import jack.security.MappingRule;
import jack.security.confuser.Confuser;

public class SuperType implements Confuser{
	String column; //
//	Customer customer;
	
	MappingRule rule;
	
	public SuperType(){
		
	}
	public SuperType(String col){
		this.column = col;
	}
	
	@Override
	public ArrayList<String> confuse(String in) {
		return null;
	}
	@Override
	public MappingRule getRule() {
		return rule;
	}
	
}
