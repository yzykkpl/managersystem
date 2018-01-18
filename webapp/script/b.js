function a(){
	setInterval("test()",2000);
}

function test() {
	console.log('aaa');
    $.ajax({
        type: "GET",
        url: "/getAnalysis",
        timeout: 60000,
        async: true,
        dataType:'json',
        success:function(result) {
                var obj = eval(result);
                // $("#nrfx tbody tr").remove();
                for (var i = 0; i < obj.length; i++) {
                    var row = $("#row").clone();
                    row.find("#sj").text(obj[i].sj);
                    row.find("#ffwz").text(obj[i].ffwz);
                    row.find("#mgch").text(obj[i].mgch);
                    row.find("#ffip").text(obj[i].ffip);
                    row.find("#sssb").text(obj[i].sssb);
                    row.appendTo("#nrfx");
                } 
        }
        
    });
}