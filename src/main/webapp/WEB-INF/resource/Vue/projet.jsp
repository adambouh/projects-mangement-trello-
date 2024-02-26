<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ include file="homehead.jspf"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="businessLayer.ProjectsManager" %>

<html lang="en">
    <head> 
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="apple-touch-icon" sizes="180x180" href="icons/apple-touch-icon.png">
        <link rel="icon" type="image/png" sizes="32x32" href="icons/favicon-32x32.png">
        <link rel="icon" type="image/png" sizes="16x16" href="icons/favicon-16x16.png">
        <link rel="manifest" href="icons/site.webmanifest">
        <link rel="mask-icon" href="icons/safari-pinned-tab.svg" color="#5bbad5">
        <link rel="shortcut icon" href="icons/favicon.ico">
        <meta name="apple-mobile-web-app-title" content="Kards">
        <meta name="application-name" content="Kards">
        <meta name="msapplication-TileColor" content="#2b5797">
        <meta name="msapplication-config" content="icons//browserconfig.xml">
        <meta name="theme-color" content="#ffffff">
		<meta charset="UTF-8">
	  <title>Project Management Dashboard </title>
	  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
	<link rel="stylesheet" href="./css/homestyle.css">
        <link rel="stylesheet" href="css/main.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" integrity="sha512-SfTiTlX6kk+qitfevl/7LibUOeJWlt9rbyDn92a1DqWOw9vWG2MFoays0sgObmWazO5BQPiFucnnEAjpAB+/Sw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
		<script>console.log("<%= request.getAttribute("id") %>")</script>

  
<body>
<!-- partial:index.partial.html -->
<div class="app-container">
<!--  top barr at homeee -->
<%@ include file="tophome.jspf"%>

  <div class="app-content">
    <%@ include file="side.jspf"%>
 
    <div class="projects-section" style="display: flex; width: 100%;
    display: flex;
    flex-wrap: nowrap;
    flex-direction: row;
    align-items: flex-start;
    overflow-y:scroll
    ">
    <div class=project-inf style="    height:fit-content;
width:35%;overflow-y:show;">
    	
    
<form id="projectForm"style="display:block;;
position :relative;
  width:100%;
  max-width:100%;
  height:fit-content;
}      ">
    <label for="projectName">Project Name:</label>
    </br>
    <input type="text" id="tName" name="tName" required>

    <label for="dateBegin">Start Date:</label>    
    
    <input type="date" id="dateBegin" name="dateBegin" required></br>

    <label for="dateLivraison">Delivery Date:</label>    </br>
    
    <input type="date" id="dateLivraison" name="dateLivraison" required>

    <label for="duration">Duration:</label>    </br>
    
    <input type="number" id="duration" name="duration" required>

    <label for="description">Description:</label>    </br>
    
    <textarea id="desc" name="description" rows="4" cols="23"  required></textarea>

    <label for="client">Client:</label>
    <input type="text" id="client" name="client" required>

    <label for="manager">Manager:</label>    
    
    <input type="text" id="manager" name="manager" required>
    
    
    <label for="equipe">Team Members:</label>
    <div id="teamMembersContainer"></div>
    <input list="teamMembersSelect" id="equipe" name="equipe" required>
    <select id="teamMembersSelect" multiple>
		  </select>

    <label for="methodologies">Methodologies:</label>
    <div id="methodologiesContainer">
  
	</div>
    <input list="methodologiesSelect" id="meths" name="methodologies" required>
		<select id="methodologiesSelect" multiple>
		  </select>
    <label for="technologies">Technologies:</label>
    <div id="technologiesContainer"></div>
    
    <input list="technologiesSelect" id="technologies" name="technologies" required>
		<select id="technologiesSelect" multiple>
		  </select>
    
    <input type="hidden" id="projectId" name="projectId">

