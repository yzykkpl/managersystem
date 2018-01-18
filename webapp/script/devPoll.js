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
//                 $("#yhlb tbody tr").remove();
//                 for (var i = 0; i < obj.length; i++) {
//                     var row = $("#row1").clone();
//                     row.find("#SSID1").text(obj[i].SSID);
//                     row.find("#MAC1").text(obj[i].MAC);
//                     row.find("#Channel1").text(obj[i].Channel);
//                     row.find("#Privacy1").text(obj[i].Privacy);
//                     row.find("#Clipher1").text(obj[i].Clipher);
//                     row.find("#Signal1").text(obj[i].Power);
//                     row.appendTo("#table");
//                     
//                 }
                 var trStr = '';//动态拼接table
                 for (var i = 0; i < obj.length; i++) {//循环遍历出json对象中的每一个数据并显示在对应的td中
                 trStr += '<tr class="example">';//拼接处规范的表格形式
                 trStr += '<td class="xz">' + '<input type="checkbox" value="">' + '</td>';
                 trStr += '<td width="15%">' + obj[i].SSID + '</td>';//对应数组表的字段值
                 trStr += '<td width="15%">' + obj[i].MAC + '</td>';
                 trStr += '<td width="15%">' + obj[i].Privacy + '</td>';
                 trStr += '<td>' + obj[i].Clipher + '</td>';
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