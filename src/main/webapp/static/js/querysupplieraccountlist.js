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
			"url": "querysupplieraccountlist",
			"data": function(d) {
				d.suppliername = $('#searchname').val();
			}
		},
		"processing" : true,
		"serverSide" : true,
		"ordering" : false,
		"aLengthMenu": [5, 10, 20, 40 ],
		"iDisplayLength" : 10,
		"oLanguage": {
			"sUrl": "../static/js/datatable.cn.txt"
		},
		"aoColumns": [
		    {"mDataProp":null},
			{"mDataProp":"suppliername"},
			{"mDataProp":"accountname"},
			{"mDataProp":"password"},
			{"mDataProp":"bsmanager"},
			{"mDataProp":"changetime"},
			{"mDataProp":"operator"}
		],
		"columnDefs": [
   			
            {
                "render": function ( data, type, row ) {
             	   return '<a class="btn btn-primary btn-xs" href="javascript:void(0);" onclick="javascript:editSupplierAccount(' + row.id + ')" title="修改"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改</a>&nbsp;&nbsp;  <a class="btn btn-danger btn-xs" href="javascript:void(0);" onclick="javascript:delSupplierAccount(' + row.id + ')" title="删除"><span class="glyphicon glyphicon glyphicon-remove" aria-hidden="true"></span>删除</a>&nbsp;&nbsp;' ;
                },
                "targets": 7
            }
       ]
	});
	//前台添加序号
	//前台添加序号
	oTable.on('draw.dt',function() {
		var info = oTable.page.info();
		oTable.column(0).nodes().each(function(cell,i) {
	        cell.innerHTML = (info.page)*(info.length) + (i+1);
	    });
	}).draw();
}
