package com.xyalgo.bookmyshow.Controllers;

import com.xyalgo.bookmyshow.Services.TheaterService;
import com.xyalgo.bookmyshow.model.DataModel;
import com.xyalgo.bookmyshow.theater.Theater;
import com.xyalgo.bookmyshow.theater.TheaterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TheaterController {
    @Autowired
    TheaterService theaterService;

    @Autowired
    TheaterRepo theaterRepo;

    @PostMapping("/add/theater")
    public ModelAndView addTheater(@ModelAttribute DataModel model) {
        Theater theater = new Theater();
        theater.setTheaterName(model.getTheaterName());
        theater.setCapacity(model.getCapacity());
        theaterRepo.save(theater);
        return new ModelAndView("redirect:/admin");
    }

    @GetMapping("/portal")
    public ModelAndView theaters() {
        return new ModelAndView("addtheater/add_theaters");
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateTheater(@PathVariable("id") int id) {
        return new ModelAndView("addtheater/update_theater")
                .addObject("theater", theaterService.getTheaterById(id));
    }

    @GetMapping("/remove/{id}")
    public ModelAndView removeTheater(@PathVariable("id") int id) {
        theaterService.removeTheater(id);
        return new ModelAndView("redirect:/admin");
    }

    @PostMapping("/update/theater")
    public ModelAndView updateTheater(@ModelAttribute Theater theater) {
        theaterService.updateTheater(theater);
        return new ModelAndView("redirect:/admin");
    }

    @GetMapping("/theaters")
    public ModelAndView getTheaterList() {
        return new ModelAndView("theater_table")
                .addObject("theaterList", theaterService.getTheaters());
    }
}


