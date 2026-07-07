<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Formulaire utilisateurs</title>
</head>
<body>

<%@ include file="/jsp/includes/header.jspf" %>

<div class="container">

<c:choose>
    <c:when test="${utilisateur == null}">
        <h1>Nouvel utilisateur</h1>
    </c:when>
    <c:otherwise>
        <h1>Modifier un utilisateur</h1>
    </c:otherwise>
</c:choose>

<!-- Formulaire unique : création si utilisateur est null, sinon modification -->
<form method="post" action="${pageContext.request.contextPath}/utilisateurs">
    <c:choose>
        <c:when test="${utilisateur == null}">
            <input type="hidden" name="action" value="create">
        </c:when>
        <c:otherwise>
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="id" value="${utilisateur.id}">
        </c:otherwise>
    </c:choose>

    <p>
        <label for="nom">Nom :</label>
        <input type="text" id="nom" name="nom" value="${utilisateur.nom}" required>
    </p>

    <p>
        <label for="prenom">Prénom :</label>
        <input type="text" id="prenom" name="prenom" value="${utilisateur.prenom}" required>
    </p>

    <p>
        <label for="email">Email :</label>
        <input type="email" id="email" name="email" value="${utilisateur.email}" required>
    </p>

    <p>
        <button type="submit">Enregistrer</button>
    </p>
</form>

<!-- Lien simple pour revenir à la liste -->
<p>
    <a href="${pageContext.request.contextPath}/utilisateurs?action=list">Retour vers la liste</a>
</p>

</div>

</body>
</html>
