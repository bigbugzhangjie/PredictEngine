package jack.ml.svd.model;

import jack.utility.BinaryFileBuffer;

public class ParaArray2D {
	int max_x;
	int max_y;
	float[][] a;
	String name;
	byte invalid;
	
	public ParaArray2D(){
		invalid = 0;
	}
	
	public static ParaArray2D load(BinaryFileBuffer bfb){
		ParaArray2D ret = new ParaArray2D();
		int i,j;
		ret.max_x = bfb.getInt();
		ret.max_y = bfb.getInt();
		ret.a = new float[ret.max_y][ret.max_x];
		for(i=0;i<ret.max_y;i++){
			for(j=0;j<ret.max_x;j++){
				ret.a[i][j]=bfb.getFloat();
				if(ParaArray.invalid(ret.a[i][j])){
					ret.invalid = 1;
				}
			}
		}
		return ret;
	}
	
	public ParaArray at(int idx){
		assert idx<max_y : "Index overflow";
		ParaArray ret = ParaArray.attach(a[idx]).clone();
		return ret;
	}
	
	public boolean valid(){
		return invalid ==0;
	}

	public String toString(){
		StringBuffer sb=new StringBuffer();
		int i;
		sb.append(String.format("|--------------SVD %4s Model Parameter--------------|\n",
				name));
		for(i=0;i<max_y;i++){
			sb.append(toString(i));
		}
		sb.append("----------------------\n");
		return sb.toString();
		
	}
	
	public String toString(int line){
		StringBuilder sb =new StringBuilder();
		int i;
		float tmp;
		sb.append(String.format(
				"|[%8d]---------------------|\n", line+1
				));
		
		for(i=0;i<max_x;i++){
			tmp = a[line][i];
			if(ParaArray.isNaN(tmp))
				sb.append("|        NaN");
			else if(tmp>=0)
				sb.append(String.format("|%8.7f", tmp));
			else
				sb.append(String.format("|%8.6f", tmp));
		
			if(i%6==5)
				sb.append("|\n");
		}
		i=i%6;
		if(i>0){
			while(i<6){
				sb.append("|       ");
				if(i++ %6 ==5)
					sb.append("|\n");
			}
		}
		return sb.toString();
	}
	
	public String getNanCols(){
		String ret;
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		int i,j,para;
		ret = "|----------------All NaN Features-----------------|\n";
		for(i=0;i<max_y;i++){
			para = 0;
			for(j=0;j<max_x;j++){
				if(ParaArray.isNaN(a[i][j]))
					para++;
			}
			if(para == max_x){
				sb.append(String.format("|%7d",i+1));
				if(cnt ++ %8 ==7){
					sb.append("|\n");
				}
			}
		}
		i=cnt%8;
		if(i>0){
			while(i<8){
				sb.append("|          ");
				if(i++ %8 == 7)	sb.append("\n");
			}
		}
		if(sb.length()>0)
			ret += sb.toString();
		else ret = "";
				
		return ret;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
