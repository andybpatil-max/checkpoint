<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/deposits/actions/Deposits.action">
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
		write_div("<a href=javascript:void(0) onmouseout='big_hide()' onmouseover='ondiv=1'><img border=0 name=ib src="+n+" width='1100'></a>");
		move_div(0,470);
	<%--
		move_div(x-250,y-100);
		move_div(20,400);
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

<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>

	<c:if test='${depsSelector.accessFlag=="inq"}'>
	    <h1>
		<stripes:label for="deposit"/> <stripes:label for="dataInq"/>
	    </h1>
	</c:if>
	<c:if test='${depsSelector.accessFlag!="inq"}'>
	    <h1>
		<stripes:label for="deposit"/> <stripes:label for="dataMaint"/>
	    </h1>
	</c:if>

	<center>
	    <hr/>
	    <stripes:errors/>
	    <c:if test='${depsSelector.actionFlag=="Modify"}'>
		<stripes:submit name="Update" value="Update"/>
	    </c:if>
	    <c:if test='${depsSelector.actionFlag=="Update"}'>
		<stripes:submit name="Confirm" value="Confirm Update Check"/>
	    </c:if>
	    <br><br/>
	</center>
	    <hr>
	<div id="detail">
	    <table colspan='2' width='70%' align='center' border='1'>
		<c:if test='${depsSelector.accessFlag!="inq"}'>
		    <tr bgcolor='bluegreen'>
			<th class="header" colspan="2">
			    <font size=3 color=blue>
				<stripes:label for="modify"/> <stripes:label for="deposits"/>
			    </font>
			</th>
		    </tr>
		</c:if>
		<c:if test='${depsSelector.accessFlag=="inq"}'>
		    <tr bgcolor='bluegreen'>
			<th class='header' colspan=2>
			    <font size=3 color=blue>
				<stripes:label for="deposit"/> <stripes:label for="details"/>
			    </font>
			</th>
		    </tr>
		</c:if>
<%-- 	************	--%>
<%-- 	Process Date	--%>
<%-- 	************	--%>
		<tr>
		    <th align='right' width='35%' height='19'>
			<stripes:label for="processDate"/></th>
		    <td align='left' height='19' width='15%'>
			<stripes:text name="depsDetail.chex_proc_date" value="${depsDetail.chex_proc_date}" 
				size='10' readonly='true'/>
		    </td>
		</tr>
<%-- 	*******************************************		--%>
<%-- 	If Status "S" ABA, Check Account, Check Num		--%>
<%-- 	*******************************************		--%>
<%--
		<c:if test='${depsDetail.chex_check_status=="S"}'>
--%>
		    <tr>
			<th align='right' width='25%' height='19'><b>
			    <stripes:label for="routing"/></b></th>
			<td align='left' width='20%' height='19'>
			    <c:if test='${depsSelector.actionFlag=="Modify"}'>
				<stripes:text name="depsDetail.chex_routing_transit"
					value="${depsDetail.chex_routing_transit}" size='22'/>
			    </c:if>
			    <c:if test='${depsSelector.actionFlag!="Modify"}'>
				<stripes:text name="depsDetail.chex_routing_transit" 
					value="${depsDetail.chex_routing_transit}" size='10' readonly='true'/>
			    </c:if>
			</td>
		    </tr>
		    <tr>
			<th align='right' width='25%' height='19'><b>
			    <stripes:label for="account"/></b></th>
			<td align='left' width='20%' height='19'>
			    <c:if test='${depsSelector.actionFlag=="Modify"}'>
				<stripes:text name="depsDetail.chex_account_num"
					value="${depsDetail.chex_account_num}" size='22'/>
			    </c:if>
			    <c:if test='${depsSelector.actionFlag!="Modify"}'>
				<stripes:text name="depsDetail.chex_account_num"
					value="${depsDetail.chex_account_num}" 
					size='22' readonly='true'/>
			    </c:if>
			</td>
		    </tr>
		    <tr>
			<th align='right' width='25%' height='19'><b>
			    <stripes:label for="payer"/> <stripes:label for="name"/></b></th>
			<td align='left' width='20%' height='19'>
			    <c:if test='${depsSelector.actionFlag=="Modify"}'>
				<stripes:text name="depsDetail.chexPayerName"
					value="${depsDetail.chexPayerName}" size='50' maxlength='80'/>
			    </c:if>
			    <c:if test='${depsSelector.actionFlag!="Modify"}'>
				<stripes:text name="depsDetail.chexPayerName"
					value="${depsDetail.chexPayerName}"
					size='50' maxlength='80' readonly='true'/>
			    </c:if>
			</td>
		    </tr>
