package com.example.cst391springmilestone.data;

import com.example.cst391springmilestone.models.LoginModel;
import com.example.cst391springmilestone.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDataService {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<UserModel> findAllUsers(){
        String sql = "SELECT * FROM 391MILESTONE.USER";
        List<UserModel> users = new ArrayList<>();
        try {
            SqlRowSet srs = jdbcTemplate.queryForRowSet(sql);
            while (srs.next()){
                users.add(new UserModel(srs.getInt("USER_ID"), srs.getString("FIRST_NAME"),
                        srs.getString("LAST_NAME"), srs.getString("EMAIL"), srs.getString("PHONE_NUMBER"),
                        srs.getString("USERNAME"), srs.getString("PASSWORD"), srs.getBoolean("IS_ACTIVE"),
                        srs.getInt("ROLE")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }

    public UserModel findUserById(int id){
        String sql = "SELECT * FROM 391MILESTONE.USER WHERE USER_ID = ?";
        UserModel user = new UserModel();
        try {
            SqlRowSet srs = jdbcTemplate.queryForRowSet(sql, id);
            while (srs.next())
            {
                user.setUserId(id);
                user.setFirstName(srs.getString("FIRST_NAME"));
                user.setLastName(srs.getString("LAST_NAME"));
                user.setEmail(srs.getString("EMAIL"));
                user.setPhoneNumber(srs.getString("PHONE_NUMBER"));
                user.setUsername(srs.getString("USERNAME"));
                user.setPassword(srs.getString("PASSWORD"));
                user.setRoleId(srs.getInt("ROLE"));
                user.setActive(srs.getBoolean("IS_ACTIVE"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public boolean createUser(UserModel user){
        String sql = "INSERT INTO 391MILESTONE.USER(FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, USERNAME, PASSWORD) VALUES (?,?,?,?,?,?)";
        try {
            jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber(),
                    user.getUsername(), user.getPassword());
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateUser(UserModel user, int userId) {
        String sql = "UPDATE 391MILESTONE.USER SET FIRST_NAME = ?, LAST_NAME = ?, EMAIL = ?, PHONE_NUMBER = ?, USERNAME = ?, PASSWORD = ? WHERE USER_ID = ?";
        try {
            jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber(),
                    user.getUsername(), user.getPassword(), userId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteUser(int userId){
        String sql = "DELETE FROM 391MILESTONE.USER WHERE USER_ID = ?";
        try {
            jdbcTemplate.update(sql, userId);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
