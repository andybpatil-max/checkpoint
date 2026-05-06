<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/inclear/actions/Account.action">
<%--
	*************************************************************************************************
	*************************************************************************************************
	*************************************************************************************************
	// account_extra1 used for Monthly average # of checks issued
	// account_extra6 used for balance available same day
--%>
	<script>
	function setField(source, target) {
		//alert("IN setField function!!!");
		//alert(document.getElementById('high1').value);
		//console.log(document.getElementById('high1').value);
		if ((document.getElementById(source).id=='high1') &&
		   (document.getElementById(source).value==99999999.99)) {
		    document.getElementById('day2').value	= 0;
		    document.getElementById('low2').value	= '0.00';
		    document.getElementById('high2').value	= '0.00';
		} else {
		  if (document.getElementById(source).id=='high1') {
		     document.getElementById('low1').value	= .01;
		  }
		}
		if (document.getElementById(source).value=='') {
		    document.getElementById(source).value='0.00';
		    document.getElementById(target).value='0.00';
		}
		if ((document.getElementById(source).value!=99999999.99) &&
		    (document.getElementById(source).value>0.00))
		{
		    document.getElementById(target).value	= +document.getElementById(source).value + .01;
		    if (document.getElementById(source).id=='high1') {
			if (((document.getElementById('high2').value=='0.00') ||
			   (document.getElementById('high2').value== 0)) && 
			   (document.getElementById('high1').value!= 99999999.99))
			{
			    document.getElementById('high2').value	= 99999999.99;
			}
		    }
		    if (document.getElementById(source).id=='high2') {
			if ((document.getElementById('high3').value=='0.00')  ||
			    (document.getElementById('high3').value== 0))
			{
			    document.getElementById('high3').value	= 99999999.99;
			}
		    }
		    if (document.getElementById(source).id=='high3') {
		      if ((document.getElementById('high4').value=='0.00')  ||
			  (document.getElementById('high4').value== 0))
			{
		      	 document.getElementById('high4').value	= 99999999.99;
		      }
		    }
		    if (document.getElementById(source).id=='high4') {
			if ((document.getElementById('high5').value=='0.00')  ||
			    (document.getElementById('high5').value== 0))
			{
			    document.getElementById('high5').value	= 99999999.99;
			}
		    }
		}
		if ((document.getElementById(source).value==99999999.99)) {
		    if (document.getElementById(source).id=='high1') {
			document.getElementById('day2').value	= 0;
			document.getElementById('low2').value	= '0.00';
			document.getElementById('high2').value	= '0.00';
			document.getElementById('day3').value	= 0;
			document.getElementById('low3').value	= '0.00';
			document.getElementById('high3').value	= '0.00';
			document.getElementById('day4').value	= 0;
			document.getElementById('low4').value	= '0.00';
			document.getElementById('high4').value	= '0.00';
			document.getElementById('day5').value	= 0;
			document.getElementById('low5').value	= '0.00';
			document.getElementById('high5').value	= '0.00';
		    }
		    if (document.getElementById(source).id=='high2') {
			document.getElementById('day3').value	= 0;
			document.getElementById('low3').value	= '0.00';
			document.getElementById('high3').value	= '0.00';
			document.getElementById('day4').value	= 0;
			document.getElementById('low4').value	= '0.00';
			document.getElementById('high4').value	= '0.00';
			document.getElementById('day5').value	= 0;
			document.getElementById('low5').value	= '0.00';
			document.getElementById('high5').value	= '0.00';
		    }
		    if (document.getElementById(source).id=='high3') {
			document.getElementById('day4').value	= 0;
			document.getElementById('low4').value	= '0.00';
			document.getElementById('high4').value	= '0.00';
			document.getElementById('day5').value	= 0;
			document.getElementById('low5').value	= '0.00';
			document.getElementById('high5').value	= '0.00';
		    }
		    if (document.getElementById(source).id=='high4') {
			document.getElementById('day5').value	= 0;
			document.getElementById('low5').value	= '0.00';
			document.getElementById('high5').value	= '0.00';
		    }
		}
	}
	</script>

	<c:if test='${acctSelector.accessFlag=="inq"}'>
	    <h1><stripes:label for="account"/> (CIF) <stripes:label for="inquiry"/></h1>
	 </c:if>
	<c:if test='${acctSelector.accessFlag!="inq"}'>
	    <h1><stripes:label for="account"/> (CIF) <stripes:label for="maint"/></h1>
	</c:if>
<%--												--%>
<%--	This is the table of selection fields. Appears first when user is asked to make  	--%>
<%--	the selection of data to view								--%>
<%--												--%>

