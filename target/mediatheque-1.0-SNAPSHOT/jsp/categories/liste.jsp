<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Liste des catégories</title>
</head>
<body>

<%@ include file="/jsp/includes/header.jspf" %>

<div class="container">
<h1>Liste des catégories</h1>

<!-- Lien vers le formulaire de création -->
<p>
    <a href="${pageContext.request.contextPath}/categories?action=new">Nouvelle catégorie</a>
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
    <!-- Boucle sur toutes les catégories envoyées par la servlet -->
    <c:forEach var="categorie" items="${categories}">
        <tr>
            <td>${categorie.id}</td>
            <td>${categorie.nom}</td>
            <td>
                <a href="${pageContext.request.contextPath}/categories?action=edit&id=${categorie.id}">Modifier</a>

                <!-- Le bouton supprimer utilise un formulaire POST -->
                <form method="post" action="${pageContext.request.contextPath}/categories" style="display:inline; margin-left:8px;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" value="${categorie.id}">
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
