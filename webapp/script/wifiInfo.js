//wifi 检测到设备信息 hy_list.html
function a() {
	wifiInfo();
    setInterval("wifiInfo()",10000);
    }
    function wifiInfo() {
        var data={
            demand:"users",
        };
        $.ajax({
            type: "post",
            url: "/getAPInfo",
            timeout: 60000,
            data: data,
            async: true,
            dataType: "json",
            success: function (result) {
                var obj = eval(result);
                var trStr = '';//动态拼接table
                for (var i = 0; i < obj.length; i++) {//循环遍历出json对象中的每一个数据并显示在对应的td中
                trStr += '<tr class="example">';//拼接处规范的表格形式
                trStr += '<td class="xz">' + '<input type="checkbox" value="">' + '</td>';
                trStr += '<td width="15%">' + obj[i].uMAC + '</td>';//对应数组表的字段值
                trStr += '<td width="15%">' + obj[i].Time + '</td>';
                trStr += '<td width="15%">' + obj[i].Power + '</td>';
                trStr += '<td>' + obj[i].Packets + '</td>';
                trStr += '<td>' + obj[i].APMAC + '</td>';
                /*经典之处，要将主键对应的值以json的形式进行传递，才能在后台使用*/
              //  trStr += "<td><a href='#'style='text-decoration:none' onclick='Delete(\"" + obj[i].NVFID + "\")'>删除</a><td>";
                trStr += '</tr>';  
                } 
                $("#wifi  tr:not(:first)").html("");

                $("#wifi").append(trStr);
               // $("#tbody").html(trStr);//运用html方法将拼接的table添加到tbody中return;
            }, error: function () {
                alert("加载失败");
            }
        });
    }