<div id="detail">
<table colspan='2' width='80%' align='center' border='1'>
	<tr bgcolor='bluegreen'>
	    <c:if test='${acctSelector.accessFlag=="inq"}'>
		<th class="header" colspan="2">
		    <font size=3>
			<stripes:label for="account"/> <stripes:label for="details" />
		    </font>
	   	</th>
	    </c:if>
	    <c:if test='${acctSelector.accessFlag!="inq"}'>
		<c:if test='${acctSelector.actionFlag=="Modify"}'>
		    <th class="header" colspan="2">
			<font size=3>
			    <stripes:label for="modify"/> <stripes:label for="account"/>
			</font>
		    </th>
		</c:if>
		<c:if test='${acctSelector.actionFlag=="Delete"}'>
		    <th class="header" colspan="2">
			<font size=3>
			    <stripes:label for="delete"/> <stripes:label for="account"/>
			</font>
		    </th>
		</c:if>
		<c:if test='${acctSelector.actionFlag=="New"}'>
		    <th class="header" colspan="2">
			<font size=3>
			    <stripes:label for="add"/> <stripes:label for="account"/>
			</font>
		    </th>
		</c:if>
		<c:if test='${acctSelector.actionFlag=="Update"}'>
		    <th class="header" colspan="2">
			<font size=3>
			    <stripes:label for="modify"/> <stripes:label for="account"/>
			</font>
		    </th>
		</c:if>
		<c:if test='${acctSelector.actionFlag=="Add"}'>
		    <th class="header" colspan="2">
			<font size=3>
			    <stripes:label for="add"/> <stripes:label for="account"/>
			</font>
		    </th>
		</c:if>
	    </c:if>
	</tr>

    <center>
	<stripes:errors />
	<c:if test='${acctSelector.actionFlag!="inq"}'>
	    <c:if test='${acctSelector.actionFlag=="Modify"}'>
		<stripes:submit name='Update' value="Update"/>
	    </c:if>
	    <c:if test='${acctSelector.actionFlag=="Update"}'>
		<stripes:submit name='Confirm' value="Confirm Update Account"/>
	    </c:if>
	    <c:if test='${acctSelector.actionFlag=="Add"}'>
		<stripes:submit name='Confirm' value="Confirm Add Account"/>
	    </c:if>
	    <c:if test='${acctSelector.actionFlag=="Delete"}'>
		<stripes:submit name='DeleteConfirm' value="Confirm Delete Account"/>
	    </c:if>
	    <c:if test='${acctSelector.actionFlag=="New"}'>
		<stripes:submit name='Add' value="Add Account"/>
	    </c:if>
	</c:if>
    </center>
    <br/>
	<tr>
	<th align='right' width='45%' height='19'><b>
	    <stripes:label for="account"/> <stripes:label for="number"/>
	</b></th>
	<td align='left' width=10% height='19'>
	    <c:if test='${acctSelector.actionFlag=="New"}'>
		<stripes:text name='acctDetail.account_num' value='${acctDetail.account_num}' size='22' />
	    </c:if>
	    <c:if test='${acctSelector.actionFlag!="New"}'>
		<stripes:text name='acctDetail.account_num' value='${acctDetail.account_num}' size='22'
			      readonly='true' />
	    </c:if>
	</td>
	</tr>

	<tr>
	<th align='right' width='40%' height='19'>
		<stripes:label for="checkAccount"/></th>
	<td align='left' height='15'>
	    <c:choose>
		<c:when test='${acctSelector.actionFlag=="Modify"}'>
		    <stripes:text name='acctDetail.account_gen_suspense' 
			value='${acctDetail.account_gen_suspense}'
		    size='22' />
		</c:when>
		<c:when test='${acctSelector.actionFlag=="New"}'>
		    <stripes:text name='acctDetail.account_gen_suspense' 
			value='${acctDetail.account_gen_suspense}'
		     size='22'/>
		</c:when>
		<c:otherwise>
		    <stripes:text name='acctDetail.account_gen_suspense' 
			value='${acctDetail.account_gen_suspense}' size='22' readonly='true'/>
		</c:otherwise>
	    </c:choose>
	</td>
	</tr>

	<tr>
	<th align='right' width='40%' height='19'>
		<stripes:label for="currency"/></th>
	<td align='left' height='15'>
	    <c:choose>
		<c:when test='${acctSelector.actionFlag=="Modify"}'>
		    <stripes:text name='acctDetail.account_currency' value='${acctDetail.account_currency}'
		    size='3' />
		</c:when>
		<c:when test='${acctSelector.actionFlag=="New"}'>
		    <stripes:text name='acctDetail.account_currency' value='${acctDetail.account_currency}'
		     size='3'/>
		</c:when>
		<c:otherwise>
		   <stripes:text name='acctDetail.account_currency' value='${acctDetail.account_currency}'
		    size='3' readonly='true'/>
		</c:otherwise>
	    </c:choose>
	</td>
	</tr>

	<tr>
	<th align='right' width='40%' height='19'>
	    <stripes:label for="client"/> <stripes:label for="name"/></th>
	<td align='left' height='15'>
	    <c:choose>
		<c:when test='${acctSelector.actionFlag=="Modify"}'>
		    <stripes:text name='acctDetail.account_client_name' 
		        value='${acctDetail.account_client_name}'
			size='35' maxlength='35'/>
		</c:when>
		<c:when test='${acctSelector.actionFlag=="New"}'>
		    <stripes:text name='acctDetail.account_client_name' 
		        value='${acctDetail.account_client_name}'
			size='35' maxlength='35'/>
		</c:when>
		<c:otherwise>
		    <stripes:text name='acctDetail.account_client_name' 
			value='${acctDetail.account_client_name}'
			size='35' readonly='true'/>
		</c:otherwise>
	    </c:choose>
	</td>
	</tr>

	<tr>
	<th align='right' width='40%' height='19'>
	    <stripes:label for="client"/> <stripes:label for="address1"/></th>
	<td align='left' height='15'>
	    <c:choose>
		<c:when test='${acctSelector.actionFlag=="Modify"}'>
		    <stripes:text name='acctDetail.account_client_addr1' 
		    	value='${acctDetail.account_client_addr1}'
			size='35' maxlength='35'/>
		</c:when>
		<c:when test='${acctSelector.actionFlag=="New"}'>
		    <stripes:text name='acctDetail.account_client_addr1' 
		    	value='${acctDetail.account_client_addr1}'
			size='35' maxlength='35'/>
		</c:when>
		<c:otherwise>
		    <stripes:text name='acctDetail.account_client_addr1' 
		    	value='${acctDetail.account_client_addr1}'
			size='35' readonly='true'/>
		</c:otherwise>
	    </c:choose>
	</td>
	</tr>
	<tr>
	<th align='right' width='40%' height='19'>
	    <stripes:label for="client"/> <stripes:label for="address2"/></th>
	<td align='left' height='15'>
	    <c:choose>
	    <c:when test='${acctSelector.actionFlag=="Modify"}'>
		<stripes:text name='acctDetail.account_client_addr2'
			value='${acctDetail.account_client_addr2}'
			size='35' maxlength='35'/>
	    </c:when>
	    <c:when test='${acctSelector.actionFlag=="New"}'>
		<stripes:text name='acctDetail.account_client_addr2'
			value='${acctDetail.account_client_addr2}'
			size='35' maxlength='35'/>
	    </c:when>
	    <c:otherwise>
		<stripes:text name='acctDetail.account_client_addr2'
			value='${acctDetail.account_client_addr2}'
			size='35' readonly='true'/>
	    </c:otherwise>
	    </c:choose>
	</td>
	</tr>

	<tr>
	<th align='right' width='40%' height='19'>
	    <stripes:label for="client"/> <stripes:label for="address3"/></th>
	<td align='left' height='15'>
	    <c:choose>
	    <c:when test='${acctSelector.actionFlag=="Modify"}'>
		<stripes:text name='acctDetail.account_client_addr3'
			value='${acctDetail.account_client_addr3}'
			size='35' maxlength='35'/>
	    </c:when>
	    <c:when test='${acctSelector.actionFlag=="New"}'>
		<stripes:text name='acctDetail.account_client_addr3'
			value='${acctDetail.account_client_addr3}'
			size='35' maxlength='35'/>
	    </c:when>
	    <c:otherwise>
		<stripes:text name='acctDetail.account_client_addr3'
			value='${acctDetail.account_client_addr3}'
			size='35' readonly='true'/>
	    </c:otherwise>
	    </c:choose>
	</td>
	</tr>

	<tr>
	<th align='right' width='40%' height='19'>
	    <stripes:label for="client"/> <stripes:label for="address4"/></th>
	<td align='left' height='15'>
	    <c:choose>
	    <c:when test='${acctSelector.actionFlag=="Modify"}'>
		<stripes:text name='acctDetail.account_client_addr4'
			value='${acctDetail.account_client_addr4}'
			size='35' maxlength='35'/>
	    </c:when>
	    <c:when test='${acctSelector.actionFlag=="New"}'>
		<stripes:text name='acctDetail.account_client_addr4'
			value='${acctDetail.account_client_addr4}'
			size='35' maxlength='35'/>
	    </c:when>
	    <c:otherwise>
		<stripes:text name='acctDetail.account_client_addr4'
			value='${acctDetail.account_client_addr4}'
			size='35' readonly='true'/>
	    </c:otherwise>
	    </c:choose>
	</td>
	</tr>

	<tr>
	<th align='right' width='40%' height='19'>
	    <stripes:label for="swAddr"/></th>
	<td align='left' height='15'>
	    <c:choose>
	    <c:when test='${acctSelector.actionFlag=="Modify"}'>
		<stripes:text name='acctDetail.account_sw_addr'
			value='${acctDetail.account_sw_addr}' size='24'  maxlength='12'/>
	    </c:when>
	    <c:when test='${acctSelector.actionFlag=="New"}'>
		<stripes:text name='acctDetail.account_sw_addr'
			value='${acctDetail.account_sw_addr}' size='24'  maxlength='12'/>
	    </c:when>
	    <c:otherwise>
		<stripes:text name='acctDetail.account_sw_addr'
			value='${acctDetail.account_sw_addr}' size='24' readonly='true'/>
	    </c:otherwise>
	    </c:choose>
	</td>
	</tr>

	<tr>
	<th align='right' width='40%' height='19'>
	    <stripes:label for="account"/> <stripes:label for="manager"/> <stripes:label for="email"/></th>
	<td align='left' height='15'>
	    <c:choose>
	    <c:when test='${acctSelector.actionFlag=="Modify"}'>
		<stripes:text name='acctDetail.account_acc_rep'
			value='${acctDetail.account_acc_rep}' size='26' maxlength='26'/>
	    </c:when>
	    <c:when test='${acctSelector.actionFlag=="New"}'>
		<stripes:text name='acctDetail.account_acc_rep'
			value='${acctDetail.account_acc_rep}' size='26' maxlength='26'/>
	    </c:when>
	    <c:otherwise>
		<stripes:text name='acctDetail.account_acc_rep'
			value='${acctDetail.account_acc_rep}' size='26' readonly='true'/>
	    </c:otherwise>
	    </c:choose>
	</td>
	</tr>

	<tr>
	<th align='right' width='40%' height='19'>
	    <stripes:label for="alt"/> <stripes:label for="manager"/> <stripes:label for="email"/></th>
	<td align='left' height='19'>
	    <c:choose>
	    <c:when test='${acctSelector.actionFlag=="Modify"}'>
		<stripes:text name='acctDetail.account_alt_acc_rep'
			value='${acctDetail.account_alt_acc_rep}'
			size='26' maxlength='26' />
	    </c:when>
	    <c:when test='${acctSelector.actionFlag=="New"}'>
		<stripes:text name='acctDetail.account_alt_acc_rep'
			value='${acctDetail.account_alt_acc_rep}'
			size='26' maxlength='26' />
	    </c:when>
	    <c:otherwise>
		<stripes:text name='acctDetail.account_alt_acc_rep'
			value='${acctDetail.account_alt_acc_rep}'
			size='26' readonly='true'/>
	    </c:otherwise>
	    </c:choose>
	</td>
	</tr>

	<tr>
	<th align='right' width='40%' height='19'>
	    <stripes:label for="date"/> <stripes:label for="opened"/></th>
	<td align='left' height='19'>
	    <c:choose>
	    <c:when test='${acctSelector.actionFlag=="Modify"}'>
		<stripes:text name='acctDetail.account_effective_date'
			value='${acctDetail.account_effective_date}'
			size='10' maxlength='8' />
	    </c:when>
	    <c:when test='${acctSelector.actionFlag=="New"}'>
		<stripes:text name='acctDetail.account_effective_date'
			value='${acctDetail.account_effective_date}'
			size='10' maxlength='8' />
	    </c:when>
	    <c:otherwise>
		<stripes:text name='acctDetail.account_effective_date'
			value='${acctDetail.account_effective_date}'
			size='10' readonly='true'/>
	    </c:otherwise>
	    </c:choose>
	</td>
	</tr>

	<tr>
	<th align='right' width='40%' height='19'>
	    <stripes:label for="date"/> <stripes:label for="closed"/></th>
	<td align='left' height='19'>
	    <c:choose>
	    <c:when test='${acctSelector.actionFlag=="Modify"}'>
		<stripes:text name='acctDetail.account_closed_date'
			value='${acctDetail.account_closed_date}'
			size='10' maxlength='8' />
	    </c:when>
	    <c:when test='${acctSelector.actionFlag=="New"}'>
		<stripes:text name='acctDetail.account_closed_date'
			value='${acctDetail.account_closed_date}'
			size='10' maxlength='8' />
	    </c:when>
	    <c:otherwise>
		<stripes:text name='acctDetail.account_closed_date'
		    value='${acctDetail.account_closed_date}'
		    size='10' readonly='true'/>
	    </c:otherwise>
	    </c:choose>
	</td>
	</tr>

	<tr>
	<th align='right' width='40%' height='19'>
	    <stripes:label for="date"/> <stripes:label for="blocked"/></th>
	<td align='left' height='19'>
	    <c:choose>
	    <c:when test='${acctSelector.actionFlag=="Modify"}'>
		<stripes:text name='acctDetail.account_blocked_date'
			value='${acctDetail.account_blocked_date}'
			size='10' maxlength='8' />
	    </c:when>
	    <c:when test='${acctSelector.actionFlag=="New"}'>
		<stripes:text name='acctDetail.account_blocked_date'
			value='${acctDetail.account_blocked_date}'
			size='10' maxlength='8' />
	    </c:when>
	    <c:otherwise>
		<stripes:text name='acctDetail.account_blocked_date'
			value='${acctDetail.account_blocked_date}'
			size='10' readonly='true'/>
	    </c:otherwise>
	    </c:choose>
	</td>
	</tr>

	<tr>
	<th align='right' width='40%' height='19'>
	    <stripes:label for="max"/> <stripes:label for="check"/> <stripes:label for="amount"/>
	</th>
	<td align='left' height='19'>
	    <c:choose>
	    <c:when test='${acctSelector.actionFlag=="Modify"}'>
		<stripes:text name='acctDetail.account_max_check_amount'
			value='${acctDetail.account_max_check_amount}'
			size='15' />
	    </c:when>
	    <c:when test='${acctSelector.actionFlag=="New"}'>
		<stripes:text name='acctDetail.account_max_check_amount'
			value='${acctDetail.account_max_check_amount}'
			size='15' />
	    </c:when>
 	    <c:otherwise>
		<stripes:text name='acctDetail.account_max_check_amount'
			value='${acctDetail.account_max_check_amount}'
			size='15' readonly='true'/>
	    </c:otherwise>
	    </c:choose>
	</td>
	</tr>

	<tr>
	<th align='right' width='40%' height='19'>
	    <stripes:label for="days"/> <stripes:label for="check"/> <stripes:label for="valid"/>
	</th>
	<td align='left' height='19'>
	    <c:choose>
	    <c:when test='${acctSelector.actionFlag=="Modify"}'>
		<stripes:text name='acctDetail.account_dayscheckvalid'
			value='${acctDetail.account_dayscheckvalid}' size='4' />
	    </c:when>
	    <c:when test='${acctSelector.actionFlag=="New"}'>
		<stripes:text name='acctDetail.account_dayscheckvalid'
			value='${acctDetail.account_dayscheckvalid}' size='4' />
	    </c:when>
 	    <c:otherwise>
		<stripes:text name='acctDetail.account_dayscheckvalid'
			value='${acctDetail.account_dayscheckvalid}' size='4' readonly='true'/>
	    </c:otherwise>
	    </c:choose>
	</td>
	</tr>

	<tr>
	<th align='right' width='40%' height='19'>
	    <stripes:label for="avgnumchexinmon"/>
	</th>
	<td align='left' height='19'>
	    <c:choose>
	    <c:when test='${acctSelector.actionFlag=="Modify"}'>
		<stripes:text name='acctDetail.account_extra1'
			value='${acctDetail.account_extra1}' size='4' />
	    </c:when>
	    <c:when test='${acctSelector.actionFlag=="New"}'>
		<stripes:text name='acctDetail.account_extra1'
			value='${acctDetail.account_extra1}' size='4' />
	    </c:when>
 	    <c:otherwise>
		<stripes:text name='acctDetail.account_extra1'
			value='${acctDetail.account_extra1}' size='4' readonly='true'/>
	    </c:otherwise>
	    </c:choose>
	</td>
	</tr>

	<tr>
	<th align='right' width='40%' height='19'>
	    <stripes:label for="posipay"/> <stripes:label for="flag"/>
	</th>
	<td align='left' height='19'>
	    <c:choose>
	    <c:when test='${acctSelector.actionFlag=="Modify"}'>
	    <select size="1" name="acctDetail.account_posi_pay_flag">
		<c:if test='${acctDetail.account_posi_pay_flag=="Y"}'>
			<option value="N">No</option>
			<option Selected value="Y">Yes</option>
		</c:if>
		<c:if test='${acctDetail.account_posi_pay_flag!="Y"}'>
			<option Selected value="N">No</option>
			<option value="Y">Yes</option>
		</c:if>
	    </select>
	    </c:when>
	    <c:when test='${acctSelector.actionFlag=="New"}'>
	    <select size="1" name="acctDetail.account_posi_pay_flag">
		<c:if test='${acctDetail.account_posi_pay_flag=="Y"}'>
			<option value="N">No</option>
			<option Selected value="Y">Yes</option>
		</c:if>
		<c:if test='${acctDetail.account_posi_pay_flag!="Y"}'>
			<option Selected value="N">No</option>
			<option value="Y">Yes</option>
		</c:if>
	    </select>
	    </c:when>
	    <c:otherwise>
		<stripes:text name='acctDetail.account_posi_pay_flag'
			value='${acctDetail.account_posi_pay_flag}'
			size='2' readonly='true'/>
	    </c:otherwise>
	    </c:choose>
	</td>
	</tr>

	<tr>
	<th align='right' width='40%' height='19'>
	    <stripes:label for="min"/> <stripes:label for="posipay"/> <stripes:label for="amount"/>
	</th>
	<td align='left' height='19'>
	    <c:choose>
	    <c:when test='${acctSelector.actionFlag=="Modify"}'>
		<stripes:text name='acctDetail.account_posi_pay_amt_min'
			value='${acctDetail.account_posi_pay_amt_min}'
			size='15' /></td>
	    </c:when>
	    <c:when test='${acctSelector.actionFlag=="New"}'>
		<stripes:text name='acctDetail.account_posi_pay_amt_min'
			value='${acctDetail.account_posi_pay_amt_min}'
			size='15' /></td>
	    </c:when>
	    <c:otherwise>
		<stripes:text name='acctDetail.account_posi_pay_amt_min'
			value='${acctDetail.account_posi_pay_amt_min}'
			size='15' readonly='true'/>
	    </c:otherwise>
	    </c:choose>
	</td>
	</tr>

	<tr>
	<th align='right' width='40%' height='19'>
	    <stripes:label for="intext"/>
	</th>
	<td align='left' height='19'>
	    <c:choose>
	    <c:when test='${acctSelector.actionFlag=="Modify"}'>
		<select size="1" name="acctDetail.account_int_ext">
		    <c:if test='${acctDetail.account_int_ext=="E"}'>
			<option value="I">Internal</option>
			<option Selected value="E">External</option>
		    </c:if>
		    <c:if test='${acctDetail.account_int_ext=="I"}'>
			<option Selected value="I">Internal</option>
			<option value="E">External</option>
		    </c:if>
