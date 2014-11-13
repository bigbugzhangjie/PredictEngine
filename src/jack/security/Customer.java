package jack.security;

import jack.ml.data.RawSample;

public class Customer {
	String dept;//业务部门的name
	RawSample corpus;// 数据源的name；
	String thirdparty;//外部第三方
	public Customer(String customer,RawSample corpus,String thirdparty){
		this.dept = customer;
		this.corpus = corpus;
		this.thirdparty = thirdparty;
	}
	
	
	public String getDept() {
		return dept;
	}


	public void setDept(String dept) {
		this.dept = dept;
	}


	public RawSample getCorpus() {
		return corpus;
	}


	public void setCorpus(RawSample corpus) {
		this.corpus = corpus;
	}


	public String getThirdparty() {
		return thirdparty;
	}


	public void setThirdparty(String thirdparty) {
		this.thirdparty = thirdparty;
	}


	public void run(){
		
	}
	
	public static void main(String[] args) throws Exception{
		//TODO
	}
}
