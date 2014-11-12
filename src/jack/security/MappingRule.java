package jack.security;

import java.util.ArrayList;
import java.util.List;

public class MappingRule {
	String name;
	List<String> params;
	
	public MappingRule(){
		
	}
	
	/**
	 * 从string中读取MappingRule。
	 * @param str	第一列为name，其他列为参数；各列用tab分割
	 * @return
	 */
	public MappingRule load(String str){
		String[] cols = str.split("\t");
		if(cols.length>0){
			MappingRule ret = new MappingRule();
			ret.setName(cols[0]);
			if(cols.length>1){
				ArrayList<String> ps = new ArrayList<String>();
				for(int i=1;i<cols.length;i++){
					ps.add(cols[i]);
				}
				ret.setParams(ps);
			}
			return ret;
		}else{
			return null;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getParams() {
		return params;
	}

	public void setParams(List<String> params) {
		this.params = params;
	}

	/**
	 * 第一列为name，其他列为参数；各列用tab分割
	 */
	public String toString(){
		String ret = name;
		if(params!=null && params.size()>0){
			for(String s : params){
				ret += "\t"+s;
			}
		}
		return ret;
	}
	
}
