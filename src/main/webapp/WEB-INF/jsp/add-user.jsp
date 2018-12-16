<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.boot.mvc.model.ApplicationCache"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.boot.mvc.model.*"%>

<br />

<form action="#">
	<div class="form-group" id="error"></div>
	<div class="form-group">
		<label for="username">Username:</label> <input type="text"
			class="form-control" id="username">
	</div>
	<div class="form-group">
		<label for="name">Full Name:</label> <input type="text"
			class="form-control" id="name">
	</div>
	<div class="form-group">
		<label for="pwd">Password:</label> <input type="password"
			class="form-control" id="pwd">
	</div>
	<div class="form-group">
		<label for="pwd_confirm">Confirm Password:</label> <input type="password"
			class="form-control" id="pwd_confirm">
	</div>
	<div class="form-group">
		<label for="location">Location</label> <select class="form-control"
			id="location">
			<%		
			for (Map.Entry<Integer, Location> entry : ApplicationCache.LOCATION.entrySet()) {
			    Integer key = entry.getKey();
			    Location item = entry.getValue();
			    out.print("<option value='"+item.getLocId()+"'>"+ item.getLocation() +"</option>");
			}
		%>
		</select>
	</div>

	<div class="form-group">
		<label for="type">User Type</label> <select class="form-control"
			id="type">
			<%		
			for (Map.Entry<Integer, UserType> entry : ApplicationCache.USER_TYPE.entrySet()) {
			    Integer key = entry.getKey();
			    UserType item = entry.getValue();
			    out.print("<option value='"+item.getTypeId()+"'>"+ item.getType()+"</option>");
			}
		%>
		</select>
	</div>
	<button type="submit" class="btn btn-primary" onclick="createUser()">Submit</button>
</form>