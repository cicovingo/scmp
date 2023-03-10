<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Tedarikçi</title>
</head>
<body>
	<p align="right">
		<a href=login.jsp>Çıkış Yap</a>
	</p>
	<p align="center">Onay Bekleyen Siparişler</p>
	<form action="supplierServlet" method="get">
		<table border="1" cellpadding="5">
			<caption>
				<h2>Sipariş Listesi</h2>
			</caption>
			<tr>
				<th>Sipariş Numarası</th>
				<th>Ürün Numarası</th>
				<th>Talep Miktarı</th>
				<th>Toplam Fiyat(TL)</th>
			</tr>
			<c:forEach var="p" items="${supplierServlet.orderProductList}">
				<tr>
					<td><c:out value="${p.orderId}" /></td>
					<td><c:out value="${p.productId}" /></td>
					<td><c:out value="${p.quantity}" /></td>
					<td><c:out value="${p.totalAmount}" /></td>
					<td><button value="Onayla"
							onclick="${supplier.updateOrderProduct(p)}" /></td>
				</tr>
			</c:forEach>
		</table>
		<p align=right>
			<button value="Onayla" onclick="${supplier.updateOrderProducs()}" />
		</p>
		<hr>
		<p align="center">Ürünlerim</p>
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
			<c:forEach var="p" items="${supplierServlet.productList}">
				<tr>
					<td><c:out value="${p.productId}" /></td>
					<td><c:out value="${p.productName}" /></td>
					<td><c:out value="${p.quantity}" /></td>
					<td><c:out value="${p.price}" /></td>
					<td><button value="Güncelle"/></td>
				</tr>
			</c:forEach>
		</table>

	</form>
</body>
</html>