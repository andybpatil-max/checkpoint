<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/deposits/actions/DepsHist.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<script>
	ns4		= (navigator.appName.indexOf("Netscape")>=0 && !document.getElementById)? 1 : 0;
	ie4		= (document.all && !document.getElementById)? 1 : 0;
	ie5		= (document.getElementById && document.all)? 1 : 0;
	ns6		= (document.getElementById && navigator.appName.indexOf("Netscape")>=0 )? 1: 0;
	w3c		= (document.getElementById)? 1 : 0;
	wid		= (ie4||ie5)?window.document.body.clientWidth-20:window.innerWidth-36
	if (ns4) {
		document.write ('<layer name="di1"></layer>')
	} else {
		document.write ('<div id="di1" style="position:absolute;z-index:100" ></div>')
	}
	outd	= ""
	if(w3c) div1	= document.getElementById('di1')
	if(ie4) div1	= document.all['di1']
	if(ns4) div1	= document.layers['di1']

	function move_div(x,y){
		if (isNaN(x+y)) return
		if (ns4) {
			div1.moveTo(x,y)
		} else {
			div1.style.left = x+'px'; div1.style.top = y+'px';
		}
	}

	function write_div(text){
		if(ns4){
			div1.document.open();
			div1.document.write(text);
			div1.document.close();
		} else {
			div1.innerHTML = text;
		}
	}
 
	function big (n){
	ondiv = n
		write_div("<a href=javascript:void(0) onmouseout='big_hide()' onmouseover='ondiv=1'><img border=0 name=ib src="+n+" width='1150'></a>");
		move_div(x-350,y-50);
	<%--
		move_div(x-250,y-100);
		move_div(20,400);
		move_div(50,550);
	--%>
	}

	function bigB(n){
	ondiv = n
		write_div("<a href=javascript:void(0) onmouseout='big_hide()' onmouseover='ondiv=1'><img border=0 name=ib src="+n+" width='1150'></a>");
		move_div(x-800,y-50);
	<%--
		move_div(x-250,y-100);
		move_div(20,400);
		move_div(50,550);
	--%>
	}

	function big_hide(){
		ondiv	= 0;
		t3		= window.setTimeout('big_hide2()',100)
	}

	function big_hide2(){
	if (ondiv==0){
		write_div("");
		move_div(-1000,-1000)}
	}

	y	= x	= 0
	function dragIt(evt){if(ie4||ie5)
	{x=window.event.clientX+document.body.scrollLeft; y=window.event.clientY+document.body.scrollTop}else {x=evt.pageX ; y=evt.pageY }}

	document.onmousemove = dragIt
	if(ns4){document.captureEvents( Event.MOUSEMOVE )}
	</script>
    <h1>
	<stripes:label for="deposit"/> <stripes:label for="check"/>
	<stripes:label for="history"/> <stripes:label for="image"/> <stripes:label for="inquiry"/>
    </h1>
    <div id="detail">
    <table align="center" border="1" height="3"  width="55%">
	<tr>
	    <th class="header" align=center height="19" colspan="3">
		<font size="3">
		    <stripes:label for="deposit"/> <stripes:label for="history"/> 
		    <stripes:label for="inquiry"/> <stripes:label for="selCriteria"/> 
		</font></TH>
	</tr>
	<tr>
	    <th align=center height="19" colspan="1"></TH>
	    <th class="header" align=center height="19" colspan="1">
		<font size="3"><stripes:label for="from"/></font></TH>
	    <th class="header" align=center height="19" colspan="1">
		<font size="3"><stripes:label for="to"/></font></TH>
	</tr>
	<tr>
	    <th align=center height="19" colspan="1">
		<font size="3"><stripes:label for="period"/></font></TH>
	    <td>
	    	<select size="1" name="depsSelector.ch_from_period">
		    <c:forEach items="${depsSelector.histList}" var="hist">
			<c:if test='${depsSelector.ch_from_period==hist}'>
			    <option selected> <c:out value="${hist}"/> </option>
			</c:if>
			<c:if test='${depsSelector.ch_from_period!=hist}'>
			    <option> <c:out value="${hist}"/> </option>
			</c:if>
		    </c:forEach>
		</select>
	    </td>
	    <td>
		<select size="1" name="depsSelector.ch_to_period">
		    <c:forEach items="${depsSelector.histList}" var="histto">
