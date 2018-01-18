// 在hy_list.html 页面 点击 wifi详情 跳转掉连接wifi用户页面
function skip() {
	    var array = $("tbody input[type=checkbox]:checked").map(function () {
	        var check = $.trim($(this).closest("tr").find("td:eq(2)").text());
            var channel = $.trim($(this).closest("tr").find("td:eq(5)").text());
	    var data={
	    	aim:"userInfo",
	    	APMAC:check,
            Channel:channel
	    };
	    console.log(data.demand+"  "+data.MAC);
    $.ajax({
        type: "post",
        url: "/Control",
        data: data,
        contentType: "application/x-www-form-urlencoded",
        success: function (data) {
                location.href = "yh_wifi.html";

        }
    });
	    }).get();
}