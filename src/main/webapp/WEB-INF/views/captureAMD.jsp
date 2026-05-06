<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/capture/actions/Capture.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<style>
	input[type='text'] { font-size: 14px; font-family: monospace; color:blue;}
	</style>
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
		write_div("<a href=javascript:void(0) onmouseout='big_hide()' onmouseover='ondiv=1'>
			      <img border=0 name=ib src="+n+" width='1150'></a>");
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
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<c:if test='${captureSelector.accessFlag=="inq"}'>
	    <h1>Remote 
		<stripes:label for="depositCapture"/></h1> <stripes:label for="inquiry"/>
	    </h1>
	</c:if>
	<c:if test='${captureSelector.accessFlag!="inq"}'>
	    <h1>Remote 
		<stripes:label for="depositCapture"/></h1> <stripes:label for="maint"/>
	    </h1>
	</c:if>

	<div id="detail">
	    <table colspan='2' width='100%' align='center' border='1'>
		<c:if test='${captureSelector.accessFlag!="inq"}'>
		    <tr bgcolor='bluegreen'>
			<th class="header" colspan="2">
			    <font size=3 color=blue>
				<stripes:label for="modify"/> 
				<stripes:label for="remote"/> 
				<stripes:label for="depositCapture"/>
			    </font>
			</th>
		    </tr>
		</c:if>
		<c:if test='${chexSelector.accessFlag=="inq"}'>
		    <tr bgcolor='bluegreen'>
			<th class='header' colspan=2>
			    <font size=3 color=blue>
				<stripes:label for="remote"/> 
				<stripes:label for="depositCapture"/> 
				<stripes:label for="details"/> 
			    </font>
			</th>
		    </tr>
		</c:if>
		<tr>
		    <th align='right' bgcolor='lightyellow' width='35%' height='19'>
			<stripes:label for="processDate"/></th>
		    <td align='left' height='19' width='15%'>
			<stripes:text name="captureDetail.procDate" value="${captureDetail.procDate}" 
				size='10' readonly='true'/>
		    </td>
		</tr>
		<tr>
		    <th align='right' bgcolor='lightyellow' width='35%' height='19'>
			<stripes:label for="issueDate"/></th>
		    <td align='left' height='19' width='15%'>
			<stripes:text name="captureDetail.chackDate" value="${captureDetail.checkDate}" 
				size='10'/>
		    </td>
		</tr>
		<tr>
		    <th align='right' bgcolor='lightyellow' width='35%' height='19'>
			<stripes:label for="amount"/></th>
		    <td align='left' height='19' width='15%'>
			<stripes:text name="captureDetail.checkAmt" value="${captureDetail.checkAmt}" 
				size='10'/>
		    </td>
		</tr>
		<tr>
		    <th align='right' bgcolor='lightyellow' width='35%' height='19'>
			<stripes:label for="number"/></th>
		    <td align='left' height='19' width='15%'>
			<stripes:text name="captureDetail.checkNum" value="${captureDetail.checkNum}" 
				size='15'/>
		    </td>
		</tr>
		<tr>
		    <th align='right' bgcolor='lightyellow' width='35%' height='19'>
			<stripes:label for="micr"/></th>
		    <td align='left' height='19' width='15%'>
			<stripes:text name="captureDetail.checkMICR" value="${captureDetail.checkMICR}" 
				size='15'/>
		    </td>
		</tr>
		<tr>
		    <th align='right' bgcolor='lightyellow' width='35%' height='19'>
			<stripes:label for="payee"/></th>
		    <td align='left' height='19' width='15%'>
			<stripes:text name="captureDetail.checkPayee" value="${captureDetail.checkPayee}" 
				size='15'/>
		    </td>
		</tr>
		<tr>
		    <th align='right' bgcolor='lightyellow' width='35%' height='19'>
			<stripes:label for="payeeacct"/></th>
		    <td align='left' height='19' width='15%'>
			<stripes:text name="captureDetail.checkPayeeAct" value="${captureDetail.checkPayeeAct}" 
				size='15'/>
		    </td>
		</tr>
		<tr>
		    <th align='right' bgcolor='lightyellow' width='35%' height='19'>
			<stripes:label for="status"/></th>
		    <td align='left' height='19' width='15%'>
			<stripes:text name="captureDetail.captureStatus"
			    value="${captureDetail.captureStatus}" size='15'/>
		    </td>
		</tr>
	    </table>
	</div>
	<br/>
	<center>
	    <a href="javascript:big('<c:out value="/imagedir/capture/${captureDetail.imageId}"/>')" onmouseout="big_hide()">
			<img border="0" src='<c:out value="/imagedir/capture/${captureDetail.imageId}"/>' width="100%"></a>
	</center>
<%--													--%>
<%--	This is the buttons area to click on to take the next step.					--%>
<%--	Any errors will appear between the data table and the buttons.					--%>
<%--													--%>
<%--
<c:out value="${ChexDetail.chex_image_f}"/>
<c:out value="${ChexDetail.chex_image_b}"/>
<c:out value="${chexSelector.actionFlag}"/>
--%>
	<center>
	    <stripes:errors />
	    <c:if test='${captureSelector.accessFlag!="inq"}'>
		<c:if test='${captureSelector.actionFlag=="Modify"}'>
		    <stripes:submit name="Update" value="Update"/>
		</c:if>
	    </c:if>
	    <c:if test='${captureSelector.actionFlag=="Update"}'>
		<stripes:submit name="Confirm" value="Confirm Update Check"/>
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
