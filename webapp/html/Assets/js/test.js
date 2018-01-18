$(function () {
//            debugger;
    $.ajax({
        type: "get",
        url: "/getAPInfo",
        //dataType: "json",
        success: function (result) {
            //alert(result);
            //var obj = JSON.parse(result);
            var obj = eval(result);
            console.log(obj[0].SSID);
            
            //$("#table tr").remove();
            for (var i = 0; i < obj.length; i++) {
                var row = $("#row1").clone();
                
                row.find("#SSID1").text(obj[i].SSID);
                row.find("#MAC1").text(obj[i].MAC);
                row.find("#Channel1").text(obj[i].Channel);
                row.find("#Mode1").text(obj[i].Mode);
                row.find("#Security1").text(obj[i].Security);
                row.find("#Privacy1").text(obj[i].Privacy);
                row.find("#Clipher1").text(obj[i].Clipher);
                row.find("#Frequency1").text(obj[i].Frequency);
                row.find("#Quality1").text(obj[i].Quality);
                row.find("#Signal1").text(obj[i].Signal);
                row.find("#BWMhz1").text(obj[i].BWMhz);
                row.find("#MinSig1").text(obj[i].MinSig);
                row.find("#MaxSig1").text(obj[i].MaxSig);
                row.find("#CenChan1").text(obj[i].CenChan);
                row.find("#Vendor1").text(obj[i].Vendor);
                row.find("#Protocol1").text(obj[i].Protocol);
                row.appendTo("#table");
            }
        }, error: function () {
            alert("加载失败");
        }
    })
})