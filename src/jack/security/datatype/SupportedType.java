package jack.security.datatype;

public class SupportedType {
	
	/**
	 * 枚举：有限个数，无法比较大小，加减无意义，乘除无意义
	 */
	public static final String  NOMINAL = "NOMINAL";
	/**
	 * 无限个数，无法比较大小，加减无意义，乘除无意义
	 */
	public static final String  STRING = "STRING";
	/**
	 * 序列：有限个数，有大小，加减有意义，乘除无意义
	 */
	public static final String  ORDINAL = "ORDINAL";
	/**
	 * 区间：有上下界，加减乘除都有意义
	 */
	public static final String  INTERVAL = "INTERVAL";
	
	
	public static final String  INT = "INT";
	public static final String  FLOAT = "FLOAT";
	public static final String  DOUBLE = "DOUBLE";
	public static final String  BOOLEAN = "BOOLEAN";
	public static final String  DATE = "DATE";
	
//	DATE, ENUM, STRING, GRADE, INT, FLOAT, DOUBLE,BOOLEAN
}
