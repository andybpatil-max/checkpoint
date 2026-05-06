<%@ include file="taglibs.jsp" %>
 <stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/sysadmin/actions/Control.action">
<%--
	*********************************************************************************************
	*********************************************************************************************
	*********************************************************************************************
--%>
	<h1>
	    <stripes:label for="control"/> <stripes:label for="table"/> <stripes:label for="management"/>
	</h1>

	<c:if test='${controlSelector.actionFlag==""}' >
	<div id="selres">
	<table colspan='7' width='100%' align='center' border='1' height='39'>
	    <tr align=center>
		<th class="header" align=center height=19 colspan=7>
		    <font size="3">
		    	<stripes:label for="control"/> <stripes:label for="table"/> 
			<stripes:label for="maintInq"/> 
		    </font>
		</th>
	    </tr>
	    <tr>
		<th align='center' height="19" colspan="1">
		    <font size="2"><stripes:label for="productId"/></font>
		</th>
		<th>
		    <font size="2"><stripes:label for="bankId"/></font>
		</th>
		<th align=center width='5%' height=15 colspan=1>
		    <font size=2>Our <stripes:label for="aba"/></font>
		</th>
		<th align=center width='5%' height=15 colspan=1>
		    <font size=2>Default <stripes:label for="currency"/></font>
		</th>
		<th align=center width='5%' height=15 colspan=1>
		    <font size=2><stripes:label for="prevBizDate"/></font>
		</th>
		<th align=center width='5%' height=15 colspan=1>
		    <font size=2>
			<stripes:label for="application"/> <stripes:label for="date"/>
		    </font>
		</th>
		<th align=center width='5%' height=15 colspan=1>
		    <font size=2><stripes:label for="nextBizDate"/></font>
		</th>
	    </tr>
	    <tr>
		<th align=center width='5%' height=15>
		    <font size=2>
			<stripes:label for="eod"/> <stripes:label for="flag"/>
		    </font>
		</th>
		<th align=center width='5%' height=15>
		    <font size=2>
			<stripes:label for="eod"/> <stripes:label for="operator"/>
		    </font>
		</th>
		<th align=center width='5%' height=15>
		    <font size=2>
			<stripes:label for="eod"/> <stripes:label for="time"/>
		    </font>
		</th>
		<th align=center width='5%' height=15>
		    <font size=2>
			<stripes:label for="bod"/> <stripes:label for="flag"/>
		    </font>
		</th>
		<th align=center width='5%' height=15>
		    <font size=2>
			<stripes:label for="bod"/> <stripes:label for="operator"/>
		    </font>
		</th>
		<th align=center width='5%' height=15>
		    <font size=2>
			<stripes:label for="bod"/> <stripes:label for="time"/>
		    </font>
		 </th>
		<th>
		    <font size=2><stripes:label for="action"/></font>
		</th>
	    </tr>
	<c:forEach items="${controlSelector.controlRows}" var="controlDetail">
	    <tr>
<%--
		<td align='left' width='5%' height='19'>
		    <c:out value="${controlDetail.productId}"/>
		</td>