<%--
		    <c:if test='${acctDetail.account_int_ext=="C"}'>
			<option value="I">Internal</option>
			<option Selected value="E">External</option>
		    </c:if>
		    <c:if test='${acctDetail.account_int_ext=="D"}'>
			<option Selected value="I">Internal</option>
			<option value="E">External</option>
		    </c:if>
--%>
		    <c:if test='${acctDetail.account_int_ext==("")}'>
			<option Selected value="E">External</option>
			<option value="I">Internal</option>
		    </c:if>
		    <c:if test='${acctDetail.account_int_ext==(" ")}'>
			<option Selected value="E">External</option>
			<option value="I">Internal</option>
		    </c:if>
		</select>
	    </c:when>
	    <c:when test='${acctSelector.actionFlag=="New"}'>
		<select size="1" name="acctDetail.account_int_ext">
			<option Selected value="E">External</option>
			<option value="I">Internal</option>
		</select>
	    </c:when>
	    <c:otherwise>
		<stripes:text name='acctDetail.account_int_ext' value='${acctDetail.account_int_ext}'
			size='2' readonly='true'/>
	    </c:otherwise>
	    </c:choose>
	</td>
	</tr>

<%--
	*************************************************************************************************
	*************************************************************************************************
	*************************************************************************************************
--%>
	<tr>
	<th align='right' width='45%' height='15'>
		<stripes:label for="deposit"/> <stripes:label for="cash"/>
		<stripes:label for="availablesameday"/></th>
	<td align='right' height='19'>
	    <c:choose>
	    	<c:when test='${acctSelector.actionFlag=="Modify"}'>
		    <select size="1" name="acctDetail.account_extra6">
			<c:if test='${acctDetail.account_extra6=="Y"}'>
			    <option value="N">No</option>
			    <option Selected value="Y">Yes</option>
			</c:if>
			<c:if test='${acctDetail.account_extra6!="Y"}'>
			    <option Selected value="N">No</option>
			    <option value="Y">Yes</option>
			</c:if>
		    </select>
		</c:when>
		<c:when test='${acctSelector.actionFlag=="New"}'>
		    <select size="1" name="acctDetail.account_extra6">
			<c:if test='${acctDetail.account_extra6=="Y"}'>
			    <option value="N">No</option>
			    <option Selected value="Y">Yes</option>
			</c:if>
			<c:if test='${acctDetail.account_extra6!="Y"}'>
			    <option Selected value="N">No</option>
			    <option value="Y">Yes</option>
			</c:if>
		    </select>
		</c:when>
		<c:otherwise>
		    <stripes:text name='acctDetail.account_extra6'
			value='${acctDetail.account_extra6}'
			size='2' readonly='true'/>
		</c:otherwise>
	    </c:choose>
	</td>
	</tr>
	<tr>
	<th align='right' width='45%' height='15'>
		<stripes:label for="deposit"/> <stripes:label for="cash"/>
		<stripes:label for="availability"/> <stripes:label for="schedule"/></th>
	<td>
	<table>
	    <tr>
		<th height='15' colspan="2"> </th>
		<th class="header" align='center' height='15' colspan="2">
		    <stripes:label for="amount"/> <stripes:label for="range"/>
		    <stripes:label for="inclusive"/>
		</th>
	    </tr>
	    <tr>
		<th class="header" height='15'>
		    <stripes:label for="tier"/></th>
		<th class="header" height='15'>
		    <stripes:label for="days"/></th>
		<th class="header" height='15'>
		    <stripes:label for="from"/></th>
		<th class="header" height='15'>
		    <stripes:label for="to"/></th>
	    </tr>
	    <tr>

