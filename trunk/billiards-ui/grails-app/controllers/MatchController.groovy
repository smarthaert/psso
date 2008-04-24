import br.edu.ufcg.psoo.billiards.facade.BilliardsFacade            
class MatchController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ matchList: Match.list( params ) ]
    }

    def show = {
        def match = Match.get( params.id )

        if(!match) {
            flash.message = "Match not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ match : match ] }
    }

    def delete = {
        def match = Match.get( params.id )
        if(match) {
            match.delete()
            flash.message = "Match ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "Match not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def match = Match.get( params.id )

        if(!match) {
            flash.message = "Match not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ match : match ]
        }
    }

    def update = {
        def match = Match.get( params.id )
        if(match) {
            match.properties = params
            if(!match.hasErrors() && match.save()) {
                flash.message = "Match ${params.id} updated"
                redirect(action:show,id:match.id)
            }
            else {
                render(view:'edit',model:[match:match])
            }
        }
        else {
            flash.message = "Match not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def match = new Match()
        match.properties = params
        return ['match':match]
    }

    def save = {
        def match = new Match(params)
        if(!match.hasErrors() && match.save()) {
            flash.message = "Match ${match.id} created"
            redirect(action:show,id:match.id)
        }
        else {
            render(view:'create',model:[match:match])
        }
    }
    
    
   
}