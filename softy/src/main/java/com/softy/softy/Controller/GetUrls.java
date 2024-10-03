package com.softy.softy.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.softy.softy.Entite.Utilisateur;
import com.softy.softy.Utils.SessionUtils;

import jakarta.servlet.http.HttpSession;

@Controller
public class GetUrls {

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        // Récupère l'utilisateur de la session
        Utilisateur user = SessionUtils.checkIfSessionIsValid(session, model);
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

    @GetMapping("/caisse/menu")
    public String caisseMenu(HttpSession session, Model model){
        return "index_caisse";
    }

}
