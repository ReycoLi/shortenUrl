<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>shortenURL</title>
</head>
<body>
	<h1>Get you short URL here</h1>
	<br>
	<h3>please enter URL</h3>
	<script type="text/javascript">
		//url regex
		var urlRegex = /(http|ftp|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&:/~\+#]*[\w\-\@?^=%&/~\+#])?/;
		
		//check url validation 
		var urlok = false;
		function checkUrl() {
			var url = document.getElementById("longURL").value;
			//delete  string blank space
			url = url.trim();
			var errormsg = document.getElementById("errorMsg");
	
			if (url == "") {
				errormsg.innerHTML = "<font color='red'>url can not be empty</font>";
				urlok = false;
			} else if (url.length < 6 || url.length > 100) {
				errormsg.innerHTML = "<font color='red'>url's length should be in range [6-100] </font>";
				urlok = false;
			} else if(!urlRegex.test(url)){
				errormsg.innerHTML = "<font color='red'>please input valid url format</font>";
				urlok = false;
			} else {
				errormsg.innerHTML = "";
				urlok = true;
			}
		}
	
		//clear error message when user go back to edit url
		function clearError() {
			var errormsg = document.getElementById("errorMsg");
			errormsg.innerHTML = "";
		}
		
		//only if user input valid url can summit form
		function checkAll() {
			return urlok;
		}
	</script>

	<form name="longURLform" action="http://localhost:9000/shorten"
		method="get" onsubmit="return checkAll()">
		<input type="text" name="longURL" id="longURL" onblur="checkUrl();"
			onfocus="clearError();" /> 
		<span id="errorMsg"></span><br> 
		<input type="reset" value="reset" /> 
		<input type="submit" value="create" />
	</form>
</body>
</html>