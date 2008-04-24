import br.edu.ufcg.psoo.billiards.facade.BilliardsFacadeclass BootStrap {

     def init = { servletContext ->
     	BilliardsFacade facade = new BilliardsFacade()     	facade.useDatabase("test")     	servletContext["facade"]=facade
     }
     def destroy = {
     }
} 