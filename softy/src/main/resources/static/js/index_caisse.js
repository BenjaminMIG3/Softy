document.addEventListener("DOMContentLoaded", function() {
    fetch("/api/user/check-session")
    .then(response => {
        if (!response.ok) {
            window.location.href = '/login'; // Rediriger vers la page de connexion
        }
    });
});