--%>
		<td align='left' width='5%' height='19'>
		    <c:out value="${controlDetail.prodDesc}"/>
		</td>
		<td align='left' width='5%' height='19'>
		     <c:out value="${controlDetail.bankId}"/>
		</td>
		<td align='left' width='5%' height='19'>
		    <c:out value="${controlDetail.ourAba}"/>
		</td>
		<td align='left' width='5%' height='19'>
		    <c:out value="${controlDetail.defCurr}"/>
		</td>
		<td align='left' width='5%' height='19'>
		    <c:out value="${controlDetail.prevBizDate}"/>
		</td>
		<td align='left' width='5%' height='19'>
		    <c:out value="${controlDetail.applDate}"/>
		</td>
		<td align='left' width='5%' height='19'>
		    <c:out value="${controlDetail.nextBizDate}"/>
		</td>
	    </tr>
	    <tr>
		<td align='left' width='5%' height='19'>
		    <c:out value="${controlDetail.eodFlag}"/>
		</td>
		<td align='left' width='5%' height='19'>
		    <c:out value="${controlDetail.eodOper}"/>
		</td>
		<td align='left' width='5%' height='19'>
		    <c:out value="${controlDetail.eodTime}"/>
		</td>
		<td align='left' width='5%' height='19'>
		    <c:out value="${controlDetail.bodFlag}"/>
		</td>
		<td align='left' width='5%' height='19'>
		    <c:out value="${controlDetail.bodOper}"/>
		</td>
		<td align='left' width='5%' height='19'>
		    <c:out value="${controlDetail.bodTime}"/>
		</td>
		<c:if test='${controlSelector.accessFlag!="inq"}'>
		    <td align="center" width='5%' height='19'>
			<stripes:link href="/econtroller/sysadmin/actions/Control.action" event="Modify">
			    Modify
			    <stripes:param name="prodId" value="${controlDetail.productId}"/>
			</stripes:link>
		    </td>
		</c:if>
		<c:if test='${controlSelector.accessFlag=="inq"}'>
		    <td align="center" width='5%' height='19'>
			<stripes:link href="/econtroller/sysadmin/actions/Control.action" event="Modify">
			    Details
			    <stripes:param name="prodId" value="${controlDetail.productId}"/>
			</stripes:link>
		    </td>
		</c:if>
	    </tr>
	</c:forEach>
	</table>
	</div>
	</c:if>

	<c:if test='${controlSelector.actionFlag!=""}' >
	<div id="detail">
	<table colspan='6' width='100%' align='center' border='1' height='39'>
	    <tr>
		<th align=center height="19" colspan="1">
		    <font size="2"><stripes:label for="bankId"/></font>
		</th>
		<c:if test='${controlSelector.accessFlag=="inq"}' >
		    <td align='left' width='5%' height='19'>
			<c:out value="${controlDetail.bankId}"/>
		    </td>
		</c:if>
		<c:if test='${controlSelector.accessFlag!="inq"}' >
		    <td align='left' width='5%' height='19'>
			<stripes:text name="controlDetail.bankId" value="${controlDetail.bankId}"/>
		    </td>
		</c:if>
	    </tr>
	</table>

	</br>
	<table colspan='6' width='100%' align='center' border='1' height='39'>
	    <tr>
		<th align=center height="19" colspan="1">
		    <font size="2"><stripes:label for="productId"/></font>
		</th>
		<td align='left' width='5%' height='19'>
		    <c:if test='${controlSelector.actionFlag=="New"}' >
			<stripes:text name="controlDetail.productId"
				value="${controlDetail.productId}" size='3'/>
		    </c:if>
		    <c:if test='${controlSelector.actionFlag!="New"}' >
			<c:out value="${controlDetail.productId}"/>
		    </c:if>
		</td>
		<th align=center width='5%' height=15 colspan=1>
		    <font size=2>Our <stripes:label for="aba"/></font>
		</th>
		<td align='left' width='5%' height='19'>
		    <c:out value="${controlDetail.ourAba}"/>
		</td>
		<th align=center width='5%' height=15 colspan=1>
		    <font size=2>Default <stripes:label for="currency"/></font>
		</th>
		<td align='left' width='5%' height='19'>
		    <c:out value="${controlDetail.defCurr}"/>
		</td>
	    </tr>
	    <tr>
		<th align=center width='5%' height=15 colspan=1>
		    <font size=2><stripes:label for="prevBizDate"/></font>
		</th>
		<td align='left' width='5%' height='19'>
		    <c:out value="${controlDetail.prevBizDate}"/>
		</td>
		<th align=center width='5%' height=15 colspan=1>
		    <font size=2>
			<stripes:label for="application"/> <stripes:label for="date"/>
		    </font>
		</th>
		<c:if test='${controlSelector.accessFlag=="inq"}' >
		    <td align='left' width='5%' height='19'>
			<c:out value="${controlDetail.applDate}"/>
		    </td>
		</c:if>
		<c:if test='${controlSelector.accessFlag!="inq"}' >
		    <td align='left' width='5%' height='19'>
			<stripes:text name="controlDetail.applDate" value="${controlDetail.applDate}"/>
		    </td>
		</c:if>
		<th align=center width='5%' height=15 colspan=1>
		    <font size=2><stripes:label for="nextBizDate"/></font>
		</th>
		<td align='left' width='5%' height='19'>
		    <c:out value="${controlDetail.nextBizDate}"/>
		</td>
	    </tr>
	    <tr>
		<th align=center width='5%' height=15>
		    <font size=2>
			<stripes:label for="eod"/> <stripes:label for="flag"/>
		    </font>
		</th>
		<td align='left' width='5%' height='19'>
		    <c:out value="${controlDetail.eodFlag}"/>
		</td>
		<th align=center width='5%' height=15>
		    <font size=2>
			<stripes:label for="eod"/> <stripes:label for="operator"/>
		    </font>
		</th>
		<td align='left' width='5%' height='19'>
		    <c:out value="${controlDetail.eodOper}"/>
		</td>
		<th align=center width='5%' height=15>
		    <font size=2>
			<stripes:label for="eod"/> <stripes:label for="time"/>
		    </font>
		</th>
		<td align='left' width='5%' height='19'>
		    <c:out value="${controlDetail.eodTime}"/>
		</td>
	    </tr>
	    <tr>
		<th align=center width='5%' height=15>
		    <font size=2>
			<stripes:label for="bod"/> <stripes:label for="flag"/>
		    </font>
		</th>
		<td align='left' width='5%' height='19'>
		    <c:out value="${controlDetail.bodFlag}"/>
		</td>
		<th align=center width='5%' height=15>
		    <font size=2>
			<stripes:label for="bod"/> <stripes:label for="operator"/>
		    </font>
		</th>
		<td align='left' width='5%' height='19'>
		    <c:out value="${controlDetail.bodOper}"/>
		</td>
		<th align=center width='5%' height=15>
		    <font size=2>
			<stripes:label for="bod"/> <stripes:label for="time"/>
		    </font>
		 </th>
		<td align='left' width='5%' height='19'>
		    <c:out value="${controlDetail.bodTime}"/>
		</td>
	    </tr>
	</table>


