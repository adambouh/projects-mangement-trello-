
<!DOCTYPE html>
<%@ include file="homehead.jspf"%>
<%@ page import="businessLayer.*" %>
<%@ page import="models.*" %>
<%@ page import="java.util.ArrayList" %>
 <%  
    AccountsManager accountmanager = new AccountsManager();
    User user = accountmanager.getUserByUsername((String)session.getAttribute("username"));
    ArrayList<Methodologie> methodologies = accountmanager.getDeveloperMethodology(user.getId());
%>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>Profile</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
   <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
  
<link rel="stylesheet" href="./css/homestyle.css">
<link rel="stylesheet" href="./css/Profilestyle.css">


</head>
<body>
<!-- partial:index.partial.html -->
<div class="app-container">
<!--  top barr at homeee -->

  <div class="app-content">



 <div class="projects-section">

<div class="container">
<div class="row gutters">
<div class="col-xl-3 col-lg-3 col-md-12 col-sm-12 col-12">
<div class="card h-100">
	<div class="card-body">
		<div class="account-settings">
			<div class="user-profile">
				<div class="user-avatar">
					<img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="Maxwell Admin">
				</div>
				<h5 class="user-name"><%= user.getUsername() %></h5>
				
			</div>
			
		</div>
	</div>
</div>
</div>
<div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12">
<div class="card h-100">
	<div class="card-body">
		<div class="row gutters">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				<h6 class="mb-2 text-primary">Personal Details</h6>
			</div>
			
			<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
		<div class="form-group">
   <label for="fullname"> Name</label>
   <span class="form-control" id="fullName"><%= user.getFullName() %></span>
     </div>
			</div>
			<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
				  <div class="form-group">
   <label for="email"> Email</label>
   <span class="form-control" id="email"><%= user.getEmail() %></span>
</div>
			</div>
		
		</div>
		<div class="row gutters">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				<h6 class="mt-3 mb-2 text-primary">Skills</h6>
			</div>
			<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
				  <div class="form-group">
   <ul>
                                <%
                                   

                                    for (Methodologie methodology : methodologies) {
                                %>
                                <li><%= methodology.getName() %></li>
                                <%
                                    }
                                %>
                            </ul>
                  </div>
			</div>
			<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
				<div class="form-group">
					  
       <ul>
        <li>Élément 1</li>
        <li>Élément 2</li>
        <li>Élément 3</li>
        <li>Élément 4</li>
        <li>Élément 5</li>
        </ul>
				</div>
			</div>
	
		</div>
		<div class="row gutters">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				<div class="text-right">
					<button type="button" id="submit" name="submit" class="btn btn-secondary">Cancel</button>
					<button type="button" id="submit" name="submit" class="btn btn-primary">Update</button>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
<!-- partial -->
  <script  src="./css/script.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

</body>
</html>
