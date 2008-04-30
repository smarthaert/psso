class League  {
	def facadeService
	String leagueId;
	String operator;
	String name;	
	Date creationDate;	
	String standingExpression;
	
	
	def operatorName = {
			def facade = facadeService.getFacade();
			return facade.getUserAttribute(operator, "lastName");
	}
	
	
}
