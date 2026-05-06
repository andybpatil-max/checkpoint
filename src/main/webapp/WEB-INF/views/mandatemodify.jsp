<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/mandate/actions/Mandate.action">
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

	<c:if test='${mandateSelector.accessFlag=="inq"}'>
	    <h1><stripes:label for="mandate.inquiry" /></h1>
	</c:if>
	<c:if test='${mandateSelector.accessFlag!="inq"}'>
	    <h1><stripes:label for="mandate.maintenance" /></h1>
	</c:if>


	<div id="detail">
	    <table colspan='6' width='100%' align='center' border='1'>
		<tr>
		    <c:if test='${mandateSelector.accessFlag!="inq"}'>
			<th class="header" colspan="6">
			    <font size=3 color=blue><stripes:label for="mandate.modify" /></font>
			</th>
		    </c:if>
		    <c:if test='${mandateSelector.accessFlag=="inq"}'>
			<th class='header' colspan=6>
			    <font size=3 color=blue><stripes:label for="mandate.details" /></font>
			</th>
		    </c:if>
		</tr>
		<tr>
		    <th align='right' width='35%' height='19'>
			<stripes:label for="mandate.processDate"/></th>
		    <td align='left' height='19' width='15%'>
			<stripes:text name="mandateDetail.procDate" value="${mandateDetail.procDate}" 
				size='10' readonly='true'/>
		    </td>
		    <th align='right' width='25%' height='19'><b>
			<stripes:label for="mandate.status"/></b></th>
		    <td align='left' width='20%' height='19'>
			    <stripes:text name="mandateDetail.mandateStatus"
				value="${mandateDetail.mandateStatus}" size='15' maxlength='15'/>
		    </td>
		    <th align='right' width='25%' height='19'><b>
			<stripes:label for="mandate.imageid"/></b></th>
		    <td align='left' width='20%' height='19'>
			    <stripes:text name="mandateDetail.imageId"
				value="${mandateDetail.imageId}" size='50' maxlength='80'/>
		    </td>
		</tr>
	    </table>
	</div>
