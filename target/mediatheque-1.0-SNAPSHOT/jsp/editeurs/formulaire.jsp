<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Formulaire éditeurs</title>
</head>
<body>

<c:choose>
    <c:when test="${editeur == null}">
        <h1>Nouvel éditeur</h1>
    </c:when>
    <c:otherwise>
        <h1>Modifier un éditeur</h1>
    </c:otherwise>
</c:choose>

<!-- Formulaire unique : création si éditeur est null, sinon modification -->
<form method="post" action="${pageContext.request.contextPath}/editeurs">
    <c:choose>
        <c:when test="${editeur == null}">
            <input type="hidden" name="action" value="create">
        </c:when>
        <c:otherwise>
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="id" value="${editeur.id}">
        </c:otherwise>
    </c:choose>

    <p>
        <label for="nom">Nom :</label>
        <input type="text" id="nom" name="nom" value="${editeur.nom}" required>
    </p>

    <p>
        <button type="submit">Enregistrer</button>
    </p>
</form>

<!-- Lien simple pour revenir à la liste -->
<p>
    <a href="${pageContext.request.contextPath}/editeurs?action=list">Retour vers la liste</a>
</p>

</body>
</html>
