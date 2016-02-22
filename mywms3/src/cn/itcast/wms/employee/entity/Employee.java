package cn.itcast.wms.employee.entity;

/**
 * Employee entity. @author MyEclipse Persistence Tools
 */

public class Employee implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String dept;
	private String password;
	private Boolean gender;
	private String mobile;
	private String email;

	// Constructors

	/** default constructor */
	public Employee() {
	}

	/** full constructor */
	public Employee(String name, String dept, String password, Boolean gender,
			String mobile, String email) {
		this.name = name;
		this.dept = dept;
		this.password = password;
		this.gender = gender;
		this.mobile = mobile;
		this.email = email;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept() {
		return this.dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getGender() {
		return this.gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}