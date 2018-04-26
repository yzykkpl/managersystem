function ZD() {
    $("tbody input[type=checkbox]:checked").map(function () {
        var MAC = $.trim($(this).closest("tr").find("td:eq(2)").text());
        var apMAC = $.trim($(this).closest("tr").find("td:eq(7)").text());
        console.log(MAC);
        console.log(apMAC);
        var mac={
            aim:"userBlock",
            uMAC:MAC,
            APMAC:apMAC
        };

        $.ajax({
            type: "post",
            url: "/Control",
            data: mac,
            contentType: "application/x-www-form-urlencoded",
            success: function (data) {
                if (data == 0) {
                    //location.href="index.html";
                    alert("干扰成功")
                } else if (data == 1) {
                    alert("干扰失败");
                }
            }
        });
    }).get();
}

//停止阻断
function TZZD() {
    src2 = {
        // deviceName:name,
        aim : "userBlock",
        action : "stop"
    };
    $.ajax({
        type : "POST",
        url : "/Control",
        data : src2,
        contentType : "application/x-www-form-urlencoded",
        success : function() {
            alert("停止成功");
        }
    });
}