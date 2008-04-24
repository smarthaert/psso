

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>League List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New League</g:link></span>
        </div>
        <div class="body">
            <h1>League List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="creationDate" title="Creation Date" />
                        
                   	        <g:sortableColumn property="name" title="Name" />
                        
                   	        <g:sortableColumn property="operator" title="Operator" />
                        
                   	        <g:sortableColumn property="standingExpression" title="Standing Expression" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${leagueList}" status="i" var="league">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${league.leagueId}">${league.leagueId?.encodeAsHTML()}</g:link></td>
                        
                            <td>${league.creationDate?.encodeAsHTML()}</td>
                        
                            <td>${league.name?.encodeAsHTML()}</td>
                        
                            <td>${league.operator?.encodeAsHTML()}</td>
                        
                            <td>${league.standingExpression?.encodeAsHTML()}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${League.count()}" />
            </div>
        </div>
    </body>
</html>
