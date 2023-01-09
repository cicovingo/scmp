<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Sepetim</title>
</head>
<body>
	<table border="1" cellpadding="5">
		<caption>
			<h2>Ürün Listesi</h2>
		</caption>
		<tr>
			<th>Ürün Numarası</th>
			<th>Ürün Adı</th>
			<th>Stok Miktarı</th>
			<th>Fiyat(TL)</th>
		</tr>
		<c:forEach var="p" items="${retailer.orderList}">
			<tr>
				<td><c:out value="${p.productId}" /></td>
				<td><c:out value="${p.productName}" /></td>
				<td><c:out value="${p.quantity}" /></td>
				<td><c:out value="${p.price}" /></td>
				<td><button value="Sil" onclick="${retailer.deleteOrder(p)}" /></td>
			</tr>
		</c:forEach>
	</table>
	<button value="Talep Et" onclick="${retailer.sellProducts()}" />
</body>
</html>