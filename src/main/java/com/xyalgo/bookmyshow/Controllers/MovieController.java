package com.xyalgo.bookmyshow.Controllers;

import com.xyalgo.bookmyshow.Commons.FileUploadUtil;
import com.xyalgo.bookmyshow.Services.MovieService;
import com.xyalgo.bookmyshow.Services.TheaterService;
import com.xyalgo.bookmyshow.cinema.MovieTheaterShows;
import com.xyalgo.bookmyshow.cinema.MovieTheaterShowsRepo;
import com.xyalgo.bookmyshow.model.DataModel;
import com.xyalgo.bookmyshow.movies.Movies;
import com.xyalgo.bookmyshow.shows.ShowRepo;
import com.xyalgo.bookmyshow.theater.Theater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;


@RestController
public class MovieController {

    @Autowired
    MovieService movieService;
    @Autowired
    TheaterService theaterService;
    @Autowired
    ShowRepo showRepo;
    @Autowired
    MovieTheaterShowsRepo movieTheaterShowsRepo;

    @GetMapping("/addmovie")
    public ModelAndView showMovie() {
        ModelAndView modelAndView = new ModelAndView("/movie/addmovie");
        modelAndView.addObject("movieList", movieService.findMovieList());
        modelAndView.addObject("theaterList", theaterService.getTheaters());
        modelAndView.addObject("showsList", showRepo.findAll());
        return modelAndView;
    }

    @PostMapping(value = "/adding")
    public ModelAndView addMovie(@RequestParam("posters") MultipartFile[] posters, @ModelAttribute DataModel model) throws IOException {
        Movies movies = new Movies();
        movies.setMovieName(model.getMovieName());
        movies.setTicketPrice(model.getTicketPrice());
        movies.setReleaseDate(model.getReleaseDate());
        movies.setTheaters(model.getTheaters());
        String fileName = "";
        for (MultipartFile multipartFile : posters) {
            if (fileName.equals(""))
                fileName = fileName + StringUtils.cleanPath(multipartFile.getOriginalFilename());
            else
                fileName = fileName + "," + StringUtils.cleanPath(multipartFile.getOriginalFilename());

            String uploadDir = "src/main/resources/static/movie_photos";
            FileUploadUtil.saveFile(uploadDir, StringUtils.cleanPath(multipartFile.getOriginalFilename()), multipartFile);
        }
        movies.setImage(fileName);
        movieService.addMovie(movies);
        int count = -1;
        for (Theater item : model.getTheaterIdList()) {
            System.out.println("theater id: --------------------" + item.getTheaterId());
            count++;
            MovieTheaterShows movieTheaterSows = new MovieTheaterShows();
            movieTheaterSows.setMovieId(movies.getMovieId());
            movieTheaterSows.setTheaterId(item.getTheaterId());
            movieTheaterSows.setShowId(model.getShowsList().get(count).getShowId());
            System.out.println("movie_theater_shows: " + movieTheaterSows);
            movieTheaterShowsRepo.save(movieTheaterSows);
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/addmovie");
        return modelAndView;

    }

    @GetMapping(value = "/movie/{id}")
    public ModelAndView deleteMovieById(@ModelAttribute Movies movies, @PathVariable Long id) {
        movieService.deleteProById(id);
        ModelAndView mv = new ModelAndView("redirect:/addmovie");
        return mv;
    }


    @RequestMapping(value = "/update/movie/{id}")
    public ModelAndView updateMovieInTable(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("/movie/updatemovie");
        modelAndView.addObject("movie", movieService.getMovieById(id));
        return modelAndView;
    }


    @PostMapping(value = "/update")
    public ModelAndView saveUpdate(@ModelAttribute Movies movies) {
        System.out.println("movieid=:" + movies.getMovieId());
        ModelAndView modelAndView = new ModelAndView("redirect:/addmovie");
        movieService.UpdateMovieById(movies);
        return modelAndView;
    }
}
