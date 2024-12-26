<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Utilisateur</title>
</head>
<body>

<h1>Utilisateur</h1>

<form action="calculImpot" method="post">
    <!-- Champs nécessaires pour l'utilisateur -->
    Nom d'utilisateur: <input type="text" name="username"><br>
    Mot de passe: <input type="password" name="password"><br>
    <input type="submit" value="Calculer Impôt">
</form>

</body>
</html>
