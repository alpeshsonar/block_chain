<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.boot.mvc.model.ApplicationCache"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.boot.mvc.model.*"%>
<%@ page import="com.boot.mvc.service.OutboxService"%>


 
<style>
.table {
	border: none;
}

.table-definition thead th:first-child {
	pointer-events: none;
	background: white;
	border: none;
}

.table td {
	vertical-align: middle;
}

.page-item>* {
	border: none;
}

.custom-checkbox {
	min-height: 1rem;
	padding-left: 0;
	margin-right: 0;
	cursor: pointer;
}

.custom-checkbox .custom-control-indicator {
	content: "";
	display: inline-block;
	position: relative;
	width: 30px;
	height: 10px;
	background-color: #818181;
	border-radius: 15px;
	margin-right: 10px;
	-webkit-transition: background .3s ease;
	transition: background .3s ease;
	vertical-align: middle;
	margin: 0 16px;
	box-shadow: none;
}

.custom-checkbox .custom-control-indicator:after {
	content: "";
	position: absolute;
	display: inline-block;
	width: 18px;
	height: 18px;
	background-color: #f1f1f1;
	border-radius: 21px;
	box-shadow: 0 1px 3px 1px rgba(0, 0, 0, 0.4);
	left: -2px;
	top: -4px;
	-webkit-transition: left .3s ease, background .3s ease, box-shadow .1s
		ease;
	transition: left .3s ease, background .3s ease, box-shadow .1s ease;
}

.custom-checkbox .custom-control-input:checked ~
	.custom-control-indicator {
	background-color: #84c7c1;
	background-image: none;
	box-shadow: none !important;
}

.custom-checkbox .custom-control-input:checked ~
	.custom-control-indicator:after {
	background-color: #84c7c1;
	left: 15px;
}

.custom-checkbox .custom-control-input:focus ~ .custom-control-indicator
	{
	box-shadow: none !important;
}
</style>
<br />

<script>
$( document ).ready(function() {
	fetchSuppliers();
	outboxLoad();
});
</script> 


<form action="#">
	<div class="form-group">
		<label for="Supplier">Supplier</label> 
		<select	class="form-control" id="Supplier">
		</select>
	</div>
	<div class="form-group">
		<label for="Items">Items</label> 
		<select	class="form-control" id="Items">
		<%		
			for (Map.Entry<Integer, Item> entry : ApplicationCache.ITEMS.entrySet()) {
			    Integer key = entry.getKey();
			    Item item = entry.getValue();
			    out.print("<option value='"+key+"'>"+ item.getName() +"</option>");
			}
		%>
		</select>
	</div>
	<div class="form-group">
		<label for="Quntity">Quntity</label> <input type="text" id="Quntity"
			class="form-control">
	</div>
	<button type="submit" class="btn btn-primary" onclick=requestSuppliers()>Request</button>
</form>

<br/>

<div id="outboxTable"></div>
