package jack.dict;

import java.io.File;
import java.util.Set;

public class Dict {
	Set<String> set;
	public Dict(File file, int index, String sep){
		
	}
	
	public Dict(Set<String> set){
		this.set = set;
	}
	
	public boolean contains(String w){
		return set.contains(w);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
