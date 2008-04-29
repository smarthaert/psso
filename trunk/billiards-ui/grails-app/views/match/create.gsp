

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create Match</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Match List</g:link></span>
        </div>
        <div class="body">
            <h1>Create Match</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${match}">
            <div class="errors">
                <g:renderErrors bean="${match}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
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
                                    <g:select name="leagueId" from="${leaguenames}"  keys="${leagueids}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="length">Length:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:match,field:'length','errors')}">
                                    <input type="text" id="length" name="length" value="${fieldValue(bean:match,field:'length')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="longestRunForLoser">Longest Run For Loser:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:match,field:'longestRunForLoser','errors')}">
                                    <input type="text" id="longestRunForLoser" name="longestRunForLoser" value="${fieldValue(bean:match,field:'longestRunForLoser')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="longestRunForWinner">Longest Run For Winner:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:match,field:'longestRunForWinner','errors')}">
                                    <input type="text" id="longestRunForWinner" name="longestRunForWinner" value="${fieldValue(bean:match,field:'longestRunForWinner')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="matchId">Match Id:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:match,field:'matchId','errors')}">
                                    <input type="text" id="matchId" name="matchId" value="${fieldValue(bean:match,field:'matchId')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="score">Score:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:match,field:'score','errors')}">
                                    <input type="text" id="score" name="score" value="${fieldValue(bean:match,field:'score')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="userIdLoser">User Loser:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:match,field:'userIdLoser','errors')}">
                                    <g:select name="userIdLoser" from="${usernames}"  keys="${userids}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="userIdWinner">User Winner:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:match,field:'userIdWinner','errors')}">
                                    <g:select name="userIdWinner" from="${usernames}"  keys="${userids}" />
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Create" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
