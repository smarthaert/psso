

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show League</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">League List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New League</g:link></span>
        </div>
        <div class="body">
            <h1>League Description</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${league.leagueId}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Creation Date:</td>
                            
                            <td valign="top" class="value">${league.creationDate}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Name:</td>
                            
                            <td valign="top" class="value">${league.name}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Operator:</td>
                            
                            <td valign="top" class="value"><g:link action="show" controller="user" id="${league.operator}">${league.operatorName().encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Standing Expression:</td>
                            
                            <td valign="top" class="value">${league.standingExpression}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${league?.leagueId}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
			
			&nbsp&nbsp&nbsp&nbsp
			<h2>League's Members</h2>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="lastName" title="Player" />
                        
                   	        <g:sortableColumn property="numberOfWins" title="Number of wins" />
                        
                   	        <g:sortableColumn property="numberOfLosses" title="Number of Losses" />
                   	        
                   	        <g:sortableColumn property="playerStanding" title="Player Standing" />

                    </thead>
                    <tbody>
                    <g:each in="${playerList}" status="i" var="user">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" controller="user" id="${user.userId}">${user.lastName?.encodeAsHTML()}</g:link></td>
                        	
                        	 <td>${user.numberOfWins?.encodeAsHTML()}</td>
                        	
                            <td>${user.numberOfLosses?.encodeAsHTML()}</td>
                            
                            <td>${user.playerStanding?.encodeAsHTML()}</td>

                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            
        </div>
    </body>
</html>
