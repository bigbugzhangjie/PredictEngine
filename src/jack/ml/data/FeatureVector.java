package jack.ml.data;

import java.util.HashMap;
import java.util.List;

public class FeatureVector {
	public static boolean needNormalization = false; //生成feature之后是否还需要做归一化
	HashMap<Integer,Float> vector = new HashMap<Integer,Float>(); // index:value
	
	public FeatureVector(List<Feature> features){
		//TODO
	}
	
	public FeatureVector(HashMap<Integer,Float> features){
		this.vector = features;
	}
	
	public FeatureVector(String features){
		//TODO
	}
	
	HashMap<Integer,Float> getVector(){
		return vector;
	}
	
	public void add(Feature f){
		if(vector==null){
			vector = new HashMap<Integer,Float>();
		}
		vector.put(f.getIndex(), f.getValue());
	}
	
	public void add(FeatureVector fv){
		if(vector==null){
			vector = new HashMap<Integer,Float>();
		}
		vector.putAll(fv.getVector());
	}
	
	public void remove(int index){
		//TODO
	}
	
	public int getMinIndex(){
		//TODO
		return 0;
	}
	public int getMaxIndex(){
		//TODO
		return 0;
	}
	
	public static float cosineSimilarity(FeatureVector v1, FeatureVector v2){
		//TODO
		return 0;
	}
	public static float innerProduct(FeatureVector v1, FeatureVector v2){
		//TODO
		return 0;
	}
	
	public static FeatureVector str2Vec(String str){
		//TODO
		return null;
	}
	
	public float[] toArray(){
		//TODO
		return null;
	}
	
	
}
