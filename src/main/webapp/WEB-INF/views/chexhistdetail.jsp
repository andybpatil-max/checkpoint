<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8"/>
  <title>Checkpoint - Check History Detail</title>
  <link rel="stylesheet" type="text/css" href="${ctx}/css/global-1.css"/>
  <link rel="stylesheet" type="text/css" href="${ctx}/css/screen.css"/>
  <style>
    html, body { height:100%; margin:0; }
    body { display:flex; flex-direction:column; }
    #content { flex:1; overflow-y:auto; text-align:center; }
    .container { position:static !important; }
    .img-row { display:flex; justify-content:center; gap:8px; margin-top:16px; }
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
  <h2>Check History Detail</h2>
  <c:if test="${not empty error}"><div style="color:red"><c:out value="${error}"/></div></c:if>
  <div id="detail">
  <table colspan="2" width="70%" align="center" border="1">
    <tr><th class="header" colspan="2">Check Details</th></tr>
    <tr><th align="right">Process Date</th>  <td align="left"><c:out value="${chexDetail.chex_proc_date_disp}"/></td></tr>
    <tr><th align="right">Account</th>       <td align="left"><c:out value="${chexDetail.chex_account_num}"/></td></tr>
    <tr><th align="right">Check Number</th>  <td align="left"><c:out value="${chexDetail.chex_check_num}"/></td></tr>
    <tr><th align="right">Currency</th>      <td align="left"><c:out value="${chexDetail.chex_check_currency}"/></td></tr>
    <tr><th align="right">Amount</th>        <td align="left"><c:out value="${chexDetail.chex_check_amount_disp}"/></td></tr>
    <tr><th align="right">Issue Date</th>    <td align="left"><c:out value="${chexDetail.chex_issue_date_disp}"/></td></tr>
    <tr><th align="right">Payee</th>         <td align="left"><c:out value="${chexDetail.chex_payee}"/></td></tr>
    <tr><th align="right">Payee Address 1</th><td align="left"><c:out value="${chexDetail.chex_payee_addr1}"/></td></tr>
    <tr><th align="right">Payee Address 2</th><td align="left"><c:out value="${chexDetail.chex_payee_addr2}"/></td></tr>
    <tr><th align="right">Payee Address 3</th><td align="left"><c:out value="${chexDetail.chex_payee_addr3}"/></td></tr>
    <c:if test="${not empty chexDetail.chex_return_status and chexDetail.chex_return_status != ' '}">
    <tr><th align="right" style="background:lightyellow;">Return Reason</th>
        <td align="left" style="background:lightyellow;"><c:out value="${chexDetail.chex_reason_desc}"/></td></tr>
    </c:if>
    <tr><th align="right" style="background:lightyellow;">BOFD</th>      <td align="left" style="background:lightyellow;"><c:out value="${chexDetail.chex_BOFD_aba}"/></td></tr>
    <tr><th align="right" style="background:lightyellow;">BOFD Date</th> <td align="left" style="background:lightyellow;"><c:out value="${chexDetail.chex_BOFD_date}"/></td></tr>
    <tr><th align="right">Comments</th>      <td align="left"><c:out value="${chexDetail.chex_comments}"/></td></tr>
    <tr><th align="right">Reject Reason</th> <td align="left"><c:out value="${chexDetail.chex_reason_codes}"/> <c:out value="${chexDetail.chex_reason_desc}"/></td></tr>
    <tr><th align="right">Status</th>        <td align="left"><c:out value="${chexDetail.chex_check_status}"/> <c:out value="${chexDetail.chex_status_desc}"/></td></tr>
    <tr><th align="right">Last Modified</th> <td align="left"><c:out value="${chexDetail.chex_last_modified}"/></td></tr>
    <tr><th align="right">Modified By</th>   <td align="left"><c:out value="${chexDetail.chex_mod_user}"/></td></tr>
  </table>
  </div>
  <div class="img-row">
    <c:if test="${not empty chexDetail.chex_image_f}">
      <img src="${chexDetail.chex_image_f}" alt="Front" onclick="zoom(this.src)"/>
    </c:if>
    <c:if test="${not empty chexDetail.chex_image_b}">
      <img src="${chexDetail.chex_image_b}" alt="Back" onclick="zoom(this.src)"/>
    </c:if>
  </div>
  <p style="margin-top:16px;">
    <a href="${ctx}/ChexHist.action">Back to History</a>
  </p>
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
