function jc() {
    $.ajax({
        type: "get",
        url: "192.168.140.15:8080/getAPInfo",
        dataType: "json",
        success: function (result) {
            var obj = eval(result);
            $("#sblb tbody tr").remove();
            for (var i = 0; i < obj.length; i++) {
                var row = $("#row").clone();
                row.find("#sb").text(obj[i].sb);
                row.find("#ry").text(obj[i].ffwz);
                // row.find("#mgch").text(obj[i].mgch);
                // row.find("#zt").text(obj[i].zt);
                // row.find("#sfzd").text(obj[i].sfzd);
                // row.find("#Privacy1").text(obj[i].Privacy);
                row.appendTo("#sblb");
            }
        }, error: function () {
            alert("加载失败");
        }
    });
    $(".btn").each(function() {
        $("#jc").bind("click", (function () {
            var ipts = $(":checkbox:checked").parents("tr").find("#sb");
        }));
        $.ajax({
            type: "post",
            url: "http://192.168.140.15:8080/test",
            data: ipts,
            contentType: "application/x-www-form-urlencoded",
            success: function (data) {
                if (data.result == 1) {
                    location.href = "hy_list.html";
                } else if (data.result == 0) {
                    alert("操作失败");
                }
            }
        });
    })}