<%----%>
		    <tr>
			<th align='right' width='25%' height='19'><b>
			    <stripes:label for="country"/>
			<td align='left' width='20%' height='19'>
			    <c:if test='${depsSelector.actionFlag=="Modify"}'>
				<stripes:text name="depsDetail.chexPayerCountry"
					value="${depsDetail.chexPayerCountry}"
					size='2' maxlength='2' style='text-transform:uppercase'/>
			    </c:if>
			    <c:if test='${depsSelector.actionFlag!="Modify"}'>
				<stripes:text name="depsDetail.chexPayerCountry"
					value="${depsDetail.chexPayerCountry}" 
					size='2' maxlength='2' readonly='true'/>
			    </c:if>
			</td>
		    </tr>

		    <tr>
			<th align='right' width='25%' height='19'><b>
			    <stripes:label for="check"/> <stripes:label for="number"/></b>
			</th>
			<td align='left' width='20%' height='19'>
			    <c:if test='${depsSelector.actionFlag=="Modify"}'>
				<stripes:text name="depsDetail.chex_check_num"
					value="${depsDetail.chex_check_num}" size='22'/>
			    </c:if>
			    <c:if test='${depsSelector.actionFlag!="Modify"}'>
				<stripes:text name="depsDetail.chex_check_num"
					value="${depsDetail.chex_check_num}" size='22' readonly='true'/>
			    </c:if>
			</td>
		    </tr>
<%--
		</c:if>
