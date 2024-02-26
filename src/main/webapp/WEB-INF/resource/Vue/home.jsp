<!DOCTYPE html>
<%@ include file="homehead.jspf"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="businessLayer.ProjectsManager" %>
<%@ page import="models.*" %>

 
<% 
    // Assuming you have a method to get projects in your ProjectsManager class
    ProjectsManager projectsManager = new ProjectsManager();
    ArrayList<Projet> projects = projectsManager.GetProjects();
%>
<%!
  // Function to darken a color by adjusting its brightness
  String darkenColor(String color, int vibrancyFactor) {
	  float[] hsb = new float[3];
	    java.awt.Color.RGBtoHSB(
	        Integer.parseInt(color.substring(1, 3), 16),
	        Integer.parseInt(color.substring(3, 5), 16),
	        Integer.parseInt(color.substring(5, 7), 16),
	        hsb
	    );

	    // Increase saturation and brightness
	    hsb[1] = Math.min(1.0f, hsb[1] + vibrancyFactor / 100.0f); // Adjust saturation
	    hsb[2] = Math.min(1.0f, hsb[2] + vibrancyFactor / 100.0f); // Adjust brightness
	    int darknessFactor = 10; // Adjust this value to control the darkness

	    return darkerColor(String.format("#%06X", java.awt.Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]) & 0xFFFFFF), darknessFactor);
	  }
String darkerColor(String color, int factor) {
    float[] hsb = new float[3];
    java.awt.Color.RGBtoHSB(
        Integer.parseInt(color.substring(1, 3), 16),
        Integer.parseInt(color.substring(3, 5), 16),
        Integer.parseInt(color.substring(5, 7), 16),
        hsb
    );

    // Decrease brightness
    hsb[2] = Math.max(0.0f, hsb[2] - factor / 100.0f); // Adjust brightness

    return String.format("#%06X", java.awt.Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]) & 0xFFFFFF);
  }
%>

