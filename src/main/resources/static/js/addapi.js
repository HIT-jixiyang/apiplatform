function addapi() {
        var str=$("#apiform").serializeArray();
        var j=JSON.stringify(str);
        alert(j);
        $.ajax({
            type : "POST",
            url : "/sp/addapi",
            dataType : "text",
            data : $("#apiform").serializeArray(),
            contentType:"application/x-www-form-urlencoded",
            success : function(data) {
                alert("成功");
            },
            error : function(data) {
                alert("失败");
                console.log(data);
            }
        });
    }
var row=0;
function addrow() {
    if(row<20){
        row++;
        var table=$("#paratable");
        console.log("OK");
        var tr=$("<tr>"+" <td class='text-center'>"+"<input class='form-control ' type='text'  placeholder='参数名称'>"+"</td>" +
            " <td class='text-center'> "+"<input class='form-control ' type='text'  placeholder='参数值'>"+" </td>" +
            "  <td class='text-center'>"+"<button type='button' class='btn-link'>删除</button>"+"</td>"+"</tr>" );
        console.log(tr.toString());
        table.append(tr);
    }else{
        alert("已经达到最大参数数量");
    }
}
function deleterow(tdobject) {
    row--;
    var td=$(tdobject);
    td.parents("tr").remove();
}
function addtr(){
    if(row<8){
        row++;
        var table = $("#para_table");
        var param="apiRequestParamList["+row+"].api_param";
        var value="apiRequestParamList["+row+"].api_param_demo";
        var ismust="apiRequestParamList["+row+"].api_param_ismust";
        var position="apiRequestParamList["+row+"].api_param_position";
        var isconstant="apiRequestParamList["+row+"].api_param_isconstant";
        console.log(param);
        var tr= $(
            "<tr>" +
            "<td style='text-align: center;' >"+"<input type='text' name="+"'"+param+"'"+" class='text-primary text-center'/>"+"</td>" +
            "<td style='text-align: center;'>"+"<input type='text'  name="+"'"+value+"'"+"class='text-primary text-center' />"+"</td>" +
            "<td style=\"text-align:center; \" ><select name=\""+ismust+"\" class=\"text-primary text-center\">\n" +
            "                        <option value=\"0\" >可选</option>\n" +
            "                        <option value=\"1\">必选</option>\n" +
            "                    </select>\n" +
            "                    </td>\n" +
            "                    <td style=\"text-align:center; \" >\n" +
            "                    <select name=\""+position+"\" class=\"text-primary text-center\">\n" +
            "                        <option value=\"path\" class=\"active\">Path</option>\n" +
            "                        <option value=\"header\">Header</option>\n" +
            "                        <option value=\"body\">Body</option>\n" +
            "                    </select>\n" +
            "                    </td>"+
            " <td style=\"text-align:center; \" ><select name=\""+isconstant+"\" class=\"text-primary text-center\">\n" +
            "                        <option value=\"0\" >是</option>\n" +
            "                        <option value=\"1\">不是</option>\n" +
            "                    </select>\n" +
            "                    </td>"+
            "<td align='center' onclick='deletetr(this)'><button type='button' class='btn btn-xs btn-link' >"+"删除"+"</button></td></tr>"
        );
        table.append(tr);
    }else{
        alert("已达到最大参数数量");
    }
}
function deletetr(tdobject){
    row--;
    var td=$(tdobject);
    td.parents("tr").remove();
}
function gettableinfo(){
    var objInput = document.getElementById("para_table").getElementsByTagName("input");
    for (i = 0; i < objInput.length; i++) alert(objInput[i].value);

}