<%----%>
	<div style="background-color: honeydew; border-style:solid;border-width:1px;
	     			     margin-bottom:-40px; border-color:honeydew; width:1015px;">
	    <ul id="horlist">
		<li class="span-5" style="margin-top:12px;">
		    <img border="0" src='<c:out value="${ctx}/config/gifs/npci2.png"/>' width="100%"
		    	 height="33px">
		</li>
	    </ul>
	    <ul id="horlist">
		<li class="span-10">
		    <stripes:label for="mandate.umrn"/>
		    <stripes:text name="mandateDetail.umrn" value="${mandateDetail.umrn}" 
				size='20'  maxlength='20'/>
		</li>
		<li class="span-6">
		    <stripes:label for="mandate.date"/>
		    <stripes:text name="mandateDetail.manDate" value="${mandateDetail.manDate}" 
					size='15' maxlength='15'/>
		</li>
	    </ul>
	    <br/>
	    <br/>
	    <ul id="horlist" >
		<li class="span-10">
		    <stripes:label for="mandate.Sponsor1"/>
		    <stripes:text name="mandateDetail.sponsorBank"
			value="${mandateDetail.sponsorBank}" size='15' maxlength='15'/>
		</li>
		<li class="span-7">
		    <stripes:label for="mandate.utilitycode"/>
		    <stripes:text name="mandateDetail.utilityCode" 
			value="${mandateDetail.utilityCode}" size='20' maxlength='20'/>
		</li>
		<br/>
	    </ul>
	    <ul id="horlist">
		<li class="span-25">
	    	<table style="border-collapse:collapse; margin-bottom:-20px; margin-top:-30px;">
		<tr>
		    <td style="background-color: honeydew; border:none;">
			<stripes:label for="mandate.payee1"/>
			<stripes:text name="mandateDetail.utilityBillerBankCo"
			    value="${mandateDetail.utilityBillerBankCo}" size='50' maxlength='35'/>
		    </td>
		    <td style="border:none; background-color: honeydew;">
			<table>
			    <td align="center" style="background-color: honeydew;">Action</td>
			    <tr>
				<td style="background-color: honeydew;">
				    <stripes:label for="mandate.createon"/>
				    <stripes:checkbox name="mandateDetail.createMandateOnB"
					checked="${mandateDetail.createMandateOnB}" />
				</td>
			    </tr>
			    <tr>
				<td style="background-color: honeydew;">
				    <stripes:label for="mandate.cancel"/>
				    <stripes:checkbox name="mandateDetail.cancelMandateOnB"
					checked="${mandateDetail.cancelMandateOnB}" />
				</td>
			    </tr>
			    <tr>
				<td style="background-color: honeydew;">
				    <stripes:label for="mandate.update"/>
				    <stripes:checkbox name="mandateDetail.updateMandateOnB"
					checked="${mandateDetail.updateMandateOnB}" />
				</td>
			    </tr>
			</table>
		    </td>
		    <td style="border:none; background-color: honeydew;">
			<table id="horlist" colspan="2">
			    <td colspan="2" style="text-align:center; background-color: honeydew;">A/C Type</td>
			    <tr>
				<td style="text-align: right; background-color: honeydew;">
				    <stripes:label for="mandate.actypesavings"/>
				    <stripes:checkbox name="mandateDetail.acTypeSavingsB"
					checked="${mandateDetail.acTypeSavingsB}" />
				</td>
				<td style="text-align: right; background-color: honeydew;">
				    <stripes:label for="mandate.actypecurrent"/>
				    <stripes:checkbox name="mandateDetail.acTypeCurrentB"
					checked="${mandateDetail.acTypeCurrentB}" />
				</td>
			    </tr>
			    <tr>
				<td style="text-align: right; background-color: honeydew;">
				    <stripes:label for="mandate.actypecc"/>
				    <stripes:checkbox name="mandateDetail.acTypeCCB"
					checked="${mandateDetail.acTypeCCB}" />
				</td>
				<td style="text-align: right; background-color: honeydew;">
				    <stripes:label for="mandate.actypeothers"/>
				    <stripes:checkbox name="mandateDetail.acTypeOthersB"
					checked="${mandateDetail.acTypeOthersB}" />
				</td>
			    </tr>
			</table>
		    </td>
		</tr>
		</table>
		</li>
	    </ul>
	    <ul id="horlist" >
		<li class="span-22">
		    <stripes:label for="mandate.legalacct1"/>
		    <stripes:text name="mandateDetail.legalAcNum"
			value="${mandateDetail.legalAcNum}" size='50' maxlength='35'/>
		</li>
	    </ul>
	    <ul id="horlist" >
		<li class="span-8">
		    <stripes:label for="mandate.acctwith1"/>
		    <stripes:text name="mandateDetail.accountWith"
			value="${mandateDetail.accountWith}" size='30' maxlength='40'/>
		</li>
		<li class="span-12">
		    <stripes:label for="mandate.ifscmicr"/>
		    <stripes:text name="mandateDetail.ifscmicr"
			value="${mandateDetail.ifscmicr}" size='10' maxlength='20'/>
		    <stripes:label for="mandate.ifscmicr1"/>
		</li>
	    </ul>
	    <ul id="horlist" >
		<li class="span-10">
		    <stripes:label for="mandate.legalamt1"/>
		    <stripes:text name="mandateDetail.legalAmount"
			value="${mandateDetail.legalAmount}" size='35' maxlength='100'/>
		</li>
		<li class="span-12">
		    <stripes:label for="mandate.courtesyamt"/>
		    <stripes:text name="mandateDetail.courtesyAmount"
			value="${mandateDetail.courtesyAmount}" size='30' maxlength='40'/>
		</li>
	    </ul>
	    <ul id="horlist">
		<li class="span-20">
		    <stripes:label for="mandate.clientrefnum1"/>
		    <stripes:text name="mandateDetail.clientRefNum"
			value="${mandateDetail.clientRefNum}" size='30' maxlength='40'/>
		</li>
	    </ul>
	    <ul id="horlist" style="padding-left:130px">
		<li class="span-18">
		    <stripes:label for="mandate.schemeplanrefnum"/>
		    <stripes:text name="mandateDetail.schemePlanRefNum"
			value="${mandateDetail.schemePlanRefNum}" size='30' maxlength='40'/>
		</li>
	    </ul>
	    <ul id="horlist">
		<li class="span-7">
	    	<table>
		<tr>
			<table>
			    <td align="center" colspan="2" style="border-bottom:1px; border-left:1px; 
			    		       background-color: honeydew;">Frequency</td>
			    <tr>
				<td width="10%" style="border:none; text-align: left; background-color: honeydew;">
				    <stripes:checkbox name="mandateDetail.monthlyB"
					checked="${mandateDetail.monthlyB}" />
				    <stripes:label for="mandate.monthly"/>
				</td>
				<td style="border-bottom:1px; border-left:1px; border-top:1px; text-align: left;
				    			       background-color: honeydew;">
				    <stripes:checkbox name="mandateDetail.halfYearlyB"
					checked="${mandateDetail.halfYearlyB}" />
				    <stripes:label for="mandate.halfyearly"/>
				</td>
			    </tr>
			    <tr>
				<td width="10%" style="border:none; text-align: left; background-color: honeydew;">
				    <stripes:checkbox name="mandateDetail.biMonthlyB"
					checked="${mandateDetail.biMonthlyB}" />
				    <stripes:label for="mandate.bimonthly"/>
				</td>
				<td style="border-bottom:1px; border-left:1px; border-top:1px; text-align: left;
				    			      background-color: honeydew;">
				    <stripes:checkbox name="mandateDetail.yearlyB"
					checked="${mandateDetail.yearlyB}" />
				    <stripes:label for="mandate.yearly"/>
				</td>
			    </tr>
			    <tr>
				<td width="10%" style="border:none; text-align: left; background-color: honeydew;">
				    <stripes:checkbox name="mandateDetail.quarterlyB"
					checked="${mandateDetail.quarterlyB}" />
				    <stripes:label for="mandate.quarterly"/>
				</td>
				<td style="border-bottom:1px; border-left:1px; border-top:1px; text-align: left;
				    			      background-color: honeydew;">
				    <stripes:checkbox name="mandateDetail.adHocB"
					checked="${mandateDetail.adHocB}" />
				    <stripes:label for="mandate.adhoc"/>
				</td>
			    </tr>
			</table>
		</tr>
	        </table>
		</li>
	    </ul>
	    <ul id="horlist">
		<li class="span-161">
	    	<table style="border:1px; margin-bottom:0px; margin-left:-30px;">
		<tr>
		    <td style="border-bottom:1px; background-color: honeydew;"> 
			<table>
			    <td style="border:none; background-color: honeydew;" align="center">Period</td>
			    <tr>
				<td width="10%" style="border:none; text-align:right; background-color: honeydew;">
				    <stripes:label for="mandate.starting"/>
				    <stripes:text name="mandateDetail.fromPeriod"
					value="${mandateDetail.fromPeriod}" size='15' maxlength='15' />
				</td>
			    </tr>
			    <tr>
				<td width="10%" style="border:none; text-align:right; background-color: honeydew;">
				    <stripes:label for="mandate.ending"/>
				    <stripes:text name="mandateDetail.toPeriod"
					value="${mandateDetail.toPeriod}" size='15' maxlength='15'/>
				</td>
			    </tr>
			    <tr>
				<td width="10%" style="border:none; text-align:right; background-color: honeydew;">
				    OR
				    <stripes:checkbox name="mandateDetail.untilCancelledB"
					checked="${mandateDetail.untilCancelledB}" />
				    <stripes:label for="mandate.untilcancelled"/>
				</td>
			    </tr>
			</table>
		    </td>
		    <td style="border:none; padding-left:130px;  background-color: honeydew;">
		    <table>
		    <tr>
			<td style="width:230px; word-wrap:break-word; border:none; padding-top:70px;
			    		padding-bottom:-20px; background-color: honeydew;">
		    	    <small><stripes:label for="mandate.signature2"/></small>
			</td>
		     </tr>
		    </table>
		    </td>
		</tr>
		</table>
		</li>
	    </ul>
	    <ul id="horlist" >
		<li class="stretch"></li>
	    </ul>
	</div>
	<div style="background-color: white; border-width:1px; border-style:solid; 
			margin-bottom:-10px; border-color:white; width:1015px;">
	    <ul id="horlist">
		<li class="span-131" style="background-color: white;">
		    <img border="0" src='<c:out value="${ctx}/config/gifs/footnote.png"/>' width="100%"
		    	 height="55px">
		</li>
		<li class="span-9"  style="background-color: white;">
		    <small><stripes:label for="mandate.clientRef1"/></small>
		    <stripes:text name="mandateDetail.custId"
			value="${mandateDetail.custId}" size='27' maxlength='30'/>
		</li>
	    </ul>
	    <ul id="horlist">
		<li class="span-6" style="background-color: white;">
		    <stripes:label for="mandate.phonenum"/>
		    <stripes:text name="mandateDetail.telephone"
			value="${mandateDetail.telephone}" size='15' maxlength='15'/>
		</li>
		<li class="span-4" style="background-color: white;">
		    <stripes:label for="mandate.mobile"/>
		    <stripes:text name="mandateDetail.mobile"
			value="${mandateDetail.mobile}" size='10' maxlength='15'/>
		</li>
	    </ul>
	    <ul id="horlist" style="float:right; margin-right:100px; background-color:white;">
		<li class="span-8" style="background-color: white;">
		    <stripes:label for="mandate.email"/>
		    <stripes:text name="mandateDetail.email"
			value="${mandateDetail.email}" size='30' maxlength='40'/>
		</li>
	    </ul>
	    <ul id="horlist" >
		<li class="stretch1" style="background-color: white;"></li>
	    </ul>
	</div>
	<br/>
	<center>
	    <a href="javascript:big('<c:out value="/imagedir/mandate/${mandateDetail.imageId}"/>')" onmouseout="big_hide()">
			<img border="0" src='<c:out value="/imagedir/mandate/${mandateDetail.imageId}"/>' width="100%"></a>
	</center>
<%--													--%>
<%--	This is the buttons area to click on to take the next step.					--%>
<%--	Any errors will appear between the data table and the buttons.					--%>
<%--													--%>
<%--
<c:out value="${ChexDetail.chex_image_f}"/>
<c:out value="${ChexDetail.chex_image_b}"/>
<c:out value="${chexSelector.actionFlag}" />
--%>
	<center>
	    <stripes:errors />
	    <c:if test='${mandateSelector.accessFlag!="inq"}'>
		<c:if test='${mandateSelector.actionFlag=="Modify"}'>
		    <stripes:submit name="Update" value="Update" />
		</c:if>
	    </c:if>
	    <c:if test='${mandateSelector.actionFlag=="Update"}'>
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
