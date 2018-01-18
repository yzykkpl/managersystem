function ksfx(){
	analysis();
	setInterval("analysis()",10000);
}

function analysis() {
    $.ajax({
        type: "GET",
        url: "/getAnalysis",
        timeout: 60000,
        async: true,
        dataType:'json',
        success:function(result) {
                var obj = eval(result);
//                for (var i = 0; i < obj.length; i++) {
//                    var row = $("#row").clone();
//                    row.find("#srcMAC").text(obj[i].src);
//                    row.find("#sj").text(obj[i].time);
//                    row.find("#ffwz").text("www.baidu.com");
//                    row.find("#dstMAC").text(obj[i].dst);
//                    row.appendTo("#nrfx");
//                } 
                var trStr = '';//动态拼接table
                for (var i = 0; i < obj.length; i++) {//循环遍历出json对象中的每一个数据并显示在对应的td中
                trStr += '<tr class="example">';//拼接处规范的表格形式
                trStr += '<td class="xz">' + '<input type="checkbox" value="">' + '</td>';
                trStr += '<td width="15%">' + obj[i].src + '</td>';//对应数组表的字段值
                trStr += '<td width="15%">' + obj[i].time + '</td>';
                trStr += '<td width="15%">' + "www.baidu.com" + '</td>';
                trStr += '<td>' + obj[i].dst + '</td>';
                /*经典之处，要将主键对应的值以json的形式进行传递，才能在后台使用*/
              //  trStr += "<td><a href='#'style='text-decoration:none' onclick='Delete(\"" + obj[i].NVFID + "\")'>删除</a><td>";
                trStr += '</tr>';  
                } 
                $("#nrfx  tr:not(:first)").html("");

                $("#nrfx").append(trStr);
        }
        
    });
}

