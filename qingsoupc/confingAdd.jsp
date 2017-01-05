<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>纠错流程字段配置增加</title>
  <link rel="stylesheet" href="../RealEstateSource/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
  <link href="../RealEstateSource/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="../RealEstateSource/xcConfirm/css/xcConfirm.css" rel="stylesheet">

  <script src="../RealEstateSource/bootstrap/js/jquery-1.9.1.min.js"></script>
  <script src="../RealEstateSource/bootstrap/js/bootstrap.min.js"></script>
  <script src="../RealEstateSource/bootstrap/js/bootstrapAlert.js"></script>

  <script src="../RealEstateSource/bootstrap/js/extendPagination.js"></script>
  <script src="../RealEstateSource/xcConfirm/js/xcConfirm.js"></script>

  <script src="../RealEstateSource/ztree/js/jquery.ztree.core-3.5.js"></script>
  <script src="../RealEstateSource/ztree/js/jquery.ztree.excheck-3.5.js"></script>
  <script src="../RealEstateSource/ztree/js/jquery.ztree.exedit-3.5.js"></script>

<style type="text/css">
  body{margin:0;padding:0;overflow-x:hidden;}
  html, body{height:100%;}
  img{border:none;}
  .container{width:100%;height:100%;margin:auto;}
  /*table*/
  .table tbody tr td{
    vertical-align: middle;
  }
