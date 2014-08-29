package jack.ml.svd.model;

import jack.utility.BinaryFileBuffer;

import java.util.Arrays;

public class ParaArray {
	int max;
	float[] a;
	String name;
	byte invalid;
	public ParaArray(){
		
	}
	public static ParaArray add(ParaArray a,ParaArray b){
		assert a.max==b.max : "Size mismatch!";
		float[] c = new float[a.max];
		for(int i=0;i<a.max;i++){
			c[i]=a.a[i]+b.a[i];
		}
		return attach(c);
	}
	public static float dot(ParaArray a,ParaArray b){
		return a.dot(b);
	}
	
	public ParaArray clone(){
		float[] b = Arrays.copyOf(a, a.length);
		return attach(b);
	}

	public static ParaArray attach(float[] fa){
		ParaArray ret = new ParaArray();
		ret.max = fa.length;
		ret.a = fa;
		return ret;
	}
	
	public static ParaArray load(BinaryFileBuffer bfb){
		ParaArray ret = new ParaArray();
		ret.max = bfb.getInt();
		ret.a = new float[ret.max];
		for(int i=0; i< ret.max;i++){
			ret.a[i]=bfb.getFloat();
			if((Float.floatToRawIntBits(ret.a[i]) & 0x7fffffff) > 0x7f800000)
				ret.invalid =1;
		}
		return ret;
	}
	
	public boolean valid(){
		return invalid == 0;
	}
	
	public ParaArray add(ParaArray k){
		assert this.max == k.max : "Size mismatch";
		for(int i=0; i<max ; i++){
			this.a[i] += k.a[i];
		}
		return this;
	}
	public ParaArray multi(float v){
		for(int i=0; i<a.length;i++){
			a[i] *= v;
		}
		return this;
	}
	
	public float dot(ParaArray b){
		float ret = 0.0f;
		assert b.max == max:"Size mismatch";
		for(int i=0; i<max ; i++){
			ret += b.a[i] * a[i];
		}
		return ret;
	}
	
	public float dotAt(float v, int idx){
		return at(idx)*v;
	}
	
	public float at(int idx){
		assert idx<a.length : "Index overflow";
		return a[idx];
	}
	
	public int size(){
		return max;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public static boolean isNaN(float f){
		return (Float.floatToRawIntBits(f) & 0x7fffffff) == 0x7fc00000;
	}
	
	public static boolean invalid(float f){
		return (Float.floatToRawIntBits(f) & 0x7fffffff) > 0x7f800000;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		int i;
		sb.append(String.format("|----------------------- SVD %4s Model Parameter-----------------------|", name));
		for(i=0; i<a.length;i++){
			if(i%6==0) sb.append("\n");
			if( (Float.floatToRawIntBits(a[i]) & 0x7fffffff) == 0x7fc00000 ){
				sb.append("|     NaN");
			}else if(a[i]>=0){
				sb.append(String.format("| %8.7f",a[i]));
			}else{
				sb.append(String.format("| %8.6f",a[i]));
			}
			if(i%6 ==5)
				sb.append("    |");
		}
		i = i%6;
		if(i>0){
			while(i<6){
				sb.append("|           ");
				if(i++ %6 ==5){
					sb.append("     |");
				}
			}
		}
		sb.append("\n|------------------------------|\n");
		return sb.toString();
	}
		
}
