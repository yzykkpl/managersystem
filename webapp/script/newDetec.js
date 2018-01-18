//  星地信号检测  hy_list.html
function detect() {
	
	sallitle();

    setInterval("sallitle()",10000);
} 
function sallitle(){

        $.ajax({
            type: "get",
            url: "/signal",
            timeout: 60000,
            async: true,
            success: function (result) {
            	if(result!=0){
            		var obj = eval(result);
            		for(var i = 0;i<obj.length;i++){
            			
            			console.log(obj[i].freq);
	                	if(obj[i].freq=='1.62-1.66GHz;'){
	                		 var str = "当前已有用户连接BGAN" + "  "+obj[i].freq;
	                		 var oTest = document.getElementById('jcjg');
	                		 oTest.value = str;
	                	}
            		}
          
            	}
            }
        });
    
    //setInterval("test()",20000);
    }


// 跳出轮询：
//      c = setInterval(checkIsExist,10000);//每10秒执行一次checkIsExist方法
//     window.clearInterval(c);  //  跳出

