package jack.ml.svd.model;

import jack.ml.data.Feature;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SVDSparseVector {
	public static final byte GLOBAL=0;
	public static final byte USER=1;
	public static final byte ITEM=2;
	
	private byte id;
	Set<VectorItem> set;
	
	public SVDSparseVector() {
		set = new HashSet<VectorItem>();
	}
	
	public void setType(byte id){
		this.id=id;
	}
	
	public byte getType(){
		return id;
	}
	
	public static SVDSparseVector newGlobalVector(){
		SVDSparseVector ret = new SVDSparseVector();
		ret.setType(GLOBAL);
		return ret;
	}
	
	public static SVDSparseVector newUserVector(){
		SVDSparseVector ret = new SVDSparseVector();
		ret.setType(USER);
		return ret;
	}
	
	public static SVDSparseVector newItemVector(){
		SVDSparseVector ret = new SVDSparseVector();
		ret.setType(ITEM);
		return ret;
	}
	
	public static SVDSparseVector create(String[] vs, int off, int len){
		SVDSparseVector ret = new SVDSparseVector();
		String[] tmp;
		for(int i=off;i<len;i++){
			tmp = vs[i].split(":");
			if(tmp.length==2){
				ret.put(Integer.parseInt(tmp[0]),Float.parseFloat(tmp[1]));
			}
		}
		return ret;
	}
	
	public void put(int idx, float value){
		VectorItem e = new VectorItem();
		e.index = idx;
		e.value = value;
		if(!set.contains(e))
			set.add(e);
	}
	
	public void putAll(Collection<Feature> col){
		Iterator<Feature> it;
		Feature item;
		if(col!=null && col.size()>0){
			it = col.iterator();
			while(it.hasNext()){
				item = it.next();
				put(item.getIndex(),(float)item.getValue());
			}
		}
	}
	
	public float dot(ParaArray pa) {
		float ret =0;
		VectorItem fi;
		Object[] oa = set.toArray();
		for(int i=0;i<oa.length;i++){
			fi=(VectorItem) oa[i];
			ret += fi.dot(pa);
		}
		return ret;
	}

	public ParaArray dot(ParaArray2D pa) {
		ParaArray ret = null;
		VectorItem fi;
		Object[] oa = set.toArray();
		for(int i=0;i<oa.length;i++){
			fi=(VectorItem) oa[i];
			if(ret==null){
				ret = fi.dot(pa);
			}else{
				ret.add(fi.dot(pa));
			}
		}
		return ret;
	}
	
	public int size(){
		return set.size();
	}

}

class VectorItem implements Serializable{
	int index;
	float value;
	public Integer getKeyValue(){
		return index;
	}
	public float dot(ParaArray pa){
//		return pa.dotAt(index,value);
		return pa.dotAt(value,index);
		
	}
	
	public ParaArray dot(ParaArray2D pa){
		ParaArray ret = pa.at(index);
		return ret.multi(value);
	}
	
	public void setKey(Integer key){
		index = key;
	}
}
