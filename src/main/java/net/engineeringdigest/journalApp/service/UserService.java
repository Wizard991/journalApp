package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password4j.BcryptPassword4jPasswordEncoder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

@Component
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    public void saveEntry(User user) {
        saveNewUser(user);
    }



    public boolean saveNewUser(User user) {
        try {

            user.setPassword(
                    passwordEncoder.encode(user.getPassword())
            );

            user.setRoles(Arrays.asList("USER"));

            userRepository.save(user);

            return true;

        } catch (Exception e) {

            log.info("hahhahahahahahahahaha");
//            logger.error("Error occuured for {} :",user.getUserName(),e);
//            logger.warn("hahhahahahahahahahaha");
//            logger.debug("hahhahahahahahahahaha");
//            logger.trace("hahhahahahahahahahaha");

            return false;
        }
    }

    public void saveAdmin(User user) {
        System.out.println("🔥 saveNewUser CALLED");

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        user.setRoles(Arrays.asList("USER", "ADMIN"));

        userRepository.save(user);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    //
    public Optional<User> findById(ObjectId id) {
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id) {
        userRepository.deleteById(id);
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}