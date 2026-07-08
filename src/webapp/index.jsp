<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Accueil - Médiathèque</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<%@ include file="/jsp/includes/header.jspf" %>

<div class="container">
    <!-- Titre principal de la page d'accueil -->
    <div class="card">
        <h1>Bienvenue dans l'application de gestion de médiathèque</h1>
        <p>
            Cette application permet de gérer :
        </p>
        <ul>
            <li>les livres</li>
            <li>les auteurs</li>
            <li>les éditeurs</li>
            <li>les catégories</li>
            <li>les utilisateurs</li>
            <li>les emprunts</li>
        </ul>
    </div>

    <!-- Grille simple de cartes pour accéder aux modules -->
    <div class="actions">
        <div class="card">
            <h2>Livres</h2>
            <a class="btn" href="${pageContext.request.contextPath}/livres?action=list">Accéder</a>
        </div>

        <div class="card">
            <h2>Auteurs</h2>
            <a class="btn" href="${pageContext.request.contextPath}/auteurs?action=list">Accéder</a>
        </div>

        <div class="card">
            <h2>Éditeurs</h2>
            <a class="btn" href="${pageContext.request.contextPath}/editeurs?action=list">Accéder</a>
        </div>

        <div class="card">
            <h2>Catégories</h2>
            <a class="btn" href="${pageContext.request.contextPath}/categories?action=list">Accéder</a>
        </div>

        <div class="card">
            <h2>Utilisateurs</h2>
            <a class="btn" href="${pageContext.request.contextPath}/utilisateurs?action=list">Accéder</a>
        </div>

        <div class="card">
            <h2>Emprunts</h2>
            <a class="btn" href="${pageContext.request.contextPath}/emprunts?action=list">Accéder</a>
        </div>
    </div>
</div>

</body>
</html>