<%--	Here is the variable part which is different for different products	--%>
<%--	*******************************************************************	--%>
<%--	*******************************************************************	--%>

	<c:if test='${controlDetail.productId=="A"}'>
	<table align='center' width='100%'>
	    <tr align='center' colspan="2">
		<th class="header" colspan="2">
		    <stripes:label for="ifFile"/>
		</th>
	    </tr>
	    <tr width='100%' align='center'>
		<th colspan=1>
		    <font size=2>
			<stripes:label for="remote"/> <stripes:label for="dataDir"/>
		    </font>
		</th>
		<td width='80%'>
		    <c:if test='${controlSelector.accessFlag=="inq"}' >
			<c:out value="${controlDetail.remBaseDir}"/>
		    </c:if>
		    <c:if test='${controlSelector.accessFlag!="inq"}' >
			<stripes:text name="controlDetail.remBaseDir" value="${controlDetail.remBaseDir}"
				      size='60'/>
		    </c:if>
		</td>
	    </tr>
	    <tr width='100%' align='center'>
		<th colspan=1>
		    <font size=2>
			<stripes:label for="local"/> <stripes:label for="inDir"/>
		    </font>
		</th>
		<td>
		    <c:if test='${controlSelector.accessFlag=="inq"}' >
			<c:out value="${controlDetail.locInputDir}"/>
		    </c:if>
		    <c:if test='${controlSelector.accessFlag!="inq"}' >
			<stripes:text name="controlDetail.locInputDir" value="${controlDetail.locInputDir}"
				      size='60'/>
		    </c:if>
		</td>
	    </tr>
	    <tr align='center'>
		<th colspan=1>
		    <font size=2>
			<stripes:label for="local"/> <stripes:label for="outDir"/>
		    </font>
		</th>
		<c:if test='${controlSelector.accessFlag=="inq"}' >
		    <td>
			<c:out value="${controlDetail.locOutputDir}"/>
		    </td>
		</c:if>
		<c:if test='${controlSelector.accessFlag!="inq"}' >
		    <td>
			<stripes:text name="controlDetail.locOutputDir" value="${controlDetail.locOutputDir}"
				      size='60'/>
		    </td>
		</c:if>
	    </tr>
	    <tr align='center'>
		<th colspan=1>
		    <font size=2>
			<stripes:label for="local"/> <stripes:label for="imageDir"/>
		    </font>
		</th>
		<c:if test='${controlSelector.accessFlag=="inq"}' >
		    <td>
			<c:out value="${controlDetail.imgBaseDir}"/>
		    </td>
		</c:if>
		<c:if test='${controlSelector.accessFlag!="inq"}' >
		    <td>
			<stripes:text name="controlDetail.imgBaseDir" value="${controlDetail.imgBaseDir}"
				      size='60'/>
		    </td>
		</c:if>
	    </tr>
	    <tr align='center'>
		<th colspan=1>
		    <font size=2>Image <stripes:label for="file"/></font>
		</th>
		<c:if test='${controlSelector.accessFlag=="inq"}' >
		    <td>
			<c:out value="${controlDetail.inclImgFile}"/>
		    </td>
		</c:if>
		<c:if test='${controlSelector.accessFlag!="inq"}' >
		    <td>
			<stripes:text name="controlDetail.inclImgFile" value="${controlDetail.inclImgFile}"
				      size='60'/>
		    </td>
		</c:if>
	    </tr>
	    <tr align='center'>
		<th colspan=1>
		    <font size=2><stripes:label for="inclMicr"/></font>
		</th>
		<c:if test='${controlSelector.accessFlag=="inq"}' >
		    <td>
			<c:out value="${controlDetail.inclMicrFile}"/>
		    </td>
		</c:if>
		<c:if test='${controlSelector.accessFlag!="inq"}' >
		    <td>
			<stripes:text name="controlDetail.inclMicrFile" value="${controlDetail.inclMicrFile}"
				      size='60'/>
		    </td>
		</c:if>
	    </tr>
	    <tr align='center'>
		<th colspan=1>
		    <font size=2><stripes:label for="rdcIcl"/></font>
		</th>
		<c:if test='${controlSelector.accessFlag=="inq"}' >
		    <td>
			<c:out value="${controlDetail.rdcIcl}"/>
		    </td>
		</c:if>
		<c:if test='${controlSelector.accessFlag!="inq"}' >
		    <td>
			<stripes:text name="controlDetail.rdcIcl" value="${controlDetail.rdcIcl}"
				      size='60'/>
		    </td>
		</c:if>
	    </tr>
	    <tr align='center'>
		<th colspan=1>
		    <font size=2><stripes:label for="lboxicl"/></font>
		</th>
		<c:if test='${controlSelector.accessFlag=="inq"}' >
		    <td>
			<c:out value="${controlDetail.lboxIcl}"/>
		    </td>
		</c:if>
		<c:if test='${controlSelector.accessFlag!="inq"}' >
		    <td>
			<stripes:text name="controlDetail.lboxIcl" value="${controlDetail.lboxIcl}"
				      size='60'/>
		    </td>
		</c:if>
	    </tr>
	    <tr align='center'>
		<th colspan=1>
		    <font size=2>CIF <stripes:label for="file"/></font>
		</th>
		<c:if test='${controlSelector.accessFlag=="inq"}' >
		    <td>
			<c:out value="${controlDetail.cifFile}"/>
		    </td>
		</c:if>
		<c:if test='${controlSelector.accessFlag!="inq"}' >
		    <td>
			<stripes:text name="controlDetail.cifFile" value="${controlDetail.cifFile}" size='60'/>
		    </td>
		</c:if>
	    </tr>
	    <tr align='center'>
		<th colspan=1>
		    <font size=2>
			<stripes:label for="posipay"/> SWIFT <stripes:label for="file"/>
		    </font>
		</th>
		<c:if test='${controlSelector.accessFlag=="inq"}' >
		    <td>
			<c:out value="${controlDetail.posiSwfFile}"/>
		    </td>
		</c:if>
		<c:if test='${controlSelector.accessFlag!="inq"}' >
		    <td>
			<stripes:text name="controlDetail.posiSwfFile" value="${controlDetail.posiSwfFile}"
				      size='60'/>
		    </td>
		</c:if>
	    </tr>
	    <tr align='center'>
		<th colspan=1>
		    <font size=2>
