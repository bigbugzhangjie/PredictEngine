package jack.translator;

import jack.ml.data.RawCorpus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Case {
	String dept;//业务部门的name
//	File corpus = null;// 数据源；
	String thirdparty;//外部第三方
	String name; //此次处理的案件名称
//	ArrayList<ArrayList<String>> data = null;
	RawCorpus corpus = null;
	
	public RawCorpus getCorpus() {
		return corpus;
	}
	public Case(String name){
		this.name = name;
	}
	/**
	 * 
	 * @param name	处理的案件的唯一标识
	 * @param depart	业务部门
	 * @param corpus	数据源
	 * @param thirdparty	外部第三方
	 */
	public Case(String name,String depart,String thirdparty){
		this.dept = depart;
		this.thirdparty = thirdparty;
		this.name = name;
	}
	
	
	public String getDept() {
		return dept;
	}


	public void setDept(String dept) {
		this.dept = dept;
	}

	
	public void fetch(File f) throws IOException {
		this.corpus = new RawCorpus(f);
	}
	public void release(){
		this.corpus = null;
	}


	public void setCorpus(File file) throws IOException {
		fetch(file);
	}


	public String getThirdparty() {
		return thirdparty;
	}


	public void setThirdparty(String thirdparty) {
		this.thirdparty = thirdparty;
	}


	public void run(){
		
	}
	
	public void setName(String s){
		this.name = s;
	}

	public String getName() {
		return this.name;
	}
}
