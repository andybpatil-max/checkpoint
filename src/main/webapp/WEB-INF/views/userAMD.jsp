<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/sysadmin/actions/User.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<h1><stripes:label for="user"/> <stripes:label for="management"/></h1>

	<c:if test='${userSelector.accessFlag=="inq"}'>
	    <h1>
		<font size="3">
		    <stripes:label for="user"/> <stripes:label for="inquiry"/>
		</font>
	    </h1>
	</c:if>
	<c:if test='${userSelector.accessFlag!="inq"}'>
	    <h1>
		<font size="3">
		    <stripes:label for="user.inquiry"/> <stripes:label for="user.maint"/>
		</font>
	    </h1>
	</c:if>

<div id="selres">
<center>
<table colspan='8' width='70%' align='center' border='1' bgcolor='lightgreen' height='39'>
	<tr>
	    <th align=center width="10%" height="19" colspan="1">
		<font size="2"><stripes:label for="userId"/></font>
	    </th>
	    <c:if test='${userSelector.actionFlag=="new_user"}'>
		<td align='left' width='5%' height='19' >
			<stripes:text name="userDetail.user_id" value="${userDetail.user_id}" 
				size="15" maxlength="15"/>
	    	</td>
	    </c:if>
	    <c:if test='${userSelector.actionFlag=="Modify"}'>
		<td align='left' width='5%' height='19' >
			<stripes:text name="userDetail.user_id" value="${userDetail.user_id}" 
				size="15" maxlength="15" readonly="true"/>
	    	</td>
	    </c:if>
	    <c:if test='${userSelector.actionFlag=="delete_confirm"}'>
		<td align='left' width='5%' height='19' >
			<stripes:text name="userDetail.user_id" value="${userDetail.user_id}" 
				size="15" maxlength="15" readonly="true"/>
	    	</td>
	    </c:if>
	    <c:if test='${userSelector.actionFlag=="add_confirm"}'>
		<td align='left' width='5%' height='19' >
			<stripes:text name="userDetail.user_id" value="${userDetail.user_id}"
				size="15" maxlength="15" readonly="true"/>
	    	</td>
	    </c:if>
	    <c:if test='${userSelector.actionFlag=="update_user"}'>
		<td align='left' width='5%' height='19' >
			<stripes:text name="userDetail.user_id" value="${userDetail.user_id}"
				size="15" maxlength="15" readonly="true"/>
	    	</td>
	    </c:if>
	    <c:if test='${userSelector.actionFlag=="update_confirm"}'>
		<td align='left' width='5%' height='19' >
			<stripes:text name="userDetail.user_id" value="${userDetail.user_id}"
				size="15" maxlength="15" readonly="true"/>
	    	</td>
	    </c:if>
	    <%-- ************************************************************************************ --%>
	    <%-- ************************************************************************************ --%>
	    <th align=center width='10%' height=15 colspan=1>
		<font size=2><stripes:label for="password"/></font>
	    </th>
	    <c:if test='${userSelector.actionFlag=="new_user"}'>
		<td align='left' width='5%' height='19' >
			<stripes:password name="userDetail.user_pass_current"
				value="${userDetail.user_pass_current}" size="32" maxlength="32"/>
	    	</td>
	    </c:if>
	    <c:if test='${userSelector.actionFlag=="Modify"}'>
		<td align='left' width='5%' height='19' >
			<stripes:password name="userDetail.user_pass_current"
				value="${userDetail.user_pass_current}" size="32" maxlength="32"/>
	    	</td>
	    </c:if>
	    <c:if test='${userSelector.actionFlag=="delete_confirm"}'>
		<td align='left' width='5%' height='19' >
			<stripes:password name="userDetail.user_pass_current"
				value="${userDetail.user_pass_current}"	size="32" maxlength="32"
				readonly="true"/>
	    	</td>
	    </c:if>
	    <c:if test='${userSelector.actionFlag=="add_confirm"}'>
		<td align='left' width='5%' height='19' >
			<stripes:password name="userDetail.user_pass_current"
				value="${userDetail.user_pass_current}"
				size="32" maxlength="32" readonly="true"/>
	    	</td>
	    </c:if>
	    <c:if test='${userSelector.actionFlag=="update_user"}'>
		<td align='left' width='5%' height='19' >
			<stripes:password name="userDetail.user_pass_current"
				value="${userDetail.user_pass_current}"
				size="32" maxlength="32" readonly="true"/>
	    	</td>
	    </c:if>
	    <c:if test='${userSelector.actionFlag=="update_confirm"}'>
		<td align='left' width='5%' height='19' >
			<stripes:password name="userDetail.user_pass_current"
				value="${userDetail.user_pass_current}"
				size="32" maxlength="32" readonly="true"/>
	    	</td>
	    </c:if>
	    <th align=center width='10%' height=15 colspan=1>
		<font size=2><stripes:label for="passwordVerify"/></font>
	    </th>
	    <c:if test='${userSelector.actionFlag=="new_user"}'>
		<td align='left' width='5%' height='19' >
			<stripes:password name="userDetail.user_verify_pass"
				value="${userDetail.user_verify_pass}"
				size="32" maxlength="32"/>
	    	</td>
	    </c:if>
	    <c:if test='${userSelector.actionFlag=="Modify"}'>
		<td align='left' width='5%' height='19' >
			<stripes:password name="userDetail.user_verify_pass"
				value="${userDetail.user_verify_pass}"
				size="32" maxlength="32"/>
	    	</td>
	    </c:if>
	    <c:if test='${userSelector.actionFlag=="delete_confirm"}'>
		<td align='left' width='5%' height='19' >
			<stripes:password name="userDetail.user_verify_pass"
				value="${userDetail.user_verify_pass}"
				size="32" maxlength="32" readonly="true"/>
	    	</td>
	    </c:if>
	    <c:if test='${userSelector.actionFlag=="add_confirm"}'>
		<td align='left' width='5%' height='19' >
			<stripes:password name="userDetail.user_verify_pass"
				value="${userDetail.user_verify_pass}"
				size="32" maxlength="32" readonly="true"/>
	    	</td>
	    </c:if>
	    <c:if test='${userSelector.actionFlag=="update_confirm"}'>
		<td align='left' width='5%' height='19' >
			<stripes:password name="userDetail.user_verify_pass"
				value="${userDetail.user_verify_pass}"
				size="32" maxlength="32" readonly="true"/>
	    	</td>
	    </c:if>
	    <c:if test='${userSelector.actionFlag=="update_user"}'>
		<td align='left' width='5%' height='19' >
			<stripes:password name="userDetail.user_verify_pass"
				value="${userDetail.user_verify_pass}"
				size="32" maxlength="32" readonly="true"/>
	    	</td>
	    </c:if>
	    <th align=center width='10%' height="19" colspan="1">
		<font size="2">User <stripes:label for="status"/></font>
	    </th>
	    <td align='center' width='5%' height='19' >
		<font size="2"><c:out value="${userDetail.user_id_blocked}"/></font>
	    </td>
	</tr>
