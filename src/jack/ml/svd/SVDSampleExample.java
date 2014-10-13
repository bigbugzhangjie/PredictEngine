package jack.ml.svd;

import jack.ml.data.Feature;
import jack.ml.svd.model.SVDSparseVector;

import java.util.Collection;

public class SVDSampleExample {
	protected float score;
	protected SVDSparseVector u_f;
	protected SVDSparseVector i_f;
	protected SVDSparseVector g_f;
	
	public static SVDSampleExample loadData(String[] sv){
		SVDSampleExample ret = new SVDSampleExample();
		int u_num, i_num, g_num;
		sv = StringUtility.trim(sv);
		
		ret.score = Float.parseFloat(sv[0]);
		g_num = Integer.parseInt(sv[1]);
		u_num = Integer.parseInt(sv[2]);
		i_num = Integer.parseInt(sv[3]);
		
		assert ( (g_num + u_num + i_num)==sv.length-4):
			"Data item crashed";
		
		ret.g_f = SVDSparseVector.create(sv,4,4+g_num);
		ret.g_f.setType(SVDSparseVector.GLOBAL);
		
		ret.u_f = SVDSparseVector.create(sv,4+g_num,4+g_num+u_num);
		ret.u_f.setType(SVDSparseVector.USER);
		
		ret.i_f = SVDSparseVector.create(sv,4+g_num+u_num,4+g_num+u_num+i_num);
		ret.i_f.setType(SVDSparseVector.ITEM);
		
		return ret;
	}
	
	public static SVDSampleExample getSample(SVDSparseVector g,SVDSparseVector u,SVDSparseVector i){
		SVDSampleExample ret = new SVDSampleExample();
		ret.g_f=g;
		ret.u_f=u;
		ret.i_f=i;
		return ret;
	}
	
	public static SVDSampleExample getSample(Collection<Feature> g,Collection<Feature> u,Collection<Feature> i){
		SVDSparseVector sg = SVDSparseVector.newGlobalVector();
		SVDSparseVector su = SVDSparseVector.newUserVector();
		SVDSparseVector si = SVDSparseVector.newItemVector();
		sg.putAll(g);
		su.putAll(u);
		si.putAll(i);
		return getSample(sg,su,si);
	}
}