<%-- row 1 --%>

		<td align='right' height='19'>1</td>
		<td align='right' height='19'>
		    <c:choose>
			<c:when test='${acctSelector.actionFlag=="Modify"}'>
				<stripes:text name='acctDetail.account_availday1' style="text-align:right"
					value='${acctDetail.account_availday1}'
					size='15' id='day1' />
	    		</c:when>
			<c:when test='${acctSelector.actionFlag=="New"}'>
				<stripes:text name='acctDetail.account_availday1' style="text-align:right"
					value='${acctDetail.account_availday1}'
					size='15' id='day1' />
	    		</c:when>
	    		<c:otherwise>
				<stripes:text name='acctDetail.account_availday1' style="text-align:right"
					value='${acctDetail.account_availday1}'
					size='15' readonly='true'/>
			</c:otherwise>
		    </c:choose>
		</td>
		<td align='right' height='19'>
		    <c:choose>
			<c:when test='${acctSelector.actionFlag=="Modify"}'>
				<stripes:text name='acctDetail.account_availlow1' style="text-align:right"
					value='${acctDetail.account_availlow1}'
					size='15' id='low1' readonly="true"/>
	    		</c:when>
			<c:when test='${acctSelector.actionFlag=="New"}'>
				<stripes:text name='acctDetail.account_availlow1' style="text-align:right"
					value='${acctDetail.account_availlow1}'
					size='15' id='low1' readonly="true"/>
	    		</c:when>
	    		<c:otherwise>
				<stripes:text name='acctDetail.account_availlow1' style="text-align:right"
					value='${acctDetail.account_availlow1}'
					size='15' readonly='true'/>
			</c:otherwise>
		    </c:choose>
		</td>
		<td align='right' height='19'>
		    <c:choose>
			<c:when test='${acctSelector.actionFlag=="Modify"}'>
				<stripes:text name='acctDetail.account_availhigh1' style="text-align:right"
					value='${acctDetail.account_availhigh1}'
					size='15' id='high1' onchange="setField('high1', 'low2')"/>
	    		</c:when>
			<c:when test='${acctSelector.actionFlag=="New"}'>
				<stripes:text name='acctDetail.account_availhigh1' style="text-align:right"
					value='${acctDetail.account_availhigh1}'
					size='15' id='high1' onchange="setField('high1', 'low2')" />
	    		</c:when>
	    		<c:otherwise>
				<stripes:text name='acctDetail.account_availhigh1' style="text-align:right"
					value='${acctDetail.account_availhigh1}'
					size='15' readonly='true'/>
			</c:otherwise>
		    </c:choose>
		</td>

	    </tr>

