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
            <h1>Inscription</h1>
            <div class="header-main">
                <div class="main-icon">
                    <span class="fa fa-eercast"></span>
                </div>
                <div class="header-left-bottom">
                    <!-- Register Form -->
                    <form action="" method="post">
         <div class="icon1">           
       
        <input placeholder="Username" id="username" name="username" required>
        </div>
        <div class="icon1">
        
        <input placeholder="Nom" id="nom" name="nom" required>
         </div>
         <div class="icon1">
        
        <input placeholder="Prenom" id="prenom" name="prenom" required>
        </div>
        <div class="icon1">
        
        <input placeholder="Email" id="email" name="email" required>
        </div>
        <div class="icon1">
        
        <input placeholder="Mot de passe" type="password" id="mot_de_passe" name="mot_de_passe" required>
        </div>
        <div class="icon1">
       
        <input placeholder="Confirmer mot de passe" type="password" id="confirmer_mot_de_passe" name="confirmer_mot_de_passe" required>
        </div>
         <div class="bottom">
                            <button class="btn" type="submit">S'inscrire</button>
                        </div>
    </form>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
