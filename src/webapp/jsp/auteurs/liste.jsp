<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Liste des auteurs</title>
</head>
<body>

<%@ include file="/jsp/includes/header.jspf" %>

<div class="container">
<h1>Liste des auteurs</h1>

<!-- Lien vers le formulaire de création -->
<p>
    <a href="${pageContext.request.contextPath}/auteurs?action=new">Nouvel auteur</a>
</p>

<table border="1" cellspacing="0" cellpadding="8">
    <thead>
    <tr>
        <th>Id</th>
        <th>Nom</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <!-- Boucle sur tous les auteurs envoyés par la servlet -->
    <c:forEach var="auteur" items="${auteurs}">
        <tr>
            <td>${auteur.id}</td> 
            <td>${auteur.nom}</td>
            <td>
                <a href="${pageContext.request.contextPath}/auteurs?action=edit&id=${auteur.id}">Modifier</a>

                <!-- Le bouton supprimer utilise un formulaire POST -->
                <form method="post" action="${pageContext.request.contextPath}/auteurs" style="display:inline; margin-left:8px;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" value="${auteur.id}">
                    <button type="submit">Supprimer</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</div>

</body>
</html>
