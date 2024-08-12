package com.xyalgo.bookmyshow.Controllers;

import com.xyalgo.bookmyshow.Commons.FileUploadUtil;
import com.xyalgo.bookmyshow.Services.MovieService;
import com.xyalgo.bookmyshow.Services.UserService;
import com.xyalgo.bookmyshow.cinema.MovieTheaterRepo;
import com.xyalgo.bookmyshow.model.DataModel;
import com.xyalgo.bookmyshow.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    MovieService movieService;
    @Autowired
    MovieTheaterRepo cinemaRepo;
    @Autowired
    HttpSession httpSession;

    @GetMapping("/")
    public ModelAndView userHome() throws ParseException {
        ModelAndView modelAndView = new ModelAndView("WebContent/index");
        modelAndView.addObject("movieList", movieService.findMovieList());
        modelAndView.addObject("nowPlayingMovies", movieService.nowPlayingMovies());
        modelAndView.addObject("openingThisWeekMovies", movieService.openingThisWeekMovies());
        modelAndView.addObject("comingSoonMovies", movieService.comingSoonMovies());
        if (httpSession.getAttribute("email") != null)
            modelAndView.addObject("user", userService.getUserByEmail(httpSession.getAttribute("email").toString()));
        return modelAndView;
    }

    @GetMapping("/user/logout")
    public ModelAndView userLogout() {
        userService.userLogout();
        return new ModelAndView("redirect:/");
    }


    @RequestMapping(value = "/user/usersignup", method = RequestMethod.POST)
    public ModelAndView userRegister(@ModelAttribute UserEntity userEntity) {
        userService.userSignin(userEntity);
        userService.userLogin(userEntity);
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        return modelAndView;
    }

    @PostMapping("/user/login")
    public ModelAndView userLogin(@ModelAttribute UserEntity userEntity) {
        userService.userLogin(userEntity);
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        modelAndView.addObject("isUserLoggedIn", userService.isUserLoggedIn());
        return modelAndView;
    }

    @GetMapping("/profile")
    public ModelAndView userProfile()  {
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        modelAndView.addObject("msg", "User Logged out! please login!");
        if (userService.isUserLoggedIn()) {
            modelAndView = new ModelAndView("WebContent/profile");
            modelAndView.addObject("user", userService.getUserByEmail(httpSession.getAttribute("email").toString()));
        }
        return modelAndView;


    }
    @PostMapping("/uploadPhoto")
    public ModelAndView userPhoto(@RequestParam("userPhoto") MultipartFile userPhoto,@ModelAttribute DataModel dataModel) throws IOException {
        UserEntity userEntity =userService.getUserById(dataModel.getUserId());
        String fileName = "";

            if (!userPhoto.isEmpty()){

                fileName = fileName + StringUtils.cleanPath(userPhoto.getOriginalFilename());
                String uploadDir = "src/main/resources/static/user_photos";
                FileUploadUtil.saveFile(uploadDir, fileName, userPhoto);
                System.out.println("gone through if******************"+ fileName);
                userEntity.setImage(fileName);
                userService.userSignin(userEntity);
            }
           return new ModelAndView("redirect:/profile");
    }
}
