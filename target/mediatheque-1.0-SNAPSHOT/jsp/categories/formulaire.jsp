<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Formulaire catégorie</title>
</head>
<body>

<c:choose>
    <c:when test="${categorie == null}">
        <h1>Nouvelle catégorie</h1>
    </c:when>
    <c:otherwise>
        <h1>Modifier une catégorie</h1>
    </c:otherwise>
</c:choose>

<!-- Formulaire unique : création si categorie est null, sinon modification -->
<form method="post" action="${pageContext.request.contextPath}/categories">
    <c:choose>
        <c:when test="${categorie == null}">
            <input type="hidden" name="action" value="create">
        </c:when>
        <c:otherwise>
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="id" value="${categorie.id}">
        </c:otherwise>
    </c:choose>

    <p>
        <label for="nom">Nom :</label>
        <input type="text" id="nom" name="nom" value="${categorie.nom}" required>
    </p>

    <p>
        <button type="submit">Enregistrer</button>
    </p>
</form>

<!-- Lien simple pour revenir à la liste -->
<p>
    <a href="${pageContext.request.contextPath}/categories?action=list">Retour vers la liste</a>
</p>

</body>
</html>
