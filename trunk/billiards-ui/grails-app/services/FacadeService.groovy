import br.edu.ufcg.psoo.billiards.facade.BilliardsFacadeclass FacadeService {

    boolean transactional = true

    BilliardsFacade facade
    
    def getFacade() {    	if(!facade) {    		facade = new BilliardsFacade()    		facade.useDatabase("test")    	}    	return facade
    }
}
