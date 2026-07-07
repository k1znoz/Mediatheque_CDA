<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des livres</title>
</head>
<body>
<h1>Liste des livres</h1>

<!-- Lien vers le formulaire de création -->
<p>
    <a href="${pageContext.request.contextPath}/livres?action=new">Nouveau livre</a>
</p>

<table border="1" cellspacing="0" cellpadding="8">
    <thead>
    <tr>
        <th>Id</th>
        <th>Titre</th>
        <th>ISBN</th>
        <th>Date de publication</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <!-- Boucle sur tous les livres envoyés par la servlet -->
    <c:forEach var="livre" items="${livres}">
        <tr>
            <td>${livre.id}</td>
            <td>${livre.titre}</td>
            <td>${livre.isbn}</td>
            <td>${livre.datePublication}</td>
            <td>
                <a href="${pageContext.request.contextPath}/livres?action=edit&id=${livre.id}">Modifier</a>

                <!-- Le bouton supprimer utilise un formulaire POST -->
                <form method="post" action="${pageContext.request.contextPath}/livres" style="display:inline; margin-left:8px;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" value="${livre.id}">
                    <button type="submit">Supprimer</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
