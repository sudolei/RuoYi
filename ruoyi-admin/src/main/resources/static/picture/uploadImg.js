var imgSrc = []; //图片路径
var imgFile = []; //文件流
var imgName = []; //图片名字
//选择图片
function imgUpload(obj) {
    var oInput = '#' + obj.inputId;
    var imgBox = '#' + obj.imgBox;
    var btn = '#' + obj.buttonId;
    $(oInput).on("change", function () {
        loading();
        var fileImg = $(oInput)[0];
        var fileList = fileImg.files;
        for (var i = 0; i < fileList.length; i++) {
            var imgSrcI = getObjectURL(fileList[i]);
            imgName.push(fileList[i].name);
            imgSrc.push(imgSrcI);
            imgFile.push(fileList[i]);
        }
        addNewContent(imgBox);
        return fileList.length;
    });
    $(btn).on('click', function () {
        var data = new Object;
        data[obj.data] = imgFile;
        submitPicture(obj.upUrl, imgFile);
    })
}

function loading() {
    $('body').loading({
        loadingWidth: 240,
        title: '请稍等!',
        name: 'test',
        discription: '图片正在上传',
        direction: 'column',
        type: 'origin',
        originDivWidth: 40,
        originDivHeight: 40,
        originWidth: 6,
        originHeight: 6,
        smallLoading: false,
        loadingMaskBg: 'rgba(0,0,0,0.2)'
    });
}


function loading2() {
    $('body').loading({
        loadingWidth: 240,
        title: '请稍等!',
        name: 'test2',
        discription: '开始提交，请稍后',
        direction: 'column',
        type: 'origin',
        originDivWidth: 40,
        originDivHeight: 40,
        originWidth: 6,
        originHeight: 6,
        smallLoading: false,
        loadingMaskBg: 'rgba(0,0,0,0.2)'
    });
}

//图片展示
function addNewContent(obj) {
    $(imgBox).html("");
    for (var a = 0; a < imgSrc.length; a++) {
        var oldBox = $(obj).html();
        $(obj).html(oldBox + '<div class="imgContainer"><img title=' + imgName[a] + ' alt=' + imgName[a] + ' src=' + imgSrc[a] + ' onclick="imgDisplay(this)"><p onclick="removeImg(this,' + a + ')" class="imgDelete">删除</p></div>');
    }
    var len = imgSrc.length;
    if (len < 10) {
        setTimeout(function () {
            removeLoading('test');
            alert_c("你已上传了"+len+"张")
        }, 550);
    } else if (len > 10 && len < 50) {
        setTimeout(function () {
            removeLoading('test');
            alert_c("你已上传了"+len+"张")
        }, 1150);
    } else if (len > 50 && len < 150) {
        setTimeout(function () {
            removeLoading('test');
            alert_c("你已上传了"+len+"张")
        }, 2350);
    } else if (len > 150 && len < 250) {
        setTimeout(function () {
            removeLoading('test');
            alert_c("你已上传了"+len+"张")
        }, 2850);
    } else {
        setTimeout(function () {
            removeLoading('test');
            alert_c("你已上传了"+len+"张")
        }, 3850);
    }


}

//删除
function removeImg(obj, index) {
    imgSrc.splice(index, 1);
    imgFile.splice(index, 1);
    imgName.splice(index, 1);
    var boxId = "#" + $(obj).parent('.imgContainer').parent().attr("id");
    addNewContent(boxId);
}

//上传(将文件流数组传到后台)
function submitPicture(url, imgFile) {
    var oid = randomn(8);
    var j = 0;
    var len = imgFile.length;
    var msg = "";
    loading2();

    var formdata;
    for (var i = 0; i < imgFile.length; i++) {
        formdata = new FormData();
        formdata.append('enctype', 'multipart/form-data');
        formdata.append('name', imgName[i]);
        formdata.append('fileNum', i);
        formdata.append('len', len);
        formdata.append('oid', oid);
        formdata.append('myfile', imgFile[i]);
        $.ajax({
            url: url,
            type: 'post',
            contentType: false,
            data: formdata,
            // async: false,
            processData: false,
            success: function (info) {
                msg = info.msg;
                if (msg) {
                    removeLoading2("test2", oid);
                }
            },
            error: function (err) {
                console.log(err)
            }
        });
    }
}


function randomn(n) {
    var today = new Date();
    var seed = today.getTime();
    seed = ( seed * 9301 + 49297 ) % 233280;
    var rn1 = parseInt((Math.random() + 1) * Math.pow(10, n - 1));
    var rn2 = parseInt((Math.random() + 1) * Math.pow(10, n - 1));
    var rn3 = parseInt((Math.random() + 1) * Math.pow(10, n - 1));
    var result = rn1 + rn2 + rn3 - seed;

    return result;

}

//图片灯箱
function imgDisplay(obj) {
    var src = $(obj).attr("src");
    var imgHtml = '<div style="width: 100%;height: 100vh;overflow: auto;background: rgba(0,0,0,0.5);text-align: center;position: fixed;top: 0;left: 0;z-index: 1000;"><img src=' + src + ' style="margin-top: 100px;width: 70%;margin-bottom: 100px;"/><p style="font-size: 50px;position: fixed;top: 30px;right: 30px;color: white;cursor: pointer;" onclick="closePicture(this)">×</p></div>'
    $('body').append(imgHtml);
}

//关闭
function closePicture(obj) {
    $(obj).parent("div").remove();
}

//图片预览路径
function getObjectURL(file) {
    var url = null;
    if (window.createObjectURL != undefined) { // basic
        url = window.createObjectURL(file);
    } else if (window.URL != undefined) { // mozilla(firefox)
        url = window.URL.createObjectURL(file);
    } else if (window.webkitURL != undefined) { // webkit or chrome
        url = window.webkitURL.createObjectURL(file);
    }
    return url;
}