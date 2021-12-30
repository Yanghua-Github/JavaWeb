// function  redirectHandle(xhr) {
//
//     var url = xhr.getResponseHeader("redirectUrl");
//
//     var enable = xhr.getResponseHeader("enableRedirect");
//
//     if((enable == "true") && (url != "")){
//         var win = window;
//         while(win != win.top){
//             win = win.top;
//         }
//         win.location.href = url;
//     }
//
// }
//
// $(function () {
//
//     $(document).ajaxComplete(function (event, xhr, settings) {
//         redirectHandle(xhr);
//     })
// })

$.ajaxSetup( {
    //设置ajax请求结束后的执行动作
    complete : function(XMLHttpRequest, textStatus) {
        // 通过XMLHttpRequest取得响应头，REDIRECT
        var enable = XMLHttpRequest.getResponseHeader("enableRedirect");//若HEADER中含有REDIRECT说明后端想重定向
        if (enable == "true") {
            var win = window;
            while (win != win.top){
                win = win.top;
            }
            //将后端重定向的地址取出来,使用win.location.href去实现重定向的要求
            win.location.href= XMLHttpRequest.getResponseHeader("redirectUrl");
        }
    }
});
