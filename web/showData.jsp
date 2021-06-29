<%--
  Created by IntelliJ IDEA.
  User: 86166
  Date: 2021/6/28
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Echart展示博客数据</title>
    <%--    引入echart所需的js--%>
    <script src="https://cdn.staticfile.org/echarts/4.3.0/echarts.min.js"></script>
    <script type="text/javascript" src="./js/jquery-1.8.3.js"></script>
</head>
<body>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="width: 600px;height:400px;"></div>
    <script type="text/javascript">

        // 基于准备好的dom，初始化echarts实例

        // 基于准备好的dom，初始化echarts实例
        <%--myChart.setOption({--%>
        <%--    series : [--%>
        <%--        {--%>
        <%--            name: '访问来源',--%>
        <%--            type: 'pie',    // 设置图表类型为饼图--%>
        <%--            radius: '55%',  // 饼图的半径，外半径为可视区尺寸（容器高宽中较小一项）的 55% 长度。--%>
        <%--            data: [--%>
        <%--                {${requestScope.list}}--%>
        <%--            ]--%>
        <%--        }--%>
        <%--    ]--%>
        <%--})--%>

       window.onload=function () {
           console.log("加载数据");
           //基于准备好的dom,初始化echarts实例
           var myChart=echarts.init(document.getElementById("main"), 'dark');
           // 指定图表的配置项和数据
           var option = {
               title : {
                   text: '博客评论量统计',
                   subtext: '数据库统计结果'
               },
               tooltip : {
                   trigger: 'axis'
               },
               legend: {
                   data:['评论数','浏览量']
               },
               toolbox: {
                   show : true,
                   feature : {
                       dataView : {show: true, readOnly: false},
                       magicType : {show: true, type: ['line', 'bar']},
                       restore : {show: true},
                       saveAsImage : {show: true}
                   }
               },
               calculable : true,
               xAxis : [
                   {
                       type : 'category',
                       data : []
                   }
               ],
               yAxis : [
                   {
                       type : 'value'
                   }
               ],
               series : [
                   {
                       name:'评论数',
                       type:'bar',
                       data:[],
                       markPoint : {
                           data : [
                               {type : 'max', name: '最大值'},
                               {type : 'min', name: '最小值'}
                           ]
                       },
                       markLine : {
                           data : [
                               {type : 'average', name: '平均值'}
                           ]
                       }
                   },
                   {
                       name:'浏览量',
                       type:'bar',
                       data:[],
                       markPoint : {
                           data : [
                               {name : '最高', value : 182.2, xAxis: 7, yAxis: 183},
                               {name : '最低', value : 2.3, xAxis: 11, yAxis: 3}
                           ]
                       },
                       markLine : {
                           data : [
                               {type : 'average', name : '平均值'}
                           ]
                       }
                   }
               ]
           };

            $.post(
                "showData",//url
                {"email":JSON.parse(localStorage.getItem("email"))},//数据
               function (success) {
               var result=JSON.parse(success);

               if(result){
                   // 填入数据
                   console.log(result);
                   var commentCount = [];
                   var browseCount = [];
                   var title=[];
                   for(var i=0;i<result.length;i++){
                       commentCount.push(result[i].browse);
                       browseCount.push(result[i].browse);
                      title.push(result[i].title);
                   }
                   myChart.setOption({
                       xAxis: {
                           data: title
                       },
                       series: [{
                           // 根据名字对应到相应的系列
                           name: '浏览量',
                           data: browseCount
                       },{
                           // 根据名字对应到相应的系列
                           name: '评论数',
                           data: commentCount
                       }]
                   });

               }


               },
            );
           //上面执行完后再执行
           // 使用刚指定的配置项和数据显示图表。
           myChart.setOption(option);

       }
    </script>

</body>
</html>
