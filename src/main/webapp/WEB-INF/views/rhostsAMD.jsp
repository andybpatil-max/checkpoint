<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/sysadmin/actions/Rhosts.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<h1><stripes:label for="remoteHost"/> <stripes:label for="maintInq"/></h1>
	<div id="detail">
	    <table colspan='2' width='50%' align='center' border='1'>
		<tr bgcolor='bluegreen'>
		<c:if test='${rhostSelector.accessFlag=="inq"}'>
		    <th  class="header" align='center' height='19' colspan='2'>
			<font size=3 color=blue>
			    <stripes:label for="remotehost"/> 
			    <stripes:label for="inquiry"/>
			</font>
		    </th>
		</c:if>
		<c:if test='${rhostSelector.accessFlag!="inq"}'>
		    <c:if test='${rhostSelector.actionFlag=="Modify"}'>
		    	<th class="header" align='center' height='19' colspan='2'>
				<font size=3 color=blue>
				    <stripes:label for="modify"/> <stripes:label for="remoteHost"/>
				</font>
		   	</th>
		    </c:if>
		    <c:if test='${rhostSelector.actionFlag=="Delete"}'>
		    	<th class="header" align='center' height='19' colspan='2'>
				<font size=3 color=blue>
				    <stripes:label for="delete"/> <stripes:label for="remoteHost"/>
				</font>
		   	</th>
		    </c:if>
		    <c:if test='${rhostSelector.actionFlag=="New"}'>
		    	<th class="header" align='center' height='19' colspan='2'>
				<font size=3 color=blue>
				    <stripes:label for="add"/> <stripes:label for="remoteHost"/>
				</font>
		   	</th>
		    </c:if>
		    <c:if test='${rhostSelector.actionFlag=="Update"}'>
		    	<th class="header" align='center' height='19' colspan='2'>
				<font size=3 color=blue>
				    <stripes:label for="modify"/> <stripes:label for="remoteHost"/>
				</font>
			</th>
		    </c:if>
		    <c:if test='${rhostSelector.actionFlag=="Add"}'>
		    	<th class="header" align='center' height='19' colspan='2'>
				<font size=3 color=blue>
				    <stripes:label for="add"/> <stripes:label for="remoteHost"/>
				</font>
		   	</th>
		    </c:if>
		</c:if>
		</tr>
		<tr>
		<th align='right' bgcolor='lightyellow' width='20%' height='19'><b>
			<stripes:label for="nodeId"/></b></th>
		<td align='left' width=10% height='19'>
		    <c:if test='${rhostSelector.actionFlag=="New"}'>
			<stripes:text name='rhostDetail.rhosts_node_id' value='${rhostDetail.rhosts_node_id}'
				size='30' maxlength="30"/>
		    </c:if>
		    <c:if test='${rhostSelector.actionFlag!="New"}'>
			<stripes:text name='rhostDetail.rhosts_node_id' value='${rhostDetail.rhosts_node_id}'
				size='30' readonly='true'/>
		    </c:if>
		</td>
		</tr>
		<tr>
		<th align='right' bgcolor='lightyellow' width='20%' height='19'><b>
			<stripes:label for="node"/></b></th>
		<td align='left' width=10% height='19'>
		    <c:if test='${rhostSelector.actionFlag=="Modify"}'>
		    	<stripes:text name='rhostDetail.rhosts_rem_node' value='${rhostDetail.rhosts_rem_node}'
				size="30" maxlength="15"/>
		    </c:if>
		    <c:if test='${rhostSelector.actionFlag=="Delete"}'>
		    	<stripes:text name='rhostDetail.rhosts_rem_node' value='${rhostDetail.rhosts_rem_node}'
				size="30" maxlength="15"/>
		    </c:if>
		    <c:if test='${rhostSelector.actionFlag=="New"}'>
			<stripes:text name='rhostDetail.rhosts_rem_node' value='${rhostDetail.rhosts_rem_node}'
				size='30'  maxlength="15"/>
		    </c:if>
		    <c:if test='${rhostSelector.actionFlag=="Update"}'>
			<stripes:text name='rhostDetail.rhosts_rem_node' value='${rhostDetail.rhosts_rem_node}'
				size='30' readonly='true'/>
		    </c:if>
		    <c:if test='${rhostSelector.actionFlag=="Add"}'>
			<stripes:text name='rhostDetail.rhosts_rem_node' value='${rhostDetail.rhosts_rem_node}'
				size='30' readonly='true'/>
		    </c:if>
		</td>
		</tr>
		<tr>
		<th align='right' bgcolor='lightyellow' width='20%' height='19'><b>
			<stripes:label for="user"/></b></th>
		<td align='left' width=10% height='19'>
		    <c:if test='${rhostSelector.actionFlag=="Modify"}'>
		    	<stripes:text name='rhostDetail.rhosts_user_name'
			      value='${rhostDetail.rhosts_user_name}' size="15" maxlength="15"/>
		    </c:if>
		    <c:if test='${rhostSelector.actionFlag=="Delete"}'>
		    	<stripes:text name='rhostDetail.rhosts_user_name'
			      value='${rhostDetail.rhosts_user_name}' size="15" maxlength="15"/>
		    </c:if>
		    <c:if test='${rhostSelector.actionFlag=="New"}'>
			<stripes:text name='rhostDetail.rhosts_user_name'
			      value='${rhostDetail.rhosts_user_name}' size='15'  maxlength="15"/>
		    </c:if>
		    <c:if test='${rhostSelector.actionFlag=="Update"}'>
			<stripes:text name='rhostDetail.rhosts_user_name'
			      value='${rhostDetail.rhosts_user_name}' size='15' readonly='true'/>
		    </c:if>
		    <c:if test='${rhostSelector.actionFlag=="Add"}'>
			<stripes:text name='rhostDetail.rhosts_user_name' 
			      value='${rhostDetail.rhosts_user_name}' size='15' readonly='true'/>
		    </c:if>
		</td>
		</tr>
		<tr>
		<th align='right' bgcolor='lightyellow' width='20%' height='19'>
			<stripes:label for="password"/></th>
		<td align='left' height='15'>
		    <c:if test='${rhostSelector.actionFlag=="Modify"}'>
			<stripes:password name='rhostDetail.rhosts_user_pass' size="15" maxlength="15"/>
		    </c:if>
		    <c:if test='${rhostSelector.actionFlag=="Delete"}'>
			<stripes:password name='rhostDetail.rhosts_user_pass' value='${rhostDetail.rhosts_user_pass}'
				size="15" maxlength="15"/>
		    </c:if>
		    <c:if test='${rhostSelector.actionFlag=="New"}'>
			<stripes:password name='rhostDetail.rhosts_user_pass' value='${rhostDetail.rhosts_user_pass}'
				size="15" maxlength="15"/>
		    </c:if>
		    <c:if test='${rhostSelector.actionFlag=="Update"}'>
			<stripes:password name='rhostDetail.rhosts_user_pass' value='${rhostDetail.rhosts_user_pass}'
				size='15' readonly='true'/>
		    </c:if>
		    <c:if test='${rhostSelector.actionFlag=="Add"}'>
			<stripes:password name='rhostDetail.rhosts_user_pass' value='${rhostDetail.rhosts_user_pass}'
				size='15' readonly='true'/>
		    </c:if>
		</td>
		</tr>
		<tr>
		<th align='right' bgcolor='lightyellow' width='20%' height='19'>
			<stripes:label for="passwordVerify"/></th>
		<td align='left' height='15'>
		    <c:if test='${rhostSelector.actionFlag=="Modify"}'>
			<stripes:password name='rhostDetail.rhosts_user_passv' value='${rhostDetail.rhosts_user_passv}'
				size="15" maxlength="15"/>
		    </c:if>
		    <c:if test='${rhostSelector.actionFlag=="Delete"}'>
			<stripes:password name='rhostDetail.rhosts_user_passv' value='${rhostDetail.rhosts_user_passv}'
				size="15" maxlength="15"/>
		    </c:if>
		    <c:if test='${rhostSelector.actionFlag=="New"}'>
			<stripes:password name='rhostDetail.rhosts_user_passv' value='${rhostDetail.rhosts_user_passv}'
				size="15" maxlength="15"/>
		    </c:if>
		    <c:if test='${rhostSelector.actionFlag=="Update"}'>
			<stripes:password name='rhostDetail.rhosts_user_passv' value='${rhostDetail.rhosts_user_passv}'
				size='15' readonly='true'/>
		    </c:if>
		    <c:if test='${rhostSelector.actionFlag=="Add"}'>
			<stripes:password name='rhostDetail.rhosts_user_passv' value='${rhostDetail.rhosts_user_passv}'
				size='15' readonly='true'/>
		    </c:if>
		</td>
		</tr>
		<tr>
		<th align='right' bgcolor='lightyellow' width='20%' height='19'>
			<stripes:label for="xferMode"/></th>
		<td align='left' height='15'>
		    <c:if test='${rhostSelector.accessFlag!="inq"}'>
			<c:if test='${rhostSelector.actionFlag=="Modify"}'>
			    <select size="1" name="rhostDetail.rhostsXferMode">
				<option selected value="FTP">FTP</option>
				<option value="sFTP">Secure FTP</option>
			    </select>
			</c:if>
			<c:if test='${rhostSelector.actionFlag=="Delete"}'>
			    <stripes:text name='rhostDetail.rhostsXferMode' size="15" maxlength="15"/>
			</c:if>
			<c:if test='${rhostSelector.actionFlag=="New"}'>
			    <select size="1" name="rhostDetail.rhostsXferMode">
				<option selected value="FTP">FTP</option>
				<option value="sFTP">Secure FTP</option>
			    </select>
			</c:if>
			<c:if test='${rhostSelector.actionFlag=="Update"}'>
			    <stripes:text name='rhostDetail.rhostsXferMode' size='15' readonly='true'/>
			</c:if>
			<c:if test='${rhostSelector.actionFlag=="Add"}'>
			    <stripes:text name='rhostDetail.rhostsXferMode' size='15' readonly='true'/>
			</c:if>
		    </c:if>
		    <c:if test='${rhostSelector.accessFlag=="inq"}'>
			    <stripes:text name='rhostDetail.rhostsXferMode' size='15' readonly='true'/>
		    </c:if>
		</td>
		</tr>
	    </table>
	</div>


<%--													--%>
<%--	Submit buttons area to click on to take the next step.						--%>
<%--	Any errors will appear between the data table and the buttons.					--%>
<%--													--%>
<%--	<c:out value="${rhostSelector.actionFlag}"/>							--%>

	<center>
	    <c:if test='${rhostSelector.accessFlag!="inq"}'>
		<c:if test='${rhostSelector.actionFlag=="Modify"}'>
		    <stripes:submit name="Update" value="Update Remote Node"/>
		</c:if>
		<c:if test='${rhostSelector.actionFlag=="Update"}'>
		    <stripes:submit name="Confirm" value="Confirm Update Remote Node"/>
		</c:if>
		<c:if test='${rhostSelector.actionFlag=="Add"}'>
		    <stripes:submit name="Confirm" value="Confirm Add Remote Node"/>
		</c:if>
		<c:if test='${rhostSelector.actionFlag=="New"}'>
		    <stripes:submit name="Add" value="Add Remote Node"/>
		</c:if>
		<c:if test='${rhostSelector.actionFlag=="Delete"}'>
		    <stripes:submit name="ConfirmDelete"  value="Confirm Delete Remote Node"/>
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
