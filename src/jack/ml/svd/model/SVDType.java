package jack.ml.svd.model;

import jack.utility.BinaryFileBuffer;

public class SVDType {
	public static final int RANDOM_ORDER_FORMAT =0;
	public static final int USER_GROUP_FORMAT =1;
	public static final int AUTO_DETECT =2;
	
	// Identiti map, square loss	
	public static final int LINEAR = 0;
	// brief sigmoid function, square loss
	public static final int SIGMOID_L2 = 1;
	public static final int SIGMOID_LIKELIHOOD = 2;
	// reserved for rank, the model will output the result before
	// sigmoid transformation, which is sufficient for rank
	public static final int SIGMOID_RANK = 3;
	public static final int HINGE_SMOOTH = 5;
	public static final int HINGE_L2 = 6;
	public static final int SIGMOID_QSGRAD = 7;
	
	byte format_type; // type of input data
	byte active_type ; // indicator of activation function and loss function
	byte extend_type;// to identify new solver
	byte variant_type; // reserved for future use
	
	public SVDType() {
	}
	
	public static SVDType load(BinaryFileBuffer bfb) {
		SVDType ret = new SVDType();
		ret.format_type = bfb.get();
		ret.active_type= bfb.get();
		ret.extend_type=bfb.get();
		ret.variant_type=bfb.get();
		return ret;
	}
	
	public String toString(){
		return String.format(
				"-------------------SVD Training Type-----------------"+
				"| Format  %02X  ActiveType  %02X  Ext %02X  Variant  %02X", 
				format_type,active_type,extend_type,variant_type);
	}

}
