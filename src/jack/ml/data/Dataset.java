package jack.ml.data;

import java.util.HashMap;

//public class Dataset<T extends Comparable<? super T>> {

public class Dataset {
	HashMap<Integer, Data> map; // data's id : data
//	HashMap<T, Data> map; // data's name or id : data
	int number;
	int featureNum;
	int featureMaxIndex;
	
	public Data get(String name){
		for(Data d: map.values()){
			if(d.getName().equals(name)){
				return d;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param key	data id
	 * @return
	 */
	public Data get(int id){
		return map.get(id);
	}
	public void add(Data d){
		map.put(d.getId(), d);
	}
}
