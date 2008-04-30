import java.util.Comparator;
class LeagueController {
	def userService
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ leagueList: list ]
    }

    def show = {
        def league = getLeague( params.id )

        if(!league) {
            flash.message = "League not found with id ${params.id}"
            redirect(action:list)
        }
        else {
        			def newPlayer = new Player()
        		}
        		Collections.sort(playerList);
        			Player[] players = playerList.toArray()
        		}
        	}
    }

    def delete = {
        def league = getLeague( params.id )
        if(league) {
        	BilliardsFacade facade = facadeService.getFacade();
            flash.message = "League ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "League not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def league = getLeague( params.id )

        if(!league) {
            flash.message = "League not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [league :league,'usernames':userService.getUserLastNames(),
        }
    }

    def update = {
        if(league) {
            league.properties = params
            	facade.changeLeagueAttribute(league.leagueId,"operator", league.operator);
            } catch (Exception e) {
				render(view:'edit',model:[league :league,'usernames':userService.getUserLastNames(),
            }
            flash.message = "League not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def league = new League()
        league.properties = params
        return ['league':league,'usernames':userService.getUserLastNames(), 'userids':userService.getUserIds()]
    }

    def save = {
        def league = new League(params)
        try {
        	String id = facade.createLeague(league.name, league.operator)
        		facade.defineStandingsExpression(id, league.standingExpression)
        	} catch (Exception e) {
        		facade.deleteLeague(id);
        	}
        } catch (Exception e) {
        }
    
    private League getLeague(String leagueId) {
    	BilliardsFacade facade = facadeService.getFacade();
    	
    }
}