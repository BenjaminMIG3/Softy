package com.softy.softy.Utils;

import org.springframework.ui.Model;
import com.softy.softy.Entite.Utilisateur;
import jakarta.servlet.http.HttpSession;

public class SessionUtils {
    
    public static Utilisateur checkIfSessionIsValid(HttpSession session, Model model){
        if(session != null){
            return (Utilisateur) session.getAttribute("user");
        }
        return null;
    }

}
