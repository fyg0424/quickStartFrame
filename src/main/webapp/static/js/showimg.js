/**
 * 
 */
$(function(){
	$('.example tbody').delegate('.showImg', 'mouseover', function () {
		showImg(this);
    });
	
	$('.example tbody').delegate('.showImg', 'mouseout', function () {
		hideImg(this);
    });
})

function showImg(obj) {
	var jsonData = oTable.rows(obj.parentElement.parentElement._DT_RowIndex).data()[0]['time'];
	
    $('#showData').html('');
    $('#showData').weekDataPicker({
        chooseClass: 'digitspan',
        unchooseClass: 'udigitspan',
        mode: 'read',
        data : JSON.parse(jsonData)
    });

    var $that = $(obj);
    $that.popover({
        html: true,
        trigger: 'hover',
        placement: 'left',
        template:'<div class="popover" role="tooltip"><div class="arrow"></div><div class="popover-content"></div></div>',
        content: $('#showData').html()
    });
    $('#showData').html('');
    $that.popover('show');
}

function hideImg(obj) {
    $(obj).popover('hide');
}