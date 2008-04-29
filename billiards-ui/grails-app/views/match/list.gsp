

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Match List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New Match</g:link></span>
        </div>
        <div class="body">
            <h1>Match List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="creationDate" title="Creation Date" />
                        
                   	        <g:sortableColumn property="leagueId" title="League Id" />
                   	        
                   	        <g:sortableColumn property="userIdWinner" title="Winner" />
                   	        
                   	        <g:sortableColumn property="userIdLoser" title="Loser" />
                        
                   	        <g:sortableColumn property="length" title="Length" />
                        
                   	        <g:sortableColumn property="longestRunForLoser" title="Longest Run For Loser" />
                        
                   	        <g:sortableColumn property="longestRunForWinner" title="Longest Run For Winner" />
                   	        
                   	        <g:sortableColumn property="score" title="Score" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${matchList}" status="i" var="match">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${match.matchId}">${match.matchId?.encodeAsHTML()}</g:link></td>
                        
                            <td>${match.creationDate?.encodeAsHTML()}</td>
                        
                            <td>${match.leagueId?.encodeAsHTML()}</td>
                            
                            <td>${match.userIdWinner?.encodeAsHTML()}</td>
                            
                            <td>${match.userIdLoser?.encodeAsHTML()}</td>
                                                   	        
                     
                            <td>${match.length?.encodeAsHTML()}</td>
                        
                            <td>${match.longestRunForLoser?.encodeAsHTML()}</td>
                        
                            <td>${match.longestRunForWinner?.encodeAsHTML()}</td>
                            
                            <td>${match.score?.encodeAsHTML()}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${Match.count()}" />
            </div>
        </div>
    </body>
</html>
