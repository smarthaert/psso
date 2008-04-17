package br.edu.ufcg.psoo.billiards.beans;

import java.util.Date;

public class Match {
	
	private String userIdWinner;
	private String userIdLoser;
	private String leagueId;
	private String matchId;
	private Integer lenght;
	private Integer score;
	private Integer longestRunForWinner;
	private Integer longestRunForLoser;
	private Date creationDate;
	
	public Match() {}
	
	/**
	 * @param userIdWinner
	 * @param userIdLoser
	 * @param leagueId
	 * @param matchId
	 * @param creationDate
	 */
	public Match(String matchId, String userIdWinner, String userIdLoser, String leagueId, Date creationDate) {
		super();
		this.userIdWinner = userIdWinner;
		this.userIdLoser = userIdLoser;
		this.leagueId = leagueId;
		this.matchId = matchId;
		this.creationDate = creationDate;
	}

	/**
	 * @param userIdWinner
	 * @param userIdLoser
	 * @param leagueId
	 * @param matchId
	 * @param lenght
	 * @param score
	 * @param longestRunForWinner
	 * @param longestRunForLoser
	 * @param creationDate
	 */
	public Match(String userIdWinner, String userIdLoser, String leagueId,
			String matchId, Integer lenght, Integer score,
			Integer longestRunForWinner, Integer longestRunForLoser,
			Date creationDate) {
		super();
		this.userIdWinner = userIdWinner;
		this.userIdLoser = userIdLoser;
		this.leagueId = leagueId;
		this.matchId = matchId;
		this.lenght = lenght;
		this.score = score;
		this.longestRunForWinner = longestRunForWinner;
		this.longestRunForLoser = longestRunForLoser;
		this.creationDate = creationDate;
	}



	/**
	 * @return the userIdWinner
	 */
	public String getUserIdWinner() {
		return userIdWinner;
	}

	/**
	 * @param userIdWinner the userIdWinner to set
	 */
	public void setUserIdWinner(String userIdWinner) {
		this.userIdWinner = userIdWinner;
	}

	/**
	 * @return the userIdLoser
	 */
	public String getUserIdLoser() {
		return userIdLoser;
	}

	/**
	 * @param userIdLoser the userIdLoser to set
	 */
	public void setUserIdLoser(String userIdLoser) {
		this.userIdLoser = userIdLoser;
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
	 * @return the matchId
	 */
	public String getMatchId() {
		return matchId;
	}

	/**
	 * @param matchId the matchId to set
	 */
	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}

	/**
	 * @return the lenght
	 */
	public Integer getLenght() {
		return lenght;
	}

	/**
	 * @param lenght the lenght to set
	 */
	public void setLenght(Integer lenght) {
		this.lenght = lenght;
	}

	/**
	 * @return the score
	 */
	public Integer getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(Integer score) {
		this.score = score;
	}

	/**
	 * @return the longestRunForWinner
	 */
	public Integer getLongestRunForWinner() {
		return longestRunForWinner;
	}

	/**
	 * @param longestRunForWinner the longestRunForWinner to set
	 */
	public void setLongestRunForWinner(Integer longestRunForWinner) {
		this.longestRunForWinner = longestRunForWinner;
	}

	/**
	 * @return the longestRunForLoser
	 */
	public Integer getLongestRunForLoser() {
		return longestRunForLoser;
	}

	/**
	 * @param longestRunForLoser the longestRunForLoser to set
	 */
	public void setLongestRunForLoser(Integer longestRunForLoser) {
		this.longestRunForLoser = longestRunForLoser;
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

	
	
}
