package br.edu.ufcg.psoo.billiards.beans;

public class User {

	private String userId;
	private String firstName;
	private String lastName;
	private String homePhone;
	private String workPhone;
	private String cellPhone;
	private String email;
	private String picture;

	public User() {
	}

	public User(String userId, String firstName, String lastName,
			String homePhone, String workPhone, String cellPhone, String email,
			String picture) throws Exception {
		workPhone = (workPhone == null) ? "" : workPhone;
		homePhone = (homePhone == null) ? "" : homePhone;
		cellPhone = (cellPhone == null) ? "" : cellPhone;
		if (workPhone.equals("")&&cellPhone.equals("")&&homePhone.equals("")) {
			throw new Exception("Need at least one phone");
		}
		
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.homePhone = homePhone;
		this.workPhone = workPhone;
		this.cellPhone = cellPhone;
		this.email = email;
		this.picture = picture;
		
		
	}
	


	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * 
	 * @param firstName
	 * @throws Exception
	 */
	public void setFirstName(String firstName) throws Exception {
		if (firstName == null || firstName.equals("")) {
			throw new Exception("Required data: first name");
		}

		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) throws Exception {
		if (lastName == null || lastName.equals("")) {
			throw new Exception("Required data: last name");
		}
		this.lastName = lastName;
	}

	/**
	 * @return the homePhone
	 */
	public String getHomePhone() {
		return homePhone;
	}

	/**
	 * 
	 * @param homePhone
	 * @throws Exception
	 */
	public void setHomePhone(String homePhone) throws Exception {
		homePhone = (homePhone == null) ? "" : homePhone;
		if (homePhone.equals("")) {
			if (workPhone.equals("") && cellPhone.equals("")) {
				throw new Exception("Need at least one phone");
			}
		}
		this.homePhone = homePhone;
	}

	/**
	 * @return the workPhone
	 */
	public String getWorkPhone() {
		return workPhone;
	}

	/**
	 * @param workPhone
	 *            the workPhone to set
	 */
	public void setWorkPhone(String workPhone) throws Exception {
		workPhone= (workPhone==null)?"":workPhone;

		if (workPhone.equals("")) {
			if (homePhone.equals("") && cellPhone.equals("")) {
				throw new Exception("Need at least one phone");
			}
		}
		this.workPhone = workPhone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) throws Exception {
		if (email == null || email.equals("")) {
			throw new Exception("Required data: email");
		}

		this.email = email;
	}

	/**
	 * @return the picture
	 */
	public String getPicture() {
		return picture;
	}

	/**
	 * @param picture
	 *            the picture to set
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}

	/**
	 * @return the cellPhone
	 */
	public String getCellPhone() {
		return cellPhone;
	}

	/**
	 * 
	 * @param cellPhone
	 * @throws Exception
	 */
	public void setCellPhone(String cellPhone) throws Exception {
		cellPhone=(cellPhone==null)?"":cellPhone;

		if (cellPhone.equals("")) {
			if (homePhone.equals("") && workPhone.equals("")) {
				throw new Exception("Need at least one phone");
			}
		}
		this.cellPhone = cellPhone;

	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof User) {
			User new_name = (User) obj;
			return new_name.getUserId().equals(this.userId);
		}
		return false;
	}	

}
