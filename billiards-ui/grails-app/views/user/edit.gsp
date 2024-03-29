

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit User</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">User List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New User</g:link></span>
        </div>
        <div class="body">
            <h1>Edit User</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${user}">
            <div class="errors">
                <g:renderErrors bean="${user}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="update" method="post" enctype="multipart/form-data">
                <input type="hidden" name="id" value="${user?.userId}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="firstName">First Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:user,field:'firstName','errors')}">
                                    <input type="text" id="firstName" name="firstName" value="${fieldValue(bean:user,field:'firstName')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="lastName">Last Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:user,field:'lastName','errors')}">
                                    <input type="text" id="lastName" name="lastName" value="${fieldValue(bean:user,field:'lastName')}"/>
                                </td>
                            </tr> 

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="homePhone">Home Phone:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:user,field:'homePhone','errors')}">
                                    <input type="text" id="homePhone" name="homePhone" value="${fieldValue(bean:user,field:'homePhone')}"/>
                                </td>
                            </tr> 
                        

                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="workPhone">Work Phone:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:user,field:'workPhone','errors')}">
                                    <input type="text" id="workPhone" name="workPhone" value="${fieldValue(bean:user,field:'workPhone')}"/>
                                </td>
                            </tr> 
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cellPhone">Cell Phone:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:user,field:'cellPhone','errors')}">
                                    <input type="text" id="cellPhone" name="cellPhone" value="${fieldValue(bean:user,field:'cellPhone')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="email">Email:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:user,field:'email','errors')}">
                                    <input type="text" id="email" name="email" value="${fieldValue(bean:user,field:'email')}"/>
                                </td>
                            </tr> 

                        <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="picture">Picture:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:user,field:'picture','errors')}">
                                    <input type="file" name="picture" />
                                </td>
                         </tr>                          
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="update" value="Update" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
