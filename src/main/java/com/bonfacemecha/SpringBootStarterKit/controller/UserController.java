package com.bonfacemecha.SpringBootStarterKit.controller;

import com.bonfacemecha.SpringBootStarterKit.model.UserPrincipal;
import com.bonfacemecha.SpringBootStarterKit.model.Users;
import com.bonfacemecha.SpringBootStarterKit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
// @RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/welcome")
    public String home() {
        return "Welcome Bonface ";
    }

     @GetMapping("/users")
    public ResponseEntity<List<Users>> allUsers() {
        List <Users> users = service.allUsers();

        return ResponseEntity.ok(users);
    }

//return the authenticated user from the JWT token provided
    @GetMapping("/me")
    public ResponseEntity<Users> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Users currentUser = userPrincipal.getUser(); // <-- adjust method name as needed
        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/principal-name")
    public String getCurrentUser(Principal principal) {
        return "Hello, " + principal;
    }

}