<%-- row 2 --%>

	    <tr>
		<td align='right' height='19'>2</td>
		<td align='left' height='19'>
		    <c:choose>
			<c:when test='${acctSelector.actionFlag=="Modify"}'>
				<stripes:text name='acctDetail.account_availday2' style="text-align:right"
					value='${acctDetail.account_availday2}'
					size='15' id='day2' />
	    		</c:when>
			<c:when test='${acctSelector.actionFlag=="New"}'>
				<stripes:text name='acctDetail.account_availday2' style="text-align:right"
					value='${acctDetail.account_availday2}'
					size='15' id='day2' />
	    		</c:when>
	    		<c:otherwise>
				<stripes:text name='acctDetail.account_availday2' style="text-align:right"
					value='${acctDetail.account_availday2}'
					size='15' readonly='true'/>
			</c:otherwise>
		    </c:choose>
		</td>
		<td align='left' height='19'>
		    <c:choose>
			<c:when test='${acctSelector.actionFlag=="Modify"}'>
				<stripes:text name='acctDetail.account_availlow2' style="text-align:right"
					value='${acctDetail.account_availlow2}'
					size='15' id='low2' readonly="true"/>
	    		</c:when>
			<c:when test='${acctSelector.actionFlag=="New"}'>
				<stripes:text name='acctDetail.account_availlow2' style="text-align:right"
					value='${acctDetail.account_availlow2}'
					size='15' id='low2' readonly="true"/>
	    		</c:when>
	    		<c:otherwise>
				<stripes:text name='acctDetail.account_availlow2' style="text-align:right"
					value='${acctDetail.account_availlow2}'
					size='15' readonly='true'/>
			</c:otherwise>
		    </c:choose>
		</td>
		<td align='left' height='19'>
		    <c:choose>
			<c:when test='${acctSelector.actionFlag=="Modify"}'>
				<stripes:text name='acctDetail.account_availhigh2' style="text-align:right"
					value='${acctDetail.account_availhigh2}'
					size='15' id='high2' onchange="setField('high2', 'low3')"/>
	    		</c:when>
			<c:when test='${acctSelector.actionFlag=="New"}'>
				<stripes:text name='acctDetail.account_availhigh2' style="text-align:right"
					value='${acctDetail.account_availhigh2}'
					size='15' id='high2' onchange="setField('high2', 'low3')" />
	    		</c:when>
	    		<c:otherwise>
				<stripes:text name='acctDetail.account_availhigh2' style="text-align:right"
					value='${acctDetail.account_availhigh2}'
					size='15' readonly='true'/>
			</c:otherwise>
		    </c:choose>
		</td>

	    </tr>

<%-- row 3 --%>

	    <tr>
		<td align='right' height='19'>3</td>
		<td align='left' height='19'>
		    <c:choose>
			<c:when test='${acctSelector.actionFlag=="Modify"}'>
				<stripes:text name='acctDetail.account_availday3' style="text-align:right"
					value='${acctDetail.account_availday3}'
					size='15' id='day3' />
	    		</c:when>
			<c:when test='${acctSelector.actionFlag=="New"}'>
				<stripes:text name='acctDetail.account_availday3' style="text-align:right"
					value='${acctDetail.account_availday3}'
					size='15' id='day3' />
	    		</c:when>
	    		<c:otherwise>
				<stripes:text name='acctDetail.account_availday3' style="text-align:right"
					value='${acctDetail.account_availday3}'
					size='15' readonly='true'/>
			</c:otherwise>
		    </c:choose>
		</td>
		<td align='left' height='19'>
		    <c:choose>
			<c:when test='${acctSelector.actionFlag=="Modify"}'>
				<stripes:text name='acctDetail.account_availlow3' style="text-align:right"
					value='${acctDetail.account_availlow3}'
					size='15' id="low3" readonly="true"/>
	    		</c:when>
			<c:when test='${acctSelector.actionFlag=="New"}'>
				<stripes:text name='acctDetail.account_availlow3' style="text-align:right"
					value='${acctDetail.account_availlow3}'
					size='15' id='low3' readonly="true"/>
	    		</c:when>
	    		<c:otherwise>
				<stripes:text name='acctDetail.account_availlow3' style="text-align:right"
					value='${acctDetail.account_availlow3}'
					size='15' readonly='true'/>
			</c:otherwise>
		    </c:choose>
		</td>
		<td align='left' height='19'>
		    <c:choose>
			<c:when test='${acctSelector.actionFlag=="Modify"}'>
				<stripes:text name='acctDetail.account_availhigh3' style="text-align:right"
					value='${acctDetail.account_availhigh3}'
					size='15' id='high3' onchange="setField('high3', 'low4')"/>
	    		</c:when>
			<c:when test='${acctSelector.actionFlag=="New"}'>
				<stripes:text name='acctDetail.account_availhigh3' style="text-align:right"
					value='${acctDetail.account_availhigh3}'
					size='15' id='high3' onchange="setField('high3', 'low4')" />
	    		</c:when>
	    		<c:otherwise>
				<stripes:text name='acctDetail.account_availhigh3' style="text-align:right"
					value='${acctDetail.account_availhigh3}'
					size='15' readonly='true'/>
			</c:otherwise>
		    </c:choose>
		</td>
	    </tr>

<%-- row 4 --%>

	    <tr>
		<td align='right' height='19'>4</td>
		<td align='left' height='19'>
		    <c:choose>
			<c:when test='${acctSelector.actionFlag=="Modify"}'>
				<stripes:text name='acctDetail.account_availday4' style="text-align:right"
					value='${acctDetail.account_availday4}'
					size='15' id='day4' />
	    		</c:when>
			<c:when test='${acctSelector.actionFlag=="New"}'>
				<stripes:text name='acctDetail.account_availday4' style="text-align:right"
					value='${acctDetail.account_availday4}'
					size='15' id='day4' />
	    		</c:when>
	    		<c:otherwise>
				<stripes:text name='acctDetail.account_availday4' style="text-align:right"
					value='${acctDetail.account_availday4}'
					size='15' readonly='true'/>
			</c:otherwise>
		    </c:choose>
		</td>
		<td align='left' height='19'>
		    <c:choose>
			<c:when test='${acctSelector.actionFlag=="Modify"}'>
				<stripes:text name='acctDetail.account_availlow4' style="text-align:right"
					value='${acctDetail.account_availlow4}'
					size='15' id="low4" readonly="true"/>
	    		</c:when>
			<c:when test='${acctSelector.actionFlag=="New"}'>
				<stripes:text name='acctDetail.account_availlow4' style="text-align:right"
					value='${acctDetail.account_availlow4}'
					size='15' id="low4" readonly="true"/>
	    		</c:when>
	    		<c:otherwise>
				<stripes:text name='acctDetail.account_availlow4' style="text-align:right"
					value='${acctDetail.account_availlow4}'
					size='15' readonly='true'/>
			</c:otherwise>
		    </c:choose>
		</td>
		<td align='left' height='19'>
		    <c:choose>
			<c:when test='${acctSelector.actionFlag=="Modify"}'>
				<stripes:text name='acctDetail.account_availhigh4' style="text-align:right"
					value='${acctDetail.account_availhigh4}'
					size='15' id='high4' onchange="setField('high4', 'low5')"/>
	    		</c:when>
			<c:when test='${acctSelector.actionFlag=="New"}'>
				<stripes:text name='acctDetail.account_availhigh4' style="text-align:right"
					value='${acctDetail.account_availhigh4}'
					size='15' id='high4' onchange="setField('high4', 'low5')" />
	    		</c:when>
	    		<c:otherwise>
				<stripes:text name='acctDetail.account_availhigh4' style="text-align:right"
					value='${acctDetail.account_availhigh4}'
					size='15' readonly='true'/>
			</c:otherwise>
		    </c:choose>
		</td>
	    </tr>

