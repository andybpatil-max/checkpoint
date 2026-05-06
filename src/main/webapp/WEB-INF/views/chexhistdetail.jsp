<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/inclear/actions/ChexHist.action">
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
 
	function big(n){
	ondiv = n
		write_div("<a href=javascript:void(0) onmouseout='big_hide()' onmouseover='ondiv=1'><img border=0 name=ib src="+n+" width='1150'></a>");
	<%--
		move_div(x-250,y-100);
		move_div(20,400);
	--%>
		move_div(50,550);
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
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<h1>
	    <stripes:label for="check"/> <stripes:label for="data"/> 
	    <stripes:label for="history"/> <stripes:label for="inquiry"/> 
	</h1>
	<div id="detail">
	    <table colspan='2' width='70%' align='center' border='1'>
		<tr>
		    <th class='header' colspan=2>
			<font size=3 color=blue>
			    <stripes:label for="check"/> <stripes:label for="details"/> 
			</font>
		    </th>
		</tr>
		<tr>
		    <th align='right' width='35%' height='19'>
			<stripes:label for="processDate"/></th>
		    <td align='left' height='19' width='15%'>
			<stripes:text name="chexDetail.chex_proc_date" value="${chexDetail.chex_proc_date}" 
				size='10' readonly='true'/>
		    </td>
		</tr>
		<tr>
		    <th align='right' width='25%' height='19'><b>
			<stripes:label for="account"/></b></th>
		    <td align='left' width='20%' height='19'>
			<stripes:text name="chexDetail.chex_account_num"
				value="${chexDetail.chex_account_num}" size='22' readonly='true' />
		    </td>
		</tr>
		<tr>
		    <th align='right' width='25%' height='19'><b>
			<stripes:label for="check"/> <stripes:label for="number"/>
		    </b></th>
		    <td align='left' width='20%' height='19'>
			<stripes:text name="chexDetail.chex_check_num"
				value="${chexDetail.chex_check_num}" size='10' readonly='true' />
		    </td>
		</tr>
		<tr>
		    <th align='right' width='25%' height='19'>
			<stripes:label for="currency"/>
		    </th>
		    <td align='left' height='15' width='20%'>
			<stripes:text name="chexDetail.chex_check_currency"
				value="${chexDetail.chex_check_currency}" size='15' readonly='true'/>
		    </td>
		</tr>
		<tr>
		    <th align='right' width='25%' height='19'>
			<stripes:label for="amount"/>
		    </th>
		    <td align='left' height='15' width='20%'>
			<stripes:text name="chexDetail.chex_check_amount"
				value="${chexDetail.chex_check_amount}" size='15' readonly='true'/>
		    </td>
		</tr>
		<tr>
		    <th align='right' width='25%' height='19'>
			<stripes:label for="issueDate"/></th>
		    <td align='left' height='15' width='20%'>
			<stripes:text name="chexDetail.chex_issue_date"/>
		</tr>
		<tr>
		    <th align='right' width='25%' height='19'>
			<stripes:label for="payee"/>
		    </th>
		    <td align='left' height='15' width='20%'>
			<stripes:text name="chexDetail.chex_payee" value="${chexDetail.chex_payee}"
					size='45' readonly='true'/>
		    </td>
		</tr>
		<tr>
		    <th align='right' width='25%' height='19'>
			<stripes:label for="payee"/> <stripes:label for="address1"/>
		    </th>
		    <td align='left' height='15' width='20%'>
			<stripes:text name="chexDetail.chex_payee_addr1"
				value="${chexDetail.chex_payee_addr1}" size='45' readonly='true'/>
		    </td>
		</tr>
		<tr>
		    <th align='right' width='25%' height='19'>
			<stripes:label for="payee"/> <stripes:label for="address2"/>
		    </th>
		    <td align='left' height='15' width='20%'>
			<stripes:text name="chexDetail.chex_payee_addr2" 
			    	value="${chexDetail.chex_payee_addr2}" size='45' readonly='true'/>
		    </td>
		</tr>
		<tr>
		    <th align='right' width='25%' height='19'>
			<stripes:label for="payee"/> <stripes:label for="address3"/>
		    </th>
		    <td align='left' height='15' width='20%'>
			<stripes:text name="chexDetail.chex_payee_addr3"
				value="${chexDetail.chex_payee_addr3}" size='45' readonly='true'/>
		    </td>
		</tr>
		<tr>
		    <c:if test='${chexDetail.chex_return_status!=" "}'>
		    <th align='right' bgcolor='lightyellow' width='35%' height='19'>
			<stripes:label for="return"/> <stripes:label for="reason"/></th>
		    <td align='left' height='19'>
			    <c:forEach var="retreason" items="${chexSelector.retReasons}">
				<c:if test='${chexDetail.chex_return_reason==retreason.key}'>
				    <c:out value='${retreason.value}'/>
				</c:if>
			    </c:forEach>
		    </c:if>
		</tr>
		<tr>
		    <th align='right' bgcolor='lightyellow' width='25%' height='19'>
			<stripes:label for="bofd"/>
		    </th>
		    <td align='left' height='19' width='20%'>
			<stripes:text name="chexDetail.chex_BOFD_aba"
				value="${chexDetail.chex_BOFD_aba}" size='9' readonly='true'/>
		    </td>
		</tr>
		<tr>
		    <th align='right' bgcolor='lightyellow' width='25%' height='19'>
			<stripes:label for="bofdDate"/>
		    </th>
		    <td align='left' height='19' width='20%'>
			<stripes:text name="chexDetail.chex_BOFD_date"
				value="${chexDetail.chex_BOFD_date}" size='10' readonly='true'/>
		    </td>
		</tr>
		<tr>
		    <th align='right' width='25%' height='19'>
			<stripes:label for="comments"/>
		    </th>
		    <td align='left' height='15' width='20%'>
			<stripes:text name="chexDetail.chex_comments" value="${chexDetail.chex_comments}"
					size='80' readonly='true'/>
		    </td>
		</tr>
		<tr>
		    <th align='right' width='25%' height='19'>
			<stripes:label for="reject"/> <stripes:label for="reason"/>
		    </th>
		    <td align='left' height='15' wirth='20%'>
			<stripes:text name="chexDetail.chex_reason_codes"
				value="${chexDetail.chex_reason_codes}" size='3' readonly='true'/>
			<stripes:text name="chexDetail.chex_reason_desc"
				value="${chexDetail.chex_reason_desc}" size='50' readonly='true'/>
		    </td>
		</tr>
		<tr>
		    <th align='right' width='25%' height='19'>
			<stripes:label for="status"/>
		    </th>
		    <td align='left' height='19' width='20%'>
			<stripes:text name="chexDetail.chex_check_status"
				value="${chexDetail.chex_check_status}" size='3' readonly='true'/>
			<stripes:text name="chexDetail.chex_status_desc"
				value="${chexDetail.chex_status_desc}" size='50' readonly='true'/>
		    </td>
		</tr>
	    </table>
	</div>
	<center>
<%--
--%>
	    <a href="javascript:big('<c:out value="${chexDetail.chex_image_f}"/>')" onmouseout="big_hide()">
			<img border="0" src='<c:out value="${chexDetail.chex_image_f}"/>' width="49%"></a>
	    <a href="javascript:big('<c:out value="${chexDetail.chex_image_b}"/>')" onmouseout="big_hide()">
			<img border="0" src='<c:out value="${chexDetail.chex_image_b}"/>' width="49%"></a>
	</center>
	<br>
<%--													--%>
<%--	This is the buttons area to click on to take the next step.					--%>
<%--	Any errors will appear between the data table and the buttons.					--%>
<%--													--%>
<%--
<c:out value="${ChexDetail.chex_image_f}"/>
<c:out value="${ChexDetail.chex_image_b}"/>
<c:out value='${chexDetail.chex_return_status}'/>
<c:out value="${chexSelector.actionFlag}"/>
--%>
	<center>
	    <stripes:errors />
	    <br/>
	</center>
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	</stripes:form>
    </stripes:layout-component>
</stripes:layout-render>