</table>
<table colspan='4' width='80%' align='center' border='1' height='39'>
	<tr>
	    <th align=center width="20%" height="19" colspan="1">
		<font size="2"><stripes:label for="firstName"/></font>
	    </th>
	    <c:if test='${userSelector.actionFlag=="new_user"}'>
		<td align='left' width='5%' height='19' >
			<stripes:text name="userDetail.user_fname" value="${userDetail.user_fname}" 
				size="30" maxlength="50"/>
	    	</td>
	    </c:if>
	    <c:if test='${userSelector.actionFlag=="Modify"}'>
		<td align='left' width='5%' height='19' >
			<stripes:text name="userDetail.user_fname" value="${userDetail.user_fname}" 
				size="30" maxlength="50"/>
	    	</td>
	    </c:if>
	    <c:if test='${userSelector.actionFlag=="delete_confirm"}'>
		<td align='left' width='5%' height='19' >
			<stripes:text name="userDetail.user_fname" value="${userDetail.user_fname}" 
				maxlength="50" readonly="true"/>
	    	</td>
	    </c:if>
	    <c:if test='${userSelector.actionFlag=="add_confirm"}'>
		<td align='left' width='5%' height='19' >
			<stripes:text name="userDetail.user_fname" value="${userDetail.user_fname}"
				maxlength="50" readonly="true"/>
	    	</td>
	    </c:if>
	    <c:if test='${userSelector.actionFlag=="update_user"}'>
		<td align='left' width='5%' height='19' >
			<stripes:text name="userDetail.user_fname" value="${userDetail.user_fname}"
				maxlength="50" readonly="true"/>
	    	</td>
	    </c:if>
	    <c:if test='${userSelector.actionFlag=="update_confirm"}'>
		<td align='left' width='5%' height='19' >
			<stripes:text name="userDetail.user_fname" value="${userDetail.user_fname}"
				maxlength="50" readonly="true"/>
	    	</td>
	    </c:if>
	    <th align=center width="20%" height="19" colspan="1">
		<font size="2"><stripes:label for="lastName"/></font>
	    </th>
	    <c:if test='${userSelector.actionFlag=="new_user"}'>
		<td align='left' width='5%' height='19' >
			<stripes:text name="userDetail.user_lname" value="${userDetail.user_lname}" 
				size="30" maxlength="50"/>
	    	</td>
	    </c:if>
	    <c:if test='${userSelector.actionFlag=="Modify"}'>
		<td align='left' width='5%' height='19' >
			<stripes:text name="userDetail.user_lname" value="${userDetail.user_lname}" 
				size="30" maxlength="50"/>
	    	</td>
	    </c:if>
	    <c:if test='${userSelector.actionFlag=="delete_confirm"}'>
		<td align='left' width='5%' height='19' >
			<stripes:text name="userDetail.user_lname" value="${userDetail.user_lname}" 
				maxlength="50" readonly="true"/>
	    	</td>
	    </c:if>
	    <c:if test='${userSelector.actionFlag=="add_confirm"}'>
		<td align='left' width='5%' height='19' >
			<stripes:text name="userDetail.user_lname" value="${userDetail.user_lname}"
				maxlength="50" readonly="true"/>
	    	</td>
	    </c:if>
	    <c:if test='${userSelector.actionFlag=="update_user"}'>
		<td align='left' width='5%' height='19' >
			<stripes:text name="userDetail.user_lname" value="${userDetail.user_lname}"
				maxlength="50" readonly="true"/>
	    	</td>
	    </c:if>
	    <c:if test='${userSelector.actionFlag=="update_confirm"}'>
		<td align='left' width='5%' height='19' >
			<stripes:text name="userDetail.user_lname" value="${userDetail.user_lname}"
				maxlength="50" readonly="true"/>
	    	</td>
	    </c:if>
	</tr>
