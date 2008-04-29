package br.edu.ufcg.psoo.billiards.beans;

import java.util.Date;

public class League implements Comparable {

	private String leagueId;
	private String operator;
	private String name;	
	private Date creationDate;	
	private String standingExpression;
	
	public League() {}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof League) {
			League new_name = (League) obj;
			return new_name.leagueId.equals(leagueId);			
		}
		return false;
	}

	/**
	 * Creates a new League
	 * @param leagueId The League id
	 * @param userOperatorId The League operator (user)
	 * @param name The League name
	 * @param date The league date
	 */
	public League(String leagueId, String userOperatorId, String name, Date date) {
		super();
		this.leagueId 		= leagueId;
		this.operator = userOperatorId;
		this.name 	  	    = name;
		this.creationDate	= date;
	}

	/**
	 * Gets the League id 
	 * @return the leagueId
	 */
	public String getLeagueId() {
		return leagueId;
	}

	/**
	 * Sets the League id
	 * @param leagueId The League id
	 */
	public void setLeagueId(String leagueId) {
		this.leagueId = leagueId;
	}

	/**
	 * Gets the League operator
	 * @return the operator
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * 
	 * @param operator the operator to set
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public int compareTo(Object o) {
		
		return 0;
	}

	/**
	 * @return the standingExpression
	 */
	public String getStandingExpression() {
		return standingExpression;
	}

	/**
	 * @param standingExpression the standingExpression to set
	 */
	public void setStandingExpression(String standingExpression) {
		this.standingExpression = standingExpression;
	}




}
