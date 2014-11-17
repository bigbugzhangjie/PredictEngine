package jack.security.confuser;

import jack.security.MappingRule;

import java.util.ArrayList;
import java.util.List;

public interface Obfuscater {
	
	/**
	 * 对输入数据进行加密
	 * 
	 * @return
	 */
	public ArrayList<String> obfuscate(String in);
	public MappingRule getRule();
	public void setRule(MappingRule rule);
}
