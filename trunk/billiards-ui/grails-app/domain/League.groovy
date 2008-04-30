class League implements Comparable {
	def facadeService
	String leagueId;
	String operator;
	String name;	
	Date creationDate;	
	String standingExpression;
	String order
	
	def operatorName = {
			def facade = facadeService.getFacade();
			return facade.getUserAttribute(operator, "lastName");
	}
	
	public int compareTo(Object o) {
		def ply = (League) o
		return this."${order}".compareTo(ply."${order}")
	}

	public void orderBy(String o) {
		order = o
	}
}