<%-- row 5 --%>

	    <tr>
		<td align='right' height='19'>5</td>
		<td align='left' height='19'>
		    <c:choose>
			<c:when test='${acctSelector.actionFlag=="Modify"}'>
				<stripes:text name='acctDetail.account_availday5' style="text-align:right"
					value='${acctDetail.account_availday5}'
					size='15' id='day5' />
	    		</c:when>
			<c:when test='${acctSelector.actionFlag=="New"}'>
				<stripes:text name='acctDetail.account_availday5' style="text-align:right"
					value='${acctDetail.account_availday5}'
					size='15' id='day5' />
	    		</c:when>
	    		<c:otherwise>
				<stripes:text name='acctDetail.account_availday5' style="text-align:right"
					value='${acctDetail.account_availday5}'
					size='15' readonly='true'/>
			</c:otherwise>
		    </c:choose>
		</td>
		<td align='right' height='19' style=text-align:right;>
		    <c:choose>
			<c:when test='${acctSelector.actionFlag=="Modify"}'>
				<stripes:text name='acctDetail.account_availlow5' style="text-align:right"
					value='${acctDetail.account_availlow5}'
					size='15' id="low5" readonly="true" />
	    		</c:when>
			<c:when test='${acctSelector.actionFlag=="New"}'>
				<stripes:text name='acctDetail.account_availlow5' style="text-align:right"
					value='${acctDetail.account_availlow5}'
					size='15' id="low5" readonly="true" />
	    		</c:when>
	    		<c:otherwise>
				<stripes:text name='acctDetail.account_availlow5' style="text-align:right"
					value='${acctDetail.account_availlow5}'
					size='15' readonly='true'/>
			</c:otherwise>
		    </c:choose>
		</td>
		<td align='right' height='19'>
		    <c:choose>
			<c:when test='${acctSelector.actionFlag=="Modify"}'>
				<stripes:text name='acctDetail.account_availhigh5' style="text-align:right"
					value='${acctDetail.account_availhigh5}'
					size='15'  id='high5' readonly='true'/>
	    		</c:when>
			<c:when test='${acctSelector.actionFlag=="New"}'>
				<stripes:text name='acctDetail.account_availhigh5' style="text-align:right"
					value='${acctDetail.account_availhigh5}'
					size='15'  id='high5' readonly='true'/>
	    		</c:when>
	    		<c:otherwise>
				<stripes:text name='acctDetail.account_availhigh5' style="text-align:right"
					value='${acctDetail.account_availhigh5}'
					size='15' readonly='true'/>
			</c:otherwise>
		    </c:choose>
		</td>
	    </tr>
	</table>
	</td>
	</tr>

<%--
	*************************************************************************************************
	*************************************************************************************************
	*************************************************************************************************
