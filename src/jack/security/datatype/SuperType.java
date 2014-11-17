package jack.security.datatype;

import java.util.ArrayList;
import java.util.List;

import jack.security.Case;
import jack.security.MappingRule;
import jack.security.confuser.Obfuscater;

public abstract class SuperType implements Obfuscater{
	String columnName; //
//	Customer customer;
	
	MappingRule rule;
	
	public SuperType(){		
	}
	
	public SuperType(String col){
		this.columnName = col;
	}
		
	abstract public String getTypeName();
	
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	@Override
	public ArrayList<String> obfuscate(String in){
		return obfuscate(in,rule.getFuncname(),rule.getParams());
	}
	
	/**
	 * 对输入数据进行加密
	 * @param in	输入数据
	 * @param func	加密函数标识
	 * @param params	加密函数所需参数
	 * @return
	 */
	abstract ArrayList<String> obfuscate(String in,String func,List<String> params);
	 
	@Override
	public MappingRule getRule() {
		return rule;
	}
	@Override
	public void setRule(MappingRule rule) {
		this.rule = rule;		
	}
	
}
