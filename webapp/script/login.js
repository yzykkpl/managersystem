function login() {
	console.log('login');
/*	var userName = document.getElementById("box_name").value;
	var pwd = document.getElementById("box_pass").value;
	// var input_code = document.getElementById('vcode').value;
	var matchResult = true;*/
	console.log('check');
	// 获取用户输入的验证码
	// alert(input_code+"----"+code);
	/*
	 * if (input_code.toLowerCase() !== code.toLowerCase()) { // 验证码正确(表单提交)
	 * matchResult = false; alert("请输入正确的验证码!"); }
	 */
	/*
	 * if (userName == "" || pwd == "") { alert("请确认是否有空缺项！"); matchResult =
	 * false; } else if (userName.length < 6 || userName.length > 20) {
	 * alert("用户名长度应在6到20个字符之间！"); matchResult = false; } else if (pwd.length <
	 * 6 || pwd.length > 20) { alert("密码长度应在6到20个字符之间！"); matchResult = false; }
	 *
	*if (matchResult == true) {
		/*
		 * if (userName.charAt(0) >= 0 && userName.charAt(0) <= 9) {
		 * alert("用户名不能以数字字符开始！"); }
		 */

		var pass = {
			username : $("#box_name").val().trim(),
			
			password : $("#box_pass").val().trim()
		};
		console.log(pass.username);

		$.ajax({
			type : "post",
			url : "/login",
			data : pass,
			contentType : "application/x-www-form-urlencoded",
			success : function(data) {
				if (data == 0) {
					
					location.href="html/sbrylb.html";
				} else if (data == 1) {
					alert("登录出错");
				}
				
			}

		});
		// return matchResult;

	//}
}
function check() {
	var rt;
	$.ajax({
		type : "get",
		url : "/session",
		contentType : "application/x-www-form-urlencoded",
		success : function(data) {
			console.log("session :" + data);
		}
	});
}