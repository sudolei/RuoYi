var M = {};

function alert_taobao() {
    if (M.dialog1) {
        return M.dialog1.show();
    }
    M.dialog1 = jqueryAlert({
        'icon': '../jqalert/img/warning.png',
        'content': '请输入淘宝号',
        'closeTime': 2000,
    })
}

function alert_phone() {
    if (M.phone) {
        return M.phone.show();
    }
    M.phone = jqueryAlert({
        'icon': '../jqalert/img/warning.png',
        'content': '请输入手机号',
        'closeTime': 2000,
    })
}

function alert_upload() {
    if (M.upload) {
        return M.upload.show();
    }
    M.upload = jqueryAlert({
        'icon': '../jqalert/img/warning.png',
        'content': '图片少于100张时,请上传9的倍数',
        'closeTime': 2000,
    })
}


function alert_image() {
    if (M.image) {
        return M.image.show();
    }
    M.image = jqueryAlert({
        'icon': '../jqalert/img/warning.png',
        'content': '请选择图片合成，支持JPG/JPEG！',
        'closeTime': 2000,
    })
}

function alert_upload_no() {
    if (M.uploadno) {
        return M.uploadno.show();
    }
    M.uploadno = jqueryAlert({
        'icon': '../jqalert/img/warning.png',
        'content': '请先选择照片再提交订单！',
        'closeTime': 3000,
    })
}

function alert_phone_v() {
    if (M.phonev) {
        return M.phonev.show();
    }
    M.phonev = jqueryAlert({
        'icon': '../jqalert/img/warning.png',
        'content': '请输入正确的手机号',
        'closeTime': 2000,
    })
}

function alert_fuzhi() {
    if (M.copy) {
        return M.copy.show();
    }
    M.copy = jqueryAlert({
        'icon': '../jqalert/img/right.png',
        'content': '复制成功',
        'closeTime': 2000,
    })
}

function alert_areaNum(areaNum) {
    // if (M.area) {
    //     return M.area.show();
    // }
    var c = "需要上传" + areaNum + "张图片";
    M.area = jqueryAlert({
        'icon': '../jqalert/img/right.png',
        'content': c,
        'closeTime': 1000,
    })
}


function alert_common(c) {
    M.common = jqueryAlert({
        'icon': '../jqalert/img/right.png',
        'content': c,
        'closeTime': 11000,
    })
}


function alert_c(c) {
    M.common = jqueryAlert({
        'icon': '../jqalert/img/right.png',
        'content': c,
        'closeTime': 3000,
    })
}