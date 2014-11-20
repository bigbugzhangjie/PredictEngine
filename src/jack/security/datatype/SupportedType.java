package jack.security.datatype;

import jack.security.MappingRule;

public class SupportedType {
	public static final String  BOOLEAN = "BOOLEAN";
	public static final String  DATE = "DATE";
	public static final String  FLOAT = "FLOAT";	
	public static final String  INT = "INT";
	/**
	 * 枚举：有限个数，无法比较大小，加减无意义，乘除无意义
	 */
	public static final String  NOMINAL = "NOMINAL";
	/**
	 * 序列：有限个数，有大小，加减有意义，乘除无意义
	 */
	public static final String  ORDINAL = "ORDINAL";
	/**
	 * 无限个数，无法比较大小，加减无意义，乘除无意义
	 */
	public static final String  STRING = "STRING";

	/**
	 * 区间：有上下界，加减乘除都有意义
	 */
//	public static final String  INTERVAL = "INTERVAL";
//	public static final String  DOUBLE = "DOUBLE";
	
}
