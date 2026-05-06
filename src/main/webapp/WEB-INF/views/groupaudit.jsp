<%@ include file="taglibs.jsp" %>
<%@ include file="header.jsp" %>

<h1>
	<stripes:message key="userGroup"/>
</h1>
<palign="center"></p>
<html:form action="/groupAudit" method="post" styleClass="FORM">
    <table align="center" border="1" width="27%">
	<tr bgcolor=turquoise>
		<th align=center height="19" colspan="3">
		    <font size="3"><bean:message key="eController.group.seltitleInq"/></font></TH>
	</tr>
	<tr bgcolor=turquoise>
		<th align=center height="15">
			<font size="3"><bean:message key="eController.prod.id"/></font></TH>
	</tr>
	<tr>
	<td>
	    <select size="1" name="product_id_sel">
		<logic:equal name="productForm" property="actionFlag" value="">
			<logic:iterate id="prod" name="product_names">
				<option value='<bean:write name="prod" property="key"/>'>
				<bean:write name="prod" property="value"/> </option>
			</logic:iterate>
		</logic:equal>
		<logic:equal name="productForm" property="actionFlag" value="new_group">
			<option selected><bean:write name="productForm"
				property="product_id_sel"/></option>
		</logic:equal>
		<logic:notEqual name="productForm" property="actionFlag" value="select_prod">
		<logic:notEqual name="productForm" property="actionFlag" value="">
			<option selected><bean:write name="productForm"
				property="product_id_sel"/></option>
		</logic:notEqual>
		</logic:notEqual>
	    </select>
	</td>
	</tr>
    </table>
<logic:equal name="productForm" property="actionFlag" value="view">
<div id="selres">
<table colspan='4' width='60%' align='center' border='1' bgcolor=lightgreen height='39'>
    <logic:equal name="productForm" property="accessFlag" value="inq">
	<tr bgcolor=bluegreen>
	    <th class="header" align=center height=19 colspan=7>
		<font size=3 color=blue><bean:message key="eController.group.inqresults"/></font>
	    </th>
	</tr>
    </logic:equal>
    <logic:notEqual name="productForm" property="accessFlag" value="inq">
	<tr bgcolor=bluegreen>
	    <th class="header" align=center height=19 colspan=7>
		<font size=3 color=blue><bean:message key="eController.group.maintresults"/></font>
	    </th>
	</tr>
    </logic:notEqual>
    <tr bgcolor=bluegreen>
	<th align='left' width='2%' height=15 colspan=1>
	    <font size=2><bean:message key="eController.prod.id"/></font>
	</th>
	<th align='left' width='2%' height=15 colspan=1>
	    <font size=2><bean:message key="eController.group.id"/></font>
	</th>
	<th align='center' width='10%' height=15 colspan=2>
	    <font size=2><bean:message key="eController.group.action"/></font>
	</th>
    </tr>
    <logic:iterate id="GroupDetail" name="GroupSelector" property="grouprows">
	<tr>
	    <td align="left" bgcolor='lightyellow' width='2%' height='19' size=2>
		<bean:write name="GroupDetail" property="gdProd"/>
	    </td>
	    <td align="left" bgcolor='lightyellow' width='4%' height='19'>
		<bean:write name="GroupDetail" property="gdGroup_id" filter="true"/>
	    </td>
	    <td align="center" bgcolor='lightyellow' width='5%' height='19'>
		<html:link page='/groupAudit.do?action=viewLogs' name="GroupDetail"
		    property="gd_modparam">View
		</html:link>
	    </td>
	</tr>
    </logic:iterate>
</table>
</div>
</logic:equal>


<p align="center"></p>
<logic:equal name="productForm" property="accessFlag" value="viewLogs">
<table colspan='2' width='35%' align='center' border='1' bgcolor=lightgreen height='39'>
<div id="selres">
	<tr bgcolor=bluegreen>
	    <th class="header" align=center height=19 colspan=8>
		<font size=3 color=blue><bean:message key="eController.group.inqresults"/></font>
	    </th>
	</tr>
<tr>
  <logic:iterate id="ProductDetail" name="ProductSelector" property="productrows" indexId="idx">
  <logic:lessEqual name="idx" value="7">
    <logic:equal name="ProductDetail" property="product_type" value="F">
    <th  align='left' bgcolor='lightyellow' width='80%' height='19'>
	<bean:write name="ProductDetail" property="product_pmf_desc" filter="true"/>
    </th>
    </logic:equal>
  </logic:lessEqual>
  </logic:iterate>
</tr>
<tr>
  <logic:iterate id="ProductDetail" name="ProductSelector" property="productrows" indexId="idx">
  <logic:greaterThan name="idx" value="7">
  <logic:lessEqual name="idx" value="15">
    <logic:equal name="ProductDetail" property="product_type" value="F">
    <th  align='left' bgcolor='lightyellow' width='80%' height='19'>
	<bean:write name="ProductDetail" property="product_pmf_desc" filter="true"/>
    </th>
    </logic:equal>
  </logic:lessEqual>
  </logic:greaterThan>
  </logic:iterate>
