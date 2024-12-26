<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Inscription</title>
</head>
<body>

<h1>Inscription</h1>

<form action="calculImpot" method="post">
    <!-- Champs nécessaires pour l'inscription -->
    Nom d'utilisateur: <input type="text" name="username"><br>
    Situation familiale:
    <select name="situationFamiliale">
        <option value="marie">Marié</option>
        <option value="celibataire">Célibataire</option>
    </select><br>
    Nombre d'enfants: <input type="text" name="nbEnfants"><br>
    Salaire annuel: <input type="text" name="salaire"><br>
    Mot de passe: <input type="password" name="password"><br>
    Confirmer le mot de passe: <input type="password" name="confirmPassword"><br>
    <input type="submit" value="se connecter">
</form>

</body>
</html>
