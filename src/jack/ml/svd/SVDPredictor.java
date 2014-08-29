package jack.ml.svd;

import jack.ml.svd.model.SVDModel;

import java.io.File;
import java.io.IOException;

public class SVDPredictor {
	SVDModel model;
	public SVDPredictor(File file) throws IOException{
		this.model =new SVDModel(file);
	}
	
	
}
