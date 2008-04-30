import org.springframework.web.multipart.commons.CommonsMultipartFile
class UserController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ userList: list ]
    }

    def show = {
        def user = getUser( params.id )

        if(!user) {
            flash.message = "User not found with id ${params.userId}"
            redirect(action:list)
        }        
        else {
    }

    def delete = {
        def user = getUser( params.id )
        if(user) {
        		facade.deleteUser(user.userId)
        	} catch (Exception e) {
        		message = e.getMessage()
        	} finally {
            
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
        def user = getUser( params.id )
            user.properties = params
        		facade.changeUserAttribute(user.userId,"firstName", firstName);
            } catch (Exception e) {
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
        def user = new User(params)     		
            	if (file.getContentType().startsWith("image")) {
            		picture  = file.getOriginalFilename()
            	} else
            }
        	}        	
        } catch (Exception e) {
        	render(view:'create',model:[user:user])
        }
      
    }
    
    private User getUser(String userId) {
    	BilliardsFacade facade = facadeService.getFacade();
    	} catch (Exception e) {
    		return null;
    	}
    }
}