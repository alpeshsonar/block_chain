function render(page) {
	$('#homeContent').load(page);
}

function fetchTrackingInfo() {

	var tag = $('#searchTag')[0].value;
	$.ajax({
		url : "/search-tag",
		type : "get",
		data : {
			tag : tag
		},
		success : function(response) {
			$('#searchResult').html(response);
		},
		error : function(xhr) {
			$('#searchResult').html("<h6>There has been issue; Please contact your administrator.</h6>");
		}
	});
}



function fetchSuppliers() {
	$.ajax({
		url : "/outbox-controller/suppliers",
		type : "get",
		data : {
			user_id : $.cookie("user_id")
		},
		success : function(response) {
			var $dropdown = $("#Supplier");
			$.each(response, function( key, value )  {
			    $dropdown.append($("<option />").val(key).text(value));
			})
		},
		error : function(xhr) {
			$('#searchResult').html("<h6>There has been issue; Please contact your administrator.</h6>");
		}
	});
}



function requestSuppliers() {
	$.ajax({
		url : "/outbox-controller",
		type : "post",
		data : {
			User : $.cookie("user_id"),
			Quntity : $('#Quntity')[0].value,
			Items :  $('#Items')[0].value,
			Supplier:  $('#Supplier')[0].value
		},
		success : function(response) {
			$('#outboxTable').html(response);
		},
		error : function(xhr) {
			$('#outboxTable').html("<h6>There has been issue; Please contact your administrator.</h6>");
		}
	});
}

function outboxLoad() {
	$.ajax({
		url : "/outbox-controller",
		type : "get",
		data : {
			User : $.cookie("user_id")
		},
		success : function(response) {
			$('#outboxTable').html(response);
		},
		error : function(xhr) {
			$('#outboxTable').html("<h6>There has been issue; Please contact your administrator.</h6>");
		}
	});
}

function stock(){
	$.ajax({
		url : "/stock-controller",
		type : "get",
		data : {
			User : $.cookie("user_id")
		},
		success : function(response) {
			$('#stockTable').html(response);
		},
		error : function(xhr) {
			$('#stockTable').html("<h6>There has been issue; Please contact your administrator.</h6>");
		}
	});
}

function inboxLoad() {
	$.ajax({
		url : "/inbox-controller",
		type : "get",
		data : {
			User : $.cookie("user_id")
		},
		success : function(response) {
			$('#inboxTable').html(response);
		},
		error : function(xhr) {
			$('#inboxTable').html("<h6>There has been issue; Please contact your administrator.</h6>");
		}
	});
}


function approve(){
	var obj =[];
	$("input").each(function(){
	      if($(this)[0].checked) 
	      obj.push($(this)[0].value)
	});
	approvePost(obj);
}


function approveAll(){
	var obj =[];
	$("input").each(function(){
	      obj.push($(this)[0].value)
	});
	approvePost(obj);
}


function approvePost(obj) {
	$.ajax({
		url : "/inbox-controller",
		type : "post",
		data : {
			User : $.cookie("user_id"),
			Request : JSON.stringify(obj)
		},
		success : function(response) {
			$('#inboxTable').html(response);
		},
		error : function(xhr) {
			$('#inboxTable').html("<h6>There has been issue; Please contact your administrator.</h6>");
		}
	});
}

function doLogin(){
	var user = $('#user')[0].value;
	var pwd = $('#pwd')[0].value;
	$.ajax({
		url : "/login",
		type : "post",
		data : {
			user : user,
			pwd : pwd
		},
		success : function(response, textStatus, request) {
			//$('#inboxTable').html(response);
	        if(request.getResponseHeader('user_id'))
	        	location.reload();
	        else
	        	$('#loginError').html("<h6>Incorrect user and/or password.</h6>");
		},
		error : function(xhr) {
			$('#loginError').html("<h6>There has been issue; Please contact your administrator.</h6>");
		}
	});
}

function logout(){
	$.removeCookie("user_id", { path: '/' });
	$.removeCookie("user_type_id", { path: '/' });
	$.removeCookie("user_name", { path: '/' });
	$.removeCookie("name", { path: '/' });

	location.reload();
}


function createUser(){
	var username = $('#username')[0].value;
	var name = $('#name')[0].value;
	var pwd = $('#pwd')[0].value;
	var pwd_confirm = $('#pwd_confirm')[0].value;
	var location = $('#location')[0].value;
	var type = $('#type')[0].value;
	if(pwd != pwd_confirm)
		$('#error').html("<h6>password mismatched</h6>");
	
	
	$.ajax({
		url : "/login/create",
		type : "post",
		data : {
			username : username,
			name : name,
			pwd: pwd,
			location: location,
			type: type
		},
		success : function(response) {
				if(response == "200"){
		        	$('#username')[0].value = "";
		        	$('#name')[0].value = "";
		        	$('#pwd')[0].value = "";
		        	$('#pwd_confirm')[0].value = "";
		        	$('#location')[0].value = undefined;
		        	$('#type')[0].value = undefined;
		        	$('#error').html("<h6>User created successfully!!!</h6>");
				}
				else{
		        	$('#error').html(response);
				}
		},
		error : function(xhr) {
			$('#loginError').html("<h6>There has been issue; Please contact your administrator.</h6>");
		}
	});
}