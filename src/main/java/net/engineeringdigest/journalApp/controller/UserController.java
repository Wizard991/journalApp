package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import net.engineeringdigest.journalApp.service.UserService;
import net.engineeringdigest.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {

        try {

            Authentication authentication =
                    SecurityContextHolder.getContext().getAuthentication();

            String userName = authentication.getName();

            User userInDb = userService.findByUserName(userName);

            System.out.println("Logged in user: " + userName);
            System.out.println("User from DB: " + userInDb);
            System.out.println("Incoming email: " + user.getEmail());

            userInDb.setEmail(user.getEmail());

            userRepository.save(userInDb);

            return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);

        } catch (Exception e) {

            e.printStackTrace(); // IMPORTANT

            return new ResponseEntity<>(e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping
    public ResponseEntity<?> deleteUserById() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        userRepository.deleteByUserName(authentication.getName());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greeting() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String userName = authentication.getName();

        WeatherResponse weather = weatherService.getWeather("Delhi");

        String greeting = "";

        if (weather != null && weather.getCurrent() != null) {
            greeting = ", Weather feels like " + weather.getCurrent().getFeelslike() + "°C";
        }

        return new ResponseEntity<>("Hi " + userName + greeting, HttpStatus.OK);
    }
}



