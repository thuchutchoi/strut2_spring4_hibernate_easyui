$(document).ready(function () {
        var url;
        function newUser(){
            $('#dlg').dialog('open').dialog('center').dialog('setTitle','New User');
            $('#fm').form('clear');
            url = 'save_user';
        }
        function editUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $('#dlg').dialog('open').dialog('center').dialog('setTitle','Edit User');
                $('#fm').form('load',row);
                url = 'update_user?id='+row.id;
            }
        }
        function saveUser(){
            $('#fm').form('submit',{
                url: url,
                onSubmit: function(){
                    return $(this).form('validate');
                },
                success: function(result){
                    var result = eval('('+result+')');
                    if (result.errorMsg){
                        $.messager.show({
                            title: 'Error',
                            msg: result.errorMsg
                        });
                    } else {
                        $('#dlg').dialog('close');        // close the dialog
                        $('#dg').datagrid('reload');    // reload the user data
                    }
                }
            });
        }
        function destroyUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('Confirm','Are you sure you want to destroy this user?',function(r){
                    if (r){
                        $.post('destroy_user',{id:row.id},function(result){
                            if (result.success){
                                $('#dg').datagrid('reload');    // reload the user data
                            } else {
                                $.messager.show({    // show error message
                                    title: 'Error',
                                    msg: result.errorMsg
                                });
                            }
                        },'json');
                    }
                });
            }
        }

        $(".addBt").click(function() {
	        newUser();
	    });
        $(".editBt").click(function() {
	        editUser();
	    });
        $(".removeBt").click(function() {
	        destroyUser();
	    });
        $(".saveBt").click(function() {
	        saveUser();
	    });
        $(".searchBt").click(function() {
        	searchEmployee();
	    });
        $(".clearBt").click(function() {
        	 $('#fmSearch').form('clear');
	    });
        
        function searchEmployee(){
        	$('#dg').datagrid('load',{
        		 onBeforeLoad: function(param){
                     console.log("before load");
//                   param.firstname=$(".firstname").val();
//                   param.lastName=$(".lastName").val();
                 }, 
        		firstname: $(".firstname").val(),
        		lastName: $(".lastName").val()
               
    	    });
        }
        
//        $("#dg").datagrid({
//            title:'DataGrid例子',
//            width:600,
//            height:400,
//            pageSize:5,
//            pageList:[5,10,15,20],
//            nowrap:false,
//            striped:true,
//            collapsible:true,
//            url:'get_employee',
//            loadMsg:'数据加载中...',
//            sortName:'code',
//            sortOrder:'desc',
//            remoteSort:false,
//
//            pagination :true,//可以不要下面的 注释部分  要显示中文 可以easyui-lang-zh_CN.js
//            frozenColumns:[[     
//                  {field:'ck',checkbox:true}
//            ]],
//            columns:[[     
//                  {title:'学号',field:'id',width:'50',rowspan:2,align:'center'},
//                  {title:'姓名',field:'firstname',width:'60',rowspan:2,align:'center'},
//                  {title:'性别',field:'lastname',width:'50',rowspan:2,align:'center'},
//                  {title:'年龄',field:'phone',width:'50',rowspan:2,align:'center'},     
//                  {title:'班级',field:'email',width:'100',rowspan:2,align:'center'}    
//            ]],
//            pagination:true,    
//            rownumbers:true
//        });
        var a=$(".firstname").val();
        var b = $(".lastName").val();
        $('#dg').datagrid({
        	toolbar: '#toolbar',
        	width:780,
        	height:450,
        	title:'DataGrid',
            url:'get_employee.action',
//            sortName:'firstname',
//            sortOrder:'asc',
            nowrap:false,
            rownumbers:"true",
            iconCls:"icon-save",
            pagination:"true",
            multiSort:"true",
            rowStyler: function(index,row){
                if (row.firstname==="nghiant"){
                    return 'background-color:#6293BB;color:#fff;font-weight:bold;';
                }
                else
                {
                	return 'background-color:#555;color:#e67e22';
                }
            },
            onBeforeLoad: function(param){
            	
                console.log("before load");
                param.firstname=$(".firstname").val();
                param.lastName=$(".lastName").val();
            }, 
            columns:[[
                {field:'firstname',title:'First Name',width:175,align:'center',sortable:'true'},
                {field:'lastname',title:'Last Name',width:175,align:'center',sortable:'true'},
                {field:'telephone',title:'Phone',width:175,align:'center',sortable:'true'},
                {field:'email',title:'Email',width:175,align:'center',sortable:'true'}
            ]]
        });
        $(".saveBt2").click(function() {
        	jQuery(".testLbXSS").text(jQuery(".testXSS").val());
	    });
});