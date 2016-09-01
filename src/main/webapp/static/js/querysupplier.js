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
			"url": "querysupplierlist",
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
			{"mDataProp":"country"},
			{"mDataProp":"time_str"},
			{"mDataProp":"bsmanager"},
			{"mDataProp":"creator"},
			{"mDataProp":"details"}
		],
		"columnDefs": [
   			
		               {
		            	   "render": function ( data, type, row ) {
		            			   return '<a class="btn btn-info btn-xs" href="javascript:void(0);" onclick="querydetails('+row.id+')" title="查看"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>查看</a>&nbsp;&nbsp;' 
		            			   		+'<a class="btn btn-primary btn-xs"href="javascript:void(0);" onclick="modifysupplierconfig('+row.id+')" title="编辑"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>编辑</a>&nbsp;&nbsp;';
		            	   },
		            	   "targets": 6
		               }
       ]
	});
	//前台添加序号
	oTable.on('draw.dt',function() {
		var info = oTable.page.info();
		oTable.column(0).nodes().each(function(cell,i) {
	        cell.innerHTML = (info.page)*(info.length) + (i+1);
	    });
	}).draw();
}


/**
 * 查看详情
 */
function querydetails(id){
	window.location.href='queryListDetails?id='+id;
}
function modifysupplierconfig(id){
	window.location.href='modifySupplierConfig?id='+id;
}
