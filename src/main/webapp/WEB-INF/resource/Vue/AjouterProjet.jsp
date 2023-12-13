<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    
    <title>Ajouter Nouveau Projet</title>
    
</head>
<body>



<form action="AjouterProjet" method="post">
    <!-- Remplacez 'process-add-project' par l'URL de votre servlet de traitement -->

    <label for="projectName">Nom du Projet*:</label>
    <input type="text" id="projectName" name="projectName" required><br>

    <label for="description">Description:</label>
    <textarea id="description" name="description" required></textarea><br>

    <label for="client">Client:</label>
    <input type="text" id="client" name="client" required><br>

    <label for="startDate">Date de Début:</label>
    <input type="date" id="startDate" name="startDate" required><br>

    <label for="deliveryDate">Date de Livraison:</label>
    <input type="date" id="deliveryDate" name="deliveryDate" required><br>

    <label for="developmentDays">Nombre de Jours de Développement:</label>
    <input type="number" id="developmentDays" name="developmentDays" required><br>

   
<label for="chefProjet">Chef de Projet:</label>
<select id="chefProjet" name="chefProjet" required>
    <c:forEach var="chef" items="${chefsDeProjet}">
        <option value="${chef.username}">${chef.username}</option>
    </c:forEach>
</select><br>

    

    <input type="submit" value="Ajouter Projet">
</form>

</body>
</html>
