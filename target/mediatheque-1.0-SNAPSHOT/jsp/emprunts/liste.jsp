<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Liste des emprunts</title>
</head>
<body>

<%@ include file="/jsp/includes/header.jspf" %>

<div class="container">
<h1>Liste des emprunts</h1>

<!-- Lien vers le formulaire de creation -->
<p>
    <a href="${pageContext.request.contextPath}/emprunts?action=new">Nouvel emprunt</a>
</p>

<table border="1" cellspacing="0" cellpadding="8">
    <thead>
    <tr>
        <th>Id</th>
        <th>Livre</th>
        <th>Utilisateur</th>
        <th>Date emprunt</th>
        <th>Date retour prevue</th>
        <th>Date retour effective</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <!-- Boucle sur tous les emprunts envoyes par la servlet -->
    <c:forEach var="emprunt" items="${emprunts}">
        <tr>
            <td>${emprunt.id}</td>
            <td>${emprunt.livre.titre}</td>
            <td>${emprunt.utilisateur.prenom} ${emprunt.utilisateur.nom}</td>
            <td>${emprunt.dateEmprunt}</td>
            <td>${emprunt.dateRetourPrevue}</td>
            <td>${emprunt.dateRetourEffective}</td>
            <td>
                <a href="${pageContext.request.contextPath}/emprunts?action=edit&id=${emprunt.id}">Modifier</a>

                <!-- Le bouton supprimer utilise un formulaire POST -->
                <form method="post" action="${pageContext.request.contextPath}/emprunts" style="display:inline; margin-left:8px;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" value="${emprunt.id}">
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