<%--			<stripes:label for="posiPay"/> CSV <stripes:label for="file"/> --%>
			<stripes:label for="posipay"/> CSV <stripes:label for="file"/>
		    </font>
		</th>
		<c:if test='${controlSelector.accessFlag=="inq"}' >
		    <td>
			<c:out value="${controlDetail.posiCsvFile}"/>
		    </td>
		</c:if>
		<c:if test='${controlSelector.accessFlag!="inq"}' >
		    <td>
			<stripes:text name="controlDetail.posiCsvFile" value="${controlDetail.posiCsvFile}"
				      size='60'/>
		    </td>
		</c:if>
	    </tr>
	    <tr align='center'>
		<th colspan=1>
		    <font size=2>
			<stripes:label for="stopPay"/> CSV <stripes:label for="file"/>
		    </font>
		</th>
		<c:if test='${controlSelector.accessFlag=="inq"}' >
		    <td>
			<c:out value="${controlDetail.stopCsvFile}"/>
		    </td>
		</c:if>
		<c:if test='${controlSelector.accessFlag!="inq"}' >
		    <td>
			<stripes:text name="controlDetail.stopCsvFile" value="${controlDetail.stopCsvFile}"
				      size='60'/>
		    </td>
		</c:if>
	    </tr>
	    <tr align='center'>
		<th colspan=1>
		    <font size=2><stripes:label for="reportHeader"/></font>
		</th>
		<c:if test='${controlSelector.accessFlag=="inq"}' >
		    <td>
			<c:out value="${controlDetail.sysRepHeader}"/>
		    </td>
		</c:if>
		<c:if test='${controlSelector.accessFlag!="inq"}' >
		    <td>
			<stripes:text name="controlDetail.sysRepHeader" value="${controlDetail.sysRepHeader}"
				      size='60'/>
		    </td>
		</c:if>
	    </tr>
	    <tr align='center'>
		<th colspan=1>
		    <font size=2><stripes:label for="statement"/> Printer</font>
		</th>
		<td>
		    <c:if test='${controlSelector.accessFlag=="inq"}' >
			<c:out value="${controlDetail.sysStmtPrinter}"/>
		    </c:if>
		    <c:if test='${controlSelector.accessFlag!="inq"}' >
			<stripes:text name="controlDetail.sysStmtPrinter" value="${controlDetail.sysStmtPrinter}"
				       size='60'/>
		    </c:if>
		</td>
	    </tr>
