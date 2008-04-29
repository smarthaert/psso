import br.edu.ufcg.psoo.billiards.facade.BilliardsFacade            
class UserController {	def facadeService	
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10                         BilliardsFacade facade = facadeService.getFacade();                                list=[]        def listId = facade.getAllUsersId()        for(i in listId) {        	list+=getUser((String)i)        }        
        [ userList: list ]
    }

    def show = {
        def user = getUser( params.id )

        if(!user) {
            flash.message = "User not found with id ${params.userId}"
            redirect(action:list)
        }
        else { return [ user : user ] }
    }

    def delete = {
        def user = getUser( params.id )        
        if(user) {        	BilliardsFacade facade = facadeService.getFacade();        	String message=""        	try {
        		facade.deleteUser(user.userId)        		message = "User ${params.id} deleted"        		        		
        	} catch (Exception e) {
        		message = e.getMessage()
        	} finally {        		flash.message = message        		redirect(action:list)        	}        	
            
        }
        else {
            flash.message = "User not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def user = getUser( params.id )
  
        if(!user) {
            flash.message = "User not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ user : user ]
        }
    }

    def update = {
        def user = getUser( params.userId )
        if(user) {
            user.properties = params                        BilliardsFacade facade = facadeService.getFacade();            try {
        		facade.changeUserAttribute(user.userId,"firstName", user.firstName);            	facade.changeUserAttribute(user.userId,"lastName", user.lastName);            	facade.changeUserAttribute(user.userId, "homePhone", user.homePhone);            	facade.changeUserAttribute(user.userId, "workPhone", user.workPhone);            	facade.changeUserAttribute(user.userId, "cellPhone", user.cellPhone);            	facade.changeUserAttribute(user.userId, "email", user.email);            	facade.changeUserAttribute(user.userId, "picture", user.picture);            	flash.message = "User ${params.id} updated"                redirect(action:show,id:user.id)            	
            } catch (Exception e) {            	flash.message = e.getMessage()
            	render(view:'edit',model:[user:user])
            }
        }
        else {
            flash.message = "User not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def user = new User()
        user.properties = params
        return ['user':user]
    }

    def save = {
        def user = new User(params)        BilliardsFacade facade = facadeService.getFacade();        try {        	String id = facade.createUser(user.firstName, user.lastName, user.homePhone, user.workPhone, user.cellPhone,        			user.email, user.picture)        	user.userId = id            flash.message = "User "+ id +" created"                 redirect(action:show,id:user.userId)
        } catch (Exception e) {        	flash.message = e.getMessage()
        	render(view:'create',model:[user:user])
        }
      
    }
    
    private User getUser(String userId) {
    	BilliardsFacade facade = facadeService.getFacade();    	    	    	    	try {    		String firstName = facade.getUserAttribute(userId,"firstName");        	String lastName = facade.getUserAttribute(userId,"lastName");        	String homePhone = facade.getUserAttribute(userId, "homePhone");        	String workPhone = facade.getUserAttribute(userId, "workPhone");        	String email = facade.getUserAttribute(userId, "email");        	String cellPhone = facade.getUserAttribute(userId, "cellPhone");        	String picture = facade.getUserAttribute(userId, "picture");        	        	User user = new User();        	user.userId = userId;        	user.firstName = firstName;        	user.lastName = lastName;        	user.homePhone = homePhone;        	user.workPhone = workPhone;        	user.cellPhone = cellPhone;        	user.email = email;        	user.picture = picture;        	        	        	return user;        	
    	} catch (Exception e) {
    		return null;
    	}    	
    }             
}