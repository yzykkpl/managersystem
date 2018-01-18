function test() {
	var ID = document.getElementById("id").value;
	var IP = document.getElementById("ip").value;
	var PORT = document.getElementById("port").value;
	var operate = document.getElementById("op").value;
	var row = {
			ID : ID,
			IP : IP,
			PORT : PORT,
			operate : operate
	};
	$.ajax({
		type : "post",
		url : "/control",
		data : row,

		contentType : "application/x-www-form-urlencoded",
		success : function(data) {
			// if (data.result == 1) {
			// location.href="index.html";
			// } else if (data.result == 0) {
			// alert("登录出错");
			// }
			console.log(data);
		}

	});
}
