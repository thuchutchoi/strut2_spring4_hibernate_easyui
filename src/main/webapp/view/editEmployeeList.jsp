<%@ taglib prefix="s" uri="/struts-tags"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Spring-4 + Struts-3 + Hibernate Integration Demo</title>
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/color.css">
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/demo/demo.css">
    <link rel="stylesheet" type="text/css" href="grid.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.6.min.js"></script>
    <script type="text/javascript" src="http://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="gridBase.js"></script>
    
</head>
<body>
 
<!-- <h2>Spring-4 + Struts-3 + Hibernate Integration Demo</h2> -->
 
<%-- <s:form method="post" action="add"> --%>
<!--     <table > -->
<!-- 	    <tr> -->
<%-- 	        <td><s:textfield key="label.firstname" name="employee.firstname"/></td>  --%>
<!-- 	    </tr> -->
<!-- 	    <tr> -->
<%-- 	        <td><s:textfield key="label.lastname" name="employee.lastname"/></td> --%>
<!-- 	    </tr> -->
<!-- 	    <tr> -->
<%-- 	        <td><s:textfield key="label.email" name="employee.email"/></td> --%>
<!-- 	    </tr> -->
<!-- 	    <tr> -->
<%-- 	        <td><s:textfield key="label.telephone" name="employee.telephone"/></td> --%>
<!-- 	    </tr> -->
<!-- 	    <tr> -->
<!-- 	        <td> -->
<%-- 	        	<s:submit key="label.add"></s:submit> --%>
<!-- 	        </td> -->
<!-- 	    </tr> -->
<!-- 	</table>  -->
<%-- </s:form> --%>
 
     
<!-- <h3>Employees</h3> -->
<%-- <c:if  test="${!empty employees}"> --%>
<!-- 	<table class="list"> -->
<!-- 		<tr> -->
<!-- 		    <th align="left">Name</th> -->
<!-- 		    <th align="left">Email</th> -->
<!-- 		    <th align="left">Telephone</th> -->
<!-- 		    <th align="left">Actions</th> -->
<!-- 		</tr> -->
<%-- 		<c:forEach items="${employees}" var="emp"> --%>
<!-- 		    <tr> -->
<%-- 		        <td>${emp.lastname}, ${emp.firstname} </td> --%>
<%-- 		        <td>${emp.email}</td> --%>
<%-- 		        <td>${emp.telephone}</td> --%>
<%-- 		        <td><a href="delete/${emp.id}">delete</a></td> --%>
<!-- 		    </tr> -->
<%-- 		</c:forEach> --%>
<!-- 	</table> -->
<%-- </c:if> --%>
 <form id="fmSearch" method="post" novalidate>
 	 <table cellpadding="5">
          <tr>
              <td>First Name:</td>
              <td><input class="easyui-textbox firstname" type="text" name="firstname"></input></td>
              <td>Last Name:</td>
              <td><input class="easyui-textbox lastname" type="text" name="lastname"></input></td>
              <td><a href="javascript:void(0)" class="easyui-linkbutton c6 searchBt" iconCls="icon-ok" style="width:90px">Search</a></td>
              <td><a href="javascript:void(0)" class="easyui-linkbutton c6 clearBt" iconCls="icon-ok" style="width:90px">Clear</a></td>
          </tr>
    </table>
 </form>
 <table id="dg"></table>
 <div id="toolbar">
     <a href="javascript:void(0)" class="easyui-linkbutton addBt" iconCls="icon-add" plain="true">New User</a>
     <a href="javascript:void(0)" class="easyui-linkbutton editBt" iconCls="icon-edit" plain="true">Edit User</a>
     <a href="javascript:void(0)" class="easyui-linkbutton removeBt" iconCls="icon-remove" plain="true">Remove User</a>
 </div>
 
 <div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
         closed="true" buttons="#dlg-buttons">
     <div class="ftitle">User Information</div>
     <form id="fm" method="post" novalidate>
         <div class="fitem">
             <label>First Name:</label>
             <input name="firstname" class="easyui-textbox" required="true">
         </div>
         <div class="fitem">
             <label>Last Name:</label>
             <input name="lastname" class="easyui-textbox" required="true">
         </div>
         <div class="fitem">
             <label>Phone:</label>
             <input name="telephone" class="easyui-textbox" required="true">
         </div>
         <div class="fitem">
             <label>Email:</label>
             <input name="email" class="easyui-textbox" required="true" validType="email">
         </div>
     </form>
 </div>
 <div id="dlg-buttons">
     <a href="javascript:void(0)" class="easyui-linkbutton c6 saveBt" iconCls="icon-ok" style="width:90px">Save</a>
     <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">Cancel</a>
 </div>
  <table > 
	    <tr>
	        <td><input class="easyui-textbox testXSS" required="true"></td> 
	        <td>
	        	<a href="javascript:void(0)" class="easyui-linkbutton c6 saveBt2" iconCls="icon-ok" style="width:90px">Save</a>
	        </td>
	        <td>
	        	 <div class="testLbXSS"></div>
	        </td>
	    </tr>
	</table> 
</body>
</html>