import br.edu.ufcg.psoo.billiards.facade.BilliardsFacade

     def init = { servletContext ->
     	BilliardsFacade facade = new BilliardsFacade()
     }
     def destroy = {
     }
} 