<%--
	    <tr align='center'>
		<th colspan=1>
		    <font size=2><stripes:label for="mandate"/> XML</font>
		</th>
		<td>
		    <c:if test='${controlSelector.accessFlag=="inq"}' >
			<c:out value="${controlDetail.mandateXML}"/>
		    </c:if>
		    <c:if test='${controlSelector.accessFlag!="inq"}' >
			<stripes:text name="controlDetail.mandateXML" value="${controlDetail.mandateXML}"
				       size='60'/>
		    </c:if>
		</td>
	    </tr>
--%>
	    <tr align='center'>
		<th colspan=1>
		    <font size=2><stripes:label for="payer"/> <stripes:label for="data"/>
		    	  	 <stripes:label for="file"/></font>
		</th>
		<td>
		    <c:if test='${controlSelector.accessFlag=="inq"}' >
			<c:out value="${controlDetail.payerData}"/>
		    </c:if>
		    <c:if test='${controlSelector.accessFlag!="inq"}' >
			<stripes:text name="controlDetail.payerData" value="${controlDetail.payerData}"
				       size='60'/>
		    </c:if>
		</td>
	    </tr>
	</table>

	<table  align='center' width='50%'>
	    <tr align='center'>
	    	<c:if test='${controlSelector.accessFlag!="inq"}' >
		    <td>Undefined</td>
		    <td>
			<stripes:textarea cols="40" rows="4" name="controlDetail.unknownFields"
				value="${controlDetail.unknownFields}"/>
		    </td>
		</c:if>
	    </tr>
	</table>
	</c:if>

<%-- ******************************************************************************** --%>
<%-- ******************************************************************************** --%>

	<c:if test='${controlDetail.productId=="C"}'>
	<table align='center' width='50%'>
	    <tr align='center' colspan="2">
		<th class="header" colspan="2">
		    <stripes:label for="fileLoaded"/>
		</th>
	    </tr>
	    <tr width='100%' align='center'>
		<th colspan=1>
		    <font size=2><stripes:label for="fileLoaded"/></font>
		</th>
		<td width='80%'>
		    <c:if test='${controlSelector.accessFlag=="inq"}' >
			<c:out value="${controlDetail.file_loaded}"/>
		    </c:if>
		    <c:if test='${controlSelector.accessFlag!="inq"}' >
			<stripes:text name="controlDetail.file_loaded" value="${controlDetail.file_loaded}"
				      size='60'/>
		    </c:if>
		</td>
	    </tr>
	    <tr width='100%' align='center'>
		<th colspan=1>
		    <font size=2><stripes:label for="fileLoaded"/> 1</font>
		</th>
		<td width='80%'>
		    <c:if test='${controlSelector.accessFlag=="inq"}' >
			<c:out value="${controlDetail.file_loaded_1}"/>
		    </c:if>
		    <c:if test='${controlSelector.accessFlag!="inq"}' >
			<stripes:text name="controlDetail.file_loaded_1"
				value="${controlDetail.file_loaded_1}" size='60'/>
		    </c:if>
		</td>
	    </tr>
	    <tr width='100%' align='center'>
		<th colspan=1>
		    <font size=2><stripes:label for="fileLoaded"/> 2</font>
		</th>
		<td width='80%'>
		    <c:if test='${controlSelector.accessFlag=="inq"}' >
			<c:out value="${controlDetail.file_loaded_2}"/>
		    </c:if>
		    <c:if test='${controlSelector.accessFlag!="inq"}' >
			<stripes:text name="controlDetail.file_loaded_2"
				value="${controlDetail.file_loaded_2}" size='60'/>
		    </c:if>
		</td>
	    </tr>
	    <tr width='100%' align='center'>
		<th colspan=1>
		    <font size=2><stripes:label for="fileLoaded"/> 3</font>
		</th>
		<td width='80%'>
		    <c:if test='${controlSelector.accessFlag=="inq"}' >
			<c:out value="${controlDetail.file_loaded_3}"/>
		    </c:if>
		    <c:if test='${controlSelector.accessFlag!="inq"}' >
			<stripes:text name="controlDetail.file_loaded_3"
				value="${controlDetail.file_loaded_3}" size='60'/>
		    </c:if>
		</td>
	    </tr>
	    <tr width='100%' align='center'>
		<th colspan=1>
		    <font size=2><stripes:label for="fileLoaded"/> 4</font>
		</th>
		<td width='80%'>
		    <c:if test='${controlSelector.accessFlag=="inq"}' >
			<c:out value="${controlDetail.file_loaded_4}"/>
		    </c:if>
		    <c:if test='${controlSelector.accessFlag!="inq"}' >
			<stripes:text name="controlDetail.file_loaded_4"
				value="${controlDetail.file_loaded_4}" size='60'/>
		    </c:if>
		</td>
	    </tr>
	    <tr width='100%' align='center'>
		<th colspan=1>
		    <font size=2><stripes:label for="fileLoaded"/> 5</font>
		</th>
		<td width='80%'>
		    <c:if test='${controlSelector.accessFlag=="inq"}' >
			<c:out value="${controlDetail.file_loaded_5}"/>
		    </c:if>
		    <c:if test='${controlSelector.accessFlag!="inq"}' >
			<stripes:text name="controlDetail.file_loaded_5"
				value="${controlDetail.file_loaded_5}" size='60'/>
		    </c:if>
		</td>
	    </tr>
	    <tr align='center'>
	    	<c:if test='${controlSelector.accessFlag!="inq"}' >
		    <td>Undefined</td>
		    <td>
			<stripes:textarea cols="40" rows="4" name="controlDetail.unknownFields"
				value="${controlDetail.unknownFields}"/>
		    </td>
		</c:if>
	    </tr>
	</table>
	</c:if>

