package jack.ml.data;

import java.util.Set;

public abstract class Corpus<T> {
	Set<String> categories;
	Set<T> samples;
	Dataset dataset;
	
	abstract Data sample2Data(T sample);
	
	void initDataset(){
		for(T t : samples){
			dataset.add(sample2Data(t));
		}
	}

	public Set<String> getCategories() {
		return categories;
	}

	public Set<T> getSamples() {
		return samples;
	}

	public Dataset getDataset() {
		return dataset;
	}

}