</table>

<table colspan='2' width='60%' align='center' border='1' bgcolor='lightgreen' height='39'>
	<tr bgcolor='bluegreen'>
	    <th align=center width='5%' height=15 colspan=1>
		<font size=2><stripes:label for="product"/></font>
	    </th>
	    <th align=center width='5%' height=15 colspan=1>
		<font size=2><stripes:label for="description"/></font>
	    </th>
	    <th align=center width='5%' height=15 colspan=1>
		<font size=2><stripes:label for="group"/></font>
	    </th>
	</tr>
	<c:forEach items="${prodSelector.groupList}" var="prodGrp">
	    <tr bgcolor='bluegreen'>
		<td align='left' width='10%' height='19' bgcolor='lightyellow'>
		    <c:out value="${prodGrp.key}"/>
		</td>
		<c:forEach items="${prodGrp.value}" var="pdescGrp">
		    <td align='left' width='10%' height='19' bgcolor='lightyellow'>
			<c:out value="${pdescGrp.key}"/>
		    </td>
		    <td>
			<c:if test='${prodGrp.key=="A"}'>
			    <select size="1" name="userDetail.user_pa_auth">
			</c:if>
			<c:if test='${prodGrp.key=="B"}'>
			    <select size="1" name="userDetail.user_pb_auth">
			</c:if>
			<c:if test='${prodGrp.key=="C"}'>
			    <select size="1" name="userDetail.user_pc_auth">
			</c:if>
			<c:if test='${prodGrp.key=="D"}'>
			    <select size="1" name="userDetail.user_pd_auth">
			</c:if>
			<c:if test='${prodGrp.key=="E"}'>
			    <select size="1" name="userDetail.user_pe_auth">
			</c:if>
			<c:if test='${prodGrp.key=="F"}'>
			    <select size="1" name="userDetail.user_pf_auth">
			</c:if>
			<c:if test='${prodGrp.key=="G"}'>
			    <select size="1" name="userDetail.user_pg_auth">
			</c:if>
			<c:if test='${prodGrp.key=="H"}'>
			    <select size="1" name="userDetail.user_ph_auth">
			</c:if>
			<c:if test='${prodGrp.key=="I"}'>
			    <select size="1" name="userDetail.user_pi_auth">
			</c:if>
			<c:if test='${prodGrp.key=="J"}'>
			    <select size="1" name="userDetail.user_pj_auth">
			</c:if>
			<c:if test='${prodGrp.key=="K"}'>
			    <select size="1" name="userDetail.user_pk_auth">
			</c:if>
			<c:if test='${prodGrp.key=="L"}'>
			    <select size="1" name="userDetail.user_pl_auth">
			</c:if>
			<c:if test='${prodGrp.key=="M"}'>
			    <select size="1" name="userDetail.user_pm_auth">
			</c:if>
			<c:if test='${prodGrp.key=="N"}'>
			    <select size="1" name="userDetail.user_pn_auth">
			</c:if>
			<c:if test='${prodGrp.key=="O"}'>
			    <select size="1" name="userDetail.user_po_auth">
			</c:if>
			<c:if test='${prodGrp.key=="P"}'>
			    <select size="1" name="userDetail.user_pp_auth">
			</c:if>
			<c:if test='${prodGrp.key=="Q"}'>
			    <select size="1" name="userDetail.user_pq_auth">
			</c:if>
			<c:if test='${prodGrp.key=="R"}'>
			    <select size="1" name="userDetail.user_pr_auth">
			</c:if>
			<c:if test='${prodGrp.key=="S"}'>
			    <select size="1" name="userDetail.user_ps_auth">
			</c:if>
			<c:if test='${prodGrp.key=="T"}'>
			    <select size="1" name="userDetail.user_pt_auth">
			</c:if>
			<c:if test='${userSelector.actionFlag=="new_user"}'>
			    <option selected>NONE</option>
				<c:forEach items="${pdescGrp.value}" var="Grp">
				    <option><c:out value="${Grp}"/></option>
				</c:forEach>
			</c:if>
			<c:if test='${userSelector.actionFlag=="Modify"}'>
			  <c:if test='${prodGrp.key=="A"}'>
				<c:forEach items="${pdescGrp.value}" var="Grp">
				    <c:if test='${userDetail.user_pa_auth==Grp}'>
					<option selected><c:out value="${Grp}"/></option>
				    </c:if>
				    <c:if test='${userDetail.user_pa_auth!=Grp}'>
					<option><c:out value="${Grp}"/></option>
				    </c:if>
				</c:forEach>
				<c:if test='${userDetail.user_pa_auth==" "}'>
				    <option selected>NONE</option>
				</c:if>
				<c:if test='${userDetail.user_pa_auth!=" "}'>
				    <option>NONE</option>
				</c:if>
			  </c:if>
			  <c:if test='${prodGrp.key=="B"}'>
				<c:forEach items="${pdescGrp.value}" var="Grp">
				    <c:if test='${userDetail.user_pb_auth==Grp}'>
					<option selected><c:out value="${Grp}"/></option>
				    </c:if>
				    <c:if test='${userDetail.user_pb_auth!=Grp}'>
					<option><c:out value="${Grp}"/></option>
				    </c:if>
				</c:forEach>
				<c:if test='${userDetail.user_pb_auth==" "}'>
				    <option selected>NONE</option>
				</c:if>
				<c:if test='${userDetail.user_pb_auth!=" "}'>
				    <option>NONE</option>
				</c:if>
			  </c:if>
			  <c:if test='${prodGrp.key=="C"}'>
				<c:forEach items="${pdescGrp.value}" var="Grp">
				    <c:if test='${userDetail.user_pc_auth==Grp}'>
					<option selected><c:out value="${Grp}"/></option>
				    </c:if>
				    <c:if test='${userDetail.user_pc_auth!=Grp}'>
					<option><c:out value="${Grp}"/></option>
				    </c:if>
				</c:forEach>
				<c:if test='${userDetail.user_pc_auth==" "}'>
				    <option selected>NONE</option>
				</c:if>
				<c:if test='${userDetail.user_pc_auth!=" "}'>
				    <option>NONE</option>
				</c:if>
			  </c:if>
			  <c:if test='${prodGrp.key=="D"}'>
				<c:forEach items="${pdescGrp.value}" var="Grp">
				    <c:if test='${userDetail.user_pd_auth==Grp}'>
					<option selected><c:out value="${Grp}"/></option>
				    </c:if>
				    <c:if test='${userDetail.user_pd_auth!=Grp}'>
					<option><c:out value="${Grp}"/></option>
				    </c:if>
				</c:forEach>
				<c:if test='${userDetail.user_pd_auth==" "}'>
				    <option selected>NONE</option>
				</c:if>
				<c:if test='${userDetail.user_pd_auth!=" "}'>
				    <option>NONE</option>
				</c:if>
			  </c:if>
			  <c:if test='${prodGrp.key=="E"}'>
				<c:forEach items="${pdescGrp.value}" var="Grp">
				    <c:if test='${userDetail.user_pe_auth==Grp}'>
					<option selected><c:out value="${Grp}"/></option>
				    </c:if>
				    <c:if test='${userDetail.user_pe_auth!=Grp}'>
					<option><c:out value="${Grp}"/></option>
				    </c:if>
				</c:forEach>
				<c:if test='${userDetail.user_pe_auth==" "}'>
				    <option selected>NONE</option>
				</c:if>
				<c:if test='${userDetail.user_pe_auth!=" "}'>
				    <option>NONE</option>
				</c:if>
			  </c:if>
			  <c:if test='${prodGrp.key=="F"}'>
				<c:forEach items="${pdescGrp.value}" var="Grp">
				    <c:if test='${userDetail.user_pf_auth==Grp}'>
					<option selected><c:out value="${Grp}"/></option>
				    </c:if>
				    <c:if test='${userDetail.user_pf_auth!=Grp}'>
					<option><c:out value="${Grp}"/></option>
				    </c:if>
				</c:forEach>
				<c:if test='${userDetail.user_pf_auth==" "}'>
				    <option selected>NONE</option>
				</c:if>
				<c:if test='${userDetail.user_pf_auth!=" "}'>
				    <option>NONE</option>
				</c:if>
			  </c:if>
			  <c:if test='${prodGrp.key=="G"}'>
				<c:forEach items="${pdescGrp.value}" var="Grp">
				    <c:if test='${userDetail.user_pg_auth==Grp}'>
					<option selected><c:out value="${Grp}"/></option>
				    </c:if>
				    <c:if test='${userDetail.user_pg_auth!=Grp}'>
					<option><c:out value="${Grp}"/></option>
				    </c:if>
				</c:forEach>
				<c:if test='${userDetail.user_pg_auth==" "}'>
				    <option selected>NONE</option>
				</c:if>
				<c:if test='${userDetail.user_pg_auth!=" "}'>
				    <option>NONE</option>
				</c:if>
			  </c:if>
			  <c:if test='${prodGrp.key=="H"}'>
				<c:forEach items="${pdescGrp.value}" var="Grp">
				    <c:if test='${userDetail.user_ph_auth==Grp}'>
					<option selected><c:out value="${Grp}"/></option>
				    </c:if>
				    <c:if test='${userDetail.user_ph_auth!=Grp}'>
					<option><c:out value="${Grp}"/></option>
				    </c:if>
				</c:forEach>
				<c:if test='${userDetail.user_ph_auth==" "}'>
				    <option selected>NONE</option>
				</c:if>
				<c:if test='${userDetail.user_ph_auth!=" "}'>
				    <option>NONE</option>
				</c:if>
			  </c:if>
			  <c:if test='${prodGrp.key=="I"}'>
				<c:forEach items="${pdescGrp.value}" var="Grp">
				    <c:if test='${userDetail.user_pi_auth==Grp}'>
					<option selected><c:out value="${Grp}"/></option>
				    </c:if>
				    <c:if test='${userDetail.user_pi_auth!=Grp}'>
					<option><c:out value="${Grp}"/></option>
				    </c:if>
				</c:forEach>
				<c:if test='${userDetail.user_pi_auth==" "}'>
				    <option selected>NONE</option>
				</c:if>
				<c:if test='${userDetail.user_pi_auth!=" "}'>
				    <option>NONE</option>
				</c:if>
			  </c:if>
			  <c:if test='${prodGrp.key=="J"}'>
				<c:forEach items="${pdescGrp.value}" var="Grp">
				    <c:if test='${userDetail.user_pj_auth==Grp}'>
					<option selected><c:out value="${Grp}"/></option>
				    </c:if>
				    <c:if test='${userDetail.user_pj_auth!=Grp}'>
					<option><c:out value="${Grp}"/></option>
				    </c:if>
				</c:forEach>
				<c:if test='${userDetail.user_pj_auth==" "}'>
				    <option selected>NONE</option>
				</c:if>
				<c:if test='${userDetail.user_pj_auth!=" "}'>
				    <option>NONE</option>
				</c:if>
			  </c:if>
			  <c:if test='${prodGrp.key=="K"}'>
				<c:forEach items="${pdescGrp.value}" var="Grp">
				    <c:if test='${userDetail.user_pk_auth==Grp}'>
					<option selected><c:out value="${Grp}"/></option>
				    </c:if>
				    <c:if test='${userDetail.user_pk_auth!=Grp}'>
					<option><c:out value="${Grp}"/></option>
				    </c:if>
				</c:forEach>
				<c:if test='${userDetail.user_pk_auth==" "}'>
				    <option selected>NONE</option>
				</c:if>
				<c:if test='${userDetail.user_pk_auth!=" "}'>
				    <option>NONE</option>
				</c:if>
			  </c:if>
			  <c:if test='${prodGrp.key=="L"}'>
				<c:forEach items="${pdescGrp.value}" var="Grp">
				    <c:if test='${userDetail.user_pl_auth==Grp}'>
					<option selected><c:out value="${Grp}"/></option>
				    </c:if>
				    <c:if test='${userDetail.user_pl_auth!=Grp}'>
					<option><c:out value="${Grp}"/></option>
				    </c:if>
				</c:forEach>
				<c:if test='${userDetail.user_pl_auth==" "}'>
				    <option selected>NONE</option>
				</c:if>
				<c:if test='${userDetail.user_pl_auth!=" "}'>
				    <option>NONE</option>
				</c:if>
			  </c:if>
			  <c:if test='${prodGrp.key=="M"}'>
				<c:forEach items="${pdescGrp.value}" var="Grp">
				    <c:if test='${userDetail.user_pm_auth==Grp}'>
					<option selected><c:out value="${Grp}"/></option>
				    </c:if>
				    <c:if test='${userDetail.user_pm_auth!=Grp}'>
					<option><c:out value="${Grp}"/></option>
				    </c:if>
				</c:forEach>
				<c:if test='${userDetail.user_pm_auth==" "}'>
				    <option selected>NONE</option>
				</c:if>
				<c:if test='${userDetail.user_pm_auth!=" "}'>
				    <option>NONE</option>
				</c:if>
			  </c:if>
			  <c:if test='${prodGrp.key=="N"}'>
				<c:forEach items="${pdescGrp.value}" var="Grp">
				    <c:if test='${userDetail.user_pn_auth==Grp}'>
					<option selected><c:out value="${Grp}"/></option>
				    </c:if>
				    <c:if test='${userDetail.user_pn_auth!=Grp}'>
					<option><c:out value="${Grp}"/></option>
				    </c:if>
				</c:forEach>
				<c:if test='${userDetail.user_pn_auth==" "}'>
				    <option selected>NONE</option>
				</c:if>
				<c:if test='${userDetail.user_pn_auth!=" "}'>
				    <option>NONE</option>
				</c:if>
			  </c:if>
			  <c:if test='${prodGrp.key=="O"}'>
				<c:forEach items="${pdescGrp.value}" var="Grp">
				    <c:if test='${userDetail.user_po_auth==Grp}'>
					<optionselected><c:out value="${Grp}"/></option>
				    </c:if>
				    <c:if test='${userDetail.user_po_auth!=Grp}'>
					<option><c:out value="${Grp}"/></option>
				    </c:if>
				</c:forEach>
				<c:if test='${userDetail.user_po_auth==" "}'>
				    <option selected>NONE</option>
				</c:if>
				<c:if test='${userDetail.user_po_auth!=" "}'>
				    <option>NONE</option>
				</c:if>
			  </c:if>
			  <c:if test='${prodGrp.key=="P"}'>
				<c:forEach items="${pdescGrp.value}" var="Grp">
				    <c:if test='${userDetail.user_pp_auth==Grp}'>
					<option selected><c:out value="${Grp}"/></option>
				    </c:if>
				    <c:if test='${userDetail.user_pp_auth!=Grp}'>
					<option><c:out value="${Grp}"/></option>
				    </c:if>
				</c:forEach>
				<c:if test='${userDetail.user_pp_auth==" "}'>
				    <option selected>NONE</option>
				</c:if>
				<c:if test='${userDetail.user_pp_auth!=" "}'>
				    <option>NONE</option>
				</c:if>
			  </c:if>
			  <c:if test='${prodGrp.key=="Q"}'>
				<c:forEach items="${pdescGrp.value}" var="Grp">
				    <c:if test='${userDetail.user_pq_auth==Grp}'>
					<option selected><c:out value="${Grp}"/></option>
				    </c:if>
				    <c:if test='${userDetail.user_pq_auth!=Grp}'>
					<option><c:out value="${Grp}"/></option>
				    </c:if>
				</c:forEach>
				<c:if test='${userDetail.user_pq_auth==" "}'>
				    <option selected>NONE</option>
				</c:if>
				<c:if test='${userDetail.user_pq_auth!=" "}'>
				    <option>NONE</option>
				</c:if>
			  </c:if>
			  <c:if test='${prodGrp.key=="R"}'>
				<c:forEach items="${pdescGrp.value}" var="Grp">
				    <c:if test='${userDetail.user_pr_auth==Grp}'>
					<option selected><c:out value="${Grp}"/></option>
				    </c:if>
				    <c:if test='${userDetail.user_pr_auth!=Grp}'>
					<option><c:out value="${Grp}"/></option>
				    </c:if>
				</c:forEach>
				<c:if test='${userDetail.user_pr_auth==" "}'>
				    <option selected>NONE</option>
				</c:if>
				<c:if test='${userDetail.user_pr_auth!=" "}'>
				    <option>NONE</option>
				</c:if>
			  </c:if>
			  <c:if test='${prodGrp.key=="S"}'>
				<c:forEach items="${pdescGrp.value}" var="Grp">
				    <c:if test='${userDetail.user_ps_auth==Grp}'>
					<option selected><c:out value="${Grp}"/></option>
				    </c:if>
				    <c:if test='${userDetail.user_ps_auth!=Grp}'>
					<option><c:out value="${Grp}"/></option>
				    </c:if>
				</c:forEach>
				<c:if test='${userDetail.user_ps_auth==" "}'>
				    <option selected>NONE</option>
				</c:if>
				<c:if test='${userDetail.user_ps_auth!=" "}'>
				    <option>NONE</option>
				</c:if>
			  </c:if>
			  <c:if test='${prodGrp.key=="T"}'>
				<c:forEach items="${pdescGrp.value}" var="Grp">
				    <c:if test='${userDetail.user_pt_auth==Grp}'>
					<option selected><c:out value="${Grp}"/></option>
				    </c:if>
				    <c:if test='${userDetail.user_pt_auth!=Grp}'>
					<option><c:out value="${Grp}"/></option>
				    </c:if>
				</c:forEach>
				<c:if test='${userDetail.user_pt_auth==" "}'>
				    <option selected>NONE</option>
				</c:if>
				<c:if test='${userDetail.user_pt_auth!=" "}'>
				    <option>NONE</option>
				</c:if>
			  </c:if>
