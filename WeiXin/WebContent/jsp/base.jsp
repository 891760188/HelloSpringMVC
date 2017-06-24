<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html ">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>基础</title>
<style type="text/css">
	img{
		width:200px;
		height:300px
	}
</style>
<!-- <script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script> -->
<script src=" https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="../js/jquery-3.1.0.min.js"></script>
</head>
<body onload="init();">
	<button onclick="share();" style="width:300px;height:400px font-size:3em">分享</button><br/><br/>
	<button onclick="chooseImage();" style="width:300px;height:400px font-size:3em">chooseImage</button><br/><br/>
	<button onclick="openLocation();" style="width:300px;height:400px font-size:3em">openLocation</button><br/><br/>
	<button onclick="getLocation();" style="width:300px;height:400px font-size:3em">getLocation</button><br/><br/>
	<button onclick="getNetworkType();" style="width:300px;height:400px font-size:3em">getNetworkType</button><br/><br/>
	<button onclick="recode();" style="width:300px;height:400px font-size:3em">录音</button><br/><br/>
	<button onclick="menu();" style="width:300px;height:400px font-size:3em">菜单</button><br/><br/>
	<button onclick="scanQRCode();" style="width:300px;height:400px font-size:3em">scanQRCode</button><br/><br/>
	<div id="imgChooseImage"></div>
	<div id="imgViews">
		<img src="../image/a.png"/><br/>
		<img src="../image/b.png"/><br/>
		<img src="../image/c.gif"/><br/>
	</div>
	
