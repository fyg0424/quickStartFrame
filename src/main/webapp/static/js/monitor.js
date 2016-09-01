/**
 * 
 */
var oTable;
$(function(){
	//初始化
	initDataTable();
});

function initDataTable(cid, ds, ipcc) {
	oTable = $('.example').DataTable({
		"dom": '<"top">rt<"bottom"ip><"clear">',
		"ajax": {
			"url": "majax",
		},
		"ordering": false,
		"aLengthMenu": [5, 10, 20, 50 ],
		"iDisplayLength" : 50,
		"oLanguage": {
			"sUrl": "../static/js/datatable.cn.txt"
		},
		"aoColumns": [
			{"mDataProp":"id"},
			{"mDataProp":"cid"},
			{"mDataProp":"dss"},
			{"mDataProp":"ipcc"},
			{"mDataProp":"status"}
		],
		"columnDefs": [
           {
               "render": function ( data, type, row ) {
            	   if(row.status == '1') {
            		   return '<span class="glyphicon glyphicon-ok" aria-hidden="true">一致</span>' +
            		   		  '<a class="btn btn-link" href="javascript:void(0);" title="查看" onclick="javascript:goFlight('+ row.id +')">查看</a>';
            	   } else {
            		   return '<span class="glyphicon glyphicon-warning-sign" aria-hidden="true">不一致</span>' +
            		   		  '<a class="btn btn-link" href="javascript:void(0);" title="查看" onclick="javascript:goFlight('+ row.id +')">查看</a>';
            	   }
               },
               "targets": 4
           }
       ]
	});
}