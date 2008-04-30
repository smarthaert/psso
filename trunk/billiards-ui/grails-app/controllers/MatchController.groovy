import br.edu.ufcg.psoo.billiards.facade.BilliardsFacade            
class MatchController {
    	def facadeService	def leagueService	def userService
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10        BilliardsFacade facade = facadeService.getFacade();                                def leaguesIds = facade.getAllLeaguesId()                def allMatches=[]        for(i in leaguesIds) {        	def partialMatches = facade.getNumberOfMatches((String)i)        	        	for(int k=1; k<=partialMatches; k++) {        		Match m = getMatch(facade.getMatch(i, k))        		allMatches+=m        	}        }                
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
        	BilliardsFacade facade = facadeService.getFacade()        	facade.deleteMatch(match.matchId)
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
        else {            return [match : match, 'leaguenames':leagueService.getLeaguesNames(),                    'leagueids':leagueService.getLeagueIds(), 'usernames':userService.getUserLastNames(),                    'userids':userService.getUserIds()]
            
        }
    }

    def update = {
        def match = getMatch( params.matchId )
        if(match) {
            match.properties = params            BilliardsFacade facade = facadeService.getFacade();            try {
            	facade.updateMatchResult(match.matchId, new StringBuilder(facade.getDateFormat().format(match.creationDate)).            			toString(), match.userIdWinner, match.userIdLoser,            			String.valueOf(match.length), String.valueOf(match.score),            			String.valueOf(match.longestRunForWinner), String.valueOf(match.longestRunForLoser))            			flash.message = "Match ${params.id} updated"            			redirect(action:show,id:match.matchId)
            } catch (Exception e) {
            	flash.message = e.getMessage()                render(view:'edit',model:[match : match, 'leaguenames':leagueService.getLeaguesNames(),                                          'leagueids':leagueService.getLeagueIds(), 'usernames':userService.getUserLastNames(),                                          'userids':userService.getUserIds()])           	
            }        }
        else {
            flash.message = "Match not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def match = new Match()
        match.properties = params        def leagues = leagueService.getLeaguesNames()        return ['match':match, 'leaguenames':leagueService.getLeaguesNames(),                'leagueids':leagueService.getLeagueIds(), 'usernames':userService.getUserLastNames(),                'userids':userService.getUserIds()]
    }

    def save = {
        def match = new Match(params)        BilliardsFacade facade = facadeService.getFacade();        try {
        	 String leagueId = match.leagueId        	String creationDate =  new StringBuilder(facade.getDateFormat().format(match.creationDate)).			toString()			String winner = match.userIdWinner			String loser = match.userIdLoser			String lenght = (!match.length.equals(""))?match.length:null;			String score  = (!match.score.equals(""))?match.score:null;			String lrw = (!match.longestRunForWinner.equals(""))?match.longestRunForWinner:null;			String lrl = (!match.longestRunForLoser.equals(""))?match.longestRunForLoser:null;						String matchId = facade.addMatchResult(leagueId, creationDate, winner, loser, lenght, score, lrw, lrl)						match.matchId = matchId								flash.message = "Match ${match.matchId} created"		    redirect(action:show,id:match.matchId)
        } catch (Exception e) {
        	flash.message = e.getMessage()        	render(view:'create',model:[match:match, 'leaguenames':leagueService.getLeaguesNames(),                                        'leagueids':leagueService.getLeagueIds(), 'usernames':userService.getUserLastNames(),                                        'userids':userService.getUserIds()])        }}		private Match getMatch(String matchId) {		BilliardsFacade facade = facadeService.getFacade();		try {			Match match = new Match()
												match.matchId = matchId			match.leagueId =  facade.getMatchLeague(matchId)						def ret=facade.getMatchDate(matchId)									match.creationDate = facade.getDateFormat().parse(ret);						match.userIdWinner = facade.getMatchWinner(matchId)			match.userIdLoser = facade.getMatchLoser(matchId)			match.length = facade.getMatchLength(matchId)			match.score = facade.getMatchScore(matchId)    		match.longestRunForWinner = facade.getMatchLongestRunForWinner(matchId)							match.longestRunForLoser = facade.getMatchLongestRunForLoser(matchId)						return match		} catch (Exception e) {
			return null
		}	}
    
    
   
}