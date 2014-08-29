package jack.ml.data;

public class Feature {
	int index;
	float value;
	String name;
	public int getIndex() {
		return index;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
}