</form>
    </div>
            <div class="header-flex" style="display:none">
                <span id="sidebar-button"><i class="fa fa-list"></i></span>
                <h1 id="title" class="text-align-center">Kards</h1>
                <span class="title-actions-container" style="font-size:25px;cursor:pointer;letter-spacing: 1ch;">
                    <label for="auto-save" class="auto-save-text">Auto Save</label>
                    <label class="switch" id="auto-save-label">
                       <input id="auto-save" type="checkbox" checked>
                       <span class="slider round"></span>
                    </label>
                    <i title="Delete this board." id="delete-button" class="fa fa-trash"></i>
                    <i title="Save all boards." id="save-button" class="fa fa-floppy-o"></i>
                    <i title="Settings" id="settings-button" class="fa fa-gear"></i>
                </span>
            </div>
        

        <div id="sidebar" class="sidenav">
            <span id="sidebar-close">&times;</span>
            <p class="is-title">Boards</p>
            <ul id="boards-list">
                <!-- Boards will be listed here... -->
            </ul>

            <div class="flex-col">
                <input type="text" id="add-board-text" name="add-board" placeholder="Add Board...">
                <button id="add-board-button">Add Board</button>
            </div>
        </div>

        <div id="card-context-menu" class="context-menu">
            <ul>
                <li id="card-context-menu-delete">Delete</li>
                <li id="card-context-menu-clear">Clear</li>
                <li id="card-context-menu-duplicate">Duplicate</li>
            </ul>
        </div>

        <div class="container" id="main-container" style="margin-left:8px;padding-left:3px;flex-grow: 1;     box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);height:100%;
        padding-top:3px;
        ">
            <div id="cards-container">
                <div id="add-card">
                    <input maxlength="128" type="text" id="add-card-text" name="add-card" placeholder="Add Service..." autofocus>
                    <button id="add-card-button" class="plus-button">+</button>
                </div>
            </div>
        </div>

        <div id="sidebar" class="sidenav">
            <span id="sidebar-close">&times;</span>
            <p class="is-title">Boards</p>
            <ul id="boards-list">
                <!-- Boards will be listed here... -->
            </ul>

            <div class="flex-col">
                <input type="text" id="add-board-text" name="add-board" placeholder="Add Board...">
                <button id="add-board-button">Add Board</button>
            </div>
        </div>

        <div id="card-context-menu" class="context-menu">
            <ul>
                <li id="card-context-menu-delete">Delete</li>
                <li id="card-context-menu-clear">Clear</li>
                <li id="card-context-menu-duplicate">Duplicate</li>
            </ul>
        </div>

        <div id="alert-container">
            <div id="alerts">
                <!-- alerts go here -->
            </div>
        </div>

        <div id="confirm-dialog" class="modal">
            <div class="dialog-content">
                <span id="confirm-dialog-close" class="confirm-dialog-close">&times;</span>
                <div id="confirm-dialog-text" class="confirm-dialog-text"></div>
                <div class="confirm-dialog-actions">
                    <button id="confirm-dialog-cancel" class="confirm-dialog-cancel"> Cancel </button>
                    <button id="confirm-dialog-confirm" class="confirm-dialog-confirm"> Confirm </button>
                </div>
            </div>
        </div>
        </div>
        </div>
        </div>
        
  		<script  src="./css/script.js"></script>
  		<script type="text/javascript">  var appData = {'User':'<%=session.getAttribute("username")%>',
  				'project':"<%=(String) request.getAttribute("id")%>",
  			    'boards': [],
  			    'settings': {
  			        'userName': "User",
  			        //[not yet] 'defaultTheme': "blue",
  			        'dataPersistence': true
  			    },
  			    'currentBoard': 0,  // The index of the currently open board.
  			    'identifier': 0
  			};</script>
        <script src="./css/js/main.js"></script>
      
      <script>		
      
      <%
      ProjectsManager dsb = new ProjectsManager();
      Projet project = dsb.getProjectById(Integer.parseInt((String) request.getAttribute("id")));
      int i = 0;
      
   // Iterating over services in the project
   for (Services service : project.getServices()) {
       i++;

       // Create a card for each service
       String cardDescription = service.getDescription();
   %>
   var serviceCard<%= i %> = new Card('<%= cardDescription %>', currentBoard().uniqueID(), currentBoard().id);

   <%
       // Add tasks to the card
       for (Tache task : service.getTaches()) {
           // Assuming you have a method getFormattedTaskInfo() in your Tache class
           String taskInfo = task.getName();
   %>
   var taskInfo<%= i %> = '<%= taskInfo %>';
   var task<%= i %> = new Item('<%= taskInfo %>', null, currentBoard().uniqueID(), serviceCard<%= i %>.id);
   serviceCard<%= i %>.addItem(task<%= i %>);
   <%
       }
   %>

   currentBoard().cards.push(serviceCard<%= i %>);

   // Add the card element to the DOM
   var newCard<%= i %> = serviceCard<%= i %>.generateElement();
   e_cardsContainer.insertBefore(newCard<%= i %>, e_cardsContainer.childNodes[e_cardsContainer.childNodes.length - 2]);

   <%
   }
   %>

   function fillForm() {
	    document.getElementById("tName").value = '<%=project.getProjectName()%>';

	    document.getElementById("dateBegin").value = "<%= project.getDateBeginweb() %>";
	    document.getElementById("dateLivraison").value = "<%= project.getDateLivraisonweb() %>";
	    document.getElementById("duration").value = "<%= project.getDuration() %>";
	    document.getElementById("desc").value = '<%= project.getDescription() %>';
	    document.getElementById("client").value = "<%= project.getClient() %>";
	    document.getElementById("manager").value = "<%= project.getManager().getUsername() %>";
	  
   }
   window.onload = function () {

   
   for (let _card of currentCards()) {
       console.log('Service: '+ _card.name);
       for (let _item of _card.items) {
    	   
           console.log('Task: '+_item.title);
       }
   }
   fillForm();

       // Add other code you want to run on page load here
   };

     // JavaScript
     // Function to add a methodology to the list
     function addMethodology( name) {
       var select = document.getElementById("methodologiesSelect");
       var optionText = name; // Replace with your methodology name
       var option = document.createElement("option");
       option.text = optionText;
       select.add(option);

       // Add bubble-like appearance
       var bubble = document.createElement("span");
       bubble.className = "methodology-bubble";
       bubble.textContent = optionText;
       bubble.setAttribute("data-value", optionText);
      
       var deleteButton = document.createElement("span");
       deleteButton.textContent = "\uF1F8"; // Unicode character for delete button
       deleteButton.className = "delete-button";
       deleteButton.addEventListener("click", function() {
         removeMethodology(optionText);
       });

       // Append the delete button to the bubble
       bubble.appendChild(deleteButton);

       document.getElementById("methodologiesContainer").appendChild(bubble);
     }

     // Function to remove a methodology from the list
     function removeMethodology(methodology) {
       var select = document.getElementById("methodologiesSelect");
       var bubble = document.querySelector('.methodology-bubble[data-value="' + methodology + '"]');
       select.remove(select.selectedIndex);
       bubble.parentNode.removeChild(bubble);
     }
  // JavaScript
  // Function to add a bubble-like appearance
