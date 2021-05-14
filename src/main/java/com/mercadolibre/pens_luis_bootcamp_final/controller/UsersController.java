package com.mercadolibre.pens_luis_bootcamp_final.controller;

import com.mercadolibre.pens_luis_bootcamp_final.exceptions.ApiException;
import com.mercadolibre.pens_luis_bootcamp_final.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {
    private UserService service;

    public UsersController(UserService userService){
        this.service = userService;
    }

    @PostMapping("/new")
    public ResponseEntity createUser(@RequestParam(name="username") String username,
                                   @RequestParam(name="password") String password) throws Exception {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String currentUser = ((UserDetails)principal).getUsername();
        if (currentUser.equals(username))
            throw new ApiException(HttpStatus.BAD_REQUEST.name(), "Only admins can create users", HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity(service.createUser(username,password), HttpStatus.OK);
    }

    @PatchMapping("/changeRole")
    public ResponseEntity changeRole(@RequestParam(name="username") String username) throws Exception{
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String currentUser = ((UserDetails)principal).getUsername();
        if (currentUser.equals(username))
            throw new ApiException(HttpStatus.BAD_REQUEST.name(), "Cant change your own role", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity(service.changeRole(username, currentUser), HttpStatus.OK);
    }
}
