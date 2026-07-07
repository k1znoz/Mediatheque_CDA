<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Formulaire livre</title>
</head>
<body>

<c:choose>
    <c:when test="${livre == null}">
        <h1>Nouveau livre</h1>
    </c:when>
    <c:otherwise>
        <h1>Modifier un livre</h1>
    </c:otherwise>
</c:choose>

<!-- Formulaire unique : création si livre est null, sinon modification -->
<form method="post" action="${pageContext.request.contextPath}/livres">
    <c:choose>
        <c:when test="${livre == null}">
            <input type="hidden" name="action" value="create">
        </c:when>
        <c:otherwise>
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="id" value="${livre.id}">
        </c:otherwise>
    </c:choose>

    <p>
        <label for="titre">Titre :</label>
        <input type="text" id="titre" name="titre" value="${livre.titre}" required>
    </p>

    <p>
        <label for="isbn">ISBN :</label>
        <input type="text" id="isbn" name="isbn" value="${livre.isbn}" required>
    </p>

    <p>
        <label for="datePublication">Date de publication :</label>
        <input type="date" id="datePublication" name="datePublication" value="${livre.datePublication}" required>
    </p>

    <p>
        <button type="submit">Enregistrer</button>
    </p>
</form>

<!-- Lien simple pour revenir à la liste -->
<p>
    <a href="${pageContext.request.contextPath}/livres?action=list">Retour vers la liste</a>
</p>

</body>
</html>
