/**
 * 
 */
$(function(){
	var table = $('.example').DataTable({
		"dom": '<"top">rt<"bottom"ip><"clear">',
		"ajax": "demo/ajax",
		"ordering": false,
		"aLengthMenu": [5, 10, 20, 40 ],
		"iDisplayLength" : 5,
		"oLanguage": {
			"sUrl": "static/js/datatable.cn.txt"
		},
		"aoColumns": [
			{"mDataProp":"id"},
			{"mDataProp":"cname"},
			{"mDataProp":"ds"},
			{"mDataProp":"ipcc"},
			{"mDataProp":"status"},
			{"mDataProp":"temp"},
			{"mDataProp":"temp1"},
			{"mDataProp":"op"}
		],
		"columnDefs": [
           {
        	   "render": function (data, type, row) {
        		   if(row.status == "1") {
        			   return '<span>已开启&nbsp;&nbsp;</span><img id="img_' +  row.id + '" alt="Brand" src="static/images/u165.png" width="40" height="40">';
        		   } else {
        			   return '<span>已关闭&nbsp;&nbsp;</span>';
        		   }
        	   },
        	   
        	   "targets": 5
           },
           {
        	   "render": function (data, type, row) {
        		   if(row.status == "1") {
        			   return '<a href="http://www.baidu.com" title="111" style="text-decoration:underline;">设置航班投放时间</a>';
        		   } else {
        			   return '<a href="#" title="111" disabled="disabled">设置航班投放时间</a>';
        		   }
        	   },
        	   
        	   "targets": 4
           },
           {
        	   "render": function (data, type, row) {
        		   if(row.status == "1") {
        			   return '<a class="btn btn-success" href="#" title="111">关闭</a>';
        		   } else {
        			   return '<a class="btn btn-success" href="#" title="111">开启</a>';
        		   }
        	   },
        	   
        	   "targets": 6
           },
           {
               "render": function ( data, type, row ) {
            	   return '<a class="btn btn-success" href="#" title="111">编辑</a>&nbsp;&nbsp;' +
            	   		  '<a class="btn btn-success" href="#" title="111">删除</a>';
               },
               "targets": 7
           }
       ]
	});
})


//		"aoColumns": [
//			{ "data": "username" },
//			{ "data": "registerDate" },
//			{ "data": "rolename" },
//			{ "data": "state" },
//			{ "data": "op" }
//		],