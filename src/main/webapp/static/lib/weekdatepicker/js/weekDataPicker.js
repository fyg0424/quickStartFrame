/**
 * Created by Zhou Cailiang on 2015/9/8.
 * Jquery plugin: WeekDataPicker
 *
 * Require Bootstrap.js && Jquery.js
 *
 * Copyright 2015, WWW.SHIJIE99.COM
 * Released under the Apache license
 *
 * Date 2015-09-08 18:00:00
 */

(function($, undefined){
    $.extend($.fn, {
        weekDataPicker: function(settings){
            var params = $.extend({
                    chooseColor: 'greenyellow',
                    chooseRgb: 'rgb(173, 255, 47)',
                    unChooseColor: '#eee',
                    mode: "read",   //read/edit
                    data: undefined
                }, settings);


            $(this).append('<div class="row" style="margin-bottom: 6px;">');
            if(params.mode == "read") {
                $(this).append('    <div style="width: 200px; float: left;">');
                $(this).append('        <div style="float: left; margin-left: 440px; margin-bottom: 6px;"><span class="digitspan">&nbsp;</span> </div>');
            } else {
                $(this).append('    <div style="width: 140px; float: left"><label>请选择投放时间段:&nbsp;&nbsp;</label></div>');
                $(this).append('    <div style="width: 100px; float: left; left: 160px;"><input id="xs" type="checkbox" checked><label for="xs-1">全选/取消</label></div>');
                $(this).append('    <div style="width: 200px; float: left; ">');
                $(this).append('        <div style="float: left; margin-left: 200px;"><span class="digitspan">&nbsp;</span> </div>');
            }

            $(this).append('        <div style="float: left; margin-left: 2px;">投放时间段 </div>');
            $(this).append('        <div style="float: left; margin-left: 2px;"><span class="digitspan" style="background-color: ' + params.unChooseColor + '">&nbsp;</span> </div>');
            $(this).append('        <div style="float: left; margin-left: 2px;">暂停时间段 </div>');
            $(this).append('    </div>');
            $(this).append('</div>');

            for(var i = 1; i <= 7; i++) {
                var name = '&nbsp;&nbsp;';
                switch (i) {
                    case 1:
                        name += "星期一";
                        break;
                    case 2:
                        name += "星期二";
                        break;
                    case 3:
                        name += "星期三";
                        break;
                    case 4:
                        name += "星期四";
                        break;
                    case 5:
                        name += "星期五";
                        break;
                    case 6:
                        name += "星期六";
                        break;
                    default:
                        name += "星期日";
                        break;
                }

                $(this).append('<div class="row">');
                if(params.mode == "edit") {
                    $(this).append('  <div style="width:70px; float: left;"><input id="xs-' + i + '" type="checkbox" checked><label for="xs-' + i + '">' + name + '</label></div>');
                } else {
                    $(this).append('  <div style="width:70px; float: left;"><label>' + name + '</label></div>');
                }
                $(this).append('  <div style="width: 560px; float: left; left: 80px;">');

                for(var j = 0; j < 24; j++) {
                    if(j % 6 == 0) {
                        $(this).append('    <div style="float: left; margin-left: 10px; "><span class="digitspan" id="xs-' + i + '-' + j + '">' + j + '</span> </div>');
                    } else {
                        $(this).append('    <div style="float: left; margin-left: 2px; "><span class="digitspan" id="xs-' + i + '-' + j + '">' + j + '</span> </div>');
                    }
                }

                $(this).append('  </div>');
                $(this).append('</div>');
            }

            return new WeekDateEvent(params, $(this));
        }
    });

    var WeekDateEvent =  function(params, container) {
        this.params = params;

        if(params.data != undefined) {
            WeekDateEvent.setWithData(params, container);
        }

        if(params.mode == "edit") {
            WeekDateEvent.callEvent(params, container);
        }

    }

    WeekDateEvent.setWithData = function(params, container) {
        for(var weekday in params.data) {
            for(var day in params.data[weekday]) {
                if(params.data[weekday][day] == "0") {
                    $('#' + day).css("background-color", params.unChooseColor);
                    $('#' + day).css("font-weight", "normal");
                } else {
                    $(this).css("background-color", params.chooseColor);
                    $(this).css("font-weight", "bolder");
                }
            }

            if(params.mode == "edit") {
                var isAll = true;
                $('span[id^="' + weekday + '"]').each(function () {
                    if ($(this).css("background-color") != params.chooseRgb) {
                        isAll = false;
                    }
                });

                if (isAll) {
                    $('#' + weekday).prop("checked", true);
                } else {
                    $('#' + weekday).prop("checked", false);
                }
            }
        }

        if(params.mode == "edit") {
            WeekDateEvent.isAll();
        }
    }

    WeekDateEvent.isAll = function() {
        var isAllChecked = true;
        $('input[id^="xs-"]').each(function(){
            if(!$(this).is(':checked')) {
                isAllChecked = false;
            }
        });

        if(isAllChecked) {
            $('#xs').prop("checked",true);
        } else {
            $('#xs').prop("checked",false);
        }
    }

    WeekDateEvent.callEvent = function(params, container) {
        container.delegate('span[id^="xs-"]', 'click', function(){
            var id = $(this).attr("id");
            var inputid = id.split("-")[0] + "-" + id.split("-")[1];

            if($(this).css("background-color") == params.chooseRgb) {
                $(this).css("background-color", params.unChooseColor);
                $(this).css("font-weight", "normal");
                $('#' + inputid).prop("checked", false);
            } else {
                $(this).css("background-color", params.chooseColor);
                $(this).css("font-weight", "bolder");
            }

            var isAllChecked = true;
            $('span[id^="' + inputid + '"]').each(function(){
                if($(this).css("background-color") != params.chooseRgb) {
                    isAllChecked = false;
                }
            });

            if(isAllChecked) {
                $('#' + inputid).prop("checked",true);
            } else {
                $('#' + inputid).prop("checked",false);
            }

            WeekDateEvent.isAll();
        });

        container.delegate('input[id^="xs-"]', 'click', function(){
            var id = $(this).attr("id");
            if($(this).is(':checked')) {
                $('span[id^="' + id + '"]').each(function(){
                    $(this).css("background-color", params.chooseColor);
                    $(this).css("font-weight", "bolder");
                })
            } else {
                $('span[id^="' + id + '"]').each(function(){
                    $(this).css("background-color", params.unChooseColor);
                    $(this).css("font-weight", "normal");
                })
            }

            WeekDateEvent.isAll();
        });

        container.delegate("#xs", 'click', function(){
            if($(this).is(':checked')) {
                $('input[id^="xs-"]').each(function(){
                    if(!$(this).is(':checked')) {
                        $(this).prop("checked", true);
                    }

                    var id = $(this).attr('id');
                    $('span[id^="' + id + '"]').each(function(){
                        $(this).css("background-color", params.chooseColor);
                        $(this).css("font-weight", "bolder");
                    })
                })
            } else {
                $('input[id^="xs-"]').each(function(){
                    if($(this).is(':checked')) {
                        $(this).prop("checked", false);
                    }

                    var id = $(this).attr('id');
                    $('span[id^="' + id + '"]').each(function(){
                        $(this).css("background-color", params.unChooseColor);
                        $(this).css("font-weight", "normal");
                    })
                })
            }
        });
    }

    WeekDateEvent.prototype.getData = function() {
        that = this;
        var map = {};
        $('input[id^="xs-"]').each(function(){
            var inputid = $(this).attr("id");

            var innermap = {};
            $('span[id^="' + inputid + '"]').each(function(){
                var spanid = $(this).attr("id");
                if($(this).css("background-color") == that.params.chooseRgb) {
                    innermap[spanid] = '1';
                } else {
                    innermap[spanid] = '0';
                }
            })
            map[inputid] = innermap;
        })

        return map;
    }
})(jQuery);