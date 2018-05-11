var currentpage=1;
//换页
function huanye(li_obj) {


    var id=""+li_obj.id;
    id=id.substring(5,6);
    pageNum =parseInt(id);
    huanyebypage(pageNum);

}
//页面加载完成后点击分页按钮
function huanyebypage(pageNum) {
    var pagemap = {};
    pagemap.pageNo = pageNum;
    window.localStorage.setItem("currentpage",pageNum);
    pagemap.pageSize = 12;
    $.ajax({
        url: "/consumer/get_applist_by_consumer_id",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(pagemap),
        success: function (data) {
            console.log(data.data);
            var con = "";
            $.each(data.data, function (index, item) {
                console.log(item.app_name);

                con += " <tr>\n" +
                    "               <td>" + item.app_id + "</td>\n" +
                    "               <td>" + item.app_name + "</td>\n" +
                    "               <td>" + item.app_secret + "</td>\n" +
                    "               <td><a>查看详情</a><a>其他</a></td>\n" +
                    "\n" +
                    "           </tr>"
                //   console.log(con);
            });
            document.getElementById("app_list").innerHTML = "";
            document.getElementById("app_list").innerHTML = con;
            //  location.reload();
            var pageNum = Math.ceil(data.total / 12);
            var n=0;
            var pre;
            var next;
            var li;
            document.getElementById("page").innerHTML="";
            if (pageNum <= 5) {

                for ( n = 0; n < pageNum + 2; n++) {
                    if (n === 0) {
                        pre = $(" <li class=\"previous\"><a href=\"#\">&larr; Older</a></li>");
                        $("#page").append(pre);
                    }
                    else if (n !== pageNum + 1) {
                        li = $("   <li><a id=\"page-"+n+"\" onclick='huanye(this)'>"+n+"</a></li>")
                        $("#page").append(li);
                    }
                    else if(n===pageNum+1) {
                        next = $("  <li class=\"next\"><a href=\"#\">Newer &rarr;</a></li>");
                        $("#page").append(next);
                    }
                }
            }
            else{
                var bigin=Math.floor(pageNum/5);
                for ( n = begin*5; n < bigin*5+2; n++) {
                    if (n === 0) {
                        pre = $(" <li class=\"previous\"><a href=\"#\">&larr; Older</a></li>");
                        $("#page").append(pre);
                    }
                    else if (n !== pageNum + 1) {
                        li = $("   <li><a id=\"page-"+n+"\" onclick='huanye(this)'>"+n+"</a></li>")
                        $("#page").append(li);
                    }
                    else if(n===pageNum+1) {
                        next = $("  <li class=\"next\"><a href=\"#\">Newer &rarr;</a></li>");
                        $("#page").append(next);
                    }
                }
            }
        }
    });

}
//页面加载就显示列表
$(document).ready(function () {
    var pagemap = {};
    pagemap.pageNo = 1;
    pagemap.pageSize = 12;
    window.localStorage.setItem("currentpage",1);
    $.ajax({
        url: "/consumer/get_applist_by_consumer_id",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(pagemap),
        success: function (data) {
            console.log(data.data);
            console.log(data.total);

            var con = "";
            $.each(data.data, function (index, item) {
                console.log(item.app_name);

                con += " <tr>\n" +
                    "               <td>" + item.app_id + "</td>\n" +
                    "               <td>" + item.app_name + "</td>\n" +
                    "               <td>" + item.app_secret + "</td>\n" +
                    "               <td><a>查看详情</a><a>其他</a></td>\n" +
                    "\n" +
                    "           </tr>"
                //   console.log(con);
            });
            document.getElementById("app_list").innerHTML = con;
            // location.reload();
            var pageNum = Math.ceil(data.total / 12);
            window.localStorage.setItem("pageNumTotal",pageNum);
            var n=0;
            var pre;
            var next;
            var li;
            document.getElementById("page").innerHTML="";
            if (pageNum <= 5) {

                for ( n = 0; n < pageNum + 2; n++) {
                    if (n === 0) {
                        pre = $(" <li class=\"previous\"><a onclick='prepage()'>&larr; Older</a></li>");
                        $("#page").append(pre);
                    }
                    else if (n !== pageNum + 1) {
                        li = $("   <li><a id=\"page-"+n+"\" onclick='huanye(this)'>"+n+"</a></li>")
                        $("#page").append(li);
                    }
                    else if(n===pageNum+1) {
                        next = $("  <li class=\"next\"><a onclick='nextpage()'>Newer &rarr;</a></li>");
                        $("#page").append(next);
                    }
                }
            }
            else{
                var bigin=Math.floor(pageNum/5);
                for ( n = begin*5; n < bigin*5+2; n++) {
                    if (n === 0) {
                        pre = $(" <li class=\"previous\"><a onclick='prepage()'>&larr; Older</a></li>");
                        $("#page").append(pre);
                    }
                    else if (n !== pageNum + 1) {
                        li = $("   <li><a id=\"page-"+n+"\" onclick='huanye(this)'>"+n+"</a></li>")
                        $("#page").append(li);
                    }
                    else if(n===pageNum+1) {
                        next = $("  <li class=\"next\"><a onclick='nextpage()'>Newer &rarr;</a></li>");
                        $("#page").append(next);
                    }
                }
            }
        }
    });
});
//添加App
function createApp() {
    var appname = $("#app_name").val();
    var appdescription = $("#app_description").val();
    var app = {};
    app.app_name = appname;
    app.app_description = appdescription;
    console.log(JSON.stringify(app));
    $.ajax({
        url: "/consumer/addapp",
        type: "POST",
        data: JSON.stringify(app),
        contentType: "application/json",
        success: function (data) {
            alert(data.status);
            location.reload();
        }
    })
}
function prepage() {
    var pagenum=window.localStorage.getItem("currentpage");
    if(pagenum>1){
        huanyebypage(pagenum-1);
    }else {
        alert("已经到第一页");
    }
}
function nextpage() {
    var pagenum=window.localStorage.getItem("currentpage");
    if(pagenum<window.localStorage.getItem("pageNumTotal")){
        huanyebypage(pagenum+1);
    }else {
        alert("已经到最后一页");
    }
}
