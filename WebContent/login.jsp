<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Giriş Yap</title>
</head>
<body>
<form action="login" method="post">
<table style="width: 20%">
<tr><td>Kullanıcı Tipi</td><td><select name="usertype"> 
    <option value="-1">--- Seçiniz ---</option> 
    <option value="retailer">Satıcı</option> 
    <option value="supplier">Tedarikçi</option> 
</select></td></tr>
<tr><td>Kullanıcı Adı</td><td><input type="text" name="username" /></td></tr>
<tr><td>Şifre</td><td><input type="password" name="password" /></td></tr>
</table>
<input type="submit" value="Giriş Yap" /></form>
</body>
</body>
</html>