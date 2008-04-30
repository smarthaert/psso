import javax.xml.crypto.dsig.spec.ExcC14NParameterSpecimport org.apache.tools.ant.util.LeadPipeInputStreamimport br.edu.ufcg.psoo.billiards.facade.BilliardsFacade            
class LeagueController {
	def userService	def facadeService
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10        BilliardsFacade facade = facadeService.getFacade();        list=[]        def listId = facade.getAllLeaguesId()        for(i in listId) {        	list+=getLeague((String)i)        }        
        [ leagueList: list ]
    }

    def show = {
        def league = getLeague( params.id )

        if(!league) {
            flash.message = "League not found with id ${params.id}"
            redirect(action:list)
        }
        else {        	BilliardsFacade facade = facadeService.getFacade();        	def users = facade.getAllUsersId()        	def playerList = []        	        	String leagueId = league.leagueId        	for(i in users) {        		String id = (String)i        		        		if (facade.isLeagueMember(id, leagueId)) {
        			def newPlayer = new Player()        			newPlayer.userId = id        			newPlayer.lastName = facade.getUserAttribute(id, "lastName")        			newPlayer.numberOfWins = String.valueOf(facade.getNumberOfWins(id, leagueId))        			newPlayer.numberOfLosses = String.valueOf(facade.getNumberOfLosses(id, leagueId))        			newPlayer.playerStanding = facade.getPlayerStanding(id, leagueId)        			        			playerList+=newPlayer
        		}        	}        	return [ league : league, 'playerList': playerList]         }
    }

    def delete = {
        def league = getLeague( params.id )
        if(league) {
        	BilliardsFacade facade = facadeService.getFacade();        	facade.deleteLeague(league.leagueId)
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
            return [league :league,'usernames':userService.getUserLastNames(),                    'userids':userService.getUserIds()]
        }
    }

    def update = {        def league = getLeague ( params.id )
        if(league) {
            league.properties = params            BilliardsFacade facade = facadeService.getFacade();            try {            	facade.defineStandingsExpression(league.leagueId, league.standingExpression)
            	facade.changeLeagueAttribute(league.leagueId,"operator", league.operator);            	facade.changeLeagueAttribute(league.leagueId,"name", league.name);            	facade.changeLeagueAttribute(league.leagueId,"creationDate",             			new StringBuilder(facade.getDateFormat().format(league.creationDate)).toString())            	flash.message = "League ${params.id} updated"                    redirect(action:show,id:league.leagueId)
            } catch (Exception e) {				flash.message = e.getMessage()				e.printStackTrace()
				render(view:'edit',model:[league :league,'usernames':userService.getUserLastNames(),				                          'userids':userService.getUserIds()])
            }        } else {
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
        def league = new League(params)        BilliardsFacade facade = facadeService.getFacade();        
        try {
        	String id = facade.createLeague(league.name, league.operator)        	try {
        		facade.defineStandingsExpression(id, league.standingExpression)
        	} catch (Exception e) {
        		facade.deleteLeague(id);        		throw e
        	}        	league.leagueId = id        	flash.message = "League ${league.leagueId} created"                redirect(action:show,id:league.leagueId)
        } catch (Exception e) {        	flash.message = e.getMessage()        	render(view:'create',model:[league:league, 'usernames':userService.getUserLastNames(), 'userids':userService.getUserIds()])
        }    }
    
    private League getLeague(String leagueId) {
    	BilliardsFacade facade = facadeService.getFacade();
    	    	try {    		String operator = facade.getLeagueAttribute(leagueId,"operator");        	String name = facade.getLeagueAttribute(leagueId,"name");        	        	Date creationDate = facade.getDateFormat().parse(        			facade.getLeagueAttribute(leagueId, "creationDate"))        	String standingExpression = facade.getLeagueAttribute(leagueId, "standingExpression");        	        	        	League league = new League();        	league.leagueId = leagueId;        	league.name = name        	league.operator = operator;        	league.standingExpression = standingExpression;        	league.creationDate = creationDate        	        	return league;        	    	} catch (Exception e) {    		return null;    	}    	
    }        /*        public List getAllUserNames() {    	BilliardsFacade facade = (BilliardsFacade)servletContext["facade"];    	def list = facade.getAllUsersId()    	ret = []    	for(i in list) {    		String lastName = facade.getUserAttribute((String)i,"lastName");    		ret+=lastName    	}    	return ret    }        public List getAllUserId() {    	return ((BilliardsFacade)servletContext["facade"]).getAllUsersId()    }*/        
}