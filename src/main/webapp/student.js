    $(document).ready(function () {
        $("#tt").datagrid({
            title:'DataGrid例子',
            width:600,
            height:400,
          //  pageSize:5,
         //   pageList:[5,10,15,20],
         //   nowrap:false,
         //   striped:true,
         //   collapsible:true,
            url:'studentAction.action',
         //   loadMsg:'数据加载中...',
         //   sortName:'code',
         //   sortOrder:'desc',
         //   remoteSort:false,

           // pagination :true,//可以不要下面的 注释部分  要显示中文 可以easyui-lang-zh_CN.js
            frozenColumns:[[     
                  {field:'ck',checkbox:true}
            ]],
            columns:[[     
                  {title:'学号',field:'id',width:'50',align:'center'},
                  {title:'姓名',field:'name',width:'60',align:'center'},
                  {title:'性别',field:'sex',width:'50',align:'center'},
                  {title:'年龄',field:'age',width:'50',align:'center'},     
                  {title:'出生日期',field:'birthday',width:'120',align:'center'},     
                  {title:'班级',field:'className',width:'100',align:'center'}    
            ]],
            pagination:true,    
            rownumbers:true
        });

　　    /* $('#tt').datagrid('getPager').pagination({    
            displayMsg:'当前显示从{from}到{to}共{total}记录',    
            onBeforeRefresh:function(pageNumber, pageSize){     
                $(this).pagination('loading');     
                    //alert('pageNumber:'+pageNumber+',pageSize:'+pageSize);     
                $(this).pagination('loaded');    
            },   
         });
        */        
        var xmlhttp;
        function verify(){
            if(window.XMLHttpRequest){        
                xmlhttp = new XMLHttpRequest();        
                if(xmlhttp.overrideMimeType){            
                    xmlhttp.overrideMimeType("text/xml");        
                }    
            }else if(window.ActiveXObject){      
                var activerxName = ["MSXML2.XMLHTTP","Microsoft.XMLHTTP"];        
                for(var i=0;i<activerxName.length;i++){            
                    try{                 
                        xmlhttp = new ActiveXObject(activerxName[i]);              
                        break;            
                    }catch(e){ 
                    }        
                }    
            }
            if(!xmlhttp){        
                alert("XMLHttpRequest对象创建失败");        
                return;    
            }
            xmlhttp.onreadystatechange = callback;    
            xmlhttp.open("POST","studentAction.action?page=1&rows=5","true");    
            xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");    
            xmlhttp.send();
        }

        function callback(){  
            if(xmlhttp.readyState == 4){        
                if(xmlhttp.status == 200){        
                    var oBook = eval('(' + xmlhttp.responseText + ')');
                    $.messager.alert('test jsonData',xmlhttp.responseText);        
                }   
            }
        }
    });
