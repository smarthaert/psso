import br.edu.ufcg.psoo.billiards.facade.BilliardsFacade

    boolean transactional = true

    def facadeService
    
    def getLeaguesNames() {
    	BilliardsFacade facade = facadeService.getFacade()
    	List list= new ArrayList()
    	def uids = facade.getAllLeaguesId()
    	for(i in uids) {
    		list.add(facade.getLeagueAttribute((String)i, "name"))
    	}
    	return list
    }
    
    def getLeagueIds() {
    	BilliardsFacade facade = facadeService.getFacade()
    	return facade.getAllLeaguesId()
    }