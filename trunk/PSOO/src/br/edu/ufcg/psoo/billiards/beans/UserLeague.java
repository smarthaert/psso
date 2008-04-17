package br.edu.ufcg.psoo.billiards.beans;

public class UserLeague {

	private String userId;
	private String leagueId;
	private Integer handCurrentCapping;
	
	public UserLeague() {}

	public UserLeague(String userId, String leagueId, Integer handCurrentCapping) {
		this.userId = userId;
		this.leagueId = leagueId;
		this.handCurrentCapping = handCurrentCapping;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(String leagueId) {
		this.leagueId = leagueId;
	}	

	public Integer getHandCurrentCapping() {
		return handCurrentCapping;
	}

	public void setHandCurrentCapping(Integer handCurrentCapping) {
		this.handCurrentCapping = handCurrentCapping;
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
