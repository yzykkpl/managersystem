//wifi 检测到设备信息 hy_list.html
$(function () {
	test();
    setInterval("test()",10000);
});
    function test() {
    	 var pass = {
    			demand : "wifi"
    		};
         $.ajax({
             type: "post",
             url: "/getAPInfo",
             timeout: 60000,
             data: pass,
             async: true,
             dataType: "json",
             success: function (result) {
                 var obj = eval(result);
                 var trStr = '';//动态拼接table
                 for (var i = 0; i < obj.length; i++) {//循环遍历出json对象中的每一个数据并显示在对应的td中
                 trStr += '<tr class="example">';//拼接处规范的表格形式
                 trStr += '<td class="xz">' + '<input type="checkbox" value="">' + '</td>';
                 trStr += '<td width="15%">' + obj[i].SSID + '</td>';//对应数组表的字段值
                 trStr += '<td width="15%">' + obj[i].MAC + '</td>';
                 trStr += '<td width="15%">' + obj[i].Frequency + '</td>';
                 trStr += '<td>' + obj[i].Power + '</td>';
                 trStr += '<td>' + obj[i].Channel + '</td>';
                 /*经典之处，要将主键对应的值以json的形式进行传递，才能在后台使用*/
               //  trStr += "<td><a href='#'style='text-decoration:none' onclick='Delete(\"" + obj[i].NVFID + "\")'>删除</a><td>";
                 trStr += '</tr>';  
                 } 
                 $("#table  tr:not(:first)").html("");

                 $("#table").append(trStr);
             }, error: function () {
                 alert("加载失败");
             }
         });
}
  //wifi阻断
    function zd(){
        $("tbody input[type=checkbox]:checked").map(function () {
        var sss = $.trim($(this).closest("tr").find("td:eq(2)").text());
        	var pass = {
        			aim : "block",
        			MACd : sss,
        	};
        	console.log(pass.MACd);
            $.ajax({
                type: "post",
                url: "/Control",
                data: pass,
                contentType: "application/x-www-form-urlencoded",
                success: function (data) {
                    if (data.result == 1) {
                    	alert("阻断成功");
                    } else if (data.result == 0) {
                        alert("操作失败");
                    }
                }
            });
        });
    }
// 停止检测
        function tzjc() {
            ipts = {
               // deviceName:name,
                aim : "detection",
                action : "stop"
          };
          $.ajax({
              type : "POST",
              url : "/Control",
               data : ipts,
              contentType : "application/x-www-form-urlencoded",
              success : function() {
                 alert("停止成功");
             }
          });
        }
        //停止阻断
function tzzd1() {
    src1 = {
        // deviceName:name,
        aim : "block",
        action : "stop"
    };
    $.ajax({
        type : "POST",
        url : "/Control",
        data : src1,
        contentType : "application/x-www-form-urlencoded",
        success : function() {
            alert("停止成功");
        }
    });
}
function send() {
    $("tbody input[type=checkbox]:checked").map(function () {
        var uMac = $.trim($(this).closest("tr").find("td:eq(2)").text());
        var send = {
            APMAC : uMac
        };
        $.ajax({
            type: "post",
            url: "/setAPmac",
            data: send,
            contentType: "application/x-www-form-urlencoded",
        });
    });
}