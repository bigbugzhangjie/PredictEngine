package jack.ml.svd;

import jack.ml.svd.model.SVDModel;
import jack.ml.svd.model.SVDSample;

import java.io.File;
import java.io.IOException;

public class SVDPredictor {
	SVDModel model;
	
	public SVDPredictor(File file) throws IOException{
		this.model =new SVDModel(file);
	}
	
	public float predict(String featureStr){
		String[] tst;
		SVDSample rs;
		tst = featureStr.split(" ");
		rs = SVDSample.loadData(tst);
		try{
			return model.predict(rs);
		}catch(Exception e){
			return -1000;
		}
	}
	
	public static int getMaxUser(){
		//TODO
		return -1;
	}
	
	
	
}
