package jack.translator;

import jack.exception.FileFormatException;

import java.util.ArrayList;
import java.util.List;

public class MappingRule {
	String rulename; //例：age
	String datatypename;//所对应的数据类型的name,例：SupportedType.INT
	String funcname=""; // 具体使用的映射函数name，例： INT.STDIZE
	List<String> params;//映射函数需要的参数, 例： ["45","15"]
	
	public MappingRule(){
		
	}
	
	/**
	 * 从string中读取MappingRule。str已#开头则为注释，返回null
	 * @param str	第一列为name，第二列为具体使用的映射函数name，其他列为映射函数需要的参数；各列用tab分割
	 * @return
	 */
	public static MappingRule load(String str) throws FileFormatException {
		MappingRule ret = null;
		if (str.startsWith("#")) {
			return ret;
		}

		String[] cols = str.split("\t");
		if (cols.length <= 0) {
			return ret;
		}

		ret = new MappingRule();
		ret.setRulename(cols[0]);

		if (cols.length >= 2) {
			ret.setFuncname(cols[1]);
			if (cols.length > 2) {
				ArrayList<String> ps = new ArrayList<String>();
				for (int i = 2; i < cols.length; i++) {
					ps.add(cols[i]);
				}
				ret.setParams(ps);
			}
		}
		return ret;
	}



	public String getRulename() {
		return rulename;
	}

	public void setRulename(String rulename) {
		this.rulename = rulename;
	}

	public String getDatatypename() {
		return datatypename;
	}

	public void setDatatypename(String datatypename) {
		this.datatypename = datatypename;
	}

	public String getFuncname() {
		return funcname;
	}

	public void setFuncname(String funcname) {
		this.funcname = funcname;
	}

	public List<String> getParams() {
		return params;
	}

	public void setParams(List<String> params) {
		this.params = params;
	}

	/**
	 * 第一列为name，第二列为所对应的数据类型的name，
	 * 第三列为具体使用的映射函数name，其他列为映射函数需要的参数；
	 * 各列用tab分割
	 */
	public String toString(){
		String ret = rulename+"\t"+datatypename+"\t"+funcname;
		if(params!=null && params.size()>0){
			for(String s : params){
				ret += "\t"+s;
			}
		}
		return ret;
	}
	
}
