//用户点击删除按钮 事件：删除用户
var username;
function userReq(){

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
				//console.log(obj[i].username);
				//console.log(obj[i].devices);
				row.find("#yh").text(obj[i].username);
				row.find("#cksb").text(obj[i].devices);
				var isAdmin=obj[i].auth=='Administrator'
				row.find("#yn").text(isAdmin);
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
}


function userDev() {
    $("tbody input[type=checkbox]:checked").map(function () {
        var aimUser = $.trim($(this).closest("tr").find("td:eq(1)").text());
        var obj={
            username : username,
            aimUsername : aimUser,
            operate : 'delete'
        };
        $.ajax({
            type : "post",
            url : "/usermanage",
            data : obj,
            contentType : "application/x-www-form-urlencoded",
            success : function(data) {
                if (data== '1') {
                    alert("操作成功")
                } else if (data == '0') {
                    alert("操作失败");
                }
            }
        });
    }).get();
}


function userSp() {
    $("tbody input[type=checkbox]:checked").map(function () {
        var aimUser = $.trim($(this).closest("tr").find("td:eq(1)").text());
        var obj={
        	username : username,
            key : 'auth',
            aimUsername : aimUser,
            operate : 'update',
            value:'Administrator'
        };
        $.ajax({
            type : "post",
            url : "/usermanage",
            data : obj,
            contentType : "application/x-www-form-urlencoded",
            success : function(data) {
            	console.log(data)
                if (data == '1') {
                    alert("设置管理员成功")
                } else if (data == '0') {
                    alert("操作失败");
                }
            }
        });
    }).get();
}


