document.getElementById('userForm').onsubmit = function(event) {
    event.preventDefault(); // Empêcher le comportement par défaut du formulaire

    // Afficher une alerte de confirmation avant de soumettre le formulaire
    Swal.fire({
        title: 'Confirmer la création',
        text: "Êtes-vous sûr de vouloir créer cet utilisateur ?",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: 'green',
        cancelButtonColor: 'red',
        confirmButtonText: 'Valider',
        cancelButtonText: 'Annuler'
    }).then((result) => {
        if (result.isConfirmed) {
            // Créer un objet pour stocker les données du formulaire
            const formData = new FormData(this);
            const jsonData = {};

            // Convertir FormData en JSON
            formData.forEach((value, key) => {
                jsonData[key] = value;
            });

            // Envoyer le formulaire avec fetch
            fetch(this.action, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json' // Définir le type de contenu comme JSON
                },
                body: JSON.stringify(jsonData) // Convertir l'objet JSON en chaîne
            })
            .then(response => {
                if (response.ok) {
                    // Afficher une alerte de succès
                    Swal.fire({
                        icon: 'success',
                        title: 'Utilisateur créé',
                        text: 'L\'utilisateur a été créé avec succès.',
                    }).then(() => {
                        window.location.href = '/login'; // Rediriger après confirmation
                    });
                } else {
                    // Afficher une alerte d'erreur
                    Swal.fire({
                        icon: 'error',
                        title: 'Erreur',
                        text: 'Une erreur s\'est produite lors de la création de l\'utilisateur.',
                    });
                }
            })
            .catch(error => {
                // Afficher une alerte d'erreur en cas d'échec de la requête
                Swal.fire({
                    icon: 'error',
                    title: 'Erreur',
                    text: 'Une erreur s\'est produite. Veuillez réessayer.',
                });
            });
        }
    });
};