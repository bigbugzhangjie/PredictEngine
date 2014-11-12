package jack.security.confuser;

import jack.utility.StringTools;

import java.util.ArrayList;

public class Function {

	/**
	 * (in - 均值)/标准差
	 * @param in
	 * @param params	[均值，标准差]
	 * @return
	 */
	public static float standardize(float in,ArrayList<String> params){
		float ret = 0f;
		//TODO
		return ret;
	}
	
	/**
	 * 对pos中指定位置的字符，用m代替。如：13987654321->139****4321
	 * @param in
	 * @param m	 例“*”
	 * @param pos	需要掩盖的位置（从0计数）
	 * @return
	 */
	public static String mask(String in, char m,int[] pos){
		return StringTools.replace(in,m,pos);
	}

}
