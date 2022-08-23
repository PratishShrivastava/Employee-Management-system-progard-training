package com.EmployeeManagementSystem.EmployeeManagementSystem.Config;

import com.EmployeeManagementSystem.EmployeeManagementSystem.Repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("userSecurity")
public class UserSecurity {

    @Autowired
    EmployeeRepo userRepo;

    public boolean hasUserId(Authentication authentication, Integer userId) {

        int userID=userRepo.findByEmail(authentication.getName()).get().getEmployeeId();
        //System.out.println(userId+"  "+userID);
        if(userID==userId) {
            return true;
        }

        return false;

    }
}
