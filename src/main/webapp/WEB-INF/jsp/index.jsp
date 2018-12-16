<%@page import="com.boot.mvc.model.ApplicationCache"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Home</title>
<meta name="description" content="Blueprint: Horizontal Slide Out Menu" />
<meta name="keywords"
	content="horizontal, slide out, menu, navigation, responsive, javascript, images, grid" />
<meta name="author" content="Codrops" />
<link rel="shortcut icon" href="../favicon.ico">
<link rel="stylesheet" type="text/css" href="css/default.css" />
<link rel="stylesheet" type="text/css" href="css/component.css" />
<link rel="stylesheet" type="text/css" href="css/vertical.css" />

<script src="js/modernizr.custom.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="js/custom.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.js" type="text/javascript"></script>


<style>

/* Create two equal columns that floats next to each other */
.search-column {
	float: left;
	width: 50%;
	padding: 10px;
	height: 100%; /* Should be removed. Only for demonstration */
}

/* Clear floats after the columns */
.search-row:after {
	content: "";
	display: table;
	clear: both;
}
</style>


</head>
<body>

				<%
					 Cookie cookie = null;
			         Cookie[] cookies = null;
			         cookies = request.getCookies();
			         String user_id = null;
			         String user_type_id  = null;
			         String user_name = null;
			         String name = null;
			         if( cookies != null ) 
			         {
			            
			            for (int i = 0; i < cookies.length; i++) {
			               cookie = cookies[i];
			               if(cookie.getName( ).equals("user_id"))
			               {
			            	   user_id = cookie.getValue( );
			               }
			               if(cookie.getName( ).equals("user_type_id"))
			               {
			            	   user_type_id = cookie.getValue( );
			               }
			               
			               if(cookie.getName( ).equals("user_name"))
			               {
			            	   user_name = cookie.getValue( );
			               }
			               if(cookie.getName( ).equals("name"))
			               {
			            	   name = cookie.getValue().replace("_", " ");
			               }
			            }			         
			           }
					%>


	<div class="container">
		<header class="clearfix">
			<span>Seeds for prosperity <span class="bp-icon bp-icon-about"
				data-content="Food is not simply an organic fuel to keep body and soul together; it is a perishable art that must be savored at the peak of perfection."></span></span>
			<h1>Nirmal Seeds</h1>
			<nav>
			<% 
				if(name !=null && !name.isEmpty())
				{
					out.print( "<h6>Welcome " + name +" - "+ ApplicationCache.USER_TYPE.get(Integer.parseInt(user_type_id)).getType() +"</h6><div onclick='logout()' style='cursor: pointer;' align='right'><span>logout</span></div>"); 
				}
				else
				if(user_name!= null && !user_name.isEmpty())
				{
					out.print( "<h6>Welcome " + user_name +" - "+ ApplicationCache.USER_TYPE.get(Integer.parseInt(user_type_id)).getType() +"</h6><div onclick='logout()' style='cursor: pointer;' align='right'><span>logout</span></div>"); 
				}
			%>
				
			</nav>
		</header>
		<div class="main">
			<nav class="cbp-hsmenu-wrapper" id="cbp-hsmenu-wrapper">
				<div class="cbp-hsinner">
					<ul class="cbp-hsmenu">
				<%
						
						out.print( "<li><a href='#' onclick='render(\"search\")'>Search</a></li>");
						if(user_type_id!= null && !user_type_id.isEmpty())
						{
							out.print( "<li><a href='#' onclick='render(\"inbox\")'>Inbox</a></li>");
						}
						if(user_type_id!= null && !user_type_id.isEmpty() && "1".equalsIgnoreCase(user_type_id))
						{
							out.print( "<li><a href='#' onclick='render(\"add-user\")'>Add Suppliers                                                                       </a></li>");
						}
						else
						if(user_type_id!= null && !user_type_id.isEmpty())
						{
							out.print( "<li><a href='#' onclick='render(\"outbox\")'>Outbox</a></li>");
							out.print( "<li><a href='#' onclick='render(\"history\")'>Stock</a></li>");
						}
						out.print( "<li><a href='#' onclick='render(\"mission\")'>Our Mission</a></li>");
						out.print( "<li><a href='#' onclick='render(\"contact\")'>Contact</a></li>");
						if(user_type_id== null || user_type_id.isEmpty())
						{
							out.print( "<li>");
							out.print( "<a href='#'>Sign in</a>");
							out.print( "<ul class='cbp-hssubmenu' class='cbp-hssubmenu' style='background-color: white;'>");
							out.print( " <div id='loginError'></div>");
							out.print( " <div class='form-group'>");
							out.print( "  <label>User Name</label>");
							out.print( "  <input type='text' class='form-control' id='user'>");
							out.print( "</div>");
							out.print( "<div class='form-group'>");
							out.print( "   <label>Password</label>");
							out.print( "   <input type='password' class='form-control' id='pwd'>");
							out.print( " </div>");
							out.print( " <button type='submit' class='btn btn-default' onclick='doLogin()'>Submit</button>");
							out.print( " </ul>");
							out.print( " </li>");
						}
					%>						
					</ul>
				</div>
			</nav>
			<div id="homeContent"></div>
		</div>
	</div>
	<script src="js/cbpHorizontalSlideOutMenu.min.js"></script>
	<script>
		var menu = new cbpHorizontalSlideOutMenu(document
				.getElementById('cbp-hsmenu-wrapper'));
		render('search');
	</script>
</body>
</html>
