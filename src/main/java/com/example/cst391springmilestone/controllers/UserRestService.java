package com.example.cst391springmilestone.controllers;

import com.example.cst391springmilestone.business.UserBusinessService;
import com.example.cst391springmilestone.models.UserModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestService {

    @Autowired
    private UserBusinessService userBusinessService;

    @GetMapping("/")
    @ApiOperation(value = "Gets all users")
    public ResponseEntity<?> findAllUsers(){
        try {
            List<UserModel> users = userBusinessService.findAllUsers();
            if(users == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else {
                return new ResponseEntity<>(users, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get user by ID")
    public ResponseEntity<?> findUserById(@PathVariable ("id") Integer userId){
        try {
            UserModel user = userBusinessService.findUserById(userId);
            if(user == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else {
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    @ApiOperation(value = "Create a new user")
    public ResponseEntity<?> createUser(@RequestBody UserModel user){
        try {
            boolean status = userBusinessService.createUser(user);
            if(status){
                return new ResponseEntity<>(HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            }

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}")
    @ApiOperation(value = "Update a user")
    public ResponseEntity<?> updateUser(@RequestBody UserModel user, @PathVariable ("id") Integer userId){
        try {
            boolean status = userBusinessService.updateUser(user, userId);
            if(status){
                return new ResponseEntity<>(HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            }

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a user")
    public ResponseEntity<?> deleteUser(@PathVariable ("id") Integer userId){
        try {
            boolean status = userBusinessService.deleteuser(userId);
            if(status){
                return new ResponseEntity<>(HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            }

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
