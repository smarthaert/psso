class Match {	def facadeService	
	String matchId;
	String leagueId;
	Date creationDate;
	String userIdWinner;
	String userIdLoser;
	String length;
	String score;
	String longestRunForWinner;
	String longestRunForLoser;
	
	def leagueName = {
		def facade = facadeService.getFacade()		String ret = (String) facade.getLeagueAttribute(leagueId, "name")		println "Passou Aqui!!"		println leagueId		println ret		return ret
	}
	
	def winnerLastName = {
		def facade = facadeService.getFacade();		return facade.getUserAttribute(userIdWinner, "lastName");	}		def loserLastName = {		def facade = facadeService.getFacade();		return facade.getUserAttribute(userIdLoser, "lastName");	}
	
}
