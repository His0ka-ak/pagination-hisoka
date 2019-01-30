<%@page import="com.pagination.web.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>OnlineDhanda</title>
<style>
a {
  text-decoration: none;
  display: inline-block;
  padding: 8px 16px;
}

a:hover {
  background-color: #ddd;
  color: red;
}

.previous {
  background-color: #f1f1f1;
  color: black;
}

.next {
  background-color: #4CAF50;
  color: white;
}

.round {
  border-radius: 50%;
}
</style>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
</head>
<body>
<h1 style="text-align:center">
		Products lelo Fraandzzz :-)
</h1>
<table class="table table-dark">
  <thead>
    <tr>
      <th >ID</th>
      <th >Name</th>
      <th >Price</th>
      <th >Quantity</th>
    </tr>
  </thead>
  <tbody>
    <%
    		List<Product> products=(List<Product>)request.getAttribute("currentProducts");
    		for(Product product : products){
    %>
    <tr>
    	<td><%=product.getId()%></td>
    	<td><%=product.getName()%></td>
    	<td><%=product.getPrice()%></td>
    	<td><%=product.getQuantity()%></td>
    </tr>
   <%
  	 }
    %>
    

      
  </tbody>
</table>
<div style="text-align:center">
    <a onclick="go('prev'); return false;" class="previous round" >&#8249;</a>
	<a onclick="go('next'); return false;" class="next round">&#8250;</a>
</div>

<script>
	function go(page) {
			window.location.href = "ProductControllerServlet?go="+page;
		
	}

</script>
	

</body>
</html>