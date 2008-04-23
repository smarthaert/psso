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

	public League(String leagueId, String userOperatorId, String name, Date date) {
		super();
		this.leagueId 		= leagueId;
		this.operator = userOperatorId;
		this.name 	  	    = name;
		this.creationDate	= date;
	}

	/**
	 * @return the leagueId
	 */
	public String getLeagueId() {
		return leagueId;
	}

	/**
	 * @param leagueId the leagueId to set
	 */
	public void setLeagueId(String leagueId) {
		this.leagueId = leagueId;
	}

	/**
	 * @return the operator
	 */
	public String getOperator() {
		return operator;
	}

	/**
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
