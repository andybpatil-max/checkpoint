<%@ include file="taglibs.jsp" %>
    <div id="repmain">
	<table align="center">
	    <tr>
		<div id="repbanner">
		    <div class="logo"/>
	        </div>
	    </tr>
	    <tr>
		<th class="headleft" width=15%>
		    <font size="4">User:
		    <c:out value='${user.userId}'/></font>
		</th>
		<th width="25%"><font size="4">
		    <c:out value='${user.currentAppl}'/>
		</th>
		<th class="headright" width="15%" colspan="1">
		    <font size="3">
			<%= java.util.Calendar.getInstance().getTime() %>
		    </font>
		</th>
	    </tr>
	    <tr>
		<th class="headleft" width=15%>
		    <font size="4">User Name:
		    <c:out value='${user.userFName}'/> <c:out value='${user.userLName}'/></font>
		</th>
		<th width="25%"><font size="4">
		    <c:out value='${user.currentAppl}'/>
		</th>
		<th class="headright" width="15%" colspan="1">
		    <font size="3">Application Date:
			<c:out value='${user.applDate}'/>
		    </font>
		</th>
	    </tr>
	</table>