</style>
<script type="text/javascript">
  var baseUrl="http://localhost:8080/a/restServlet?";
  //var baseUrl="http://"+location.hostname+":8084/realestate/restServlet?";
  var bm="";
  var bzwm="";
  var oldLcdm="";//已经添加流程模块代码
  var oldLcmc="";//已添加流程模块名称
  var oldbz="";//已添加流程模块备注
  var vemuList=[];//枚举视图列表
  var setting = {
    data: {
      simpleData: {
        enable: true
      }
    },
    callback: {
      onClick: zTreeOnClick
    }
  };
  var zNodes =[
    { id:1, pId:0, name:"所有表", open:true},
    { id:11, pId:1, name:"A"},
    { id:12, pId:1, name:"B"},
    { id:13, pId:1, name:"C"},
    { id:14, pId:1, name:"D"},
    { id:15, pId:1, name:"E"},
    { id:16, pId:1, name:"F"},
    { id:17, pId:1, name:"G"},
    { id:18, pId:1, name:"H"},
    { id:19, pId:1, name:"I"},
    { id:110, pId:1, name:"J"},
    { id:111, pId:1, name:"K"},
    { id:112, pId:1, name:"L"},
    { id:113, pId:1, name:"M"},
    { id:114, pId:1, name:"N"},
    { id:115, pId:1, name:"O"},
    { id:116, pId:1, name:"P"},
    { id:117, pId:1, name:"Q"},
    { id:118, pId:1, name:"R"},
    { id:119, pId:1, name:"S"},
    { id:120, pId:1, name:"T"},
    { id:121, pId:1, name:"U"},
    { id:122, pId:1, name:"V"},
    { id:123, pId:1, name:"W"},
    { id:124, pId:1, name:"X"},
    { id:125, pId:1, name:"Y"},
    { id:126, pId:1, name:"Z"}
  ];
  $(function(){
    $.fn.zTree.init($("#treeDemo"), setting, zNodes);
    $("[data-toggle='popover']").popover();
    setVemuList();
    //修改流程模块
    $("#updateLcMk").click(function(){
      debugger;
      var fmcV=$("#formJclcmc").val();
      var fbzV=$("#formJclcbz").val();
      var data={jclcmc:fmcV,bz:fbzV};
      var updateLcUrl=baseUrl+"method=put&urlPath=/systemapp/modifycontentconfigsByLcdm/"+oldLcdm;
      $.post(updateLcUrl, {jsonData: JSON.stringify(data)}, function (result) {
        var array = result.split("♀");
        var status = array[0];
        if (status == 200) {
          window.wxc.xcConfirm("修改成功！", window.wxc.xcConfirm.typeEnum.success);
          oldLcmc = fmcV
          oldbz = fbzV;
          $("#formJclcmc").css("color","blue");//修改对应td为蓝色
          $("#formJclcbz").css("color","blue");//修改对应td为蓝色
          reloadselectedTable(1);
        } else{
          window.wxc.xcConfirm("修改失败！", window.wxc.xcConfirm.typeEnum.error);
          $("#formJclcmc").css("color","red");//修改对应td为红色
          $("#formJclcbz").css("color","blue");//修改对应td为蓝色
        }
      });
    });
  });
  function zTreeOnClick(event, treeId, treeNode) {
    debugger;
    if(treeNode.pId!=1&&treeNode.pId!=0){//查询该表对应的字段
      bm=treeNode.value;
      bzwm=treeNode.comments;
      var searchUrl=baseUrl+"method=get&urlPath=/systemapp/modifycontentconfigs?queryType=2￥bm="+bm;
      $.post(searchUrl, function(result){
        debugger;
        var array = result.split("♀");
        var status = array[0];
        var message = array[1];
        if(status==200){
          var jmessage=JSON.parse(message);
          var totalcuontV=jmessage.total;
          var data=jmessage.data;
          createAddTableTr(data);
          $('#addTcallBackPager').extendPagination({
            totalCount:totalcuontV,
            showCount: 10,
            limit: 10,
            callback: function (curr) {
              addTablePageCallBack(curr);
            }
          });
        }else{
          window.wxc.xcConfirm("获取字段失败！", window.wxc.xcConfirm.typeEnum.error);
        }
      });
    }else{//添加表节点
      if(!treeNode.children){
        var searchUrl=baseUrl+"method=get&urlPath=/systemapp/usertabless-all?islike=2￥type=1￥tableName="+treeNode.name;
        $.post(searchUrl, function(result){
          var array = result.split("♀");
          var status = array[0];
          var message = array[1];
          if(status==200){
            var tables=JSON.parse(message);
            var children=[];
            for(var i =0; i < tables.length; i++) {
              var tableName=tables[i].tableName;
              var viewName=tables[i].tableName;//显示名
              var comments=tables[i].comments;//表说明
              if(comments){
                viewName+=comments;
              }
              var child = { name:viewName,value:tableName,comments:comments};
              children.push(child);
            }
            var treeObj = $.fn.zTree.getZTreeObj(treeId);
            treeObj.addNodes(treeNode, children);
          }else{
            window.wxc.xcConfirm("获取表失败！", window.wxc.xcConfirm.typeEnum.error);
          }
        });
      }
    }
  }

  //当前选择表所有字段表翻页
  function addTablePageCallBack(currPage){
    var searchUrl=baseUrl+"method=get&urlPath=/systemapp/modifycontentconfigs?queryType=2￥bm="+bm;
    if(currPage!=null&&currPage!=''){
      currPage=(currPage-1)*10;
      searchUrl=searchUrl+"￥start="+currPage;
    }
    $.post(searchUrl, function(result){
      var array = result.split("♀");
      var status = array[0];
      var message = array[1];
      if(status==200) {
        var jmessage=JSON.parse(message);
        var data=jmessage.data;
        createAddTableTr(data);
      }else{
        window.wxc.xcConfirm("获取字段失败！", window.wxc.xcConfirm.typeEnum.error);
      }
    });
  }

  //创建添加表行
  function createAddTableTr(data){
    var dataSize=data.length;
    var html = [];
    for (var i = 0; i <dataSize; i++) {
      html.push("<tr><td>" + data[i].zdm + "</td>");
      if (data[i].zdsm!=null) {
        html.push("<td>" + data[i].zdsm + "</td>");
      }else{
        html.push("<td></td>");
      }
      if(1==data[i].zdlx){
        html.push("<td>VARCHAR2</td>");
      }else if(2==data[i].zdlx){
        html.push("<td>NUMBER</td>");
      }else if(3==data[i].zdlx){
        html.push("<td>DATE</td>");
      }else{
        html.push("<td>其他类型（转换为VARCHAR2）</td>");
      }
      var mjjsmc="";
      if(data[i].enumvalueEntity){
        html.push("<td>是</td>");
        html.push("<td>" + data[i].enumvalueEntity.enumcaption + "</td>");
        mjjsmc=data[i].enumvalueEntity.enumname;
      }else{
        html.push("<td>否</td>");
        html.push("<td></td>");
      }
      var zdlx="";
      if(99==data[i].zdlx){
        zdlx=1;//所有的其他类型都转换为varchar2
      } else{
        zdlx=data[i].zdlx;
      }
      html.push("<td><a class='btn btn-primary btn-sm' onclick=\"addCols('"+data[i].bm+"','"+data[i].zdm+"','"+data[i].zdsm+"',"
        +zdlx+",'"+mjjsmc +"',this)\" >添加</a></td>");
      html.push('</tr>');
    }
    var mainObj = $('#addTbody');
    mainObj.empty();
    mainObj.html(html.join(''));
  }
  //为流程模块添加字段
  function addCols(bm,zdm,zdsm,zdlx,mjjsmc,obj){
    debugger;
    var lcdmV=$("#formJclcdm").val();
    var lcmcV=$("#formJclcmc").val();
    var bzV=$("#formJclcbz").val();
    var sfmjjs=1;
    if(mjjsmc==""||mjjsmc==null||mjjsmc=="null"){
      sfmjjs=0;
      mjjsmc="";
    }
    var data="";
    var addUrl="";
    if(oldLcdm==""){//第一次添加
      if(lcdmV==""||lcdmV==null){
        window.wxc.xcConfirm("请添加流程代码！", window.wxc.xcConfirm.typeEnum.warning);
      }else if(lcmcV==""||lcmcV==null){
        window.wxc.xcConfirm("请添加流程名称！", window.wxc.xcConfirm.typeEnum.warning);
      }else {
        if(zdsm=="null"){
          data = {jclcmc: lcmcV, jclcdm: lcdmV, bm: bm, zdm: zdm, zdlx: zdlx, sfmjjs: sfmjjs, mjjsmc: mjjsmc, bz: bzV, bzwm:bzwm};
        }else{
          data = {jclcmc: lcmcV, jclcdm: lcdmV, bm: bm, zdm: zdm, zdsm: zdsm, zdlx: zdlx, sfmjjs: sfmjjs, mjjsmc: mjjsmc, bz: bzV, bzwm:bzwm};
        }
        addUrl = baseUrl + "method=post&urlPath=/systemapp/modifycontentconfigssingle";
        $.post(addUrl, {jsonData: JSON.stringify(data)}, function (result) {
          debugger;
          var array = result.split("♀");
          var status = array[0];
          if (status == 201) {
            var $obj=$(obj);
            messageAlert("message","添加成功!!",1000);
            $obj.parent().parent().css("background-color","green");//修改对应td为绿色
            oldLcmc = lcmcV;
            oldLcdm = lcdmV;
            oldbz = bzV;
            reloadselectedTable(1);
            $("#formJclcdm").attr("disabled",true);
            $("#updateLcMk").attr("disabled",false);
          } else if(status==423){
            window.wxc.xcConfirm("已有该流程模块请修改流程代码！", window.wxc.xcConfirm.typeEnum.warning);
          }else{
            window.wxc.xcConfirm("添加失败！", window.wxc.xcConfirm.typeEnum.error);
          }
        });
      }
    }else{
      if(zdsm=="null"){
        data = {jclcmc: lcmcV, jclcdm: lcdmV, bm: bm, zdm: zdm, zdlx: zdlx, sfmjjs: sfmjjs, mjjsmc: mjjsmc, bz: bzV, bzwm:bzwm};
      }else{
        data = {jclcmc: lcmcV, jclcdm: lcdmV, bm: bm, zdm: zdm, zdsm: zdsm, zdlx: zdlx, sfmjjs: sfmjjs, mjjsmc: mjjsmc, bz: bzV, bzwm:bzwm};
      }
      addUrl=baseUrl+"method=post&urlPath=/systemapp/modifycontentconfigs";
      $.post(addUrl, { jsonData: JSON.stringify(data) }, function (result) {
        debugger;
        var array = result.split("♀");
        var status = array[0];
        if(status==201){
          debugger;
          var $obj=$(obj);
          messageAlert("message","添加成功!!",1000);
          $obj.parent().parent().css("background-color","green");//修改对应td为绿色
          reloadselectedTable(1);
        }else if(status==423){
          window.wxc.xcConfirm("已添加该字段！", window.wxc.xcConfirm.typeEnum.warning);
        }else{
          window.wxc.xcConfirm("添加失败！", window.wxc.xcConfirm.typeEnum.error);
        }
      });
    }
  }
  //重新加载已选列表
  function reloadselectedTable(currPage){
    var searchUrl=baseUrl+"method=get&urlPath=/systemapp/modifycontentconfigs?order=desc￥sort=gxsj￥jclcdm="+oldLcdm;
    if(currPage!=null&&currPage!=''){
      currPage=(currPage-1)*10;
      searchUrl=searchUrl+"￥start="+currPage;
    }
    $.post(searchUrl, function(result) {
      debugger;
      var array = result.split("♀");
      var status = array[0];
      var message = array[1];
      if (status == 200) {
        var jmessage = JSON.parse(message);
        var totalcountV = jmessage.total;
        var data = jmessage.data;
        createSelectedTableTr(data);
        $('#sTcallBackPager').extendPagination({
          totalCount: totalcountV,
          showCount: 10,
          limit: 10,
          callback: function (curr) {
            selectedTablepageCallBack(curr);
          }
        });
      }
    });
  }
  function selectedTablepageCallBack(currPage){
    var searchUrl=baseUrl+"method=get&urlPath=/systemapp/modifycontentconfigs?order=desc￥sort=gxsj￥jclcdm="+oldLcdm;
    if(currPage!=null&&currPage!=''){
      currPage=(currPage-1)*10;
      searchUrl=searchUrl+"￥start="+currPage;
    }
    $.post(searchUrl, function(result){
      var array = result.split("♀");
      var status = array[0];
      var message = array[1];
      if(status==200) {
        var jmessage=JSON.parse(message);
        var data=jmessage.data;
        createSelectedTableTr(data);
      }else{
        window.wxc.xcConfirm("获取字段失败！", window.wxc.xcConfirm.typeEnum.error);
      }
    });
  }
  //创建选择表行
  function createSelectedTableTr(data){
    debugger;
    var dataSize = data.length;
    var html = [];
    for (var i = 0; i < dataSize; i++) {
      html.push("<tr><td>" + data[i].bm + "</td>");
      if(data[i].bzwm){
        //html.push("<td><input type='text' value='"+data[i].bzwm+"' /></td>");
        html.push("<td>"+data[i].bzwm+"</td>");
      }else{
        //html.push("<td><input type='text' value=''/></td>");
        html.push("<td></td>");
      }
      html.push("<td>" + data[i].zdm + "</td>");
      if(data[i].zdsm){
        html.push("<td><input type='text' value='"+data[i].zdsm+"'/></td>");
      }else{
        html.push("<td><input type='text' value=''/></td>");
      }
      if(data[i].zdlx==1) {
        html.push("<td>VARCHAR2</td>");
      }else if(data[i].zdlx==2){
        html.push("<td>NUMBER</td>");
      }else if(data[i].zdlx==3){
        html.push("<td>DATE</td>");
      }
      var eSize=vemuList.length;
      if(data[i].sfmjjs==0){
        html.push("<td>否</td>");
        html.push("<td><select class='form-control'>");
        html.push("<option selected = 'selected' value=''>没有枚举</option>");
        for(var v = 0; v <eSize; v++){
          html.push("<option value='"+vemuList[v].ENUMNAME+"'>"+vemuList[v].ENUMCAPTION+"</option>");
        }
      }else{
        html.push("<td>是</td>");
        html.push("<td><select class='form-control'>");
        html.push("<option  value=''>没有枚举</option>");
        for(var v = 0; v <eSize; v++){
          if(data[i].mjjsmc==vemuList[v].ENUMNAME){
            html.push("<option selected = 'selected' value='"+vemuList[v].ENUMNAME+"'>"+vemuList[v].ENUMCAPTION+"</option>");
          }else{
            html.push("<option value='"+vemuList[v].ENUMNAME+"'>"+vemuList[v].ENUMCAPTION+"</option>");
          }
        }
      }
      html.push("</select></td>");
      html.push("<td><a class='btn btn-primary btn-sm' onclick=\"updateCols("+data[i].id+",this)\" >修改</a>"
       +"<a class='btn btn btn-danger btn-sm' onclick=\"delZdByIdClick("+data[i].id+")\" >删除</a></td>");
      html.push('</tr>');
    }
    var mainObj = $('#selectedTbody');
    mainObj.empty();
    mainObj.html(html.join(''));
  }
  //修改字段
  function updateCols(id,obj){
    debugger;
    //获取对应的字段说明的值
    var $obj=$(obj);
    var td=$obj.parent().prev().prev().prev().prev();
    var zdsmV=td.children().val();
    var mjtd=$obj.parent().prev();
    var mjjsmcV=mjtd.children().val();
    var sfmjjsV=0;
    if(mjjsmcV!==""){
      sfmjjsV=1;
    }
    var updateUrl=baseUrl+"method=put&urlPath=/systemapp/modifycontentconfigs/"+id;
    var data={zdsm:zdsmV,mjjsmc:mjjsmcV,sfmjjs:sfmjjsV};
    $.post(updateUrl, { jsonData: JSON.stringify(data) }, function(result){
      debugger;
      var array = result.split("♀");
      var status = array[0];
      if(status==200) {
        window.wxc.xcConfirm("修改成功！", window.wxc.xcConfirm.typeEnum.success);
        var sfmjjstd=$obj.parent().prev().prev();
        if(sfmjjsV==0){
          sfmjjstd.html("否");
        }else{
          sfmjjstd.html("是");
        }
        $obj.parent().parent().css("background-color","green");//修改对应td为绿色
      }else{
        window.wxc.xcConfirm("获取字段失败！", window.wxc.xcConfirm.typeEnum.error);
        $obj.parent().parent().css("background-color","red");//修改对应td为红色
      }
    });
  }
  //根据id删除配置字段
  function delZdByIdClick(id){
    debugger;
    var option = {
      title: "删除确认",
      btn: parseInt("0011",2),
      onOk: function(){
        delZdById(id);
      }
    }
    window.wxc.xcConfirm("你确定要删除这个配置字段么？", "custom", option);
  }
  function delZdById(id){
    var delUrl=baseUrl+"method=del&urlPath=/systemapp/modifycontentconfigs/"+id;
    $.post(delUrl, function(result){
      var array = result.split("♀");
      var status = array[0];
      if(status==204){
        window.wxc.xcConfirm("删除成功！", window.wxc.xcConfirm.typeEnum.success);
        reloadselectedTable(1);
      }else{
        window.wxc.xcConfirm("删除失败！", window.wxc.xcConfirm.typeEnum.error);
      }
    });
  }
  //设置视图枚举列表
  function setVemuList(){
    var searchUrl=baseUrl+"method=get&urlPath=/systemapp/getAllVEnum";
    debugger;
    $.get(searchUrl,function(data,status){
      debugger;
      if(status) {
        var arrray = data.split("♀");
        vemuList = JSON.parse(arrray[1]);
      }else{
        window.wxc.xcConfirm("加载枚举列表失败！", window.wxc.xcConfirm.typeEnum.error);
      }
    });
  }
