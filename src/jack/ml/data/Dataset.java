package jack.ml.data;

import java.util.HashMap;

public class Dataset {
	HashMap<String, Data> map; // data name : data
	int number;
	int featureNum;
	int featureMaxIndex;
	
	public Data get(int id){
		for(Data d: map.values()){
			if(d.getId()==id){
				return d;
			}
		}
		return null;
	}
	public Data get(String name){
		return map.get(name);
	}
}
