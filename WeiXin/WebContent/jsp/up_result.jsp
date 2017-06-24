<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="controller.util.Constant" %>
<% String URL_FILEUPLOAD = Constant.URL_FILEUPLOAD; %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="../js/jquery-3.1.0.min.js"></script>
<script type="text/javascript">
	var url = "<%=URL_FILEUPLOAD%>";	
	var accessToken = '';
	function ajax() {
		debugger
          //先声明一个异步请求对象
         var xmlHttpReg = null;
          if (window.ActiveXObject) {//如果是IE
             xmlHttpReg = new ActiveXObject("Microsoft.XMLHTTP");
          } else if (window.XMLHttpRequest) {
              xmlHttpReg = new XMLHttpRequest(); //实例化一个xmlHttpReg
          }
          //如果实例化成功,就调用open()方法,就开始准备向服务器发送请求
          if (xmlHttpReg != null) {
              xmlHttpReg.open("get", url, true);
               xmlHttpReg.send(null);
              xmlHttpReg.onreadystatechange = doResult; //设置回调函数

          }
          //回调函数
          //一旦readyState的值改变,将会调用这个函数,readyState=4表示完成相应
          //设定函数doResult()
          function doResult() {
              if (xmlHttpReg.readyState == 4) {//4代表执行完成
                 
                  if (xmlHttpReg.status == 200) {//200代表执行成功
                      //将xmlHttpReg.responseText的值赋给ID为resText的元素
                      accessToken = xmlHttpReg.responseText;
                  }
              }
          }
      }
	
	$.ajax({
		type : "post",
		//url: basePath + 'j_spring_security_check',
		url:'https://api.weixin.qq.com/cgi-bin/media/upload?access_token='+accessToken+'&type=image',
		data: {url : location.href.split('#')[0]},
		dataType: "json",
		success: function(response) {
			
	

		}
	});
	
	function submitForm(){
		var url = 'https://api.weixin.qq.com/cgi-bin/media/upload?access_token='+accessToken+'&type=image';
		document.getElementById("form1").action="url"
		var upload =	document.getElementById('upload');
		upload.click();
	}
</script>
<title>上传文件</title>
</head>
<body>
	<form action="up_result.jsp" method="post" enctype="multipart/form-data" name="form1" id="form1">
		 <label>
		 <input type="text" name="name" value="" />
		 </label>
		 <label>
		 <input type="file" name="userfile" />
		 </label>
		 <label>
		 <input id="upload" type="submit" value="上传" />
		 </label>
	</form>
	<br/>
	<button onclick="submitForm();"></button>
</body>
</html>