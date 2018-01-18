
function createXMLHttpRequest() {
	if (window.XMLHttpRequest) {
		var xmlHttp = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
}
function prepareJSON() {
	var number = document.getElementById("num").value;
	var name = document.getElementById("name").value;
	var balance = document.getElementById("bal").value;
	var account = new Object();
	account.number = number;
	account.name = name;
	account.balance = balance;
	return account.toJSONString();
}
function handleAccount() {
	var json = prepareJSON();
	var url = "JSONServlet?timeStamp" + new Date().getTime();
	createXMLHttpRequest();
	xmlHttp.onreadystatechange = handleStateChange;
	xmlHttp.open("POST", url);
	xmlHttp.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	xmlHttp.send(json);
}
function handleStateChange() {
	if (xmlHttp.readyState == 4) {

		if (xmlHttp.status == 200) {
			document.getElementById("response").innerHTML = xmlHttp.responseText;
		}
	}
}