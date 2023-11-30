<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
    <link href="//fonts.googleapis.com/css?family=Hind:300,400,500,600,700" rel="stylesheet">
 	<link rel="stylesheet" type="text/css" href="css/style.css">

</head>
<body>
    <!-- login.jsp -->

    <script>
        window.addEventListener("load", function () {
            setTimeout(hideURLbar, 0);
        }, false);

        function hideURLbar() {
            window.scrollTo(0, 1);
        }
    </script>

    <div class="w3layouts-main"> 
        <div class="bg-layer">
            <h1>Find Your Account</h1>
            <div class="header-main">
                <div class="main-icon">
                    <span class="fa fa-eercast"></span>
                </div>
                <div class="header-left-bottom">
                    <!-- login form -->
                    <form action="Login" method="get">
                        <div class="icon1">
                            <span class="fa fa-user"></span>
                            <input placeholder="Username" id="username" name="username" required>
                        </div>
                        
                        <!-- error message -->
                        <div class="bottom">
                            <button class="btn1" type="submit">Searchjhkjnkjnknkjnkj</button>
                            <button class="btn2" type="submit">Annuler</button>
                        </div>

                        <div class="links">
                            <p><a href="#">Forgot Password?</a></p>
                            <p class="right"><a href="">New User? Register</a></p>
                            <div class="clear"></div>
                        </div>
                    </form>	
                </div>
            </div>
        </div>
    </div>

</body>
</html>
