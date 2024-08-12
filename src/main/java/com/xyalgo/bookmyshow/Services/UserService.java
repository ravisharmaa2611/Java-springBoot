package com.xyalgo.bookmyshow.Services;

import com.xyalgo.bookmyshow.user.UserEntity;
import com.xyalgo.bookmyshow.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    HttpSession httpSession;

    public void userSignin(UserEntity sign) {
        userRepo.save(sign);
    }

    public boolean isUserLoggedIn() {
        return httpSession.getAttribute("email") != null;
    }

    public void userLogin(UserEntity login) {
        Optional<UserEntity> userEntity = userRepo.findByEmail(login.getEmail());
        if (userEntity.isPresent() && userEntity.get().getPassword().equals(login.getPassword())) {
            httpSession.setAttribute("email", login.getEmail());
        }
    }

    public void userLogout() {
        httpSession.setAttribute("email", null);
    }

    public UserEntity getUserByEmail(String email) {
        return userRepo.findByEmail(email).get();
    }

    public UserEntity getUserById(long userId) {
        return  userRepo.findById(userId).get();
    }
}
