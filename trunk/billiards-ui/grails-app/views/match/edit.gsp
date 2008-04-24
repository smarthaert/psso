

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit Match</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Match List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New Match</g:link></span>
        </div>
        <div class="body">
            <h1>Edit Match</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${match}">
            <div class="errors">
                <g:renderErrors bean="${match}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${match?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="creationDate">Creation Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:match,field:'creationDate','errors')}">
                                    <g:datePicker name="creationDate" value="${match?.creationDate}" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="leagueId">League Id:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:match,field:'leagueId','errors')}">
                                    <input type="text" id="leagueId" name="leagueId" value="${fieldValue(bean:match,field:'leagueId')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="length">Length:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:match,field:'length','errors')}">
                                    <input type="text" id="length" name="length" value="${fieldValue(bean:match,field:'length')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="longestRunForLoser">Longest Run For Loser:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:match,field:'longestRunForLoser','errors')}">
                                    <input type="text" id="longestRunForLoser" name="longestRunForLoser" value="${fieldValue(bean:match,field:'longestRunForLoser')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="longestRunForWinner">Longest Run For Winner:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:match,field:'longestRunForWinner','errors')}">
                                    <input type="text" id="longestRunForWinner" name="longestRunForWinner" value="${fieldValue(bean:match,field:'longestRunForWinner')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="score">Score:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:match,field:'score','errors')}">
                                    <input type="text" id="score" name="score" value="${fieldValue(bean:match,field:'score')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="userIdLoser">User Id Loser:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:match,field:'userIdLoser','errors')}">
                                    <input type="text" id="userIdLoser" name="userIdLoser" value="${fieldValue(bean:match,field:'userIdLoser')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="userIdWinner">User Id Winner:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:match,field:'userIdWinner','errors')}">
                                    <input type="text" id="userIdWinner" name="userIdWinner" value="${fieldValue(bean:match,field:'userIdWinner')}"/>
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