function addBubble(name, containerId, removeFunctionName) {
    var container = document.getElementById(containerId);

    var bubble = document.createElement("span");
    bubble.className = "teammember-bubble"; // Change the class name based on the type

    bubble.textContent = name;
    bubble.setAttribute("data-value", name);

    var deleteButton = document.createElement("span");
    deleteButton.textContent = "\uF1F8"; // Unicode character for delete button
    deleteButton.className = "delete-button";
    deleteButton.addEventListener("click", function () {
        window[removeFunctionName](name); // Call the remove function dynamically
    });

    // Append the delete button to the bubble
    bubble.appendChild(deleteButton);

    container.appendChild(bubble);
}

// Now you can use the addBubble function in your code

  // Function to add a team member to the list
  function addTeamMember(name) {
      var teamMembersInput = document.getElementById("equipe");
      var existingTeamMembers = teamMembersInput.value.split(',').map(function(item) {
          return item.trim();
      });

      // Add only if not already present
      if (!existingTeamMembers.includes(name)) {
          existingTeamMembers.push(name);

          // Update the input field
          teamMembersInput.value = existingTeamMembers.join(', ');

          // Add a bubble-like appearance
          addBubble(name, "teamMembersContainer", "removeTeamMember");
      }
  }

  // Function to remove a team member from the list
  function removeTeamMember(name) {
      var teamMembersInput = document.getElementById("equipe");
      var existingTeamMembers = teamMembersInput.value.split(',').map(function(item) {
          return item.trim();
      });

      // Remove the team member
      var index = existingTeamMembers.indexOf(name);
      if (index !== -1) {
          existingTeamMembers.splice(index, 1);

          // Update the input field
          teamMembersInput.value = existingTeamMembers.join(', ');

          // Remove the bubble
          removeBubble(name, "teamMembersContainer");
      }
  }

