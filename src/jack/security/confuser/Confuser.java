package jack.security.confuser;

import jack.security.MappingRule;

import java.util.ArrayList;

public interface Confuser {
	public ArrayList<String> confuse(String in);
	public MappingRule getRule();
}