<%--
			<c:if test='${depsSelector.ch_to_period==""}'>
			    <option selected>NONE</option>
			</c:if>
			<c:if test='${depsSelector.ch_to_period=="NONE"}'>
			    <option selected>NONE</option>
			</c:if>
--%>
			<c:if test='${depsSelector.ch_to_period==histto}'>
			    <option selected> <c:out value="${histto}"/> </option>
			</c:if>
			<c:if test='${depsSelector.ch_to_period!=histto}'>
			    <option> <c:out value="${histto}"/> </option>
			</c:if>
		    </c:forEach>
		</select>
	    </td>
	</tr>
	<tr>
	    <th align=center height="19" colspan="1">
		<font size="3"><stripes:label for="date"/></font></TH>
	    <td align="center" width="5%" height="19">
		<stripes:text name="depsSelector.ch_from_date" maxlength="15"
			value="${depsSelector.ch_from_date}"/>
	    </td>
	    <td align="center" width="5%" height="19">
		<stripes:text name="depsSelector.ch_to_date"  maxlength="15"
			value="${depsSelector.ch_to_date}"/>
	    </td>
	</tr>
	<tr>
	    <th align=center height="19" colspan="1">
		<font size="3"><stripes:label for="payer"/> <stripes:label for="account"/></font></TH>
	    <td align="center" width="5%" height="19">
		<stripes:text name="depsSelector.ch_from_account"  maxlength="18"
			value="${depsSelector.ch_from_account}"/>
	    </td>
	    <td align="center" width="5%" height="19">
		<stripes:text name="depsSelector.ch_to_account"  maxlength="18"
			value="${depsSelector.ch_to_account}"/>
	    </td>
	</tr>
	<tr>
	    <th align=center height="19" colspan="1">
		<font size="3"><stripes:label for="payee"/> <stripes:label for="account"/></font></TH>
	    <td align="center" width="5%" height="19">
		<stripes:text name="depsSelector.ch_fromPayee"  maxlength="18"
			value="${depsSelector.ch_fromPayee}"/>
	    </td>
	    <td align="center" width="5%" height="19">
		<stripes:text name="depsSelector.ch_toPayee"  maxlength="18"
			value="${depsSelector.ch_toPayee}"/>
	    </td>
	</tr>
	<tr>
	    <th align=center height="19" colspan="1">
		<font size="3">
		    <stripes:label for="check"/> <stripes:label for="number"/>
		</font>
	    </TH>
	    <td align="center" width="5%" height="19">
		<stripes:text name="depsSelector.ch_from_check"  maxlength="15"
			value="${depsSelector.ch_from_check}"/>
	    </td>
	    <td align="center" width="5%" height="19">
		<stripes:text name="depsSelector.ch_to_check"  maxlength="15"
			value="${depsSelector.ch_to_check}"/>
	    </td>
	</tr>
	<tr>
	    <th align=center height="19" colspan="1" rowspan="1" valign="bottom">
		<font size="3"><stripes:label for="amount"/></font></TH>
	    <td align="center" width="5%" height="19">
		<stripes:text name="depsSelector.ch_from_amount"  maxlength="15"
			value="${depsSelector.ch_from_amount}"/>
	    </td>
	    <td align="center" width="5%" height="19">
		<stripes:text name="depsSelector.ch_to_amount"  maxlength="15"
			value="${depsSelector.ch_to_amount}"/>
	    </td>
	</tr>
	<tr>
	    <th align=center height="19" colspan="1" rowspan=1  valign="bottom">
		<font size="3"><stripes:label for="currency"/></font></TH>
	    <td align="center" width="5%" height="19" colspan="2">
		<stripes:text name="depsSelector.ch_currency"  maxlength="15"
			value="${depsSelector.ch_currency}"/>
	    </td>
	</tr>
	<tr>
	    <th align=center height="19" rowspan="1"  colspan="1">
		<font size="3">
		    <stripes:label for="deposit"/> <stripes:label for="source"/>
		</font>
	    </th>
	    <td align="center" width="5%" height="19" colspan="2">
		<c:if test='${depsSelector.actionFlag==""}'>
	    	<select size="1" name="depsSelector.ch_source">
		    <option selected value="ALL">ALL</option>
		    <option value="LOCKBOX">Lockbox</option>
		    <option value="RDC">Remote Deposits</option>
		</select>
		</c:if>
		<c:if test='${depsSelector.actionFlag!=""}'>
		    <select size="1" name="depsSelector.ch_source">
			<c:if test="${depsSelector.ch_source=='ALL'}">
			    <option selected value="ALL">ALL</option>
			    <option value="LOCKBOX">Lockbox</option>
			    <option value="RDC">Remote Deposits</option>
			</c:if>
			<c:if test="${depsSelector.ch_source=='LOCKBOX'}">
			    <option value="ALL">ALL</option>
			    <option selected value="LOCKBOX">Lockbox</option>
			    <option value="RDC">Remote Deposits</option>
			</c:if>
			<c:if test="${depsSelector.ch_source=='RDC'}">
			    <option value="ALL">ALL</option>
			    <option value="LOCKBOX">Lockbox</option>
			    <option selected value="RDC">Remote Deposits</option>
			</c:if>
		    </select>
		</c:if>
	    </td>
	</tr>
    </table>
    </div>