<%-- ******************************************************************************** --%>
<%-- ******************************************************************************** --%>

	<c:if test='${controlDetail.productId=="D"}'>
	<table align='center' width='100%'>
	    <tr align='center' colspan="2">
		<th class="header" colspan="2">
		    <stripes:label for="sodOSN"/>
		</th>
	    </tr>
	    <tr width='100%' align='center'>
		<th colspan=1>
		    <font size=2><stripes:label for="osn"/></font>
		</th>
		<td width='80%'>
		    <c:if test='${controlSelector.accessFlag=="inq"}' >
			<c:out value="${controlDetail.depOSN}"/>
		    </c:if>
		    <c:if test='${controlSelector.accessFlag!="inq"}' >
			<stripes:text name="controlDetail.depOSN" value="${controlDetail.depOSN}" size='10'/>
		    </c:if>
		</td>
	    </tr>
	    <tr width='100%' align='center'>
		<th colspan=1>
		    <font size=2><stripes:label for="sodOSN"/></font>
		</th>
		<td width='80%'>
		    <c:if test='${controlSelector.accessFlag=="inq"}' >
			<c:out value="${controlDetail.sodDepOSN}"/>
		    </c:if>
		    <c:if test='${controlSelector.accessFlag!="inq"}' >
			<stripes:text name="controlDetail.sodDepOSN" value="${controlDetail.sodDepOSN}" size='10'/>
		    </c:if>
		</td>
	    </tr>
	    <tr width='100%' align='center'>
		<th colspan=1>
		    <font size=2><stripes:label for="fileLoaded"/></font>
		</th>
		<td width='80%'>
		    <c:if test='${controlSelector.accessFlag=="inq"}' >
			<c:out value="${controlDetail.file_loaded}"/>
		    </c:if>
		    <c:if test='${controlSelector.accessFlag!="inq"}' >
			<stripes:text name="controlDetail.file_loaded" value="${controlDetail.file_loaded}" size='60'/>
		    </c:if>
		</td>
	    </tr>
	    <tr width='100%' align='center'>
		<th colspan=1>
		    <font size=2><stripes:label for="fileLoaded"/> 1</font>
		</th>
		<td width='80%'>
		    <c:if test='${controlSelector.accessFlag=="inq"}' >
			<c:out value="${controlDetail.file_loaded_1}"/>
		    </c:if>
		    <c:if test='${controlSelector.accessFlag!="inq"}' >
			<stripes:text name="controlDetail.file_loaded_1" value="${controlDetail.file_loaded_1}" size='60'/>
		    </c:if>
		</td>
	    </tr>
	    <tr width='100%' align='center'>
		<th colspan=1>
		    <font size=2><stripes:label for="fileLoaded"/> 2</font>
		</th>
		<td width='80%'>
		    <c:if test='${controlSelector.accessFlag=="inq"}' >
			<c:out value="${controlDetail.file_loaded_2}"/>
		    </c:if>
		    <c:if test='${controlSelector.accessFlag!="inq"}' >
			<stripes:text name="controlDetail.file_loaded_2" value="${controlDetail.file_loaded_2}" size='60'/>
		    </c:if>
		</td>
	    </tr>
	    <tr width='100%' align='center'>
		<th colspan=1>
		    <font size=2><stripes:label for="fileLoaded"/> 3</font>
		</th>
		<td width='80%'>
		    <c:if test='${controlSelector.accessFlag=="inq"}' >
			<c:out value="${controlDetail.file_loaded_3}"/>
		    </c:if>
		    <c:if test='${controlSelector.accessFlag!="inq"}' >
			<stripes:text name="controlDetail.file_loaded_3" value="${controlDetail.file_loaded_3}" size='60'/>
		    </c:if>
		</td>
	    </tr>
	    <tr width='100%' align='center'>
		<th colspan=1>
		    <font size=2><stripes:label for="fileLoaded"/> 4</font>
		</th>
		<td width='80%'>
		    <c:if test='${controlSelector.accessFlag=="inq"}' >
			<c:out value="${controlDetail.file_loaded_4}"/>
		    </c:if>
		    <c:if test='${controlSelector.accessFlag!="inq"}' >
			<stripes:text name="controlDetail.file_loaded_4"
				      value="${controlDetail.file_loaded_4}" size='60'/>
		    </c:if>
		</td>
	    </tr>
	    <tr width='100%' align='center'>
		<th colspan=1>
		    <font size=2><stripes:label for="fileLoaded"/> 5</font>
		</th>
		<td width='80%'>
		    <c:if test='${controlSelector.accessFlag=="inq"}' >
			<c:out value="${controlDetail.file_loaded_5}"/>
		    </c:if>
		    <c:if test='${controlSelector.accessFlag!="inq"}' >
			<stripes:text name="controlDetail.file_loaded_5"
				      value="${controlDetail.file_loaded_5}" size='60'/>
		    </c:if>
		</td>
	    </tr>
	</table>
	<table align='center' width='50%'>
	    <tr align='center'>
	    	<c:if test='${controlSelector.accessFlag!="inq"}' >
		    <td>Undefined</td>
		    <td>
			<stripes:textarea cols="40" rows="4" name="controlDetail.unknownFields"
					  value="${controlDetail.unknownFields}"/>
		    </td>
		</c:if>
	    </tr>
	</table>
	</c:if>

