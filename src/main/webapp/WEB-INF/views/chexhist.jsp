<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8"/>
  <title>Checkpoint - Check History</title>
  <link rel="stylesheet" type="text/css" href="${ctx}/css/global-1.css"/>
  <link rel="stylesheet" type="text/css" href="${ctx}/css/screen.css"/>
  <style>
    html, body { height:100%; margin:0; }
    body { display:flex; flex-direction:column; }
    #content { flex:1; overflow-y:auto; text-align:center; }
    .container { position:static !important; }
  </style>
</head>
<body>
<%@ include file="header.jsp" %>
<div id="content">
  <h2>Check History Inquiry</h2>
  <c:if test="${not empty error}"><div style="color:red"><c:out value="${error}"/></div></c:if>

  <form method="get" action="${ctx}/ChexHist.action">
  <div id="detail">
  <table align="center" border="1" width="55%">
    <tr><th class="header" colspan="3">Check History Inquiry Selection Criteria</th></tr>
    <tr>
      <th></th>
      <th class="header">From</th>
      <th class="header">To</th>
    </tr>
    <tr>
      <th>Period</th>
      <td>
        <select name="fromPeriod">
          <c:forEach items="${sel.histList}" var="hist">
            <option value="${hist}" ${hist == sel.ch_from_period ? 'selected' : ''}><c:out value="${hist}"/></option>
          </c:forEach>
        </select>
      </td>
      <td>
        <select name="toPeriod">
          <c:forEach items="${sel.histList}" var="hist">
            <option value="${hist}" ${hist == sel.ch_to_period ? 'selected' : ''}><c:out value="${hist}"/></option>
          </c:forEach>
        </select>
      </td>
    </tr>
    <tr>
      <th>Date</th>
      <td><input type="text" name="fromDate" value="${sel.ch_from_date}" maxlength="15"/></td>
      <td><input type="text" name="toDate"   value="${sel.ch_to_date}"   maxlength="15"/></td>
    </tr>
    <tr>
      <th>Account</th>
      <td><input type="text" name="fromAccount" value="${sel.ch_from_account}" maxlength="18"/></td>
      <td><input type="text" name="toAccount"   value="${sel.ch_to_account}"   maxlength="18"/></td>
    </tr>
    <tr>
      <th>Check Number</th>
      <td><input type="text" name="fromCheck" value="${sel.ch_from_check}" maxlength="15"/></td>
      <td><input type="text" name="toCheck"   value="${sel.ch_to_check}"   maxlength="15"/></td>
    </tr>
    <tr>
      <th>Amount</th>
      <td><input type="text" name="fromAmount" value="${sel.ch_from_amount}" maxlength="15"/></td>
      <td><input type="text" name="toAmount"   value="${sel.ch_to_amount}"   maxlength="15"/></td>
    </tr>
    <tr>
      <th>Currency</th>
      <td colspan="2"><input type="text" name="currency" value="${sel.ch_currency}" maxlength="15"/></td>
    </tr>
    <tr>
      <th>Return Status</th>
      <td colspan="2">
        <select name="retstat">
          <option value="N" ${sel.ch_retstat != 'Y' ? 'selected' : ''}>NONE</option>
          <option value="Y" ${sel.ch_retstat == 'Y' ? 'selected' : ''}>ALL</option>
        </select>
      </td>
    </tr>
  </table>
  </div>
  <br/>
  <input type="submit" name="action" value="View"/>

  <c:if test="${sel.detail_count > 0}">
    <hr/>
    <div id="totals">
      <table align="center">
        <tr>
          <th>Row Count</th><td><c:out value="${sel.detail_count}"/></td>
          <th>Total Amount</th><td><b><c:out value="${sel.detailAmount}"/></b></td>
        </tr>
      </table>
    </div>
    <table width="100%" align="center" border="1" class="sortable">
      <tr>
        <th>#</th>
        <th>Process Date</th>
        <th>Account</th>
        <th>Check #</th>
        <th>Currency</th>
        <th>Amount</th>
        <th>Payee</th>
        <th>Return Status</th>
        <th>Status</th>
        <th>Modified By</th>
        <th>Modified By Func</th>
        <th>Last Modified</th>
        <th>View</th>
      </tr>
      <c:forEach items="${sel.checkrows}" var="row" varStatus="idx">
      <tr>
        <td><c:out value="${idx.index + 1}"/></td>
        <td><c:out value="${row.chex_proc_date_disp}"/></td>
        <td><b><c:out value="${row.chex_account_num}"/></b></td>
        <td align="right"><c:out value="${row.chex_check_num}"/></td>
        <td><c:out value="${row.chex_check_currency}"/></td>
        <td align="right"><b><c:out value="${row.chex_check_amount_disp}"/></b></td>
        <td><c:out value="${row.chex_payee}"/></td>
        <td><c:out value="${row.chex_return_status}"/></td>
        <td><c:out value="${row.chex_check_status}"/></td>
        <td><c:out value="${row.chex_mod_user}"/></td>
        <td><c:out value="${row.chex_mod_func}"/></td>
        <td><c:out value="${row.chex_last_modified}"/></td>
        <td><a href="${ctx}/ChexHist.action?action=Details&procDate=${row.chex_proc_date}&acctNum=${row.chex_account_num}&checkNum=${row.chex_check_num}&uniqueIsn=${row.chex_unique_isn}">Details</a></td>
      </tr>
      </c:forEach>
    </table>
    <div id="totals">
      <table align="center">
        <tr>
          <th>Row Count</th><td><c:out value="${sel.detail_count}"/></td>
          <th>Total Amount</th><td><b><c:out value="${sel.detailAmount}"/></b></td>
        </tr>
      </table>
    </div>
  </c:if>
  </form>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
