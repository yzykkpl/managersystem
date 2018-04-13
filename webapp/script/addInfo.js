function addInfo() {
    var ffwz = document.getElementById("ffwztj").value;
    var ffip = document.getElementById("ffiptj").value;
    console.log(ffwz);
    var zwmgch = document.getElementById("zwmgchtj").value;
    var ywmgch = document.getElementById("ywmgchtj").value;
    //console.log(ffwz);
    var pass = {
        aim: "analysis",
        words: ffwz + "+" + ffip + "+" + zwmgch + "+" + ywmgch
    };
    $.ajax({
        type: "post",
        url: "/Control",
        data: pass,
        contentType: "application/x-www-form-urlencoded",
        success: function (rt) {
        	console.log(rt)
            if (rt == 0) {
                alert("添加成功")
            } else if (rt == 1) {
                alert("添加失败");
            }
        }
    });
}

