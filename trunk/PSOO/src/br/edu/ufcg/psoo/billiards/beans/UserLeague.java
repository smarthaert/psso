package br.edu.ufcg.psoo.billiards.beans;

import java.util.Date;

public class UserLeague {

	private String userId;
	private String leagueId;
	private Integer currentHandicap;
	private Date joinDate;
	
	public UserLeague() {}

	/**
	 * Creates a new relationship between User(player) and League
	 * @param userId The User id
	 * @param leagueId The League id
	 * @param currentHandicap The initial Handicap
	 * @param joinDate The join date
	 */
	public UserLeague(String userId, String leagueId, Integer currentHandicap, Date joinDate) {
		this.userId 		 = userId;
		this.leagueId	 	 = leagueId;
		this.currentHandicap = currentHandicap;
		this.joinDate		 = joinDate;
	}

	/**
	 * Gets the User id
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets the User id
	 * @param userId The user id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Gets the League id
	 * @return The league id
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
	 * Gets the current Handicap
	 * @return The current Handicap
	 */
	public Integer getCurrentHandicap() {
		return currentHandicap;
	}

	/**
	 * Sets the current Hand cap
	 * @param currentHandicap The current Handicap
	 */
	public void setCurrentHandicap(Integer currentHandicap) {
		this.currentHandicap = currentHandicap;
	}

	/**
	 * Gets the join date
	 * @return the joinDate
	 */
	public Date getJoinDate() {
		return joinDate;
	}

	/**
	 * Sets the join date
	 * @param joinDate The join date
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
