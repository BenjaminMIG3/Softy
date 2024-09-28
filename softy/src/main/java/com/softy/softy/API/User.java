package com.softy.softy.API;

import java.util.Map;

import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.softy.softy.SoftyApplication;
import com.softy.softy.Entite.Utilisateur;

import ch.qos.logback.classic.pattern.Util;
import jakarta.servlet.http.HttpSession;

@Controller
public class User {
    
    @PostMapping("/api/user/connexion")
    public String tryUserConnexion(@RequestParam String login, @RequestParam String password, HttpSession session, Model model) {

        if (SoftyApplication.monModel.checkUserConnexion(login, password)) {
            // Ajouter l'utilisateur à la session
            session.setAttribute("user", SoftyApplication.monModel.getUser(login, password));
            return "redirect:/"; 
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/api/user/deconnexion")
    public String deconnexionUser(HttpSession session, Model model) {
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/api/user/creer")
    @ResponseBody
    public ResponseEntity<Void> tryCreateUser(@RequestBody Map<String, Object> userData) {
        // Traitement des données
        try {
            Utilisateur newUser = new Utilisateur(
                (String) userData.get("nom"), 
                (String) userData.get("prenom"), 
                (String) userData.get("login"), 
                (String) userData.get("password"), 
                (String) userData.get("email"), 
                (String) userData.get("role")
            );

            if(SoftyApplication.monModel.insertIntoUser(newUser)){
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (JSONException e) {
            System.err.println("Erreur lecture formulaire création user json : " + e);
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/api/user/check-session")
    @ResponseBody
    public ResponseEntity<Void> checkSession(HttpSession session, Model model) {
        System.out.println("Je cherche si le user est encore en session");
        if (session.getAttribute("user") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Session expirée
        }
        return ResponseEntity.ok().build(); // Session valide
    }
}