</body>
<script type="text/javascript">
	function init(){
		$.ajax({
			type : "post",
			//url: basePath + 'j_spring_security_check',
			url:'http://1667006iw3.imwork.net/WeiXin/jsSignnaure.do',
			data: {url : location.href.split('#')[0]},
			dataType: "json",
			success: function(response) {
				wx.config({
				    debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
				    appId: response.appId, // 必填，公众号的唯一标识
				    timestamp: response.timestamp, // 必填，生成签名的时间戳
				    nonceStr: response.nonceStr, // 必填，生成签名的随机串
				    signature: response.signature,// 必填，签名，见附录1
				    jsApiList: [
						        'checkJsApi',
						        'onMenuShareTimeline',
						        'onMenuShareQQ',
						        'chooseImage',
						        'uploadImage',
						        'downloadImage',
						        'getLocation',
						        'getNetworkType',
						        'previewImage',
						        'openLocation',
						        'startRecord',
						        'stopRecord',
						        'playVoice',
						        'showOptionMenu',
						        'hideOptionMenu',
						        'scanQRCode',
						        'onMenuShareAppMessage'
						      ]
				});
				wx.ready(function () {
					  wx.checkJsApi({
					      jsApiList: [
							'checkJsApi',
							'onMenuShareTimeline',
							'onMenuShareQQ',
							'chooseImage',
							'uploadImage',
							'downloadImage',
							'getNetworkType',
							'getLocation',
							'previewImage',
							'openLocation',
							'startRecord',
							'stopRecord',
							'playVoice',
							'hideOptionMenu',
							'showOptionMenu',
							'scanQRCode',
							'onMenuShareAppMessage'
					      ],
					      success: function (res) {
					        alert(JSON.stringify(res));
					        
					      },
					      fail: function (res) {
						        alert('前客户端版本不支持指定JS接口');
					      }
					    });
					});
		
				  wx.error(function(res){
					    alert('2'+JSON.stringify(res));
					});

			}
		});
	}
	function share(){
		alert('调用微信分享方法');
		  wx.onMenuShareAppMessage({
			    title: '11', // 分享标题
			    desc: '11', // 分享描述
			    link: location.href.split('#')[0], // 分享链接
			    imgUrl: 'https://www.baidu.com/img/baidu_jgylogo3.gif', // 分享图标
			    type: 'link', // 分享类型,music、video或link，不填默认为link
			    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
			    success: function () { 
			        alert(11);
			    },
			    cancel: function () { 
			    	alert(22);
			    },
			    fail: function (res) {
		        	alert('微信分享失败');
		          alert(JSON.stringify(res));
		        },
			    complete: function (res) {
		        	alert('接口调用完成时执行的回调函数，无论成功或失败都会执行');
		        }
			}); 
		 
		 /*   wx.onMenuShareQQ({
			    title: '标题', // 分享标题
			    desc: '描述', // 分享描述
			    link: 'http://1667006iw3.imwork.net/WeiXin/image/a.png', // 分享链接
			    imgUrl: 'http://1667006iw3.imwork.net/WeiXin/image/a.png', // 分享图标
			    success: function () { 
			       // 用户确认分享后执行的回调函数
			    },
			    cancel: function () { 
			       // 用户取消分享后执行的回调函数
			    }
			});  */
			
	}	
	//选用图片
	function chooseImage(){
		alert('chooseImage');
		wx.chooseImage({
		    count: 3, // 默认9
		    sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
		    sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
		    success: function (res) {
		        var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
		        alert('图片路径='+localIds);
		        var div = window.document.getElementById('imgChooseImage');
		        for(i = 0 ; i < localIds.length ; i++){
		        	var img = window.document.createElement('img');
		        	img.setAttribute('src',localIds[i]);
			        div.appendChild(img)
			        //上传图片接口
			        wx.uploadImage({
			            localId: localIds[i], // 需要上传的图片的本地ID，由chooseImage接口获得
			            isShowProgressTips: 1, // 默认为1，显示进度提示
			            success: function (res) {
			                var serverId = res.serverId; // 返回图片的服务器端ID
			                alert('返回图片的服务器端ID='+serverId);
			                wx.downloadImage({
			                    serverId: serverId, // 需要下载的图片的服务器端ID，由uploadImage接口获得
			                    isShowProgressTips: 1, // 默认为1，显示进度提示
			                    success: function (res) {
			                        var localId = res.localId; // 返回图片下载后的本地ID
			                        alert('返回图片下载后的本地ID='+localId);
			                    }
			                });
			            }
			        });
		        }
		        
		    }
		});

	}
	
	//图片预览接口
	
	var img = window.document.getElementsByTagName('img');
    $('img').click(function(event) {//绑定图片点击事件
    	debugger
           var thisimg=$(this).attr('src');
           var imglist=$('img');
           var srcs=new Array();
           srcs.push("http://1667006iw3.imwork.net/WeiXin/image/a.png");
           srcs.push("http://1667006iw3.imwork.net/WeiXin/image/b.png");
           srcs.push("http://1667006iw3.imwork.net/WeiXin/image/c.gif");
           wx.ready(function(){
               wx.previewImage({
                   current: "http://1667006iw3.imwork.net/WeiXin/image/a.png", // 当前显示图片的http链接
                   urls: srcs // 需要预览的图片http链接列表
               });
           });
   });
    
    //使用微信内置地图查看位置接口
    function openLocation(){
    	wx.openLocation({
            latitude: 0, // 纬度，浮点数，范围为90 ~ -90
            longitude: 0, // 经度，浮点数，范围为180 ~ -180。
            name: '', // 位置名
            address: '', // 地址详情说明
            scale: 1, // 地图缩放级别,整形值,范围从1~28。默认为最大
            infoUrl: '' // 在查看位置界面底部显示的超链接,可点击跳转
        });
    }
    
   // 获取地理位置接口
   function getLocation(){
	   wx.getLocation({
	        type: 'wgs84', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
	        success: function (res) {
	            var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
	            var longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
	            var speed = res.speed; // 速度，以米/每秒计
	            var accuracy = res.accuracy; // 位置精度
	        }
	    });
   }
    
   	//获取网络状态接口
   	function getNetworkType(){
   		wx.getNetworkType({
   	       success: function (res) {
   	           var networkType = res.networkType; // 返回网络类型2g，3g，4g，wifi
   	       }
   	   });
   	}
   //录音
   function recode(){
	   wx.startRecord();
	   wx.stopRecord({
		    success: function (res) {
		        var localId = res.localId;
		        wx.playVoice({
		            localId: localId // 需要播放的音频的本地ID，由stopRecord接口获得
		        });
		    }
		});
   }
    
   //菜单
   function menu (){
	   wx.hideOptionMenu();
	   setTimeout(function(){
		   wx.showOptionMenu();
	   },2000); 
   }
    
  	// 调起微信扫一扫接口
  	function scanQRCode(){
  		wx.scanQRCode({
  	       needResult: 0, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
  	       scanType: ["qrCode","barCode"], // 可以指定扫二维码还是一维码，默认二者都有
  	       success: function (res) {
  	       var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
  	   }
  	   });
  	}
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	
</script>
</html>