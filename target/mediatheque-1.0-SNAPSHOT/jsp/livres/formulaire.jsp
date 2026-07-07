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
        <label for="categorieId">Catégorie :</label>
        <select id="categorieId" name="categorieId" required>
            <option value="">-- Choisir une catégorie --</option>
            <c:forEach var="categorie" items="${categories}">
                <c:choose>
                    <c:when test="${livre != null && livre.categorie != null && livre.categorie.id == categorie.id}">
                        <option value="${categorie.id}" selected>${categorie.nom}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${categorie.id}">${categorie.nom}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
    </p>

    <p>
        <label for="editeurId">Éditeur :</label>
        <select id="editeurId" name="editeurId" required>
            <option value="">-- Choisir un éditeur --</option>
            <c:forEach var="editeur" items="${editeurs}">
                <option value="${editeur.id}" ${livre != null && livre.editeur != null && livre.editeur.id == editeur.id ? 'selected' : ''}>
                        ${editeur.nom}
                </option>
            </c:forEach>
        </select>
    </p>

    <p>Auteurs :</p>
    <c:forEach var="auteur" items="${auteurs}">
        <p>
            <label>
                <input type="checkbox" name="auteurIds" value="${auteur.id}"
                    <c:set var="auteurSelectionne" value="false"/>
                    <c:if test="${livre != null && livre.auteurs != null}">
                        <c:forEach var="auteurLivre" items="${livre.auteurs}">
                            <c:if test="${auteurLivre.id == auteur.id}">
                                <c:set var="auteurSelectionne" value="true"/>
                            </c:if>
                        </c:forEach>
                    </c:if>
                    <c:if test="${auteurSelectionne}">checked</c:if>
                >
                    ${auteur.prenom} ${auteur.nom}
            </label>
        </p>
    </c:forEach>

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
