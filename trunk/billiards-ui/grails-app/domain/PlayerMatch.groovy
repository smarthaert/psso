class PlayerMatch {
	def facadeService
	String creationDate
	String opponent
	String matchId
	
	def operatorName = {
			def facade = facadeService.getFacade();
			return facade.getUserAttribute(opponent, "lastName");
	}
}
