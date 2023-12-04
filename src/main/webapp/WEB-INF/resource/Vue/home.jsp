<!DOCTYPE html>
<%@ include file="homehead.jspf"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="businessLayer.ProjectsManager" %>

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
        <p class="time">December, 12</p>
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
      <div class="project-box-wrapper">
          <div class="project-box" style="background-color: <%=baseColor%>;">
            <div class="project-box-header">
              <span><%=project.getDateBegin() %></span>
              <div class="more-wrapper">
                <button class="project-btn-more">
                  <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-more-vertical">
                    <circle cx="12" cy="12" r="1" />
                    <circle cx="12" cy="5" r="1" />
                    <circle cx="12" cy="19" r="1" /></svg>
                </button>
          </div>
        </div>
        <div class="project-box-content-header"><%System.out.println(project.getProjectName()); %>
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
            <img src="./pics/<%=team.getProfilePic()%>" alt="participant">
          <%}%>  <button class="add-participant" style="color: <%=darkerColor%>;">
              <svg xmlns="http://www.w3.org/2000/svg" width="12" height="12" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="3" stroke-linecap="round" stroke-linejoin="round" class="feather feather-plus">
                <path d="M12 5v14M5 12h14" />
              </svg>
            </button>
          </div>
          <div class="days-left" style="color: <%=darkerColor%>;">
            2 Days Left
          </div>
        </div>
      </div>
    </div>    <%}%> 
      </div>
  
</div>

</div>
</div>
<!-- partial -->
  <script  src="./css/script.js"></script>

</body>
</html>
