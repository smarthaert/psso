package br.edu.ufcg.psoo.billiards.beans;

import java.util.Date;

public class UserLeague {

	private String userId;
	private String leagueId;
	private Integer currentHandicap;
	private Date joinDate;
	
	public UserLeague() {}

	public UserLeague(String userId, String leagueId, Integer currentHandicap, Date joinDate) {
		this.userId 		 = userId;
		this.leagueId	 	 = leagueId;
		this.currentHandicap = currentHandicap;
		this.joinDate		 = joinDate;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
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
	 * @return the currentHandicap
	 */
	public Integer getCurrentHandicap() {
		return currentHandicap;
	}

	/**
	 * @param currentHandicap the currentHandicap to set
	 */
	public void setCurrentHandicap(Integer currentHandicap) {
		this.currentHandicap = currentHandicap;
	}

	/**
	 * @return the joinDate
	 */
	public Date getJoinDate() {
		return joinDate;
	}

	/**
	 * @param joinDate the joinDate to set
	 */
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof UserLeague) {
			UserLeague new_name = (UserLeague) obj;
			return (new_name.getUserId().equals(this.userId) && new_name
					.getLeagueId().equals(this.leagueId));
		}
		return false;
	}

}
