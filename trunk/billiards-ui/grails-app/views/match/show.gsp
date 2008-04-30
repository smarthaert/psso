

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show Match</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Match List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New Match</g:link></span>
        </div>
        <div class="body">
            <h1>Show Match</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                             <td valign="top" class="value">${match.matchId}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Creation Date:</td>
                            
                            <td valign="top" class="value">${match.creationDate}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">League:</td>
                            
                            <td valign="top" class="value"><g:link action="show" controller="league" id="${match.leagueId}">${match.leagueName().encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Length:</td>
                            
                            <td valign="top" class="value">${match.length}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Longest Run For Loser:</td>
                            
                            <td valign="top" class="value">${match.longestRunForLoser}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Longest Run For Winner:</td>
                            
                            <td valign="top" class="value">${match.longestRunForWinner}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Score:</td>
                            
                            <td valign="top" class="value">${match.score}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">User Winner:</td>
                            
                            <td valign="top" class="value"><g:link action="show" controller="user" id="${match.userIdWinner}">${match.winnerLastName().encodeAsHTML()}</g:link></td>
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name">User Loser:</td>
                            
                            <td valign="top" class="value"><g:link action="show" controller="user" id="${match.userIdLoser}">${match.loserLastName().encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    

                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${match?.matchId}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
