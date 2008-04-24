

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show User</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">User List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New User</g:link></span>
        </div>
        <div class="body">
            <h1>Show User</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                        <tr class="prop">
                            <td valign="top" class="name">User Id:</td>
                            
                            <td valign="top" class="value">${user.userId}</td>
                            
                        </tr>

                         <tr class="prop">
                            <td valign="top" class="name">First Name:</td>
                            
                            <td valign="top" class="value">${user.firstName}</td>
                            
                        </tr>
                        
                         <tr class="prop">
                            <td valign="top" class="name">Last Name:</td>
                            
                            <td valign="top" class="value">${user.lastName}</td>
                            
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name">Home Phone:</td>
                            
                            <td valign="top" class="value">${user.homePhone}</td>
                            
                        </tr>
						
						<tr class="prop">
                            <td valign="top" class="name">Work Phone:</td>
                            
                            <td valign="top" class="value">${user.workPhone}</td>
                            
                        </tr>

 
                        <tr class="prop">
                            <td valign="top" class="name">Cell Phone:</td>
                            
                            <td valign="top" class="value">${user.cellPhone}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Email:</td>
                            
                            <td valign="top" class="value">${user.email}</td>
                            
                        </tr>
                                            
                        <tr class="prop">
                            <td valign="top" class="name">Picture:</td>
                            
                            <td valign="top" class="value">${user.picture}</td>
                            
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${user?.userId}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
