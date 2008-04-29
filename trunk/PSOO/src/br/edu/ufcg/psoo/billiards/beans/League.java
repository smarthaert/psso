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
	 * @return The League id
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
	 * @return The League operator
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * Sets the League operator
	 * @param operator The League operator
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}


	/**
	 * Gets the League name
	 * @return 	The League name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the League name
	 * @param name The League name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the League creation date
	 * @return The League creation date
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * Sets the League creation date
	 * @param creationDate The League creation date
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public int compareTo(Object o) {
		
		return 0;
	}

	/**
	 * Gets the League standing expression 
	 * @return the standingExpression
	 */
	public String getStandingExpression() {
		return standingExpression;
	}

	/**
	 * Sets the League standing expression
	 * @param standingExpression The League standing expression
	 */
	public void setStandingExpression(String standingExpression) {
		this.standingExpression = standingExpression;
	}




}