--%>
		<tr>
		    <th align='right' width='25%' height='19'>
			<stripes:label for="currency"/>
		    </th>
		    <td align='left' height='15' width='20%'>
			<stripes:text name="depsDetail.chex_check_currency"
			    value="${depsDetail.chex_check_currency}" size='15' readonly='true'/>
		    </td>
		</tr>
		<tr>
		    <th align='right' width='25%' height='19'>
			<stripes:label for="amount"/>
		    </th>
		    <td align='left' height='15' width='20%'>
			<c:if test='${depsSelector.actionFlag=="Modify"}'>
			    <stripes:text name="depsDetail.chex_check_amount"
			        value="${depsDetail.chex_check_amount}" size='15'/>
			</c:if>
			<c:if test='${depsSelector.actionFlag=="Update"}'>
			    <stripes:text name="depsDetail.chex_check_amount"
			        value="${depsDetail.chex_check_amount}" size='15' readonly='true'/>
			</c:if>
			<c:if test='${depsSelector.actionFlag=="Detail"}'>
			    <stripes:text name="depsDetail.chex_check_amount"
			        value="${depsDetail.chex_check_amount}" size='15' readonly='true'/>
			</c:if>
		    </td>
		</tr>
		<tr>
		    <th align='right' width='25%' height='19'>
			<stripes:label for="issueDate"/> (YYYYMMDD)</th>
		    <td align='left' height='15' width='20%'>
			<c:if test='${depsSelector.actionFlag=="Modify"}'>
			    <stripes:text name="depsDetail.chex_issue_date"
			        value="${depsDetail.chex_issue_date}" size='10' maxlength='8'/></td>
			</c:if>
			<c:if test='${depsSelector.actionFlag!="Modify"}'>
			    <stripes:text name="depsDetail.chex_issue_date"
			        value="${depsDetail.chex_issue_date}" size='10' readonly='true'/></td>
			</c:if>
		    </td>
		</tr>
		<tr>
		    <th align='right' width='25%' height='19'>
			<stripes:label for="payee"/>
		    </th>
		    <td align='left' height='15' width='20%'>
			<c:if test='${depsSelector.actionFlag=="Modify"}'>
			    <stripes:text name="depsDetail.chex_payee"
				value="${depsDetail.chex_payee}" size='45' />
			</c:if>
			<c:if test='${depsSelector.actionFlag!="Modify"}'>
			    <stripes:text name="depsDetail.chex_payee"
				value="${depsDetail.chex_payee}" size='45' readonly='true'/>
			</c:if>
		    </td>
		</tr>
		<tr>
		    <th align='right' width='25%' height='19'>
			<stripes:label for="payeeAba"/>
		    </th>
		    <td align='left' height='15' width='20%'>
			<c:if test='${depsSelector.actionFlag=="Modify"}'>
			    <stripes:text name="depsDetail.chex_payeeaba"
				value="${depsDetail.chex_payeeaba}" size='45' maxlength='80' />
			</c:if>
			<c:if test='${depsSelector.actionFlag!="Modify"}'>
			    <stripes:text name="depsDetail.chex_payeeaba"
				value="${depsDetail.chex_payeeaba}" size='45' readonly='true'/>
			</c:if>
		    </td>
		</tr>
		<tr>
		    <th align='right' width='25%' height='19'>
			<stripes:label for="payeeAcct"/>
		    </th>
		    <td align='left' height='15' width='20%'>
			<c:if test='${depsSelector.actionFlag=="Modify"}'>
			    <stripes:text name="depsDetail.chex_payeeacct"
				value="${depsDetail.chex_payeeacct}" size='45' maxlength='80' />
			</c:if>
			<c:if test='${depsSelector.actionFlag!="Modify"}'>
			    <stripes:text name="depsDetail.chex_payeeacct"
				value="${depsDetail.chex_payeeacct}" size='45' readonly='true'/>
			</c:if>
		    </td>
		</tr>
		<tr>
		    <th align='right' width='25%' height='19'>
			<stripes:label for="status"/>
		    </th>
		    <td align='left' height='19' width='20%'>
			<stripes:text name="depsDetail.chex_check_status"
				value="${depsDetail.chex_check_status}" size='3' readonly='true'/>
			<stripes:text name="depsDetail.chex_status_desc"
				value="${depsDetail.chex_status_desc}" size='50' readonly='true'/>
		    </td>
		</tr>
	    </table>
	</div>
	<hr/>
	<center>
	    <a href="javascript:big('<c:out value="${depsDetail.chex_image_f}"/>')" onmouseout="big_hide()">
			<img border="0" src='<c:out value="${depsDetail.chex_image_f}"/>'
			width="45%"></a>
	    <a href="javascript:big('<c:out value="${depsDetail.chex_image_b}"/>')" onmouseout="big_hide()">
			<img border="0" src='<c:out value="${depsDetail.chex_image_b}"/>'
			width="45%"></a>
	</center>
	<br>
<%--													--%>
<%--	This is the buttons area to click on to take the next step.					--%>
<%--	Any errors will appear between the data table and the buttons.					--%>
<%--		<c:out value="${depsSelector.actionFlag}"/>						--%>

	<center>
	    <hr/>
	    <stripes:errors/>
	    <c:if test='${depsSelector.actionFlag=="Modify"}'>
		<stripes:submit name="Update" value="Update"/>
	    </c:if>
	    <c:if test='${depsSelector.actionFlag=="Update"}'>
		<stripes:submit name="Confirm" value="Confirm Update Check"/>
	    </c:if>
	    <br/>
	</center>
<%--
	    <c:out value="${depsSelector.accessFlag}"/>
	    <c:out value="${depsSelector.actionFlag}"/>
	    <c:if test='${depsSelector.accessFlag!="inq"}'>
	    </c:if>
	    <%@ include file="rejectreasons.jsp" %>
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	</stripes:form>
    </stripes:layout-component>
</stripes:layout-render>
