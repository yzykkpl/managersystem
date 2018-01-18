function kq1() {
    var pass={
        aim:"Switch",
        num:1,
        sta:1
    };
    $.ajax({
        type: "post",
        url: "/Control",
        data: pass,
        contentType: "application/x-www-form-urlencoded",
        success: function (data) {
                alert("开启成功")
            }
    });
}

function gb1() {
    var pass={
        aim:"Switch",
        num:1,
        sta:0
    };
    $.ajax({
        type: "post",
        url: "/Control",
        data: pass,
        contentType: "application/x-www-form-urlencoded",
        success: function (data) {
            alert("关闭成功")
        }
    });
}

function kq2() {
    var pass={
        aim:"Switch",
        num:2,
        sta:1
    };
    $.ajax({
        type: "post",
        url: "/Control",
        data: pass,
        contentType: "application/x-www-form-urlencoded",
        success: function (data) {
                alert("开启成功")
        }
    });
}

function gb2() {
    var pass={
        aim:"Switch",
        num:2,
        sta:0
    };
    $.ajax({
        type: "post",
        url: "/Control",
        data: pass,
        contentType: "application/x-www-form-urlencoded",
        success: function (data) {
                alert("关闭成功")
        }
    });
}

function kq3() {
    var pass={
        aim:"Switch",
        num:3,
        sta:1
    };
    $.ajax({
        type: "post",
        url: "/Control",
        data: pass,
        contentType: "application/x-www-form-urlencoded",
        success: function (data) {
                alert("开启成功")
        }
    });
}

function gb3() {
    var pass={
        aim:"Switch",
        num:3,
        sta:0
    };
    $.ajax({
        type: "post",
        url: "/Control",
        data: pass,
        contentType: "application/x-www-form-urlencoded",
        success: function (data) {
                alert("关闭成功")
        }
    });
}

function kq4() {
    var pass={
        aim:"Switch",
        num:4,
        sta:1
    };
    $.ajax({
        type: "post",
        url: "/Control",
        data: pass,
        contentType: "application/x-www-form-urlencoded",
        success: function (data) {
                alert("开启成功")
        }
    });
}

function gb4() {
    var pass={
        aim:"Switch",
        num:4,
        sta:0
    };
    $.ajax({
        type: "post",
        url: "/Control",
        data: pass,
        contentType: "application/x-www-form-urlencoded",
        success: function (data) {
                alert("关闭成功")
        }
    });
}

function kq5() {
    var pass={
        aim:"Switch",
        num:0,
        sta:1
    };
    $.ajax({
        type: "post",
        url: "/Control",
        data: pass,
        contentType: "application/x-www-form-urlencoded",
        success: function (data) {
                alert("开启成功")
        }
    });
}

function gb5() {
    var pass={
        aim:"Switch",
        num:0,
        sta:1
    };
    $.ajax({
        type: "post",
        url: "/Control",
        data: pass,
        contentType: "application/x-www-form-urlencoded",
        success: function (data) {
                alert("关闭成功")
        }
    });
}