</script>
</head>
<body id="bg">
<div class="container">
<!--导航头-->
  <nav class="navbar navbar-default" role="navigation">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">纠错流程字段配置---添加流程模块</a>
    </div>
    <div class="navbar-right">
      <button  class='btn navbar-btn'
               data-container="body" data-toggle="popover" data-placement="bottom"
               data-content="1.该页可以完成添加流程模块，添加配置字段，修改字段说明，修改流程模块名称备注的操作;2.点击'当前选择表所有字段'中的添加按钮将增加配置字段，在第一次添加时会添加流程模块3.可以在'已选配置字段'中修改字段说明;4.如果发现流程模块名称或者备注填错可以重新填，然后点击‘修改’进行修改5.蓝色字体代表修改过并已经与数据库同步，红色字体代表操作失败">说明
      </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </div>
  </nav>
  <!--流程模块表单-->
  <form class="form-horizontal" role="form">
    <div class="form-group">
      <label for="formJclcmc" class="col-sm-2 control-label">纠错模块名称</label>
      <div class="col-sm-10">
        <input type="text" class="form-control" id="formJclcmc"
               placeholder="请输入纠错流程名称">
      </div>
    </div>
    <div class="form-group">
      <label for="formJclcdm" class="col-sm-2 control-label">纠错模块代码</label>
      <div class="col-sm-10">
        <input type="text" class="form-control" id="formJclcdm"
               placeholder="请输入纠错流程代码，不能与数据库中已有的重复">
      </div>
    </div>
    <div class="form-group">
      <label for="formJclcbz" class="col-sm-2 control-label">备注</label>
      <div class="col-sm-10">
        <input type="text" class="form-control" id="formJclcbz"
               placeholder="请输入备注">
      </div>
    </div>
    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <button type="button" class="btn btn-default" disabled="true" id="updateLcMk">修改</button>
      </div>
    </div>
  </form>

  <table class="table table-bordered table-striped text-center bg-info" id="selectedTable">
    <caption>已选配置字段</caption>
    <thead >
    <tr class="info">
      <th class="text-center">表名</th>
      <th class="text-center">表说明</th>
      <th class="text-center">字段名</th>
      <th class="text-center">字段说明</th>
      <th class="text-center">字段类型</th>
      <th class="text-center">是否枚举</th>
      <th class="text-center">枚举类型</th>
      <th class="text-center">操作</th>
    </tr>
    </thead>
    <tbody id="selectedTbody">
    </tbody>
  </table>
  <div id="sTcallBackPager"></div>

  <div class="row">
    <!--左侧导航-->
    <div class="col-xs-4 bg-blue" >
      <div class="content_wrap">
        <ul id="treeDemo" class="ztree"></ul>
      </div>
    </div>
    <!--右侧编辑框-->
    <div class="col-xs-8" >

      <table class="table table-bordered table-striped text-center bg-info" id="addTable">
        <caption>当前选择表所有字段 <div style="float: right"><strong id="message"></strong></div></caption>
        <thead >
          <th class="text-center">字段名</th>
          <th class="text-center">字段说明</th>
          <th class="text-center">字段类型</th>
          <th class="text-center">是否枚举</th>
          <th class="text-center">枚举名称</th>
          <th class="text-center">操作</th>
        </tr>
        </thead>
        <tbody id="addTbody">
        </tbody>
      </table>
      <div id="addTcallBackPager"></div>

    </div>
  </div>
</div>

</body>
</html>
