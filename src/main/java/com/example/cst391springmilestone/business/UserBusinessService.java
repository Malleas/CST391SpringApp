package com.example.cst391springmilestone.business;

import com.example.cst391springmilestone.data.UserDataService;
import com.example.cst391springmilestone.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBusinessService {

    @Autowired
    private UserDataService service;

    public UserBusinessService(UserDataService service) {
        this.service = service;
    }

    public List<UserModel> findAllUsers(){
        return service.findAllUsers();
    }

    public UserModel findUserById(int userId){
        return service.findUserById(userId);
    }

    public boolean createUser(UserModel userModel){
        return service.createUser(userModel);
    }

    public boolean updateUser(UserModel userModel, int userId){
        return service.updateUser(userModel, userId);
    }

    public boolean deleteuser(int userId){
        return service.deleteUser(userId);
    }

}
