package com.softy.softy.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.softy.softy.Entite.Utilisateur;
import jakarta.servlet.http.HttpSession;

@Controller
public class GetUrls {

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        // Vérifie si la session est null ou si l'utilisateur n'est pas connecté
        if (session == null || session.getAttribute("user") == null) {
            System.out.println("Aucun utilisateur connecté. Redirection vers la page de connexion.");
            // return "redirect:/login"; // Redirection vers la page de connexion
        }
        // Récupère l'utilisateur de la session
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        System.out.println("Dans la session : " + user); // Affiche les détails de l'utilisateur
        // Optionnel : ajouter l'utilisateur au modèle pour la vue
        model.addAttribute("user", user);
        return "index"; // Renvoie le nom de la vue
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/signin")
    public String signin(){
        return "signin";
    }
}
