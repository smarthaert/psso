import br.edu.ufcg.psoo.billiards.facade.BilliardsFacadeclass UserService {

    boolean transactional = true    def facadeService

    def getUserLastNames() {
    	BilliardsFacade facade = facadeService.getFacade()    	List list= new ArrayList()
    	def uids = facade.getAllUsersId()    	for(i in uids) {    		list.add(facade.getUserAttribute((String)i, "lastName"))    	}    	return list
    }        def getUserIds() {    	BilliardsFacade facade = facadeService.getFacade()    	return facade.getAllUsersId()    }
}
