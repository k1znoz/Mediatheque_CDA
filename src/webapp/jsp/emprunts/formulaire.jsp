<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Formulaire emprunt</title>
</head>
<body>

<%@ include file="/jsp/includes/header.jspf" %>

<div class="container">

<c:choose>
    <c:when test="${emprunt == null}">
        <h1>Nouvel emprunt</h1>
    </c:when>
    <c:otherwise>
        <h1>Modifier un emprunt</h1>
    </c:otherwise>
</c:choose>

<!-- Formulaire unique : creation si emprunt est null, sinon modification -->
<form method="post" action="${pageContext.request.contextPath}/emprunts">
    <c:choose>
        <c:when test="${emprunt == null}">
            <input type="hidden" name="action" value="create">
        </c:when>
        <c:otherwise>
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="id" value="${emprunt.id}">
        </c:otherwise>
    </c:choose>

    <p>
        <label for="utilisateurId">Utilisateur :</label>
        <select id="utilisateurId" name="utilisateurId" required>
            <option value="">-- Choisir un utilisateur --</option>
            <c:forEach var="utilisateur" items="${utilisateurs}">
                <c:choose>
                    <c:when test="${emprunt != null && emprunt.utilisateur != null && emprunt.utilisateur.id == utilisateur.id}">
                        <option value="${utilisateur.id}" selected>${utilisateur.prenom} ${utilisateur.nom}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${utilisateur.id}">${utilisateur.prenom} ${utilisateur.nom}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
    </p>

    <p>
        <label for="livreId">Livre :</label>
        <select id="livreId" name="livreId" required>
            <option value="">-- Choisir un livre --</option>
            <c:forEach var="livre" items="${livres}">
                <c:choose>
                    <c:when test="${emprunt != null && emprunt.livre != null && emprunt.livre.id == livre.id}">
                        <option value="${livre.id}" selected>${livre.titre}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${livre.id}">${livre.titre}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
    </p>

    <p>
        <label for="dateEmprunt">Date emprunt :</label>
        <input type="date" id="dateEmprunt" name="dateEmprunt" value="${emprunt.dateEmprunt}" required>
    </p>

    <p>
        <label for="dateRetourPrevue">Date retour prevue :</label>
        <input type="date" id="dateRetourPrevue" name="dateRetourPrevue" value="${emprunt.dateRetourPrevue}" required>
    </p>

    <p>
        <label for="dateRetourEffective">Date retour effective :</label>
        <input type="date" id="dateRetourEffective" name="dateRetourEffective" value="${emprunt.dateRetourEffective}">
    </p>

    <p>
        <button type="submit">Enregistrer</button>
    </p>
</form>

<!-- Lien simple pour revenir a la liste -->
<p>
    <a href="${pageContext.request.contextPath}/emprunts?action=list">Retour vers la liste</a>
</p>

</div>

</body>
</html>
