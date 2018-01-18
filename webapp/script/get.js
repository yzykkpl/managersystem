function request() {
    $.ajax({
        type: "GET",
        url: "/getAPInfo",//利用ajax请求后台的并返回值
        // data: "json",
        success: function (result) {//result为后台返回的值,是json字符串的形式
            //alert(result);
            var obj = JSON.parse(result);//解析json字符串为json对象形式
            var trStr = '';//动态拼接table
            // var html = '';
            for (var i = 0; i < obj.length; i++) {//循环遍历出json对象中的每一个数据并显示在对应的td中
                trStr += '<tr class="example">';//拼接处规范的表格形式
                trStr += '<td style="display:none" id="user">' + obj[i].NVFID + '</td>';//数据表的主键值 NVFID是什么
                trStr += '<td>' + obj[i].SSID + '</td>';//对应数组表的字段值
                trStr += '<td>' + obj[i].MAC + '</td>';
                trStr += '<td>' + obj[i].Channel + '</td>';
                trStr += '<td>' + obj[i].Mode + '</td>';
                trStr += '<td>' + obj[i].Security + '</td>';
                trStr += '<td>' + obj[i].Privacy + '</td>';
                trStr += '<td>' + obj[i].Clipher + '</td>';
                trStr += '<td>' + obj[i].Frequency + '</td>';
                trStr += '<td>' + obj[i].Quality + '</td>';
                trStr += '<td>' + obj[i].Signal + '</td>';
                trStr += '<td>' + obj[i].BWMhz + '</td>';
                trStr += '<td>' + obj[i].MinSig + '</td>';
                trStr += '<td>' + obj[i].MaxSig + '</td>';
                trStr += '<td>' + obj[i].CenChan + '</td>';
                trStr += '<td>' + obj[i].Vendor + '</td>';
                trStr += '<td>' + obj[i].Protocol + '</td>';
                /*经典之处，要将主键对应的值以json的形式进行传递，才能在后台使用*/
                // trStr += "<td><a href='#' style='text-decoration:none' onclick='Delete(\"" + obj[i].NVFID + "\")'>删除</a><td>";
                trStr += '</tr>';
            }
            $("#row1").html(trStr);//运用html方法将拼接的table添加到tbody中return;
            // $('#treeList').append(html);
        },
        error: function (error) {
           alert(error);
        }
    });
}