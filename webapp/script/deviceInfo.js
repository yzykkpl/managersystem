//wifi 检测到设备信息 hy_list.html
$(function () {
//            debugger;
    $.ajax({
        type: "get",
        url: "/getAPInfo",
        dataType: "json",
        success: function (result) {
           // alert(result);
            var obj = eval(result);
            $("#yhlb tbody tr").remove();
            for (var i = 0; i < obj.length; i++) {
                var row = $("#row1").clone();
                row.find("#SSID1").text(obj[i].SSID);
                row.find("#MAC1").text(obj[i].MAC);
                row.find("#Channel1").text(obj[i].Channel);
                row.find("#Privacy1").text(obj[i].Privacy);
                row.find("#Clipher1").text(obj[i].Clipher);
                row.find("#Signal1").text(obj[i].Power);
                row.appendTo("#table");
            }
        }, error: function () {
            alert("加载失败");
        }
    });
})