<%--
			  <c:if test='${userSelector.actionFlag=="Modify"}'>
				<option>NONE</option>
			  </c:if>
--%>
			</c:if>
			<c:if test='${userSelector.actionFlag!="new_user"}'>
			<c:if test='${userSelector.actionFlag!="Modify"}'>
			  <c:if test='${prodGrp.key=="A"}'>
				<option selected><c:out value="${userDetail.user_pa_auth}"/></option>
			  </c:if>
			  <c:if test='${prodGrp.key=="B"}'>
				<option selected><c:out value="${userDetail.user_pb_auth}"/></option>
			  </c:if>
			  <c:if test='${prodGrp.key=="C"}'>
				<option selected><c:out value="${userDetail.user_pc_auth}"/></option>
			  </c:if>
			  <c:if test='${prodGrp.key=="D"}'>
				<option selected><c:out value="${userDetail.user_pd_auth}"/></option>
			  </c:if>
			  <c:if test='${prodGrp.key=="E"}'>
				<option selected><c:out value="${userDetail.user_pe_auth}"/></option>
			  </c:if>
			  <c:if test='${prodGrp.key=="F"}'>
				<option selected><c:out value="${userDetail.user_pf_auth}"/></option>
			  </c:if>
			  <c:if test='${prodGrp.key=="G"}'>
				<option selected><c:out value="${userDetail.user_pg_auth}"/></option>
			  </c:if>
			  <c:if test='${prodGrp.key=="H"}'>
				<option selected><c:out value="${userDetail.user_ph_auth}"/></option>
			  </c:if>
			  <c:if test='${prodGrp.key=="I"}'>
				<option selected><c:out value="${userDetail.user_pi_auth}"/></option>
			  </c:if>
			  <c:if test='${prodGrp.key=="J"}'>
				<option selected><c:out value="${userDetail.user_pj_auth}"/></option>
			  </c:if>
			  <c:if test='${prodGrp.key=="K"}'>
				<option selected><c:out value="${userDetail.user_pk_auth}"/></option>
			  </c:if>
			  <c:if test='${prodGrp.key=="L"}'>
				<option selected><c:out value="${userDetail.user_pl_auth}"/></option>
			  </c:if>
			  <c:if test='${prodGrp.key=="M"}'>
				<option selected><c:out value="${userDetail.user_pm_auth}"/></option>
			  </c:if>
			  <c:if test='${prodGrp.key=="N"}'>
				<option selected><c:out value="${userDetail.user_pn_auth}"/></option>
			  </c:if>
			  <c:if test='${prodGrp.key=="O"}'>
				<option selected><c:out value="${userDetail.user_po_auth}"/></option>
			  </c:if>
			  <c:if test='${prodGrp.key=="P"}'>
				<option selected><c:out value="${userDetail.user_pp_auth}"/></option>
			  </c:if>
			  <c:if test='${prodGrp.key=="Q"}'>
				<option selected><c:out value="${userDetail.user_pq_auth}"/></option>
			  </c:if>
			  <c:if test='${prodGrp.key=="R"}'>
				<option selected><c:out value="${userDetail.user_pr_auth}"/></option>
			  </c:if>
			  <c:if test='${prodGrp.key=="S"}'>
				<option selected><c:out value="${userDetail.user_ps_auth}"/></option>
			  </c:if>
			  <c:if test='${prodGrp.key=="T"}'>
				<option selected><c:out value="${userDetail.user_pt_auth}"/></option>
			  </c:if>
			</c:if>
			</c:if>
		    </select>
		</td>
