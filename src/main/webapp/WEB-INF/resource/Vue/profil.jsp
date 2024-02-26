
<!DOCTYPE html>
<%@ include file="homehead.jspf"%>
<%@ page import="businessLayer.*" %>
<%@ page import="models.*" %>
<%@ page import="java.util.ArrayList" %>

 <%  
    AccountsManager accountmanager = new AccountsManager();
    User user = accountmanager.getUserByUsername((String)session.getAttribute("username"));
    ArrayList<Methodologie> methodologies = accountmanager.getDeveloperMethodology(user.getId());
    ArrayList<Technologie> technologies = accountmanager.getDeveloperTechnology(user.getId());
 
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
<%@ include file="tophome.jspf"%>

  <div class="app-content">
     <%@ include file="side.jspf"%>
 
    <div class="projects-section">
      <div class="projects-section-header">

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
        <form method="post" action="editProfile">
            <div class="form-group">
            <label for="firstName">First Name</label>
            <input type="text" class="form-control" id="newFirstName" name="newFirstName" value="<%= user.getPrenom() %>" required readonly>
        </div>
        
        <div class="form-group">
            <label for="lastName">Last Name</label>
            <input type="text" class="form-control" id="newLastName" name="newLastName" value="<%= user.getNom() %>" required readonly>
        </div>
  

            <div class="form-group">
                <label for="email">Email</label>
                <!-- Ajoutez un champ input désactivé avec un ID -->
    <input type="email" class="form-control" id="newEmail" name="newEmail" value="<%= user.getEmail() %>" required readonly>
            </div>

            <!-- Ajoutez un bouton Update avec un ID -->
            <button type="button" class="btn btn-primary" id="editDetailsBtn">Update</button>

            <!-- Ajoutez un bouton Submit caché avec un ID -->
            <button type="submit" class="btn btn-primary" id="submitDetailsBtn" style="display: none;">Submit</button>

            <!-- Ajoutez un bouton Cancel avec un ID -->
            <button type="button" class="btn btn-secondary" id="cancelEditBtn" style="display: none;">Cancel</button>
        </form>
    </div>

    <!-- ... (autres éléments) ... -->
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
                                <%
                                   

                                    for (Technologie technology : technologies) {
                                %>
                                <li><%= technology.getName() %></li>
                                <%
                                    }
                                %>
                            </ul>
				</div>
			</div>
	
		</div>
		
					  <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addSkillsModal">
            Add Skills
        </button>
					
		</div>
	</div>
</div>
</div>
</div>
</div>
</div>
</div>

<!-- Formulaire modal pour ajouter de nouvelles compétences -->
<div class="modal" id="addSkillsModal">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Add Skills</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal Body -->
            <div class="modal-body">
                <!-- Formulaire pour ajouter de nouvelles technologies et méthodologies -->
                <form method="post" action="EditSkillsServlet">
                    <!-- Champs de formulaire pour les nouvelles compétences -->
                    <label for="newTechnology">New Technology:</label>
                    <input type="text" id="newTechnology" name="newTechnology" >
                    <br>
                    <label for="newMethodology">New Methodology:</label>
                    <input type="text" id="newMethodology" name="newMethodology" >
                    <br>
                    <!-- Bouton pour soumettre le formulaire -->
                    <button type="submit" class="btn btn-primary">Add Skills</button>
                </form>
            </div>
        </div>
    </div>
</div>


<!-- partial -->
  <script  src="./css/script.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
  <script  src="./css/script.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script>
    function toggleFormFields(editable) {
        document.getElementById('newFirstName').readOnly = !editable;
        document.getElementById('newLastName').readOnly = !editable;
        document.getElementById('newEmail').readOnly = !editable;
        document.getElementById('editDetailsBtn').style.display = editable ? 'none' : 'inline-block';
        document.getElementById('submitDetailsBtn').style.display = editable ? 'inline-block' : 'none';
        document.getElementById('cancelEditBtn').style.display = editable ? 'inline-block' : 'none';
    }

    document.getElementById('editDetailsBtn').addEventListener('click', function () {
        toggleFormFields(true);
    });

    document.getElementById('cancelEditBtn').addEventListener('click', function () {
        toggleFormFields(false);
    });
</script>
  <script  src="./css/script.js"></script>

</body>
</html>
