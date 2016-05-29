<%@page import="yyx.hive.server.Wifi"%>
<%@page import="yyx.hbase.server.StartInsert"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>WiFi</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
   <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
   <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
  </head>
  <body>
  <div class="container">
  		<div>
  				<% 
					String type = request.getParameter("type");
				
					if(type != null) {
						//hbase 导入
						String msg = "导入成功！";
						type = type.trim();
						if(type.compareTo("hbase_insert")==0) {
							String dataPath = request.getParameter("path");
							try{
								int consumer = Integer.valueOf(request.getParameter("consumer"));
								int producer = Integer.valueOf(request.getParameter("producer"));
								if(consumer!= 0 && producer != 0) {
									StartInsert.webApp(producer, consumer, dataPath);
								} else {
									msg = "数据为空！";
								}	
							} catch(Exception e) {
								msg = "请输入数字";
							}
	
						}
						//hive导入
						if(type.compareTo("hive_insert")==0) {
							String live_path = request.getParameter("hive_path");
							Wifi.load(live_path);
						}
						out.println("<div class='alert alert-danger' role='alert'>"+msg+"</div>");
					}
				%>
  		</div>
  		<h2 class="text-center">WiFi记录 大数据查询系统</h2>
    	<div class="panel panel-primary">
		   <div class="panel-heading">
		      <h3 class="panel-title">HBase</h3>
		   </div>
		   <div class="panel-body">
		   		
		   		<h4>导入数据</h4>
				<form class="form-inline" method="get">
				<input type="text" style="display:none" name="type" value="hbase_insert">
				  <div class="form-group">
				      <input type="text" class="form-control" name="path" value="/mnt/hgfs/yyx/data/test" placeholder="导入数据路径">
				  </div>
				  <div class="form-group">
				      <input type="text" class="form-control" name="consumer" value="5" placeholder="消费者线程数">
				  </div>
				  <div class="form-group">
				      <input type="text" class="form-control" name="producer" value="6" placeholder="生产者线程数">
				  </div>
				  <div class="form-group">
				      <button type="submit" class="btn btn-primary">提交导入</button>
				  </div>
				</form>
			</div>
		</div>
		<div class="panel panel-primary">
		   <div class="panel-heading"><h3 class="panel-title">Hive</h3></div>
		   <div class="panel-body">
		   		
		   		<h4>Hive导入</h4>
		   		<form class="form-inline">
		   			<input type="text" style="display:none" name="type" value="hive_insert">
				  <div class="form-group">
				      <input type="text" class="form-control" name="hive_path" value="/mnt/hgfs/yyx/data/test" placeholder="导入路径">
				  </div>
				  <div class="form-group">
				      <button type="submit" class="btn btn-primary">提交导入</button>
				  </div>
				 </form>
				
				<h4>用户轨迹查询</h4>
		   		<form class="form-inline" method="get" action="./hive.jsp">
		   			<input type="text" style="display:none" name="type" value="user_track">
				  <div class="form-group">
				      <input type="text" class="form-control" value="2016-04-13 10:43:36" name="startTime" placeholder="查询开始时间">
				  </div>
				  <div class="form-group">
				      <input type="text" class="form-control" id="endTime" value="2016-04-14 11:43:36" name="endTime" placeholder="查询结束时间">
				  </div>
				  <div class="form-group">
				      <input type="text" class="form-control" value="ff:ff:ff:ff:ff:ff" name="userMac" placeholder="用户mac地址">
				  </div>
				  <div class="form-group">
				      <button type="submit" class="btn btn-primary">提交查询</button>
				  </div>
				</form>
				
				<h4>伴随情况分析</h4>
		   		<form class="form-inline" method="get" action="./hive.jsp">
		   				   <input type="text" style="display:none" name="type" value="user_accompany">
				  <div class="form-group">
				      <input type="text" class="form-control" name="startTime" value="2016-04-13 10:43:36" placeholder="查询开始时间">
				  </div>
				  <div class="form-group">
				      <input type="text" class="form-control" id="endTime" value="2016-04-14 11:43:36" name="endTime" placeholder="查询结束时间">
				  </div>
				  <div class="form-group">
				      <input type="text" class="form-control" value="ff:ff:ff:ff:ff:ff" name="userMac" placeholder="用户mac地址">
				  </div>
				  <div class="form-group">
				      <button type="submit" class="btn btn-primary">提交查询</button>
				  </div>
				</form>
				
				<h4>碰撞分析</h4>
		   		<form class="form-inline" method="get" action="./hive.jsp">
		   			<input type="text" style="display:none" name="type" value="user_crash">
				  <div class="form-group">
				      <input type="text" class="form-control" value="2016-04-13 10:43:36" name="startTime" placeholder="查询开始时间">
				  </div>
				  <div class="form-group">
				      <input type="text" class="form-control" id="endTime" value="2016-04-14 11:43:36" name="endTime" placeholder="查询结束时间">
				  </div>
				  <div class="form-group">
				      <input type="text" class="form-control" name="deviceMac" value="b8:27:eb:1c:c0:09" placeholder="设备Mmac">
				  </div>
				  <div class="form-group">
				      <button type="submit" class="btn btn-primary">提交查询</button>
				  </div>
				</form>
		
		   </div>		   
		</div>
	</div>
  </body>
</html>
