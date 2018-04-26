function ksfx(){
	analysis();
	setInterval("analysis()",10000);
}

function analysis() {
	console.log("start")
    $.ajax({
        type: "GET",
        url: "/getAnalysis",
        timeout: 60000,
        async: true,
        dataType:'json',
        success:function(result) {
                var obj = eval(result);
                console.log(result)
                var trStr = '';//动态拼接table
                for (var i = 0; i < obj.length; i++) {//循环遍历出json对象中的每一个数据并显示在对应的td中
                trStr += '<tr class="example">';//拼接处规范的表格形式
                trStr += '<td class="xz">' + '<input type="checkbox" value="">' + '</td>';
                trStr += '<td width="15%">' + obj[i].time + '</td>';//对应数组表的字段值
                trStr += '<td width="15%">' + obj[i].srcMAC + '</td>';
                trStr += '<td width="15%">' + obj[i].dstMAC + '</td>';
                trStr += '<td width="15%">' + obj[i].srcIP + '</td>';//对应数组表的字段值
                trStr += '<td width="15%">' + obj[i].dstIP + '</td>';
                trStr += '<td width="15%">' + obj[i].keyWord + '</td>';
                /*经典之处，要将主键对应的值以json的形式进行传递，才能在后台使用*/
              //  trStr += "<td><a href='#'style='text-decoration:none' onclick='Delete(\"" + obj[i].NVFID + "\")'>删除</a><td>";
                trStr += '</tr>';  
                } 
                $("#nrfx  tr:not(:first)").html("");

                $("#nrfx").append(trStr);
        }
        
    });
}

function tzfx() {
    ipts = {
        // deviceName:name,
        aim : "analysis",
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
