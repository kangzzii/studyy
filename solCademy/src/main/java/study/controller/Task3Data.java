package study.controller;

public class Task3Data {
	String name;
	int age;
	String phone;
	String nation;
	String gender;
	String userYn;
	
	public Task3Data() {}
	
	public Task3Data(String name,int age,String phone,String nation,String gender,String userYn) {
		this.name = name;
		this.age = age;
		this.phone = phone;
		this.nation = nation;
		this.gender = gender;
		this.userYn = userYn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUserYn() {
		return userYn;
	}

	public void setUserYn(String userYn) {
		this.userYn = userYn;
	}
	
}
