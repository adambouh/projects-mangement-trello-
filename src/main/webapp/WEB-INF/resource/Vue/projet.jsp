
<!DOCTYPE html>
<%@ include file="homehead.jspf"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="businessLayer.ProjectsManager" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>Project Management Dashboard </title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<link rel="stylesheet" href="../css/homestyle.css">

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
        <meta name="msapplication-config" content="icons/browserconfig.xml">
        <meta name="theme-color" content="#ffffff">

 <link rel="stylesheet" href="../css/main.css"> 
        
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" integrity="sha512-SfTiTlX6kk+qitfevl/7LibUOeJWlt9rbyDn92a1DqWOw9vWG2MFoays0sgObmWazO5BQPiFucnnEAjpAB+/Sw==" crossorigin="anonymous" referrerpolicy="no-referrer" />    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<link rel="stylesheet" href="../css/homestyle.css">
</head>
<body>
<!-- partial:index.partial.html -->
<div class="app-container">
<!--  top barr at homeee -->
<%@ include file="tophome.jspf"%>

  <div class="app-content">
    <%@ include file="side.jspf"%>
 
		<script>console.log("<%= request.getAttribute("id") %>")</script>
    
       
            <div class="header-flex" style="display:none;">
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
            <!-- Right-click context menu on cards. -->
            <ul>
                <li id="card-context-menu-delete">Delete</li>
                <li id="card-context-menu-clear">Clear</li>
                <li id="card-context-menu-duplicate">Duplicate</li>
            </ul>
        </div>

        <div class="container" id="main-container">
            <div id="cards-container">
                <div id="add-card">
                    <input maxlength="128" type="text" id="add-card-text" name="add-card" placeholder="Add Card..." autofocus>
        	            <button id="add-card-button" class="plus-button">+</button>
                </div>
            </div>
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

        <script type="text/javascript" src="../css/js/main.js"></script>
    </body>
</html>