package jack.security.datatype;

import jack.security.MappingRule;
import jack.security.confuser.Confuser;

public abstract class AbstractType implements Confuser{
	String name; //
	String corpus;// 数据源的name；
	String partner; // 目标合作方name；
	int confuseMethod;
	MappingRule rule;
	
}
