<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title></title>
	<style type="text/css">
		font{
			font-size=22em;
			font-weight: bold;
		}
	</style>
</head>
<body>
	
	<h1>9*9乘法表</h1>
	<hr/>
	<%for (int i =1 ;i<=9 ;i++ ) {   	
			 for(int j=1; j<=i; j++){ %>
			 	<%! int a;%>
			 	<%a = i*j; %>
			 	<%if(a%2==0){ %>
			 		<font style="color: green"><%=i%>*<%=j%>=<%=i*j%><font>
			 	<%}else{ %>	
			 		<font style="color: red"><%=i%>*<%=j%>=<%=i*j%><font>
			 	<%} %>
			 	<br>
			<%}%>
			 <br>
		<%}%>

</body>
</html>