//JavaScript
//Function to add a technology to the list
function addTechnology(name) {
   var technologiesInput = document.getElementById("technologies");
   var existingTechnologies = technologiesInput.value.split(',').map(function(item) {
       return item.trim();
   });

   // Add only if not already present
   if (!existingTechnologies.includes(name)) {
       existingTechnologies.push(name);

       // Update the input field
       technologiesInput.value = existingTechnologies.join(', ');

       // Add a bubble-like appearance
       addBubble(name, "technologiesContainer", "removeTechnology");
   }
}

//Function to remove a technology from the list
function removeTechnology(name) {
   var technologiesInput = document.getElementById("technologies");
   var existingTechnologies = technologiesInput.value.split(',').map(function(item) {
       return item.trim();
   });

   // Remove the technology
   var index = existingTechnologies.indexOf(name);
   if (index !== -1) {
       existingTechnologies.splice(index, 1);

       // Update the input field
       technologiesInput.value = existingTechnologies.join(', ');

       // Remove the bubble
       removeBubble(name, "technologiesContainer");
   }
}

//Initial setup, add existing technologies to the list
var existingTechnologies = [<%for(Technologie technology:project.getTechnologies()){%>"<%=technology.getName()%>",<%}%>]; // Replace with your technologies
for (var i = 0; i < existingTechnologies.length; i++) {
   addTechnology(existingTechnologies[i]);
}

  // Initial setup, add existing team members to the list
  var existingTeamMembers = [<%for(User member:project.getEquipe()){%>"<%=member.getUsername()%>",<%}%>]; // Replace with your team members
  for (var i = 0; i < existingTeamMembers.length; i++) {
      addTeamMember(existingTeamMembers[i]);
  }


     // Initial setup, add existing methodologies to the list
     var existingMethodologies = [<%for(Methodologie meth:project.getMethodologies()){%>"<%=meth.getName()%>",<%}%>]; // Replace with your methodologies
     for (var i = 0; i < existingMethodologies.length; i++) {
       addMethodology(existingMethodologies[i]);
     }
     document.getElementById("equipe").addEventListener("keydown", function (event) {
    	    // Check if the "Enter" key is pressed
    	    if (event.key === "Enter") {
    	        event.preventDefault(); // Prevent the default behavior (e.g., newline in the input field)
    	        var inputValue = event.target.value;
    	        if (inputValue.trim() !== "") {
    	            addTeamMember(inputValue);
    	            event.target.value = ""; // Clear the input field after adding the team member
    	        }
    	    }
    	});
   </script>


	<style>
	:root {
    --delete-button-color: var(--primary-color-light);
    --delete-button-size: 14px;
    /* Add other styles as needed */
}

.delete-button {
    display: inline-block;
    font: normal normal normal var(--delete-button-size)/1 FontAwesome;
    font-size: inherit;
    text-rendering: auto;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    color: var(--delete-button-color);
}
	
     /* CSS */
     .methodology-bubble {
       display: inline-block;
       margin: 5px;
       padding: 5px 10px;
       background-color: #e0e0e0;
       border-radius: 20px;
       cursor: pointer;
     }
          .technology-bubble {
       display: inline-block;
       margin: 5px;
       padding: 5px 10px;
       background-color: #e0e0e0;
       border-radius: 20px;
       cursor: pointer;
     }
     .teammember-bubble {
       display: inline-block;
       margin: 5px;
       padding: 5px 10px;
       background-color: #e0e0e0;
       border-radius: 20px;
       cursor: pointer;
     }

    #methodologiesSelect,#teamMembersSelect,#technologiesSelect {
        display: none;
        position: absolute;
      
    }

    #meths:focus + #methodologiesSelect {
        display: block;
    }
    .project-inf {
   
    padding: 20px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    background-color: #fff;
}

input,
textarea {
    margin-bottom: 15px;
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 4px;
}
.methodology-bubble,
.technology-bubble,
.teammember-bubble {
    display: inline-block;
    margin: 5px;
    padding: 5px 10px;
    background-color: #e0e0e0;
    border-radius: 20px;
    cursor: pointer;
}

.delete-button {
    display: inline-block;
    font: normal normal normal 14px/1 FontAwesome;
    font-size: inherit;
    text-rendering: auto;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    color: var(--delete-button-color);
}
   </style>
    </body>
</html>