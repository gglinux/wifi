<%@page import="java.sql.ResultSet"%>
<%@page import="yyx.hive.server.Wifi"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
   <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
   <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
  
  </head>
  
  <body>
  <div class="panel panel-primary">
  <!-- Default panel contents -->
  <div class="panel-heading">Hbase查询结果</div>
  <div class="panel-body">

	  <table class="table">
	      <thead>
	        <tr>
	          <th>device_mac</th>
	          <th>record_time</th>
	          <th>user_mac</th>
	          <th>ap_mac</th>
	          <th>data_rate</th>
	          <th>rssi_signal</th>
	          <th>channel_id</th>
	          <th>app_type</th>
	          <th>app_info</th>
	        </tr>
	      </thead>
	      
	      <tbody>
	      
	      
	        <%
				String type = request.getParameter("type");
				
				if(type != null) {
					
					if(type.compareTo("user_track")==0) {
						ResultSet result = Wifi.userTrack("'"+request.getParameter("startTime")+"'","'"+request.getParameter("endTime")+"'", "'"+request.getParameter("userMac")+"'");						
						while(result.next()) {
							out.println("<tr>");
							out.println(
						    		  "<td>"+result.getString(1) + "</td>" + 
						    		  "<td>"+result.getString(2) + "</td>" + 
						    		  "<td>"+result.getString(3) + "</td>" + 
						    		  "<td>"+result.getString(4) + "</td>" + 
						    		  "<td>"+result.getString(5) + "</td>" + 
						    		  "<td>"+result.getString(6) + "</td>" + 
						    		  "<td>"+result.getString(7) + "</td>" + 
						    		  "<td>"+result.getString(8) + "</td>" + 
						    		  "<td>"+result.getString(9) + "</td>"
						    		  );						
							out.println("<tr>");
						}
					}
					
					
					if(type.compareTo("user_accompany")==0) {
						ArrayList<ResultSet> results = Wifi.userAccompany("'"+request.getParameter("startTime")+"'","'"+request.getParameter("endTime")+"'", "'"+request.getParameter("userMac")+"'");						
						for(ResultSet result:results) {
							while(result.next()) {
								out.println("<tr>");
								out.println(
							    		  "<td>"+result.getString(1) + "</td>" + 
							    		  "<td>"+result.getString(2) + "</td>" + 
							    		  "<td>"+result.getString(3) + "</td>" + 
							    		  "<td>"+result.getString(4) + "</td>" + 
							    		  "<td>"+result.getString(5) + "</td>" + 
							    		  "<td>"+result.getString(6) + "</td>" + 
							    		  "<td>"+result.getString(7) + "</td>" + 
							    		  "<td>"+result.getString(8) + "</td>" + 
							    		  "<td>"+result.getString(9) + "</td>"
							    		  );						
								out.println("<tr>");
							}
						}						
					}
					
					
					if(type.compareTo("user_crash")==0) {
						ResultSet result = Wifi.userCrash("'"+request.getParameter("startTime")+"'","'"+request.getParameter("endTime")+"'", "'"+request.getParameter("deviceMac")+"'");						
						while(result.next()) {
							out.println("<tr>");
							out.println(
						    		  "<td>"+result.getString(1) + "</td>" + 
						    		  "<td>"+result.getString(2) + "</td>" + 
						    		  "<td>"+result.getString(3) + "</td>" + 
						    		  "<td>"+result.getString(4) + "</td>" + 
						    		  "<td>"+result.getString(5) + "</td>" + 
						    		  "<td>"+result.getString(6) + "</td>" + 
						    		  "<td>"+result.getString(7) + "</td>" + 
						    		  "<td>"+result.getString(8) + "</td>" + 
						    		  "<td>"+result.getString(9) + "</td>"
						    		  );						
							out.println("<tr>");
						}
						
					}
					
				} else {
					out.println("<div class='alert alert-danger' role='alert'>"+"参数错误！！"+"</div>");
				}
			  %>
	      </tbody>
	      
	    </table>
	   </div>
	  </div>
  </body>
</html>
