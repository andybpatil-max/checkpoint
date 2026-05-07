<%@ include file="taglibs.jsp" %>
<c:if test="${not empty menuGroups}">
  <div class="container my">
    <hr style="color:red;background-color:red;height:1px;margin:0;"/>
    <div style="max-width:1024px; margin:0 auto;">
    <table cellpadding="4" cellspacing="0" border="0" style="width:auto; margin:0 auto;">
      <tr>
        <c:forEach items="${menuGroups}" var="group">
        <td style="vertical-align:top; padding:5px 15px;">
          <h1 class="footerH1"><c:out value="${group.desc}"/></h1>
          <ul>
            <c:forEach items="${group.functions}" var="func">
            <li><a href="${ctx}/Menu.action?prod=${func.productId}&amp;menu=${func.menuId}&amp;func=${func.funcId}"><c:out value="${func.desc}"/></a></li>
            </c:forEach>
          </ul>
        </td>
        </c:forEach>
        <td style="vertical-align:top; padding:5px 15px;">
          <h1 class="footerH1">Start Over</h1>
          <ul>
            <li><a href="${ctx}/Menu.action?home=true">Home</a></li>
            <li><a href="${ctx}/Logoff.action">Logout</a></li>
          </ul>
        </td>
      </tr>
    </table>
    </div>
  </div>
</c:if>
