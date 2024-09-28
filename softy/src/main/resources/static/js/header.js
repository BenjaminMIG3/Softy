function checkSession() {
    fetch('/api/user/check-session') // Endpoint pour vérifier la session
        .then(response => {
            if (!response.ok) {
                // Si la session a expiré
                window.location.href = '/login'; // Rediriger vers la page de connexion
            }
        });
}

// Vérifiez si l'utilisateur est connecté avant de démarrer l'intervalle
if (document.getElementById('user-session').value) { // Assurez-vous d'avoir un élément caché avec l'ID 'user-session'
    // Vérification toutes les 5 minutes
    setInterval(checkSession, 300000); // 300000 ms = 5 minutes

    // Appel initial lors du chargement de la page
    window.onload = checkSession;
}