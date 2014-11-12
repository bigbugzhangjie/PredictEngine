package jack.security;

import java.util.ArrayList;

public interface Confuser {
	public ArrayList<String> confuse(String in);
	public MappingRule getRule();
}
