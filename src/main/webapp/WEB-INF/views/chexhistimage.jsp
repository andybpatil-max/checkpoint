<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8"/>
  <title>Checkpoint - Check History Images</title>
  <link rel="stylesheet" type="text/css" href="${ctx}/css/global-1.css"/>
  <link rel="stylesheet" type="text/css" href="${ctx}/css/screen.css"/>
  <style>
    html, body { height:100%; margin:0; }
    body { display:flex; flex-direction:column; }
    #content { flex:1; overflow-y:auto; text-align:center; }
    .container { position:static !important; }
    .img-row { display:flex; justify-content:center; gap:8px; padding:6px; }
    .img-row img { width:49%; cursor:zoom-in; border:1px solid #ccc; }
    #zoom-overlay {
      display:none; position:fixed; inset:0;
      background:rgba(0,0,0,0.82); z-index:9999;
      align-items:center; justify-content:center; cursor:zoom-out;
    }
    #zoom-overlay.active { display:flex; }
    #zoom-overlay img { max-width:95vw; max-height:92vh; border:2px solid #fff; }
  </style>
</head>
<body>
<%@ include file="header.jsp" %>
<div id="content">
  <h2>Check History Image Inquiry</h2>
  <c:if test="${not empty error}"><div style="color:red"><c:out value="${error}"/></div></c:if>

  <form method="get" action="${ctx}/ChexHist.action">
  <div id="detail">
  <table align="center" border="1" width="55%">
    <tr><th class="header" colspan="3">Check History Image Inquiry Selection Criteria</th></tr>
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
  </table>
  </div>
  <br/>
  <input type="submit" name="action" value="ViewImages"/>

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
    <table width="100%" align="center" border="1">
      <tr>
        <th>Process Date</th>
        <th>Account</th>
        <th>Check #</th>
        <th>Amount</th>
        <th>Payee</th>
      </tr>
      <c:forEach items="${sel.checkrows}" var="row">
      <tr>
        <td><c:out value="${row.chex_proc_date_disp}"/></td>
        <td><b><c:out value="${row.chex_account_num}"/></b></td>
        <td align="right"><c:out value="${row.chex_check_num}"/></td>
        <td align="right"><b><c:out value="${row.chex_check_amount_disp}"/></b></td>
        <td><c:out value="${row.chex_payee}"/></td>
      </tr>
      <tr>
        <td colspan="5" style="padding:0;">
          <div class="img-row">
            <c:if test="${not empty row.chex_image_f}">
              <img src="${row.chex_image_f}" alt="Front" onclick="zoom(this.src)"/>
            </c:if>
            <c:if test="${not empty row.chex_image_b}">
              <img src="${row.chex_image_b}" alt="Back"  onclick="zoom(this.src)"/>
            </c:if>
          </div>
        </td>
      </tr>
      </c:forEach>
    </table>
  </c:if>
  </form>

  <p><a href="${ctx}/ChexHist.action">Back to History</a></p>
</div>

<div id="zoom-overlay" onclick="this.classList.remove('active')">
  <img id="zoom-img" src="" alt=""/>
</div>
<script>
  function zoom(src) {
    document.getElementById('zoom-img').src = src;
    document.getElementById('zoom-overlay').classList.add('active');
  }
</script>
<%@ include file="footer.jsp" %>
</body>
</html>
