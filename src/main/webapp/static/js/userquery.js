/**
 * 
 */
var oTable;
$(function(){
	//初始化
	initDataTable();
	
	$('#search').click(function(){
		oTable.ajax.reload();
	});
});

function initDataTable() {
	oTable = $('.example').DataTable({
		"dom": '<"top">rt<"bottom"ip><"clear">',
		"ajax": {
			"type": "POST",
			"url": "query.ajax",
			"data": function(d) {
				d.searchname = $('#searchname').val(),
				d.querytype = $('#querytype').val();
			}
		},
		"ordering": false,
		"aLengthMenu": [5, 10, 20, 40 ],
		"iDisplayLength" : 10,
		"oLanguage": {
			"sUrl": "../static/js/datatable.cn.txt"
		},
		"aoColumns": [
		    {"mDataProp":"choose"},
			{"mDataProp":"orderid"},
			{"mDataProp":"id"},
			{"mDataProp":"nickname"},
			{"mDataProp":"username"},
			{"mDataProp":"roleNames"},
			{"mDataProp":"companyName"},
			{"mDataProp":"status"},
			{"mDataProp":"lastdate"},
			{"mDataProp":"op"}
		],
		"columnDefs": [
   			{
   				"render": function (data, type, row) {
         		   return '<input id="check_' + row.id + '" name="usercheck" type="checkbox" value="' + row.status + '"/>';
   				},
				"targets": 0
			},
			{
				"targets": 2,
				"visible": false
			},
			{
				"targets": 1,
				"visible": false
			},
            {
        	   "render": function (data, type, row) {
        		   if(row.status == 1) {
        			   return '<span>已审核&nbsp;&nbsp;</span>';
        		   } else {
        			   return '<span>待审核&nbsp;&nbsp;</span>';
        		   }
        	   },
        	   
        	   "targets": 7
            },
            {
                "render": function ( data, type, row ) {
             	   return '<a class="btn btn-info btn-xs" href="javascript:void(0);" onclick="javascript:showUser(' + row.id + ')" title="查看"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>查看</a>&nbsp;&nbsp;' +
             	   		  '<a class="btn btn-primary btn-xs" href="javascript:void(0);"  onclick="javascript:editUser(' + row.id + ')" title="编辑"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>编辑</a>&nbsp;&nbsp;' +
             	   		  '<a class="btn btn-danger btn-xs" href="javascript:void(0);"  onclick="javascript:delUser(\'' + row.id + '\')" title="删除"><span class="glyphicon glyphicon glyphicon-remove" aria-hidden="true"></span>删除</a>';
                },
                "targets": 9
            }
       ]
	});
}