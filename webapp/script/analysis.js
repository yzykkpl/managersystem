$(function () {
    $.ajax({
        type: "get",
        url: "/getAnalysis",
        dataType: "json",
        success: function (result) {
            var obj = eval(result);
            //$("#nrfx tbody tr").remove();
            for (var i = 0; i < obj.length; i++) {
                var row = $("#row").clone();
                row.find("#sssb").text(obj[i].sssb);
                row.find("#ffwz").text(obj[i].ffwz);
                row.find("#mgch").text(obj[i].mgch);
                row.find("#zt").text(obj[i].zt);
                row.find("#sfzd").text(obj[i].sfzd);
                // row.find("#Privacy1").text(obj[i].Privacy);
                row.appendTo("#nrfx");
            }
        }, error: function () {
            alert("加载失败");
        }
    });
    // $(".btn").each(function() {
    //     $("#gr").bind("click", (function () {
    //         var ipts = $(":checkbox:checked").parents("tr").find("#sssb");
    //     }));
       /* $.ajax({
            type: "post",
            url: "http://192.168.140.15:8080/test",
            data: ipts,
            contentType: "application/x-www-form-urlencoded",
            success: function (data) {
                if (data.result == 1) {
                    location.href = "zdgr.html";
                } else if (data.result == 0) {
                    alert("操作失败");
                }
            }
        });*/
    })