<%----%>
		</c:forEach>
	    </tr>
	</c:forEach>
</table>

<table colspan='6' width='80%' align='center' border='1' bgcolor='lightgreen' height='39'>
	<tr bgcolor=bluegreen>
	    <th align=center width='5%' height=15 colspan=1>
		<font size=2><stripes:label for="passwordChanged"/></font>
	    </th>
	    <td align='left' width='5%' height='19' >
		<c:out value="${userDetail.user_pass_lastmodified_f}"/>
	    </td>
	    <th align=center width='5%' height=15 colspan=1>
		<font size=2><stripes:label for="passwordExpires"/></font>
	    </th>
	    <td align='left' width='5%' height='19' >
		<c:out value="${userDetail.user_pass_expiry_f}"/>
	    </td>
	    <th align=center width='5%' height=15 colspan=1>
		<font size=2><stripes:label for="firstLogin"/></font>
	    </th>
	    <td align='left' width='5%' height='19' >
		<c:out value="${userDetail.user_first_login_f}"/>
	    </td>
	</tr>
	<tr bgcolor=bluegreen>
	    <th align=center width='5%' height=15 colspan=1>
		<font size=2><stripes:label for="lastLogin"/></font>
	    </th>
	    <td align='left' width='5%' height='19' >
		<c:out value="${userDetail.user_last_success_login_f}"/>
	    </td>
	    <th align=center width='5%' height=15>
		<font size=2><stripes:label for="lastFailure"/></font>
	    </th>
	    <td align='left' width='5%' height='19' >
		<c:out value="${userDetail.user_last_failed_login_f}"/>
	    </td>
	    <th align=center width='5%' height=15>
		<font size=2><stripes:label for="failedAttempts"/></font>
	    </th>
	    <td align='left' width='5%' height='19' >
		<c:out value="${userDetail.user_failed_attempts}"/>
	    </td>
	</tr>
</table>
</center>
</div> <%-- selres --%>

    <center>
	<stripes:errors/>
<%--
	<c:out value='${userSelector.accessFlag}'/>
	<c:out value='${userSelector.actionFlag}'/>
--%>
	<c:if test='${userSelector.accessFlag!="inq"}'>
	    <c:if test='${userSelector.actionFlag=="Modify"}'>
		<stripes:submit name="Update"  value="Update User"/>
	    </c:if>
	    <c:if test='${userSelector.actionFlag=="delete_confirm"}'>
		<stripes:submit name="DeleteConfirm"  value="Confirm Delete User"/>
	    </c:if>
	    <c:if test='${userSelector.actionFlag=="update_confirm"}'>
		<stripes:submit name="Confirm"  value="Confirm Update User"/>
	    </c:if>
	    <c:if test='${userSelector.actionFlag=="add_confirm"}'>
		<stripes:submit name="Confirm"  value="Confirm Add User"/>
	    </c:if>
	    <c:if test='${userSelector.actionFlag=="new_user"}'>
		<stripes:submit name="Add"  value="Add User"/>
	    </c:if>
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

