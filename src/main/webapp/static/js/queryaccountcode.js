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
			"url": "querycodeinfo"
		},
		/*"processing" : false,
		"serverSide" : false,*/
		"ordering" : false,
		"aLengthMenu": [5, 10, 20, 40 ],
		"iDisplayLength" : 10,
		"oLanguage": {
			"sUrl": "../static/js/datatable.cn.txt"
		},
		"aoColumns": [
		    {"mDataProp":null},
			{"mDataProp":"ipcc"},
			{"mDataProp":"hkcode"},
			{"mDataProp":"gds"},
			{"mDataProp":"accountcode"},
			{"mDataProp":"lasttimeStr"},
			{"mDataProp":"operator"},
			{"mDataProp":"status"},
			{"mDataProp":null},
		],
		"columnDefs": [
		               
		               	{
		               			"render": function ( data, type, row ) {
		               				if(row.status == 1){
		               					return "生效中";
		               				}
		               				else{
		               					return "失效";
		               				}
		               			},
		               				"targets": 7
		               		},
		               	{
		               			"render": function ( data, type, row ) {
		               				return '<a class="btn btn-primary btn-xs" href="javascript:void(0);" onclick="javascript:editAccountCode(' + row.id + ')" title="编辑"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>编辑</a>&nbsp;&nbsp;  <a class="btn btn-primary btn-xs" href="javascript:void(0);" onclick="javascript:editStatus(' + row.id + ')" title="修改状态"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改状态</a>&nbsp;&nbsp;' ;
		               			},
		               			  "targets": 8
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
