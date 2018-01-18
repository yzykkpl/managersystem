function Xxjc_fx() {
	var pass = {
		aim:"analysis"

	}
	$.ajax({
		type : "post",
		url : "/Control",
		data : pass,
		contentType : "application/x-www-form-urlencoded",
		success : function(data) {
			if (data.result == 1) {
				location.href = "nrfx.html";
			} else if (data.result == 0) {
				alert("选择错误");
			}
		}
	});

}
