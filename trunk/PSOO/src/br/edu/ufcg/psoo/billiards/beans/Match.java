package br.edu.ufcg.psoo.billiards.beans;

import java.util.Date;

public class Match {

	private String userIdWinner;
	private String userIdLoser;
	private String leagueId;
	private String matchId;
	private Integer length;
	private Integer score;
	private Integer longestRunForWinner;
	private Integer longestRunForLoser;
	private Date creationDate;

	public Match() {
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Match) {
			Match new_name = (Match) obj;
			return new_name.getMatchId().equals(matchId);
		}
		return false;
	}

	/**
	 * Creates a new Match
	 * @param userIdWinner The User winner id
	 * @param userIdLoser The User loser id
	 * @param leagueId The League id
	 * @param matchId The Match id
	 * @param creationDate Creation date
	 */
	public Match(String matchId, String userIdWinner, String userIdLoser,
			String leagueId, Date creationDate) {
		super();
		this.userIdWinner = userIdWinner;
		this.userIdLoser = userIdLoser;
		this.leagueId = leagueId;
		this.matchId = matchId;
		this.creationDate = creationDate;
	}

	/**
	 * Creates a new Match
	 * @param matchId The Match id
	 * @param userIdWinner The User winner id
	 * @param userIdLoser The User loser id
	 * @param leagueId The League id
	 * @param length
	 * @param score
	 * @param longestRunForWinner
	 * @param longestRunForLoser
	 * @param creationDate Creation date
	 */
	public Match(String matchId, String userIdWinner, String userIdLoser,
			String leagueId, Integer length, Integer score,
			Integer longestRunForWinner, Integer longestRunForLoser,
			Date creationDate) {
		super();
		this.userIdWinner = userIdWinner;
		this.userIdLoser = userIdLoser;
		this.leagueId = leagueId;
		this.matchId = matchId;
		this.length = length;
		this.score = score;
		this.longestRunForWinner = longestRunForWinner;
		this.longestRunForLoser = longestRunForLoser;
		this.creationDate = creationDate;
	}

	/**
	 * Gets the User winner id
	 * @return the userIdWinner
	 */
	public String getUserIdWinner() {
		return userIdWinner;
	}

	/**
	 * Sets the User winner id
	 * @param userIdWinner
	 *            the userIdWinner to set
	 */
	public void setUserIdWinner(String userIdWinner) {
		this.userIdWinner = userIdWinner;
	}

	/**
	 * Gets the User loser id
	 * @return the userIdLoser
	 */
	public String getUserIdLoser() {
		return userIdLoser;
	}

	/**
	 * Sets the User loser id
	 * @param userIdLoser
	 *            the userIdLoser to set
	 */
	public void setUserIdLoser(String userIdLoser) {
		this.userIdLoser = userIdLoser;
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
	 * @param leagueId
	 *            the leagueId to set
	 */
	public void setLeagueId(String leagueId) {
		this.leagueId = leagueId;
	}

	/**
	 * Gets the Match id
	 * @return the matchId
	 */
	public String getMatchId() {
		return matchId;
	}

	/**
	 * Sets the Match id
	 * @param matchId
	 *            the matchId to set
	 */
	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}

	/**
	 * @return the lenght
	 */

	/**
	 * Gets the match's score attribute
	 * @return the score
	 */
	public Integer getScore() {
		return score;
	}

	/**
	 * Sets the match's score attribute
	 * @param score
	 *            the score to set
	 */
	public void setScore(Integer score) {
		this.score = score;
	}

	/**
	 * Gets the math's longestRunForWinner attribute
	 * @return the longestRunForWinner
	 */
	public Integer getLongestRunForWinner() {
		return longestRunForWinner;
	}

	/**
	 * Sets the math's longestRunForWinner attribute
	 * @param longestRunForWinner
	 *            the longestRunForWinner to set
	 */
	public void setLongestRunForWinner(Integer longestRunForWinner) {
		this.longestRunForWinner = longestRunForWinner;
	}

	/**
	 * Gets the match's longestRunForLoser attribute
	 * @return the longestRunForLoser
	 */
	public Integer getLongestRunForLoser() {
		return longestRunForLoser;
	}

	/**
	 * Sets the match's longestRunForLoser attribute
	 * @param longestRunForLoser
	 *            the longestRunForLoser to set
	 */
	public void setLongestRunForLoser(Integer longestRunForLoser) {
		this.longestRunForLoser = longestRunForLoser;
	}

	/**
	 * Gets the match's creationDate attribute
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * Set the match's creationDate attribute
	 * @param creationDate
	 *            the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * Gets the match's length attribute
	 * @return the length
	 */
	public Integer getLength() {
		return length;
	}

	/**
	 * Sets the match's length attribute
	 * @param length
	 *            the length to set
	 */
	public void setLength(Integer length) {
		this.length = length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Objeto: [" + userIdWinner + ", " + userIdLoser + ", " + leagueId + ", "
				+ matchId + ", " + length + ", " + score + ", "
				+ longestRunForLoser + ", " + longestRunForWinner + ", " + creationDate + "]";
	}
	

}