--%>


	<tr>
	<th align='right' width='45%' height='15'>
		<stripes:label for="stmtEmailnF"/></th>
	<td>
	<table>
	    <tr> 
		<td align='left' height='19'>
		    <c:choose>
			<c:when test='${acctSelector.actionFlag=="Modify"}'>
			    <stripes:text name='acctDetail.account_stmt_email'
					value='${acctDetail.account_stmt_email}'
					size='40' maxlength='40' />
			</c:when>
			<c:when test='${acctSelector.actionFlag=="New"}'>
			    <stripes:text name='acctDetail.account_stmt_email'
					value='${acctDetail.account_stmt_email}'
					size='40' maxlength='40' />
			</c:when>
			<c:otherwise>
			    <stripes:text name='acctDetail.account_stmt_email'
					value='${acctDetail.account_stmt_email}'
					size='40' readonly='true'/>
		        </c:otherwise>
		    </c:choose>
		</td>
		<td align='left' height='19'>
		    <c:choose>
			<c:when test='${acctSelector.actionFlag=="Modify"}'>
			    <select size="1" name="acctDetail.account_stmt_emailfreqD">
				<c:if test='${acctDetail.account_stmt_emailfreqD=="Y"}'>
					<option value="N">Daily: No</option>
					<option Selected value="Y">Daily: Yes</option>
				</c:if>
				<c:if test='${acctDetail.account_stmt_emailfreqD!="Y"}'>
					<option Selected value="N">Daily: No</option>
					<option value="Y">Daily: Yes</option>
				</c:if>
			    </select>
			</c:when>
			<c:when test='${acctSelector.actionFlag=="Update"}'>
			    <select size="1" name="acctDetail.account_stmt_emailfreqD">
				<option Selected><c:out value='${acctDetail.account_stmt_emailfreqD}'/>
				</option>
			    </select>
			</c:when>
			<c:when test='${acctSelector.actionFlag=="New"}'>
			    <select size="1" name="acctDetail.account_stmt_emailfreqD">
				<option Selected value="N">Daily: No</option>
				<option value="Y">Daily: Yes</option>
			    </select>
			</c:when>
			<c:otherwise>
			    <c:if test="${acctDetail.account_stmt_emailfreqD=='N'}">
				<c:out value="Daily: No" />
			    </c:if>
			    <c:if test="${acctDetail.account_stmt_emailfreqD=='Y'}">
				<c:out value="Daily: Yes" />
			    </c:if>
			</c:otherwise>
		    </c:choose>
		</td>
		<td align='left' height='19'>
		    <c:choose>
			<c:when test='${acctSelector.actionFlag=="Modify"}'>
			    <select size="1" name="acctDetail.account_stmt_emailfreqW">
				<c:if test='${acctDetail.account_stmt_emailfreqW=="Y"}'>
					<option value="N">Weekly: No</option>
					<option Selected value="Y">Weekly: Yes</option>
				</c:if>
				<c:if test='${acctDetail.account_stmt_emailfreqW!="Y"}'>
					<option Selected value="N">Weekly: No</option>
					<option value="Y">Weekly: Yes</option>
				</c:if>
			    </select>
			</c:when>
			<c:when test='${acctSelector.actionFlag=="Update"}'>
			    <select size="1" name="acctDetail.account_stmt_emailfreqW">
				<option Selected><c:out value='${acctDetail.account_stmt_emailfreqW}'/>
				</option>
			    </select>
			</c:when>
			<c:when test='${acctSelector.actionFlag=="New"}'>
			    <select size="1" name="acctDetail.account_stmt_emailfreqW">
				<option Selected value="N">Weekly: No</option>
				<option value="Y">Weekly: Yes</option>
			    </select>
			</c:when>
			<c:otherwise>
			    <c:if test="${acctDetail.account_stmt_emailfreqW=='N'}">
				<c:out value="Weekly: No" />
			    </c:if>
			    <c:if test="${acctDetail.account_stmt_emailfreqW=='Y'}">
				<c:out value="Weekly: Yes" />
			    </c:if>
			</c:otherwise>
		    </c:choose>
		</td>
		<td align='left' height='19'>
		    <c:choose>
			<c:when test='${acctSelector.actionFlag=="Modify"}'>
			    <select size="1" name="acctDetail.account_stmt_emailfreqM">
				<c:if test='${acctDetail.account_stmt_emailfreqM=="Y"}'>
					<option value="N">Monthly: No</option>
					<option Selected value="Y">Monthly: Yes</option>
				</c:if>
				<c:if test='${acctDetail.account_stmt_emailfreqM!="Y"}'>
					<option Selected value="N">Monthly: No</option>
					<option value="Y">Monthly: Yes</option>
				</c:if>
			    </select>
			</c:when>
			<c:when test='${acctSelector.actionFlag=="Update"}'>
			    <select size="1" name="acctDetail.account_stmt_emailfreqM">
				<option Selected><c:out value='${acctDetail.account_stmt_emailfreqM}'/>
				</option>
			    </select>
			</c:when>
			<c:when test='${acctSelector.actionFlag=="New"}'>
			    <select size="1" name="acctDetail.account_stmt_emailfreqM">
				<option Selected value="N">Monthly: No</option>
				<option value="Y">Monthly: Yes</option>
			    </select>
			</c:when>
			<c:otherwise>
			    <c:if test="${acctDetail.account_stmt_emailfreqM=='N'}">
				<c:out value="Monthly: No" />
			    </c:if>
			    <c:if test="${acctDetail.account_stmt_emailfreqM=='Y'}">
				<c:out value="Monthly: Yes" />
			    </c:if>
			</c:otherwise>
		    </c:choose>
		</td>
	    </tr>
	</table>
	</td>
	</tr>

	<tr>
	<th align='right' width='40%' height='19'>
		<stripes:label for="stmtFaxnF"/></th>
	<td>
	    <table>
		<tr> 
		    <td align='left' height='19'>
		    <c:choose>
			<c:when test='${acctSelector.actionFlag=="Modify"}'>
			    <stripes:text name='acctDetail.account_stmt_fax'
				value='${acctDetail.account_stmt_fax}'
				size='40' maxlength='40' />
			</c:when>
			<c:when test='${acctSelector.actionFlag=="New"}'>
			    <stripes:text name='acctDetail.account_stmt_fax'
				value='${acctDetail.account_stmt_fax}'
				size='40' maxlength='40' />
			</c:when>
			<c:otherwise>
			    <stripes:text name='acctDetail.account_stmt_fax'
				value='${acctDetail.account_stmt_fax}'
				size='40' readonly='true'/>
			</c:otherwise>
		    </c:choose>
		    </td>
		    <td align='left' height='19'>
		    <c:choose>
			<c:when test='${acctSelector.actionFlag=="Modify"}'>
			    <select size="1" name="acctDetail.acctDetail.account_stmt_faxfreqD">
				<c:if test='${acctDetail.account_stmt_faxfreqD=="Y"}'>
					<option value="N">Daily: No</option>
					<option Selected value="Y">Daily: Yes</option>
 				</c:if>
				<c:if test='${acctDetail.account_stmt_faxfreqD!="Y"}'>
					<option Selected value="N">Daily: No</option>
					<option value="Y">Daily: Yes</option>
				</c:if>
			    </select>
			</c:when>
			<c:when test='${acctSelector.actionFlag=="Update"}'>
			    <select size="1" name="acctDetail.account_stmt_faxfreqD">
				<option Selected><c:out value='${acctDetail.account_stmt_faxfreqD}'/></option>
			    </select>
			</c:when>
			<c:when test='${acctSelector.actionFlag=="New"}'>
			    <select size="1" name="acctDetail.account_stmt_faxfreqD">
				<option Selected value="N">Daily: No</option>
				<option value="Y">Daily: Yes</option>
			    </select>
			</c:when>
			<c:otherwise>
			    <c:if test="${acctDetail.account_stmt_faxfreqD=='N'}">
				<c:out value="Daily: No" />
			    </c:if>
			    <c:if test="${acctDetail.account_stmt_faxfreqD=='Y'}">
				<c:out value="Daily: Yes" />
			    </c:if>
			</c:otherwise>
		    </c:choose>
		    </td>
		    <td align='left' height='19'>
		    <c:choose>
			<c:when test='${acctSelector.actionFlag=="Modify"}'>
			    <select size="1" name="acctDetail.account_stmt_faxfreqW">
				<c:if test='${acctDetail.account_stmt_faxfreqW=="Y"}'>
					<option value="N">Weekly: No</option>
					<option Selected value="Y">Weekly: Yes</option>
				</c:if>
				<c:if test='${acctDetail.account_stmt_faxfreqW!="Y"}'>
					<option Selected value="N">Weekly: No</option>
					<option value="Y">Weekly: Yes</option>
				</c:if>
			    </select>
			</c:when>
			<c:when test='${acctSelector.actionFlag=="Update"}'>
			    <select size="1" name="acctDetail.account_stmt_faxfreqW">
				<option Selected><c:out value='${acctDetail.account_stmt_faxfreqW}'/></option>
			    </select>
			</c:when>
			<c:when test='${acctSelector.actionFlag=="New"}'>
			    <select size="1" name="acctDetail.account_stmt_faxfreqW">
				<option Selected value="N">Weekly: No</option>
				<option value="Y">Weekly: Yes</option>
			    </select>
			</c:when>
			<c:otherwise>
			    <c:if test="${acctDetail.account_stmt_faxfreqW=='N'}">
				<c:out value="Weekly: No" />
			    </c:if>
			    <c:if test="${acctDetail.account_stmt_faxfreqW=='Y'}">
				<c:out value="Weekly: Yes" />
			    </c:if>
			</c:otherwise>
		    </c:choose>
		    </td>
		    <td align='left' height='19'>
		    <c:choose>
			<c:when test='${acctSelector.actionFlag=="Modify"}'>
			    <select size="1" name="acctDetail.account_stmt_faxfreqM">
				<c:if test='${acctDetail.account_stmt_faxfreqM=="Y"}'>
					<option value="N">Monthly: No</option>
					<option Selected value="Y">Monthly: Yes</option>
				</c:if>
				<c:if test='${acctDetail.account_stmt_faxfreqM!="Y"}'>
					<option Selected value="N">Monthly: No</option>
					<option value="Y">Monthly: Yes</option>
				</c:if>
			    </select>
			</c:when>
			<c:when test='${acctSelector.actionFlag=="Update"}'>
			    <select size="1" name="acctDetail.account_stmt_faxfreqM">
				<option Selected><c:out value='${acctDetail.account_stmt_faxfreqM}'/>
				</option>
			    </select>
			</c:when>
			<c:when test='${acctSelector.actionFlag=="New"}'>
			    <select size="1" name="acctDetail.account_stmt_faxfreqM">
				<option Selected value="N">Monthly: No</option>
				<option value="Y">Monthly: Yes</option>
			    </select>
			</c:when>
			<c:otherwise>
			    <c:if test="${acctDetail.account_stmt_faxfreqM=='N'}">
				<c:out value="Monthly: No" />
			    </c:if>
			    <c:if test="${acctDetail.account_stmt_faxfreqM=='Y'}">
				<c:out value="Monthly: Yes" />
			    </c:if>
			</c:otherwise>
		    </c:choose>
		    </td>
		</tr>
	    </table>
	</td>
	</tr>

	<tr>
	<th align='right' width='40%' height='19'>
		<stripes:label for="stmtMailAddr1"/></th>
	<td>
	    <table>
		<tr> 
		    <td align='left' height='19'>
		    <c:choose>
			<c:when test='${acctSelector.actionFlag=="Modify"}'>
			    <stripes:text name='acctDetail.account_stmt_mail1'
					value='${acctDetail.account_stmt_mail1}'
				size='40' maxlength='40' />
			</c:when>
			<c:when test='${acctSelector.actionFlag=="New"}'>
			    <stripes:text name='acctDetail.account_stmt_mail1'
					value='${acctDetail.account_stmt_mail1}'
				size='40' maxlength='40' />
			</c:when>
			<c:otherwise>
			    <stripes:text name='acctDetail.account_stmt_mail1'
					value='${acctDetail.account_stmt_mail1}'
					size='40' readonly='true'/>
			</c:otherwise>
		    </c:choose>
		    </td>
		    <td align='left' height='19'>
		    <c:choose>
			<c:when test='${acctSelector.actionFlag=="Modify"}'>
			    <stripes:text name='acctDetail.account_stmt_mail2'
					value='${acctDetail.account_stmt_mail2}'
					size='40' maxlength='40' />
			</c:when>
			<c:when test='${acctSelector.actionFlag=="New"}'>
			    <stripes:text name='acctDetail.account_stmt_mail2'
					value='${acctDetail.account_stmt_mail2}'
					size='40' maxlength='40' />
			</c:when>
			<c:otherwise>
			    <stripes:text name='acctDetail.account_stmt_mail2'
					value='${acctDetail.account_stmt_mail2}'
					size='40' readonly='true'/>
			</c:otherwise>
		    </c:choose>
		    </td>
		</tr>
	    </table>
	</td>
	</tr>
	<th align='right' width='40%' height='19'>
		<stripes:label for="stmtMailAddr2"/></th>
	<td>
	    <table>
		<tr> 
		    <td align='left' height='19'>
		    <c:choose>
			<c:when test='${acctSelector.actionFlag=="New"}'>
			    <stripes:text name='acctDetail.account_stmt_mail3'
				value='${acctDetail.account_stmt_mail3}'
				size='40' maxlength='40' />
			</c:when>
			<c:when test='${acctSelector.actionFlag=="New"}'>
			    <stripes:text name='acctDetail.account_stmt_mail3'
				value='${acctDetail.account_stmt_mail3}'
				size='40' maxlength='40' />
			</c:when>
			<c:otherwise>
			    <stripes:text name='acctDetail.account_stmt_mail3'
				value='${acctDetail.account_stmt_mail3}'
				size='40' readonly='true'/>
			</c:otherwise>
		    </c:choose>
		    </td>
		    <td align='left' height='19'>
		    <c:choose>
			<c:when test='${acctSelector.actionFlag=="New"}'>
			    <stripes:text name='acctDetail.account_stmt_mail4'
				value='${acctDetail.account_stmt_mail4}'
				size='40' maxlength='40' />
			</c:when>
			<c:when test='${acctSelector.actionFlag=="New"}'>
			    <stripes:text name='acctDetail.account_stmt_mail4'
				value='${acctDetail.account_stmt_mail4}'
				size='40' maxlength='40' />
			</c:when>
			<c:otherwise>
			    <stripes:text name='acctDetail.account_stmt_mail4'
				value='${acctDetail.account_stmt_mail4}'
				size='40' readonly='true'/>
			</c:otherwise>
		    </c:choose>
		    </td>
		</tr>
	    </table>
	</td>
	</tr>

	<tr>
	<th align='right' width='40%' height='19'>
		<stripes:label for="stmtMailnF"/></th>
	<td>
	    <table>
		<tr>
		    <td align='left' height='19'>
		    <c:choose>
			<c:when test='${acctSelector.actionFlag=="Modify"}'>
			    <select size="1" name="acctDetail.account_stmt_mailfreqD">
				<c:if test='${acctDetail.account_stmt_mailfreqD=="Y"}'>
					<option value="N">Daily: No</option>
					<option Selected value="Y">Daily: Yes</option>
				</c:if>
				<c:if test='${acctDetail.account_stmt_mailfreqD=="N"}'>
					<option Selected value="N">Daily: No</option>
					<option value="Y">Daily: Yes</option>
				</c:if>
				<c:if test='${acctDetail.account_stmt_mailfreqD==""}'>
					<option Selected value="N">Daily: No</option>
					<option value="Y">Daily: Yes</option>
				</c:if>
			    </select>
			</c:when>
			<c:when test='${acctSelector.actionFlag=="Update"}'>
			    <select size="1" name="acctDetail.account_stmt_mailfreqD">
				<option Selected><c:out value='${acctDetail.account_stmt_mailfreqD}'/>
				</option>
			    </select>
			</c:when>
			<c:when test='${acctSelector.actionFlag=="New"}'>
			    <select size="1" name="acctDetail.account_stmt_mailfreqD">
				<option Selected value="N">Daily: No</option>
				<option value="Y">Daily: Yes</option>
			    </select>
			</c:when>
			<c:otherwise>
			    <c:if test="${acctDetail.account_stmt_mailfreqD=='N'}">
				<c:out value="Daily: No" />
			    </c:if>
			    <c:if test="${acctDetail.account_stmt_mailfreqD=='Y'}">
				<c:out value="Daily: Yes" />
			    </c:if>
			</c:otherwise>
		    </c:choose>
		    </td>
		    <td align='left' height='19'>
		    <c:choose>
			<c:when test='${acctSelector.actionFlag=="Modify"}'>
			    <select size="1" name="acctDetail.account_stmt_mailfreqW">
				<c:if test='${acctDetail.account_stmt_mailfreqW=="Y"}'>
					<option value="N">Weekly: No</option>
					<option Selected value="Y">Weekly: Yes</option>
				</c:if>
				<c:if test='${acctDetail.account_stmt_mailfreqW=="N"}'>
					<option Selected value="N">Weekly: No</option>
					<option value="Y">Weekly: Yes</option>
				</c:if>
				<c:if test='${acctDetail.account_stmt_mailfreqW==""}'>
					<option Selected value="N">Weekly: No</option>
					<option value="Y">Weekly: Yes</option>
				</c:if>
			    </select>
			</c:when>
			<c:when test='${acctSelector.actionFlag=="Update"}'>
			    <select size="1" name="acctDetail.account_stmt_mailfreqW">
				<option Selected><c:out value='${acctDetail.account_stmt_mailfreqW}'/>
				</option>
			    </select>
			</c:when>
			<c:when test='${acctSelector.actionFlag=="New"}'>
			    <select size="1" name="acctDetail.account_stmt_mailfreqW">
				<option Selected value="N">Weekly: No</option>
				<option value="Y">Weekly: Yes</option>
			    </select>
			</c:when>
			<c:otherwise>
			    <c:if test="${acctDetail.account_stmt_mailfreqW=='N'}">
				<c:out value="Weekly: No" />
			    </c:if>
			    <c:if test="${acctDetail.account_stmt_mailfreqW=='Y'}">
				<c:out value="Weekly: Yes" />
			    </c:if>
			</c:otherwise>
		    </c:choose>
		    </td>
		    <td align='left' height='19'>
		    <c:choose>
			<c:when test='${acctSelector.actionFlag=="Modify"}'>
			    <select size="1" name="acctDetail.account_stmt_mailfreqM">
				<c:if test='${acctDetail.account_stmt_mailfreqM=="Y"}'>
					<option value="N">Monthly: No</option>
					<option Selected value="Y">Monthly: Yes</option>
				</c:if>
				<c:if test='${acctDetail.account_stmt_mailfreqM=="N"}'>
					<option Selected value="N">Monthly: No</option>
					<option value="Y">Monthly: Yes</option>
				</c:if>
				<c:if test='${acctDetail.account_stmt_mailfreqM==""}'>
					<option Selected value="N">Monthly: No</option>
					<option value="Y">Monthly: Yes</option>
				</c:if>
			    </select>
			</c:when>
			<c:when test='${acctSelector.actionFlag=="Update"}'>
			    <select size="1" name="acctDetail.account_stmt_mailfreqM">
				<option Selected><c:out value='${acctDetail.account_stmt_mailfreqM}'/>
				</option>
			    </select>
			</c:when>
			<c:when test='${acctSelector.actionFlag=="New"}'>
			    <select size="1" name="acctDetail.account_stmt_mailfreqM">
				<option Selected value="N">Monthly: No</option>
				<option value="Y">Monthly: Yes</option>
			    </select>
			</c:when>
			<c:otherwise>
			    <c:if test="${acctDetail.account_stmt_mailfreqM=='N'}">
				<c:out value="Monthly: No" />
			    </c:if>
			    <c:if test="${acctDetail.account_stmt_mailfreqM=='Y'}">
				<c:out value="Monthly: Yes" />
			    </c:if>
			</c:otherwise>
		    </c:choose>
		    </td>
		</tr>
	    </table>
	</td>
	</tr>

	<tr>
	<th align='right' width='40%' height='19'>
	    <stripes:label for="user"/> <stripes:label for="comments"/>
	</th>
	<td align='left' height='15'>
	    <c:choose>
	    <c:when test='${acctSelector.actionFlag=="Modify"}'>
		<stripes:text name='acctDetail.account_user_comments'
			value='${acctDetail.account_user_comments}'
			size='80' maxlength='80' />
	    </c:when>
	    <c:when test='${acctSelector.actionFlag=="New"}'>
		<stripes:text name='acctDetail.account_user_comments'
			value='${acctDetail.account_user_comments}'
			size='80' maxlength='80' />
	    </c:when>
	    <c:otherwise>
		<stripes:text name='acctDetail.account_user_comments'
			value='${acctDetail.account_user_comments}'
			size='80' readonly='true'/>
	    </c:otherwise>
	    </c:choose>
	</td>
	</tr>
