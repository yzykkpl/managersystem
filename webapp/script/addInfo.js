function tj() {
    var ffwz = document.getElementById("ffwztj").value;
    var ffip = document.getElementById("ffiptj").value;
    var mgch = document.getElementById("mgchtj").value;
    var pass = {
        aim: "analysis",
        words: ffwz + "+" + ffip + "+" + mgch
    };
    $.ajax({
        type: "post",
        url: "/Control",
        data: pass,
        contentType: "application/x-www-form-urlencoded",
        success: function (rt) {
            if (rt == 0) {
                alert("添加成功")
            } else if (rt == 1) {
                alert("添加失败");
            }
        }
    });
}

