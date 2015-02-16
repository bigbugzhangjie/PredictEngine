package jack.translator.datatype;

import jack.exception.UndefinedException;
import jack.translator.MappingRule;
import jack.translator.confuser.Obfuscater;

import java.util.ArrayList;
import java.util.List;

public abstract class SuperType implements Obfuscater{
	public static final String DEL="DEL";
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
	public ArrayList<String> obfuscate(String in) throws UndefinedException{
		return obfuscate(in,rule.getFuncname(),rule.getParams());
	}
	
	/**
	 * 对输入数据进行加密
	 * @param in	输入数据
	 * @param func	加密函数标识
	 * @param params	加密函数所需参数
	 * @return
	 */
	abstract ArrayList<String> obfuscate(String in,String func,List<String> params) throws UndefinedException;
	 
	/**
	 * 返回“”
	 * @param in
	 * @param params
	 * @return
	 */
	public static String delete(String in,List<String> params){
		return "";
	}
	
	@Override
	public MappingRule getRule() {
		return rule;
	}
	@Override
	public void setRule(MappingRule rule) {
		this.rule = rule;		
	}
	
}