<c:if test='${depsSelector.detail_count>0}'>
<c:if test='${depsSelector.actionFlag!=""}'>
     <center>
	<stripes:submit name="ViewImages" value="View"/>
    </center>
<hr>
<p align="center"></p>
<div id="totals">
<table align='center'>
	<tr colspan=2>
	    <th>
		<font size=2><stripes:label for="rowcount"/></font>
	    </th>
	    <td>
		<c:out value="${depsSelector.detail_count}"/></b>
	    </td>
	    <th>
		<font size=2><stripes:label for="totalAmount"/></font>
	    </th>
	    <td>
		<c:out value="${depsSelector.detailAmount}" /></b>
	    </td>
	</tr>
</table>
</div>
	<h3>
	    <c:out value="Displaying "/>
	    <c:out value="${depsSelector.rowStart+1}"/>
	    <c:out value=" to "/>
	    <c:out value="${depsSelector.rowEnd}"/>
	    <c:out value=" of "/>
	    <c:out value="${depsSelector.detail_count}"/>
	    <c:out value=" rows "/>
	    <c:if test='${depsSelector.detail_count>depsSelector.rowsDisplayed}'>
		<c:if test='${depsSelector.rowEnd!=depsSelector.detail_count}'>
		    <stripes:submit name="Next" value="Next"/>
		</c:if>
		<c:if test='${depsSelector.detail_count<=depsSelector.rowsDispStr}'>
		    <stripes:text name="depsSelector.detail_count" size="4" maxlength="4"
			value="${depsSelector.rowsDispStr}"/>
		</c:if>
		<c:if test='${depsSelector.detail_count>depsSelector.rowsDispStr}'>
		    <stripes:text name="depsSelector.rowsDispStr" size="4" maxlength="4"
			value="${depsSelector.rowsDispStr}"/>
		</c:if>
		<c:if test='${depsSelector.rowStart!=0}'>
		    <stripes:submit name="Prev" value="Prev"/>
		</c:if>
		<c:if test='${depsSelector.rowStart==0}'>
		    <stripes:submit name="Prev" value="Prev"/>
		</c:if>
	    </c:if>
	</h3>
	<br/>
<%--
	</c:if>
<table colspan='12' width='100%' align='center' border='1' height='39'>
	<tr bgcolor=bluegreen>
	    <th align=center height=19 colspan="12">
		<font size=3 color=blue>
		    <stripes:label for="check"/> <stripes:label for="data"/>
		    <stripes:label for="history"/> <stripes:label for="inquiry"/>
		    <stripes:label for="selResults"/>
		</font>
	    </th>
	</tr>
