package jack.security;

import java.util.ArrayList;
import java.util.List;

/**
 * 严格按照原始数据各个字段的顺序，命名各个字段的数据类型
 * @author bigbug
 *
 */
public class Definer {
	String name;
	List<String> type = new ArrayList<String>();
	
	public Definer(){
		
	}
	
	public void add(String t){
		type.add(t);
	}
	
	public void addAll(List<String> list){
		type.addAll(list);
	}
	
	public void transform(){
		for(String t:type){
			//TODO
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
