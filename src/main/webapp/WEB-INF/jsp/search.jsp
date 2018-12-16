<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<style>
table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	padding: 1px;
	text-align: left;
	border-bottom: 1px solid #ddd;
}
</style>

<br />

<!-- Another variation with a button -->
<div class="input-group">
	<input type="text" id="searchTag" class="form-control"
		placeholder="Search tag">
	<div class="input-group-append">
		<button class="btn btn-secondary" type="button"
			onclick="fetchTrackingInfo()">
			<i class="fa fa-search"></i>
		</button>
	</div>
</div>
<div id="searchResult"></div>