<%-- ******************************************************************************** --%>
<%-- ******************************************************************************** --%>

	<c:if test='${controlDetail.productId=="E"}'>
	<table align='center' width='100%'>
	    <tr align='center' colspan="2">
		<th class="header" colspan="2">
		    <stripes:label for="sodOSN"/>
		</th>
	    </tr>
	    <tr width='100%' align='center'>
		<th colspan=1>
		    <font size=2><stripes:label for="osn"/></font>
		</th>
		<td width='80%'>
		    <c:if test='${controlSelector.accessFlag=="inq"}' >
			<c:out value="${controlDetail.depOSN}"/>
		    </c:if>
		    <c:if test='${controlSelector.accessFlag!="inq"}' >
			<stripes:text name="controlDetail.depOSN" value="${controlDetail.depOSN}" size='10'/>
		    </c:if>
		</td>
	    </tr>
	    <tr width='100%' align='center'>
		<th colspan=1>
		    <font size=2><stripes:label for="sodOSN"/></font>
		</th>
		<td width='80%'>
		    <c:if test='${controlSelector.accessFlag=="inq"}' >
			<c:out value="${controlDetail.sodDepOSN}"/>
		    </c:if>
		    <c:if test='${controlSelector.accessFlag!="inq"}' >
			<stripes:text name="controlDetail.sodDepOSN" value="${controlDetail.sodDepOSN}" size='10'/>
		    </c:if>
		</td>
	    </tr>
	    <tr width='100%' align='center'>
		<th colspan=1>
		    <font size=2><stripes:label for="fileLoaded"/></font>
		</th>
		<td width='80%'>
		    <c:if test='${controlSelector.accessFlag=="inq"}' >
			<c:out value="${controlDetail.file_loaded}"/>
		    </c:if>
		    <c:if test='${controlSelector.accessFlag!="inq"}' >
			<stripes:text name="controlDetail.file_loaded" value="${controlDetail.file_loaded}" size='60'/>
		    </c:if>
		</td>
	    </tr>
	    <tr width='100%' align='center'>
		<th colspan=1>
		    <font size=2><stripes:label for="fileLoaded"/> 1</font>
		</th>
		<td width='80%'>
		    <c:if test='${controlSelector.accessFlag=="inq"}' >
			<c:out value="${controlDetail.file_loaded_1}"/>
		    </c:if>
		    <c:if test='${controlSelector.accessFlag!="inq"}' >
			<stripes:text name="controlDetail.file_loaded_1" value="${controlDetail.file_loaded_1}" size='60'/>
		    </c:if>
		</td>
	    </tr>
	    <tr width='100%' align='center'>
		<th colspan=1>
		    <font size=2><stripes:label for="fileLoaded"/> 2</font>
		</th>
		<td width='80%'>
		    <c:if test='${controlSelector.accessFlag=="inq"}' >
			<c:out value="${controlDetail.file_loaded_2}"/>
		    </c:if>
		    <c:if test='${controlSelector.accessFlag!="inq"}' >
			<stripes:text name="controlDetail.file_loaded_2" value="${controlDetail.file_loaded_2}" size='60'/>
		    </c:if>
		</td>
	    </tr>
	    <tr width='100%' align='center'>
		<th colspan=1>
		    <font size=2><stripes:label for="fileLoaded"/> 3</font>
		</th>
		<td width='80%'>
		    <c:if test='${controlSelector.accessFlag=="inq"}' >
			<c:out value="${controlDetail.file_loaded_3}"/>
		    </c:if>
		    <c:if test='${controlSelector.accessFlag!="inq"}' >
			<stripes:text name="controlDetail.file_loaded_3" value="${controlDetail.file_loaded_3}" size='60'/>
		    </c:if>
		</td>
	    </tr>
	    <tr width='100%' align='center'>
		<th colspan=1>
		    <font size=2><stripes:label for="fileLoaded"/> 4</font>
		</th>
		<td width='80%'>
		    <c:if test='${controlSelector.accessFlag=="inq"}' >
			<c:out value="${controlDetail.file_loaded_4}"/>
		    </c:if>
		    <c:if test='${controlSelector.accessFlag!="inq"}' >
			<stripes:text name="controlDetail.file_loaded_4"
				      value="${controlDetail.file_loaded_4}" size='60'/>
		    </c:if>
		</td>
	    </tr>
	    <tr width='100%' align='center'>
		<th colspan=1>
		    <font size=2><stripes:label for="fileLoaded"/> 5</font>
		</th>
		<td width='80%'>
		    <c:if test='${controlSelector.accessFlag=="inq"}' >
			<c:out value="${controlDetail.file_loaded_5}"/>
		    </c:if>
		    <c:if test='${controlSelector.accessFlag!="inq"}' >
			<stripes:text name="controlDetail.file_loaded_5"
				      value="${controlDetail.file_loaded_5}" size='60'/>
		    </c:if>
		</td>
	    </tr>
	</table>
	<table align='center' width='50%'>
	    <tr align='center'>
	    	<c:if test='${controlSelector.accessFlag!="inq"}' >
		    <td>Undefined</td>
		    <td>
			<stripes:textarea cols="40" rows="4" name="controlDetail.unknownFields"
					  value="${controlDetail.unknownFields}"/>
		    </td>
		</c:if>
	    </tr>
	</table>
	</c:if>

	</div>
	</c:if>

