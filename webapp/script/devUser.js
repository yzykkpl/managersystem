// sbrylb.html 检测设备和人员
function dectect() {
	request();
	setInterval("request()", 20000);
}
function request() {
	$.ajax({
		type : "GET",
		url : "",
		timeout : 60000,
		async : true,
		dataType : "json",
		success : function(result) {
			var obj = eval(result);
			$("#sblb tbody tr").remove();
			for (var i = 0; i < obj.length; i++) {
				var row = $("#row").clone();
				row.find("#sb").text(obj[i].sb);
				row.find("#ry").text(obj[i].ry);
				row.appendTo("#sblb");

			}
		}
	});
}

ipts = {
	// deviceName:name,
	aim : "detection",
	action : "start"
};
function start() {
	$.ajax({
		type : "POST",
		url : "/Control",
		data : ipts,
		contentType : "application/x-www-form-urlencoded",
		success : function() {

			location.href = "hy_list.html";

		}
	});
	console.log(ipts.action + " " + ipts.aim);
}
