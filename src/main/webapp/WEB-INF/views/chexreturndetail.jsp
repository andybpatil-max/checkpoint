<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/inclear/actions/ChexReturn.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<h1>
	    <stripes:label for="return"/> <stripes:label for="check"/> <stripes:label for="detail"/>
	</h1>

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
		move_div(50,630);
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
<table align='center'>
	<tr>
	    <th class="header" colspan="2">
		<stripes:label for="return"/> <stripes:label for="check"/> <stripes:label for="detail"/>
	    </th>
	</tr>

	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
		<stripes:label for="processDate"/></th>
	<td align='left' height='19'>
		<stripes:text name='chexDetail.chex_proc_date' value='${chexDetail.chex_proc_date}' 
			      size='8' readonly='true'/></td>
	</tr>

	<tr>
	    <th align='right' bgcolor='lightyellow' width='35%' height='19'><b>
		<stripes:label for="account"/></b></th>
	    <td align='left' width=10% height='19'>
		<stripes:text name='chexDetail.chex_account_num' value='${chexDetail.chex_account_num}'
			      size='21' readonly='true' />
	    </td>
	</tr>

	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'><b>
	    <stripes:label for="check"/> <stripes:label for="Number"/>
	</b></th>
	<td align='left' width=10% height='19'>
		<stripes:text name='chexDetail.chex_check_num' value='${chexDetail.chex_check_num}'
		    size='21' readonly='true' /></td>
	</tr>

	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
		<stripes:label for="amount"/></th>
	<td align='left' height='15'>
		<stripes:text name='chexDetail.chex_check_amount' value='${chexDetail.chex_check_amount}'
		    size='15' readonly='true'/></td>
	</tr>

	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
		<stripes:label for="payee"/></th>
	<td align='left' height='15'>
		<stripes:text name='chexDetail.chex_payee' value='${chexDetail.chex_payee}'
		    size='35' readonly='true'/></td>
	</tr>

	<c:if test='${ChexDetail.chex_payee_addr1!=""}'>
	    <tr>
		<th align='right' bgcolor='lightyellow' width='35%' height='19'>
		    <stripes:label for="payee"/> <stripes:label for="address1"/>
		</th>
		<td align='left' height='15'>
			<stripes:text name='chexDetail.chex_payee_addr1' value='${chexDetail.chex_payee_addr1}'
			    size='35' readonly='true'/></td>
	    </tr>
	</c:if>

	<c:if test='${ChexDetail.chex_payee_addr2!=""}'>
	    <tr>
		<th align='right' bgcolor='lightyellow' width='35%' height='19'>
		    <stripes:label for="payee"/> <stripes:label for="address2"/>
		</th>
		<td align='left' height='15'>
			<stripes:text name='chexDetail.chex_payee_addr2' value='${chexDetail.chex_payee_addr2}'
			    size='35' readonly='true'/></td>
	    </tr>
	</c:if>

	<c:if test='${ChexDetail.chex_payee_addr3!=""}'>
	    <tr>
		<th align='right' bgcolor='lightyellow' width='35%' height='19'>
		    <stripes:label for="payee"/> <stripes:label for="address3"/>
		</th>
		<td align='left' height='15'>
			<stripes:text name='chexDetail.chex_payee_addr3' value='${chexDetail.chex_payee_addr3}'
			    size='35' readonly='true'/></td>
	    </tr>
	</c:if>

	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
		<stripes:label for="issueDate"/></th>
	<td align='left' height='15'>
		<stripes:text name='chexDetail.chex_issue_date' value='${chexDetail.chex_issue_date}'
		    size='10' readonly='true'/></td>
	</tr>

	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
		<stripes:label for="comments"/></th>
	<td align='left' height='15'>
		<stripes:text name='chexDetail.chex_comments' value='${chexDetail.chex_comments}'
		    size='80' maxlength='80'/></td>
	</tr>
<%--
	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
	    <stripes:label for="rejectReason"/> <stripes:label for="reason"/></th>
	<td align='left' height='15'>
		<stripes:text name='chexDetail.chex_reason_codes' size='16' readonly='true'/></td>
	</tr>
--%>
	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
	    <stripes:label for="status"/></th>
	<td align='left' height='19'>
		<stripes:text name='chexDetail.chex_check_status' value='${chexDetail.chex_check_status}'
		    size='2' readonly='true'/></td>
	</tr>

	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
	    <stripes:label for="return"/> <stripes:label for="reason"/></th>
	<td align='left' height='19'><font color='black' size='3'> 
<%--
	    <select size="1" name="chexDetail.chex_return_reason">
		<c:if test='${chexSelector.actionFlag=="Return"}'>
--%>
		<c:if test='${chexDetail.chex_return_status==" "}'>
		    <select size="1" name="chexDetail.chex_return_reason">
			    <c:forEach var="retreason" items="${chexSelector.retReasons}">
			    <c:if test='${chexDetail.chex_return_reason!=retreason.key}'>
				<option value='<c:out value="${retreason.key}"/>'>
				    <c:out value="${retreason.value}"/>
				</option>
			    </c:if>
			    <c:if test='${chexDetail.chex_return_reason==retreason.key}'>
				 <option selected value='<c:out value="${retreason.key}"/>'>
				 	 <c:out value="${retreason.value}"/>
			    	 </option>
			    </c:if>
			    </c:forEach>
		    </select>
		</c:if>
<%--
		<c:if test='${chexSelector.actionFlag!="Return"}'>
--%>
		<c:if test='${chexDetail.chex_return_status!=" "}'>
<%--
		    <stripes:text name='chexDetail.chex_return_reason'
			value='${chexDetail.chex_return_reason}' size='80' maxlength='80'/>
--%>
		    <c:forEach var="retreason" items="${chexSelector.retReasons}">
		        <c:if test='${chexDetail.chex_return_reason==retreason.key}'>
			    <c:out value="${retreason.key}"/> = <c:out value="${retreason.value}"/>
			</c:if>
		    </c:forEach>
		</td>
		</c:if>
<%--
	    </select>
--%>
	</tr>
	<tr>
	</td>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
		<stripes:label for="bofd"/></th>
	<td align='left' height='19'>
		<stripes:text name='chexDetail.chex_BOFD_aba' value='${chexDetail.chex_BOFD_aba}'
		    size='9'/></td>
	</tr>
	<tr>
	</td>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
		<stripes:label for="bofdDate"/></th>
	<td align='left' height='19'>
		<stripes:text name='chexDetail.chex_BOFD_date' value='${chexDetail.chex_BOFD_date}'
		    size='8'/></td>
	</tr>
</table>
</div>

<center>
	<a href="javascript:big('<c:out value="${chexDetail.chex_image_f}"/>')" onmouseout="big_hide()">
		<img border="0" src='<c:out value="${chexDetail.chex_image_f}"/>' width="45%"></a>
	<a href="javascript:big('<c:out value="${chexDetail.chex_image_b}"/>')" onmouseout="big_hide()">
		<img border="0" src='<c:out value="${chexDetail.chex_image_b}"/>' width="45%"></a>
</center>
<br/>

	<center>
	    <stripes:errors />
	    <hr>
	    <c:if test='${chexSelector.actionFlag=="Return"}'>
		<stripes:submit name="ConfirmReturn" value="Confirm Mark Check For Return"/>
	    </c:if>
	    <c:if test='${chexSelector.actionFlag=="UndoReturn"}'>
		<stripes:submit name="ConfirmUndoReturn" value="Confirm Undo Mark Check For Return"/>
	    </c:if>
	</center>
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	</stripes:form>
    </stripes:layout-component>
</stripes:layout-render>