<%--													--%>
<%--	This is the buttons area to click on to take the next step.					--%>
<%--	Any errors will appear between the data table and the buttons.					--%>
<%--													--%>

     <center>
	<stripes:errors />
<%--
	<c:out value="${controlSelector.actionFlag}"/>
	<c:out value="${controlSelector.actionFlag}"/>
--%>
	<c:if test='${controlSelector.actionFlag==""}' >
		<stripes:submit name="New" value="New Control"/>
	</c:if>
	<c:if test='${controlSelector.accessFlag!="inq"}'>
<%--
	    <c:if test='${controlSelector.actionFlag=="Modify"}'>	
		<stripes:submit name="NewFields" value="Add More Fields"/>
	    </c:if>
--%>
	    <c:if test='${controlSelector.actionFlag=="Modify"}'>	
		<stripes:submit name="Update" value="Update Control"/>
	    </c:if>
	    <c:if test='${controlSelector.actionFlag=="Update"}'>	
		<stripes:submit name="Confirm" value="Confirm Update Control"/>
	    </c:if>
	    <c:if test='${controlSelector.actionFlag=="New"}'>	
		<stripes:submit name="NewFields" value="Add More Fields"/>
		<stripes:submit name="Add" value="Add Control"/>
	    </c:if>
	    <c:if test='${controlSelector.actionFlag=="Add"}'>
		<stripes:submit name="Confirm" value="Confirm Add Control"/>
	    </c:if>
	</c:if>
    </center>

<%--
	*********************************************************************************************
	*********************************************************************************************
	*********************************************************************************************
--%>
	</stripes:form>
    </stripes:layout-component>
</stripes:layout-render>
