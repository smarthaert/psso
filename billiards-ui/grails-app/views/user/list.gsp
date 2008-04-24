

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>User List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New User</g:link></span>
        </div>
        <div class="body">
            <h1>User List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="userId" title="Id" />
                        
                   	        <g:sortableColumn property="firstName" title="First Name" />
                        
                   	        <g:sortableColumn property="lastName" title="Last Name" />
                   	        
                   	        <g:sortableColumn property="homePhone" title="Home Phone" />

                   	        <g:sortableColumn property="cellPhone" title="Cell Phone" />
                        
                   	        <g:sortableColumn property="email" title="Email" />                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${userList}" status="i" var="user">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${user.userId}">${user.userId?.encodeAsHTML()}</g:link></td>
                        	
                        	 <td>${user.firstName?.encodeAsHTML()}</td>
                        	
                            <td>${user.lastName?.encodeAsHTML()}</td>
                            
                            <td>${user.homePhone?.encodeAsHTML()}</td>
                            
                            <td>${user.cellPhone?.encodeAsHTML()}</td>
                                                    
                            <td>${user.email?.encodeAsHTML()}</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${User.count()}" />
            </div>
        </div>
    </body>
</html>
