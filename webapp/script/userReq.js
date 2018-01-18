//用户点击删除按钮 事件：删除用户
function userReq(){
	var username;
	$.ajax({
		type : "get",
		url : "/session",
		contentType : "application/x-www-form-urlencoded",
		success : function(rt) {
			username = rt;
		}
	});
	$.ajax({
		type : "get",
		url : "/getUserInfo",
		dataType : "json",
		success : function(result) {
			var obj = eval(result);
			// $("#yhlb tbody tr").remove();
			for (var i = 0; i < obj.length; i++) {
				var row = $("#row1").clone();
				console.log(obj[i].username);
				console.log(obj[i].devices);
				row.find("#yh").text(obj[i].username);
				row.find("#cksb").text(obj[i].devices);
				
				// row.find("#yn").text(obj[i].yn);
				// row.find("#Mode1").text(obj[i].Mode);
				// row.find("#Security1").text(obj[i].Security);
				// row.find("#Privacy1").text(obj[i].Privacy);
				row.appendTo("#yhlb");
			}
		},
		error : function() {
			alert("加载失败");
		}
	});
	$(".btn").each(function() {
		$("#dev").bind("click", (function() {
			if ($("input[name='test']:checkbox:checked").length > 0) {

				$("input[name='test']:checkbox:checked").each(function() {
					var obj = {
						username : username,
						aimUsername : $("#yh").val(),
						operate : 'delete'
					};
					$.ajax({
						type : "post",
						url : "/usermanage",
						data : obj,
						contentType : "application/x-www-form-urlencoded",
						success : function(data) {
							if (data.result == 1) {
								alert("操作成功")
							} else if (data.result == 0) {
								alert("操作失败");
							}
						}
					});
				})
			} else {
				alert('没有选择');
				return false;
			}
		}))
	})
}
