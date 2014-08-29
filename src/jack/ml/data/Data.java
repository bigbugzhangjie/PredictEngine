package jack.ml.data;

public class Data {
	int id;
	String name;
	int label;
	String body;
	FeatureVector vec;
	
	public Data(int id, FeatureVector vec){
		this.id=id;
		this.vec = vec;
	}
	public Data(int id, String vecStr){
		//TODO
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLabel() {
		return label;
	}
	public void setLabel(int label) {
		this.label = label;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public FeatureVector getVec() {
		return vec;
	}
	public void setVec(FeatureVector vec) {
		this.vec = vec;
	}
	public String toString(){
		//TODO
		return null;
	}

}
