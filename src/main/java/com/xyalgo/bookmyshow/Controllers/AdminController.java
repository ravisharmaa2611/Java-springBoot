package com.xyalgo.bookmyshow.Controllers;

import com.xyalgo.bookmyshow.Services.MovieService;
import com.xyalgo.bookmyshow.Services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class AdminController {
    @Autowired
    MovieService movieService;
    @Autowired
    TheaterService theaterService;
    @Autowired
    HttpSession httpSession;


    @GetMapping("/admin")
    public ModelAndView movieList() {
        ModelAndView modelAndView = new ModelAndView("tables");

        modelAndView.addObject("movieList", movieService.findMovieList());
        modelAndView.addObject("theaterList", theaterService.getTheaters());
//        modelAndView.addObject("theaterList2",movieService.findMovieList().get(0).getTheaters());
        return modelAndView;
    }

    @GetMapping(value = "/adminlogout")
    public ModelAndView adminlogout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/login");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView adminLogin() {
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }

}
