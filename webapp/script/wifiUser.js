//wifi 检测到设备信息 hy_list.html

$(function () {
	wifiDect();
    setInterval("test()",2000);
    $("#wifi tbody").html("");
});
    function wifiDect() {
    	
       	 var pass = {
       			demand : users
       		};
        $.ajax({
            type: "post",
            url: "",
            timeout: 60000,
            data: pass,
            async: true,
            dataType: "json",
            success: function (result) {
                var obj = eval(result);
                $("#wifi tbody tr").remove();
                for (var i = 0; i < obj.length; i++) {
                    var row = $("#row").clone();
                    row.find("#Mac").text(obj[i].MAC);
                    row.find("#time").text(obj[i].time);
                    row.find("#power").text(obj[i].Power);
                    row.find("#packet").text(obj[i].Packet);
                    row.find("#apmac").text(obj[i].APMAC); 
                    row.appendTo("#wifi");
                }
            }, error: function () {
                alert("加载失败");
            }
        });
    }


// 跳出轮询：
//      c = setInterval(checkIsExist,10000);//每10秒执行一次checkIsExist方法
//     window.clearInterval(c);  //  跳出

