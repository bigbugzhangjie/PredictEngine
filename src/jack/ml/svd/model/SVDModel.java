package jack.ml.svd.model;

import jack.utility.BinaryFileBuffer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SVDModel {
	SVDType type;
	SVDConfig conf;
	ParaArray u_bias;
	ParaArray2D u_weights;
	ParaArray i_bias;
	ParaArray2D i_weights;
	ParaArray g_bias;
	
	public SVDModel(File f)throws IOException{
		InputStream fin = new FileInputStream(f);
		BinaryFileBuffer bfb = new BinaryFileBuffer(fin);
		type = SVDType.load(bfb);
		conf = SVDConfig.load(bfb);
		
		u_bias = ParaArray.load(bfb);
		u_bias.setName("U_F");
		u_weights = ParaArray2D.load(bfb);
		u_weights.setName("U_W");
		
		if(conf.common_latent_space==0){
			i_bias = ParaArray.load(bfb);
			i_bias.setName("I_F");
			i_weights = ParaArray2D.load(bfb);
			i_weights.setName("I_W");
		}else{
			i_bias = u_bias;
			i_bias.setName("UI_F");
			i_weights = u_weights;
			i_weights.setName("UI_W");
		}
		g_bias=ParaArray.load(bfb);
		g_bias.setName("G_F");
		bfb.close();
		fin.close();
	}
	
	public boolean valid(){
		return u_bias.valid() &&
				i_bias.valid()&&
				g_bias.valid()&&
				u_weights.valid()&&
				i_weights.valid();
	}
	
	public float predict(SVDSample ws){
		float bs = conf.base_score;
		SVDSparseVector gf = ws.getGlobalFeature();
		float gs = gf.dot(g_bias);
		SVDSparseVector itf = ws.getItemFeature();
		float is = itf.dot(i_bias);
		float ret = bs+gs+is;
		
		if(conf.no_user_bias==0){
			ret += ws.getUserFeature().dot(u_bias);
		}
		ret += ParaArray.dot(ws.getUserFeature().dot(u_weights), 
							 ws.getItemFeature().dot(i_weights));
		return adjust(ret);
	}
	
	protected float adjust(float f){
		switch(type.active_type){
		case SVDType.LINEAR : break;
		case SVDType.SIGMOID_L2 :
		case SVDType.SIGMOID_LIKELIHOOD :
			f = (float) (1.0 / (1.0+Math.exp(-1.0*f)));
			break;
		case SVDType.SIGMOID_RANK:
		case SVDType.HINGE_SMOOTH:
		case SVDType.HINGE_L2:
		case SVDType.SIGMOID_QSGRAD:
			break;
		default:
			f = 0;
		}
		return f;
	}
	
	public String brief(){
		StringBuilder sb = new StringBuilder();
		sb.append(type.toString());
		sb.append(conf.toString());
		return sb.toString();
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		if(!valid()){
			sb.append("|---------------------Notice: the model is INVALID (has NaN)-------------------------|");
		}
		sb.append(type.toString());
		sb.append(conf.toString());
		sb.append(u_weights.getNanCols());
		sb.append(i_weights.getNanCols());
		sb.append(g_bias.toString());
		sb.append(u_bias.toString());
		sb.append(u_weights.toString());
		
		if(conf.common_latent_space==0){
			sb.append(i_bias.toString());
			sb.append(i_weights.toString());
		}
		return sb.toString();
	}
}
