package concurrency.testMultiThreadOperateOnObject;

public class TestPerson {

	private int age;
	private int height;
	private int grade;
//	private String Name;
//	private Double weight;
//	private boolean graduated;
//	private String newField;

	public void setAge(int age) {
		this.age = age;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
//
//	public void setName(String name) {
//		Name = name;
//	}
//
//	public void setWeight(Double weight) {
//		this.weight = weight;
//	}
//
//	public void setGraduated(boolean graduated) {
//		this.graduated = graduated;
//	}
//
//	public void setNewField(String newField) {
//		this.newField = newField;
//	}

	public int getAge() {
		return age;
	}

	public int getHeight() {
		return height;
	}

	public int getGrade() {
		return grade;
	}

//	public String getName() {
//		return Name;
//	}
//
//	public Double getWeight() {
//		return weight;
//	}
//
//	public boolean isGraduated() {
//		return graduated;
//	}
//
//	public String getNewField() {
//		return newField;
//	}

	@Override
	public String toString() {
		return "TestPerson [age=" + age + ", height=" + height + ", grade=" + grade + "]";
	}
	

}
