function checkSession() {
    fetch('/api/user/check-session') // Endpoint pour vérifier la session
        .then(response => {
            if (!response.ok) {
                // Si la session a expiré
                window.location.href = '/login'; // Rediriger vers la page de connexion
            }
        });
}