</tr>
<tr>
  <logic:iterate id="ProductDetail" name="ProductSelector" property="productrows" indexId="idx">
  <logic:greaterThan name="idx" value="15">
  <logic:lessEqual name="idx" value="23">
    <logic:equal name="ProductDetail" property="product_type" value="F">
    <th  align='left' bgcolor='lightyellow' width='80%' height='19'>
	<bean:write name="ProductDetail" property="product_pmf_desc" filter="true"/>
    </th>
    </logic:equal>
  </logic:lessEqual>
  </logic:greaterThan>
  </logic:iterate>
</tr>
<tr>
  <logic:iterate id="ProductDetail" name="ProductSelector" property="productrows" indexId="idx">
  <logic:greaterThan name="idx" value="23">
  <logic:lessEqual name="idx" value="31">
    <logic:equal name="ProductDetail" property="product_type" value="F">
    <th  align='left' bgcolor='lightyellow' width='80%' height='19'>
	<bean:write name="ProductDetail" property="product_pmf_desc" filter="true"/>
    </th>
    </logic:equal>
  </logic:lessEqual>
  </logic:greaterThan>
  </logic:iterate>
</tr>
<tr>
  <logic:iterate id="ProductDetail" name="ProductSelector" property="productrows" indexId="idx">
  <logic:greaterThan name="idx" value="31">
  <logic:lessEqual name="idx" value="39">
    <logic:equal name="ProductDetail" property="product_type" value="F">
    <th  align='left' bgcolor='lightyellow' width='80%' height='19'>
	<bean:write name="ProductDetail" property="product_pmf_desc" filter="true"/>
    </th>
    </logic:equal>
  </logic:lessEqual>
  </logic:greaterThan>
  </logic:iterate>
</tr>
<tr>
  <logic:iterate id="ProductDetail" name="ProductSelector" property="productrows" indexId="idx">
  <logic:greaterThan name="idx" value="39">
  <logic:lessEqual name="idx" value="47">
    <logic:equal name="ProductDetail" property="product_type" value="F">
    <th  align='left' bgcolor='lightyellow' width='80%' height='19'>
	<bean:write name="ProductDetail" property="product_pmf_desc" filter="true"/>
    </th>
    </logic:equal>
  </logic:lessEqual>
  </logic:greaterThan>
  </logic:iterate>
</tr>
<tr>
  <logic:iterate id="ProductDetail" name="ProductSelector" property="productrows" indexId="idx">
  <logic:greaterThan name="idx" value="47">
  <logic:lessEqual name="idx" value="55">
    <logic:equal name="ProductDetail" property="product_type" value="F">
    <th  align='left' bgcolor='lightyellow' width='80%' height='19'>
	<bean:write name="ProductDetail" property="product_pmf_desc" filter="true"/>
    </th>
    </logic:equal>
  </logic:lessEqual>
  </logic:greaterThan>
  </logic:iterate>
</tr>
<tr>
  <logic:iterate id="ProductDetail" name="ProductSelector" property="productrows" indexId="idx">
  <logic:greaterThan name="idx" value="55">
  <logic:lessEqual name="idx" value="63">
    <logic:equal name="ProductDetail" property="product_type" value="F">
    <th  align='left' bgcolor='lightyellow' width='80%' height='19'>
	<bean:write name="ProductDetail" property="product_pmf_desc" filter="true"/>
    </th>
    </logic:equal>
  </logic:lessEqual>
  </logic:greaterThan>
  </logic:iterate>
</tr>

<logic:iterate id="ProductDetail" name="ProductSelector" property="productrows" indexId="idx">
  <tr>
    <td align="left" bgcolor='lightyellow' width='1%' height='19'>
	<logic:equal name="ProductDetail" property="product_type" value="F">
	    <html:multibox name="ProductSelector" property="prod_pmf_entitled">
		<bean:write name="ProductDetail" property="product_key"/>
	    </html:multibox>
	</logic:equal>
    </td>
  </tr>
</logic:iterate>
</div>
</table>
</logic:equal>



<div id="errors">
<center>
<html:errors/>
</center>
</div>

    <p align="center">
    <html:hidden property="dbUsed"/>
    <html:hidden property="actionFlag"/>
    <html:hidden property="accessFlag"/>
    <SCRIPT>function set(target) {document.forms[0].actionFlag.value=target;}</SCRIPT>

    <logic:equal name="productForm" property="actionFlag" value="">
	<html:submit onclick="set('view');" value="View" >
                   <bean:message key="button.view"/>
	</html:submit>
    </logic:equal>
    </p>
<br>
<%@ include file="footer.jsp" %>
</html:form>
