import br.edu.ufcg.psoo.billiards.facade.BilliardsFacade

    boolean transactional = true

    def getUserLastNames() {
    	BilliardsFacade facade = facadeService.getFacade()
    	def uids = facade.getAllUsersId()
    }
}