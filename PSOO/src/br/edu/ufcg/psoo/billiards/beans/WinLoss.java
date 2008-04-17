package br.edu.ufcg.psoo.billiards.beans;

import java.util.Date;

public class WinLoss {
	
	private String userIdWinner;
	private String userIdLoser;
	private Date creationDate;
	
	public WinLoss() {}

	public WinLoss(String userIdWinner, String userIdLoser, Date creationDate) {
		this.userIdWinner = userIdWinner;
		this.userIdLoser = userIdLoser;
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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
}