<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>Project Management Dashboard </title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<link rel="stylesheet" href="./css/homestyle.css">

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
        <p>Projects</p>
     <%!    String formatDate(Date date) {
        if (date == null) {
            return "N/A";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy");
        return sdf.format(date);
    }%>
        <p class="time"><%=formatDate(new Date()) %></p>
      </div>
      <div class="projects-section-line">
        <div class="projects-status">
          <div class="item-status">
            <span class="status-number">45</span>
            <span class="status-type">In Progress</span>
          </div>
          <div class="item-status">
            <span class="status-number">24</span>
            <span class="status-type">Upcoming</span>
          </div>
          <div class="item-status">
            <span class="status-number">62</span>
            <span class="status-type">Total Projects</span>
          </div>
        </div>
        <div class="view-actions">
          <button class="view-btn list-view" title="List View">
            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-list">
              <line x1="8" y1="6" x2="21" y2="6" />
              <line x1="8" y1="12" x2="21" y2="12" />
              <line x1="8" y1="18" x2="21" y2="18" />
              <line x1="3" y1="6" x2="3.01" y2="6" />
              <line x1="3" y1="12" x2="3.01" y2="12" />
              <line x1="3" y1="18" x2="3.01" y2="18" /></svg>
          </button>
          <button class="view-btn grid-view active" title="Grid View">
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-grid">
              <rect x="3" y="3" width="7" height="7" />
              <rect x="14" y="3" width="7" height="7" />
              <rect x="14" y="14" width="7" height="7" />
              <rect x="3" y="14" width="7" height="7" /></svg>
          </button>
        </div>
      </div>
      <div class="project-boxes jsGridView">
<%
  String[] colors = {"#e9e7fd", "#e9e7fd", "#ffd3e2", "#c8f7dc", "#dbf6fd"};

for (Projet project : projects) {
  int randomIndex = (int) (Math.random() * colors.length);
  String baseColor = colors[randomIndex];

  // Manually adjust brightness for a darker shade
  int darkenFactor = 50; // Adjust this value to control the darkness
  String darkerColor = darkenColor(baseColor, darkenFactor);
%>
<div class="project-box-wrapper" >
<div class="project-box" style="background-color: <%=baseColor%>;" onclick="navigateToProject(<%=project.getId()%>)">
  <div class="project-box-header">
    <span><%=project.getDateBegin() %></span>
    <div class="more-wrapper">
      <button class="project-btn-more" onclick="toggleDropdown('<%=project.getId()%>')">
        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-more-vertical">
          <circle cx="12" cy="12" r="1" />
          <circle cx="12" cy="5" r="1" />
          <circle cx="12" cy="19" r="1" />
        </svg>
      </button>
      <div id="dropdown<%=project.getId()%>" class="dropdown-content">
        <a href="#" onclick="deleteProject('<%=project.getId()%>')">Delete</a>
      </div>
          </div>
        </div>
        <div class="project-box-content-header">
          <p class="box-content-header"><%=project.getProjectName() %></p>
          <p class="box-content-subheader"><%=project.getDescription()%></p>
        </div>
        <div class="box-progress-wrapper">
          <p class="box-progress-header">Progress</p>
          <div class="box-progress-bar">
            <span class="box-progress" style="width: <%=project.calculateProgress()%>%; background-color: <%=darkerColor%>;"></span>
          </div>
          <p class="box-progress-percentage"><%=project.calculateProgress()%>%</p>
        </div>
        <div class="project-box-footer">
          <div class="participants">
          <% for (User team : project.getEquipe()) { %>
            <img src="<%=team.getProfilePic()%>" alt="participant">
          <%}%>  <button class="add-participant" style="color: <%=darkerColor%>;">
              <svg xmlns="http://www.w3.org/2000/svg" width="12" height="12" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="3" stroke-linecap="round" stroke-linejoin="round" class="feather feather-plus">
                <path d="M12 5v14M5 12h14" />
              </svg>
            </button>
          </div>
          <div class="days-left<%=project.getId()%>" style="color: <%= darkerColor %>;">
    <script type="text/javascript">
        // Assuming project.getDateLivraison() returns a formatted date as a string
        var deliveryDateStr = '<%= project.getDateLivraison() %>';

        var deliveryDate = new Date(deliveryDateStr);

        // Current date
        var currentDate = new Date();

        // Calculate the difference in milliseconds
        var timeDifference = deliveryDate-currentDate;

        // Calculate the number of days
        var daysDifference = Math.ceil(timeDifference / (1000 * 60 * 60 * 24));

        // Update the content of the element based on the number of days
        var daysLeftElement = document.querySelector('.days-left<%=project.getId()%>');
        if (daysDifference > 0) {
            daysLeftElement.textContent = daysDifference + " Days Left";
        } else if (daysDifference === 0) {
            daysLeftElement.textContent = "Ends today!";
        } else {
            daysLeftElement.textContent = "Project ended.";
        }
    </script>
    <style>
    
.days-left<%=project.getId()%> {
  background-color: rgba(255, 255, 255, 0.6);
  font-size: 12px;
  border-radius: 20px;
  flex-shrink: 0;
  padding: 6px 16px;
  font-weight: 700;
}
  .days-left<%=project.getId()%> {
    font-size: 8px;
    padding: 6px 6px;
    text-align: center;
  }</style>
</div>

        </div>
      </div>
    </div>    <%}%> 
      </div>
  
</div>

</div>
</div>
<style>
/* Style the dropdown button and content */
.dropdown {
    position: relative;
    display: inline-block;
}


.dropdown-content {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 160px;
    box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
    z-index: 1;
}

/* Style the dropdown links */
.dropdown-content a {
    color: #333;
    padding: 12px 16px;
    display: block;
    text-decoration: none;
}

.dropdown-content a:hover {
    background-color: #ddd;
}

.show {
    display: block;
}
</style>
<!-- partial -->
  <script  src="./css/script.js"></script>
  <script>

  function deleteProject(projectId) {
    // Implement your delete logic here for the specific project identified by projectId
    alert(`Deleting project ${projectId}...`);
    // You can make an AJAX request or perform any other actions as needed
  }


  function toggleDropdown(projectId) {
	    const dropdown = document.getElementById("dropdown"+projectId);
	    console.log("dropdown"+projectId);
	    // Check if the dropdown element exists before accessing its classList
	    if (dropdown) {
	        const displayStyle = window.getComputedStyle(dropdown).getPropertyValue('display');
	        if (displayStyle === "block") {
	            dropdown.style.display = "none";
	        } else {
	        	closeAllDropdowns();
	            dropdown.style.display = "block";
	        }
	    }
	}
  function closeAllDropdowns() {
	    const dropdowns = document.getElementsByClassName('dropdown-content');
	    for (let i = 0; i < dropdowns.length; i++) {
	        const openDropdown = dropdowns[i];
	        if (window.getComputedStyle(openDropdown).getPropertyValue('display') === "block") {
	            openDropdown.style.display = "none";
	        }
	    }
	}
 
  function navigateToProject(projectId) {
	    // You can use the projectId parameter to construct the URL or perform other actions
	    var projectUrl = "project"
	    // Add the id parameter to the URL
	    projectUrl += "?id=" + projectId;

	    // Navigate to the specified URL
	    window.location.href = projectUrl;
	}
</script>
	
</body>
</html>
