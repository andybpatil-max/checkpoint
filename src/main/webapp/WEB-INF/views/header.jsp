<%@ include file="taglibs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<div id="banner" style="height:80px;"><div class="logo" style="height:80px;"></div></div>
<div style="max-width:1024px; margin:0 auto;">
<table width="100%" cellpadding="4" cellspacing="0" border="0">
  <tr>
    <th class="headleft" width="20%">User ID: <c:out value="${user.userId}"/></th>
    <th width="60%" align="center"><strong><c:out value="${user.currentAppl}"/></strong></th>
    <th class="headright" width="20%" align="right"><%= new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm").format(new java.util.Date()) %></th>
  </tr>
  <tr>
    <th class="headleft" width="20%"><c:out value="${user.userFName}"/> <c:out value="${user.userLName}"/></th>
    <th width="60%" align="center">App Date: <c:out value="${user.applDate}"/></th>
    <th class="headright" width="20%" align="right">Node: <c:out value="${user.nodeName}"/></th>
  </tr>
</table>
</div>
