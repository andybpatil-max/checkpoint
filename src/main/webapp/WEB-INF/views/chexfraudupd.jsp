<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/inclear/actions/Chex.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<h1><stripes:label for="check"/> <stripes:label for="data"/> <stripes:label for="maint"/></h1>

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
		write_div("<a href=javascript:void(0) onmouseout='big_hide()' onmouseover='ondiv=1'><img border=0 name=ib src="+n+" width='100%'></a>");
	<%--
		move_div(x-250,y-100);
	--%>
		move_div(20,400);
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

	<div id="detail">
	<table colspan='2' width='60%' align='center' border='1'>
	    <tr bgcolor='bluegreen'>
		<th class="header" align='center' height='19' colspan=2>
		    <font size=3 color=blue>
			<stripes:label for="check"/> <stripes:label for="authorize"/>
		    </font>
		</th>
	    </tr>
	    <tr>
		<th align='right' bgcolor='lightyellow' width='35%' height='19'>
		    <stripes:label for="processDate"/></th>
		<td align='left' height='19'>
		    <stripes:text name="chexDetail.chex_proc_date" value="${chexDetail.chex_proc_date}" 
				size='8' readonly='true'/></td>
	    </tr>

	    <tr>
		<th align='right' bgcolor='lightyellow' width='35%' height='19'><b>
		    <stripes:label for="account"/></b></th>
		<td align='left' width=10% height='19'>
		    <stripes:text name="chexDetail.chex_account_num" value="${chexDetail.chex_account_num}"
				size='15' readonly='true' />
		</td>
	    </tr>

	    <tr>
		<th align='right' bgcolor='lightyellow' width='35%' height='19'><b>
		    <stripes:label for="number"/></b></th>
		<td align='left' width=10% height='19'>
		    <stripes:text name="chexDetail.chex_check_num" value="${chexDetail.chex_check_num}"
				size='15' readonly='true' /></td>
	    </tr>

	    <tr>
		<th align='right' bgcolor='lightyellow' width='35%' height='19'>
		    <stripes:label for="amount"/></th>
		<td align='left' height='15'>
		    <stripes:text name="chexDetail.chex_check_amount" value="${chexDetail.chex_check_amount}"
				size='15' readonly='true'/></td>
	    </tr>

	    <tr>
	    <th align='right' bgcolor='lightyellow' width='35%' height='19'>
		<stripes:label for="payee"/></th>
	    <td align='left' height='15'>
		<stripes:text name="chexDetail.chex_payee" value="${chexDetail.chex_payee}"
				size='35' readonly='true'/></td>
	    </tr>

	    <c:if test='${ChexDetail.chex_payee_addr1!=""}'>
		<tr>
		    <th align='right' bgcolor='lightyellow' width='35%' height='19'>
			<stripes:label for="payee"/> <stripes:label for="address1"/>
		    </th>
		    <td align='left' height='15'>
			<stripes:text name="chexDetail.chex_payee_addr1"
			    value="${chexDetail.chex_payee_addr1}" size='35' readonly='true'/></td>
		</tr>
	    </c:if>

	    <c:if test='${ChexDetail.chex_payee_addr2!=""}'>
		<tr>
		    <th align='right' bgcolor='lightyellow' width='35%' height='19'>
			<stripes:label for="payee"/> <stripes:label for="address2"/>
		    </th>
		    <td align='left' height='15'>
			<stripes:text name="chexDetail.chex_payee_addr2" 
			    value="${chexDetail.chex_payee_addr2}" size='35' readonly='true'/></td>
		</tr>
	    </c:if>

	    <c:if test='${ChexDetail.chex_payee_addr3!=""}'>
		<tr>
		    <th align='right' bgcolor='lightyellow' width='35%' height='19'>
			<stripes:label for="payee"/> <stripes:label for="address1"/>
		    </th>
		    <td align='left' height='15'>
			<stripes:text name="chexDetail.chex_payee_addr3"
			    value="${chexDetail.chex_payee_addr3}" size='35' readonly='true'/></td>
		</tr>
	    </c:if>

	    <tr>
		<th align='right' bgcolor='lightyellow' width='35%' height='19'>
		    <stripes:label for="date"/></th>
		<td align='left' height='15'>
		    <stripes:text name="chexDetail.chex_issue_date" value="${chexDetail.chex_issue_date}"
				size='10' readonly='true'/></td>
	    </tr>

	    <tr>
	    <th align='right' bgcolor='lightyellow' width='35%' height='19'>
		<stripes:label for="comments"/></th>
	    <td align='left' height='15'>
		<stripes:text name="chexDetail.chex_comments" value="${chexDetail.chex_comments}"
				size='80' maxlength='80'/></td>
	    </tr>

	    <tr>
		<th align='right' bgcolor='lightyellow' width='35%' height='19'>
		    <stripes:label for="status"/></th>
		<td align='left' height='19'>
		    <stripes:text name="chexDetail.chex_check_status" value="${chexDetail.chex_check_status}"
				size='2' readonly='true'/></td>
	    </tr>
	</table>
	</div>
	<br/>
	<center>
	    <a href="javascript:big('<c:out value="${chexDetail.chex_image_f}"/>')" onmouseout="big_hide()">
			<img border="0" src='<c:out value="${chexDetail.chex_image_f}"/>' width="45%"></a>
	    <a href="javascript:big('<c:out value="${chexDetail.chex_image_b}"/>')" onmouseout="big_hide()">
			<img border="0" src='<c:out value="${chexDetail.chex_image_b}"/>' width="45%"></a>
	</center>

<%--													--%>
<%--	Submit buttons area to click on to take the next step.						--%>
<%--	Any errors will appear between the data table and the buttons.					--%>
<%--													--%>

     <center>
	<stripes:errors />
	<c:if test='${chexSelector.actionFlag=="Genuine"}'>
	    <stripes:submit  name='GenuineFraudCheckConfirm' value="Confirm Genuine Check"/>
	</c:if>
	<c:if test='${chexSelector.actionFlag=="Fraud"}'>
	    <stripes:submit  name='GenuineFraudCheckConfirm' value="Confirm Fraud Check"/>
	</c:if>
	<stripes:submit  name="FraudChex"  value="Cancel"/>
    </center>


<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	</stripes:form>
    </stripes:layout-component>
</stripes:layout-render>

