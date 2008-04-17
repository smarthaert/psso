package br.edu.ufcg.psoo.billiards.beans;

import java.util.Date;

public class Match {
	
	private String userIdWinner;
	private String userIdLoser;
	private String leagueId;
	private String matchId;
	private Date creationDate;
	
	public Match() {}

	public Match(String matchId, String userIdWinner, String userIdLoser, String leagueId, Date creationDate) {
		this.matchId	  = matchId;
		this.userIdWinner = userIdWinner;
		this.userIdLoser  = userIdLoser;
		this.leagueId     = leagueId;
		this.creationDate = creationDate;
	}

	public String getUserIdWinner() {
		return userIdWinner;
	}

	public void setUserIdWinner(String userIdWinner) {
		this.userIdWinner = userIdWinner;
	}

	public String getUserIdLoser() {
		return userIdLoser;
	}

	public void setUserIdLoser(String userIdLoser) {
		this.userIdLoser = userIdLoser;
	}

	public String getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(String leagueId) {
		this.leagueId = leagueId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
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
	
}