</table>
<table colspan='6' width='100%' align='center' border='1' height='39' class="sortable">
--%>
<table colspan='6' width='100%' align='center' border='1' height='39'>
	<tr bgcolor=bluegreen>
	    <th align='center' width='4%' height=15 colspan=1>
		<font size=2><stripes:label for="processDate"/></font>
	    </th>
	    <th align='center' width='8%' height=15 colspan=1>
		<font size=2><stripes:label for="account"/></font>
	    </th>
	    <th align='right' width='8%' height=15 colspan=1>
		<font size=2>
		    <stripes:label for="check"/> <stripes:label for="number"/>
		</font>
	    </th>
<%--
	    <th align='center' width='3%' height=15 colspan=1>
		<font size=2><stripes:label for="currency"/></font>
	    </th>
--%>
	    <th align='right' width='8%' height=15 colspan=1>
		<font size=2><stripes:label for="amount"/></font>
	    </th>
	    <th align='center' width='20%' height=15 colspan=1>
		<font size=2><stripes:label for="payee"/></font>
	    </th>
	</tr>

<c:forEach items="${depsSelector.checkrows}" var="DepsDetail" varStatus="idx0" end="${depsSelector.rowEnd-1}" 
	begin="${depsSelector.rowStart}">
<%--
<c:forEach items="${depsSelector.checkrows}" var="DepsDetail">
--%>
  <tr>
    <td align="center" width='5%' height='19'>
      <c:out value="${DepsDetail.chex_proc_date_disp}"/>
    </td>
    <td align="center" width='10%' height='19'><b>
      <c:out value="${DepsDetail.chex_account_num}"/></b>
    </td>
    <td align="right" width='5%' height='19'>
      <c:out value="${DepsDetail.chex_check_num}"/>
    </td>
<%--
    <td align="left" width='5%' height='19'>
      <c:out value="${DepsDetail.chex_check_currency}"/>
    </td>
--%>
    <td align="right" width='5%' height='19'>
      <fmt:formatNumber value="${DepsDetail.chex_check_amount}" type="currency"/>
    </td>
    <td  align='center' width='15%' height='19'>
      <c:out value="${DepsDetail.chex_payee}"/>
    </td>
  </tr>
  <tr>
     <td colspan="6" width="100%">
	<table colspan="6">
	<center>
	<td colspan='3'>
	    <a href="javascript:big('<c:out value="${DepsDetail.chex_image_f}"/>')" onmouseout="big_hide()">
			<img border="0" src='<c:out value="${DepsDetail.chex_image_f}"/>' width="85%"></a>
	</td>
	<td colspan='3'>
	    <a href="javascript:bigB('<c:out value="${DepsDetail.chex_image_b}"/>')" onmouseout="big_hide()">
			<img border="0" src='<c:out value="${DepsDetail.chex_image_b}"/>' width="85%"></a>
	</td>
	</center>
	</table>
     </td>
  </tr>
</c:forEach>
</table>
<%--
<div id="totals">
<table align='center'>
	<tr colspan=2>
	    <th>
		<font size=2><stripes:label for="rowcount"/></font>
	    </th>
	    <td>
		<c:out value="${depsSelector.detail_count}"/></b>
	    </td>
	    <th>
		<font size=2><stripes:label for="totalAmount"/></font>
	    </th>
	    <td>
		<c:out value="${depsSelector.detailAmount}" /></b>
	    </td>
	</tr>
</table>
</div>
--%>
</c:if>
</c:if>
<%--													--%>
<%--	Submit buttons area to click on to take the next step.						--%>
<%--	Any errors will appear between the data table and the buttons.					--%>
<%--													--%>
     <center>
	<hr>
	<stripes:errors />
	<stripes:submit name="ViewImages" value="View"/>
    </center>
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	</stripes:form>
    </stripes:layout-component>
</stripes:layout-render>
