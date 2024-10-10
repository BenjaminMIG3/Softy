package com.softy.softy.API;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

import com.softy.softy.SoftyApplication;
import com.softy.softy.Entite.Role;


@Controller
@RequestMapping("/api")
public class Utils {
    @GetMapping("/get_all_role")
    public ResponseEntity<List<Role>> fetch_all_role(){
        return ResponseEntity.ok(SoftyApplication.monModel.getAllRole());
    }
}
