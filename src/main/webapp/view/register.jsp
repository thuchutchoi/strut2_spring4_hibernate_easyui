<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Page</title>
<s:head />
<style type="text/css">
@import url(style.css);
</style>
</head>
<body>
<s:actionerror/>
<s:form action="addUser">
	<s:hidden name="user.id" />
	<s:textfield key="user.name" />
	<s:password key="user.password" />
	<s:select list="{'Male','Female'}" headerKey=""
		key="user.gender" headerValue="Select" label="Gender"/>
	<s:select list="{'India','USA','UK'}" headerKey=""
		key="user.country" headerValue="Select" label="Country" />
	<s:textarea key="user.aboutYou" />
	<s:checkbox key="user.mailingList"
		label="Would you like to join our mailing list?" />
	<s:submit />
</s:form>


<s:if test="userList.size() > 0">
	<div class="content">
	<table class="userTable" cellpadding="5px">
		<tr class="even">
			<th>Name</th>
			<th>Gender</th>
			<th>Country</th>
			<th>About You</th>
			<th>Mailing List</th>
		</tr>
		<s:iterator value="userList" status="userStatus">
			<tr
				class="<s:if test="#userStatus.odd == true ">odd</s:if><s:else>even</s:else>">
				<td><s:property value="name" /></td>
				<td><s:property value="gender" /></td>
				<td><s:property value="country" /></td>
				<td><s:property value="aboutYou" /></td>
				<td><s:property value="mailingList" /></td>
			</tr>
		</s:iterator>
	</table>
	</div>
</s:if>
</body>
</html>