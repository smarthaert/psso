

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit League</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">League List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New League</g:link></span>
        </div>
        <div class="body">
            <h1>Edit League</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${league}">
            <div class="errors">
                <g:renderErrors bean="${league}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${league?.leagueId}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="creationDate">Creation Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:league,field:'creationDate','errors')}">
                                    <g:datePicker name="creationDate" value="${league?.creationDate}" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:league,field:'name','errors')}">
                                    <input type="text" id="name" name="name" value="${fieldValue(bean:league,field:'name')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="operator">Operator:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:league,field:'operator','errors')}">
                                    <g:select name="operator" from="${usernames}"  keys="${userids}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="standingExpression">Standing Expression:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:league,field:'standingExpression','errors')}">
                                    <input type="text" id="standingExpression" name="standingExpression" value="${fieldValue(bean:league,field:'standingExpression')}"/>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Update" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