</table>
</div> <%-- detail --%>

<%--												--%>
<%--												--%>
<%--	<c:out value='${acctSelector.actionFlag}'/>						--%>
<%--	<c:out value='${acctSelector.bankId}' />						--%>

    <center>
	<stripes:errors />
	<c:if test='${acctSelector.actionFlag!="inq"}'>
	    <c:if test='${acctSelector.actionFlag=="Modify"}'>
		<stripes:submit name='Update' value="Update"/>
	    </c:if>
	    <c:if test='${acctSelector.actionFlag=="Update"}'>
		<stripes:submit name='Confirm' value="Confirm Update Account"/>
	    </c:if>
	    <c:if test='${acctSelector.actionFlag=="Add"}'>
		<stripes:submit name='Confirm' value="Confirm Add Account"/>
	    </c:if>
	    <c:if test='${acctSelector.actionFlag=="Delete"}'>
		<stripes:submit name='DeleteConfirm' value="Confirm Delete Account"/>
	    </c:if>
	    <c:if test='${acctSelector.actionFlag=="New"}'>
		<stripes:submit name='Add' value="Add Account"/>
	    </c:if>
	</c:if>
    </center>
<%--
	*************************************************************************************************
	*************************************************************************************************
	*************************************************************************************************
--%>
	</stripes:form>
    </stripes:layout-component>
</stripes:layout-render>
