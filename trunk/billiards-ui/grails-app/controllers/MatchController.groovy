
class MatchController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ matchList: allMatches ]
    }

    def show = {
        def match = getMatch( params.id )

        if(!match) {
            flash.message = "Match not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ match : match ] }
    }

    def delete = {
        def match = getMatch( params.id )
        if(match) {
        	BilliardsFacade facade = facadeService.getFacade()
            flash.message = "Match ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "Match not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def match = getMatch( params.id )

        if(!match) {
            flash.message = "Match not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            
        }
    }

    def update = {
        def match = getMatch( params.matchId )
        if(match) {
            match.properties = params
            	facade.updateMatchResult(match.matchId, new StringBuilder(facade.getDateFormat().format(match.creationDate)).
            } catch (Exception e) {
            	flash.message = e.getMessage()
            }
        else {
            flash.message = "Match not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def match = new Match()
        match.properties = params
    }

    def save = {
        def match = new Match(params)
        	 String leagueId = match.leagueId
        } catch (Exception e) {
        	flash.message = e.getMessage()
			
			return null
		}
    
    
   
}