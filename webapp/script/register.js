function reg() {
	// console.log('login');
	var pass = {
		username : $("#uname").val().trim(),
		password : $("#pwd").val().trim(),
		email : $("#email").val().trim(),
		auth:'normal'
	};
	var jsons = JSON.stringify(pass);
	console.log(jsons);
	$.ajax({
		type : "post",
		url : "/register",
		data : pass,
		contentType : "application/x-www-form-urlencoded",
		success : function(data) {
			console.log(data);
			if (data == 0) {
				location.href="/html/index.html";
				alert("注册成功");
			} else if (data == 1) {
				alert("用户已存在");
			}
			// console.log(data);
		}
	});
}
