
jQuery(function(){
	var wait = 60;
	jQuery("#yanzheng").click(function (){
		if(wait==60){
			sendMessage();
			time(this);
		}
	})
	function sendMessage() {
		var request = new XMLHttpRequest();
		var method = "POST";
		phone=document.getElementById("userPhone").value;
		var url = "/ego_web/member/userRegYanZheng?phone="+ phone;
		request.open(method, url);
		request.send(null);
		request.onreadystatechange = function() {
			if (request.readyState == 4) {
				if (request.status == 200) {
					if (request.responseText == 1) {
//						alert("验证码失败");
						box.className = "box err";
						tip.className = "tip err";
						span.innerHTML = "验证码发送失败，请重试！";
						return false;
					} else if(request.responseText == 2) {
//						alert("验证码成功");
						box.className = "box right";
						tip.className = "tip right";
						span.innerHTML = "验证码发送成功！";
						return true;
					}
				}
			}
		}
	}
	function time(o) {
		if(wait == 0) {
			yanzheng.removeAttribute("disabled");
			yanzheng.value = "免费获取验证码";
			wait = 60;
		} else {
			yanzheng.setAttribute("disabled", true);
			yanzheng.value = "重新发送(" + wait + ")";
			wait--;
			setTimeout(function() {
					time(o)
				},
				1000)
		}
	}
	/* 	//省市区三级联动
	     $("#sheng").change(function(){
				var strCode=$(this).val();
				 alert(strCode) 
				var strArr=strCode.split("|");
				 var name=strArr[0]; 
				var shengcode=strArr[1];
				 alert(shengcode) 
				 alert(strCode) 
				var shiObj=$("#shi");
				var quObj=$("#qu"); 
				if(shengcode!=0){
					var url="${pageContext.request.contextPath}/getListShiBysheng";
					$.post(url,
					{"shengCode":shengcode},
					function(data){
						shiObj.html("<option value='0'>请选择市</option>");
						quObj.html("<option value='0'>请选择区</option>");
						var html="";
						for(var i=0;i<data.length;i++){
							html+="<option value='"+data[i].name+'|'+data[i].code+"'>"+data[i].name+"</option>";
						}
						shiObj.append(html);
					},
					'json');
				}else{
					shiObj.html("<option value='0'>请选择市</option>");
					quObj.html("<option value='0'>请选择区</option>");
					
				}
			});
		$("#shi").change(function(){
				var strCode=$(this).val();
				var strArr=strCode.split("|");
				var shicode=strArr[1];
				var quObj=$("#qu");
				if(shicode!=0){
					var url="${pageContext.request.contextPath}/getQuByShi";
					$.post(url,{"shiCode":shicode},
						function(data){
							quObj.html("<option value='0'>请选择区</option>");
							var html="";
							for(var i=0;i<data.length;i++){
								html+="<option value='"+data[i].name+'|'+data[i].code+"'>"+data[i].name+"</option>";
							}
							quObj.append(html);
					},
					'json');
				}else{
					quObj.html("<option value='0'>请选择区</option>");
				}
			}) */
		})
		
	function $(id) {
		return document.getElementById(id);
	}
	var regs = {
		userNameReg : /^[a-zA-Z0-9]{4,20}$/,
		pwdReg : /^[a-zA-Z0-9_]{6,20}$/,
		emailReg : /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
		mobileReg1 : /^1([3|5|8][0-9]{9})$/,
		mobileReg2 : /^1(7[0|6|7|8][0-9]{8})$/,
		numReg : /^\d$/,
		strReg : /^[a-zA-Z]$/,
		tsReg : /^[_]$/
	}
	window.onload = function() {
		// box   常规 box  出错的时候  box error  正确的时候  box right
		// tip   常规 tip hide  出错的时候  tip error  默认的时候  tip default
		var userName = $("userLoginname");
		var pwd = $("userPassword");
		var pwd2 = $("pwd2");
//		var rightName = $("userName");
//		var sex = $("userSex");
		var email = $("userMail");
		var mobile = $("userPhone");
		var ck = $("ck");
		var btn = $("btn");
		var vCode=$("vCode");
		var yanzheng=$("yanzheng")
		
		//松开键盘     获取焦点    失去焦点
		userName.onkeyup = userName.onfocus = userName.onblur = function(evt) {
			var e = evt || window.event;
			 //获得事件对象     兼容 IE等各浏览器
			checkUserName(e);
		}
		function checkUserName(_e) {
			var type;
			if (_e) {
				type = _e.type;
			}
			var value = userName.value;
			var box = userName.parentNode;
			var tip = box.nextElementSibling;
			var span = tip.lastElementChild;
			if (type == "focus") { // 获取焦点
				if (value == "") {
					box.className = "box";
					tip.className = "tip default";
					span.innerHTML = "支持  字母 数字 下划线 的组合，4-20个字符";
					return false;
				}
			}
			if (type == "blur") { //失去焦点
				if (value == "") {
					box.className = "box";
					tip.className = "tip hide";
					return false;
				}
			}
			if (value == "") {
				box.className = "box error";
				tip.className = "tip error";
				span.innerHTML = "用户名不能为空";
				return false;
			} else if (regs.userNameReg.test(value)) {
				var request = new XMLHttpRequest();
				var method = "POST";
				var url = "/ego_web/member/checkLoginname?loginname="+ value;
				request.open(method, url);
				request.send(null);
				request.onreadystatechange = function() {
					if (request.readyState == 4) {
						if (request.status == 200) {
							if (request.responseText == 1) {
								box.className = "box error";
								tip.className = "tip error";
								span.innerHTML = "用户名已存在！"
							} else {
								box.className = "box right";
								tip.className = "tip hide";
								return true;
							}
						}
					}
				}
				return true;
			}else{
				box.className = "box error";
				tip.className = "tip error";
				span.innerHTML = "格式错误，仅支持、字母、数字和下划线 的组合的4-20位";
				return false;
			}
		}

		//松开键盘     获取焦点    失去焦点
		pwd.onkeyup = pwd.onfocus = pwd.onblur = function(evt) {
			var e = evt || window.event;
			checkPwd(e);
		}

		function checkPwd(_e) {
			var type;
			if (_e) {
				type = _e.type;
			}
			var value = pwd.value;
			var box = pwd.parentNode;
			var tip = box.nextElementSibling;
			var span = tip.lastElementChild;
			if (type == "focus") {
				if (value == "") {
					box.className = "box";
					tip.className = "tip default";
					span.innerHTML = "建议使用字母、数字和下划线组合的形式,6-10个字符";
					return false;
				}
			}
			if (type == "blur") {
				if (value == "") {
					box.className = "box";
					tip.className = "tip hide";
					return false;
				}
			}
			if (value == "") {
				box.className = "box error";
				tip.className = "tip error";
				span.innerHTML = "密码不能为空";
				return false;
			} else if (regs.pwdReg.test(value)) {
				box.className = "box right";
				var level = getPwdLevel(value);
				switch (level) {
				case 1:
					tip.className = "tip ruo";
					span.innerHTML = "";
					break;
				case 2:
					tip.className = "tip zhong";
					span.innerHTML = "";
					break;
				case 3:
					tip.className = "tip qiang";
					span.innerHTML = "";
					break;
				}
				return true;
			} else {
				box.className = "box error";
				tip.className = "tip error";
				span.innerHTML = "格式错误，仅支持、字母、数字和下划线 的组合的6-20位";
				return false;
			}
		}

		pwd2.onkeyup = pwd2.onfocus = pwd2.onblur = function(evt) {
			var e = evt || window.event;
			checkPwd2(e);
		}
		function checkPwd2(_e) {
			var type;
			if (_e) {
				type = _e.type;
			}
			var value1 = pwd.value;
			var value = pwd2.value;
			var box = pwd2.parentNode;
			var tip = box.nextElementSibling;
			var span = tip.lastElementChild;
			if (type == "focus") {
				if (value == "") {
					box.className = "box";
					tip.className = "tip default";
					span.innerHTML = "请再次输入密码";
					return false;
				}
			}
			if (type == "blur") {
				if (value == "") {
					box.className = "box";
					tip.className = "tip hide";
					return false;
				}
			}

			if (value == "") {
				box.className = "box error";
				tip.className = "tip error";
				span.innerHTML = "请再次输入密码";
				return false;
			} else if (value1 == value) {
				box.className = "box right";
				tip.className = "tip hide";
				return true;
			} else {
				box.className = "box error";
				tip.className = "tip error";
				span.innerHTML = "两次密码不一致";
				return false;
			}
		}

		//真实姓名
		/*		rightName.onkeyup = rightName.onfocus = rightName.onblur = function(evt) {
			var e = evt || window.event;
			checkrightName(e);
		}
		function checkrightName(_e) {
			var type;
			if (_e) {
				type = _e.type;
			}
			var value = rightName.value;
			var box = rightName.parentNode;
			var tip = box.nextElementSibling;
			var span = tip.lastElementChild;
			if (type == "focus") {
				if (value == "") {
					box.className = "box";
					tip.className = "tip default";
					span.innerHTML = "请填写真实姓名";
					return false;
				}
			}
			if (type == "blur") {
				if (value == "") {
					box.className = "box";
					tip.className = "tip hide";
					return false;
				}
			}
			if (value == "") {
				box.className = "box error";
				tip.className = "tip error";
				span.innerHTML = "真实姓名不能为空";
				return false;
			} else {
				box.className = "box right";
				tip.className = "tip hide";
				return true;
			}
		}*/

		//邮箱
		email.onkeyup = email.onfocus = email.onblur = function(evt) {
			var e = evt || window.event;
			checkEmail(e);
		}
		function checkEmail(_e) {
			var type;
			if (_e) {
				type = _e.type;
			}
			var value = email.value;
			var box = email.parentNode;
			var tip = box.nextElementSibling;
			var span = tip.lastElementChild;
			if (type == "focus") {
				if (value == "") {
					box.className = "box";
					tip.className = "tip default";
					span.innerHTML = "例如：sdyongcun@163.com";
					return false;
				}
			}
			if (type == "blur") {
				if (value == "") {
					box.className = "box";
					tip.className = "tip hide";
					return false;
				}
			}
			if (value == "") {
				box.className = "box error";
				tip.className = "tip error";
				span.innerHTML = "邮箱不能为空";
				return false;
			} else if (regs.emailReg.test(value)) {
				box.className = "box right";
				tip.className = "tip hide";
				return true;
			} else {
				box.className = "box error";
				tip.className = "tip error";
				span.innerHTML = "邮箱格式错误";
				return false;
			}
		}
		//手机
		mobile.onkeyup = mobile.onfocus = mobile.onblur = function(evt) {
			var e = evt || window.event;
			checkMobile(e);
		}
		function checkMobile(_e) {
			var type;
			if (_e) {
				type = _e.type;
			}
			var value = mobile.value;
			var box = mobile.parentNode;
			var tip = box.nextElementSibling;
			var span = tip.lastElementChild;
			if (type == "focus") {
				if (value == "") {
					box.className = "box";
					tip.className = "tip default";
					span.innerHTML = "支持11位正确的手机号";
					return false;
				}
			}
			if (type == "blur") {
				if (value == "") {
					box.className = "box";
					tip.className = "tip hide";
					return false;
				}
			}
			if (value == "") {
				box.className = "box error";
				tip.className = "tip error";
				span.innerHTML = "手机号不能为空";
				yanzheng.setAttribute("disabled", true);
				return false;
			} else if (regs.mobileReg1.test(value) || regs.mobileReg2.test(value)) {
				var request = new XMLHttpRequest();
				var method = "POST";
				var url = "/ego_web/member/checkLoginPhone?phone="+ value;
				request.open(method, url);
				request.send(null);
				request.onreadystatechange = function() {
					if (request.readyState == 4) {
						if (request.status == 200) {
							if (request.responseText == 1) {
								box.className = "box error";
								tip.className = "tip error";
								span.innerHTML = "该手机号已经被注册，请前去登录！"
								yanzheng.setAttribute("disabled", true);
								return false;
							} else {
								box.className = "box right";
								tip.className = "tip hide";
								yanzheng.removeAttribute("disabled");
								return true;
							}
						}
					}
				}
				return true;
			} else {
				box.className = "box error";
				tip.className = "tip error";
				span.innerHTML = "格式错误";
				yanzheng.setAttribute("disabled", true);
				return false;
			}
		}
		
		//松开键盘     获取焦点    失去焦点
		vCode.onkeyup = vCode.onfocus = vCode.onblur = function(evt) {
			var e = evt || window.event;  // 获得事件对象     兼容 IE等各浏览器
			checkVCode(e);
		}
		function checkVCode(_e) {
			var type;
			if (_e) {
				type = _e.type;
			}
			var value = vCode.value;
			var box = vCode.parentNode;
			var tip = box.nextElementSibling;
			var span = tip.lastElementChild;
			if (type == "focus") { // 获取焦点
				if (value == "") {
					box.className = "box";
					tip.className = "tip default";
					span.innerHTML = "输入六位验证码";
					return false;
				}
			}
			if (type == "blur") { //失去焦点
				if (value == "") {
					box.className = "box";
					tip.className = "tip hide";
					return false;
				}
			}
			if (value == "") {
				box.className = "box error";
				tip.className = "tip error";
				span.innerHTML = "验证码不能为空";
				return false;
			} else if (regs.userNameReg.test(value)) {
				var request = new XMLHttpRequest();
				var method = "POST";
				var url = "/ego_web/member/userRegServlet?vCode="+ value;
				request.open(method, url);
				request.send(null);
				request.onreadystatechange = function() {
					if (request.readyState == 4) {
						if (request.status == 200) {
							if (request.responseText == 1) {
								box.className = "box error";
								tip.className = "tip error";
								span.innerHTML = "验证码不正确！"
								return false;
							} else {
								box.className = "box right";
								tip.className = "tip hide";
								return true;
							}
						}
					}
				}
				return true;
			}else{
				 box.className = "box error";
				tip.className = "tip error";
				span.innerHTML = "验证码不正确"; 
				return false;
			}
		}
		
		/*//地址
		address.onkeyup = address.onfocus = address.onblur = function(evt) {
			var e = evt || window.event;
			checkAddress(e);
		}
		function checkAddress(_e) {
			var type;
			if (_e) {
				type = _e.type;
			}
			var value = address.value;
			var box = address.parentNode;
			var tip = box.nextElementSibling;
			var span = tip.lastElementChild;
			if (type == "focus") {
				if (value == "") {
					box.className = "box";
					tip.className = "tip default";
					span.innerHTML = "请填写真实地址";
					return false;
				}
			}
			if (type == "blur") {
				if (value == "") {
					box.className = "box";
					tip.className = "tip hide";
					return false;
				}
			}
			if (value == "") {
				box.className = "box error";
				tip.className = "tip error";
				span.innerHTML = "真实地址不能为空";
				return false;
			} else {
				box.className = "box right";
				tip.className = "tip hide";
				return true;
			}
		}*/
		//协议
		ck.onclick = function(evt) {
			var e = evt || window.event;
			checkCk(e);
		}
		function checkCk(_e) {
			var type;
			if (_e) {
				type = _e.type;
			}
			var box = ck.parentNode;
			var tip = box.nextElementSibling;
			var span = tip.lastElementChild;
			if (ck.checked) {
				tip.className = "tip default";
				span.innerHTML = "已同意协议";
				return true;
			} else {
				tip.className = "tip error";
				span.innerHTML = "请同意协议";
				return false;
			}
		}
		//注册
		 btn.onclick=function(){
			var box = ck.parentNode;
			var tip = box.nextElementSibling;
			var span = tip.lastElementChild;
			if (checkUserName() && checkPwd() && checkPwd2() && checkMobile() && checkVCode() && checkCk()) {
				 document.getElementById("btn").setAttribute("type", "submit");
				 return true;
			} else {
				return false;
			}
		}
	}

	//级别
	function getPwdLevel(pwd) {
		var level = 0;
		var numReg = true, strReg = true, tsReg = true;
		for (var i = 0; i < pwd.length; i++) {
			if (numReg && regs.numReg.test(pwd[i])) {
				level++;
				numReg = false;
				continue;
			}
			if (strReg && regs.strReg.test(pwd[i])) {
				level++;
				strReg = false;
				continue;
			}
			if (tsReg && regs.tsReg.test(pwd[i])) {
				level++;
				tsReg = false;
			}
		}
		return level;
	}

	
