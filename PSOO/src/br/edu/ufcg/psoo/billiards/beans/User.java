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

	/**
	 * Creates a new User
	 * @param userId The User id
	 * @param firstName The User first name
	 * @param lastName The User last name
	 * @param homePhone The User home phone
	 * @param workPhone The User work phone
	 * @param cellPhone The User cell phone
	 * @param email The User email address
	 * @param picture The User's picture
	 * @throws Exception
	 */
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
	 * Gets the User id
	 * @return The User Id
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets the User id
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Gets the User first name 
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the User first name
	 * @param firstName The User first name
	 * @throws Exception
	 */
	public void setFirstName(String firstName) throws Exception {
		if (firstName == null || firstName.equals("")) {
			throw new Exception("Required data: first name");
		}

		this.firstName = firstName;
	}

	/**
	 * Gets the User last name
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Sets the User last name
	 * @param lastName The User last name
	 * @throws Exception
	 */
	public void setLastName(String lastName) throws Exception {
		if (lastName == null || lastName.equals("")) {
			throw new Exception("Required data: last name");
		}
		this.lastName = lastName;
	}

	/**
	 * Gets the User home phone
	 * @return the homePhone
	 */
	public String getHomePhone() {
		return homePhone;
	}

	/**
	 * Sets the User home phone 
	 * @param homePhone The User home phone
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
	 * Gets the User work phone
	 * @return the workPhone
	 */
	public String getWorkPhone() {
		return workPhone;
	}

	/**
	 * Sets the User work phone
	 * @param workPhone The User work phone
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
	 * Gets the User email address
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the User email address
	 * @param email The User email address
	 */
	public void setEmail(String email) throws Exception {
		if (email == null || email.equals("")) {
			throw new Exception("Required data: email");
		}

		this.email = email;
	}

	/**
	 * Gets the User picture
	 * @return the picture
	 */
	public String getPicture() {
		return picture;
	}

	/**
	 * Sets the User's picture
	 * @param picture The User's picture
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}

	/**
	 * Gets the User cell phone
	 * @return the cellPhone
	 */
	public String getCellPhone() {
		return cellPhone;
	}

	/**
	 * Sets the User cell phone 
	 * @param cellPhone The User cell phone
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
