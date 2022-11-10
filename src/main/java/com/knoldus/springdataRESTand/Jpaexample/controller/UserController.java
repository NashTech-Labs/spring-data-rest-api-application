package com.knoldus.springdataRESTand.Jpaexample.controller;

import com.knoldus.springdataRESTand.Jpaexample.entity.User;
import com.knoldus.springdataRESTand.Jpaexample.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    /** The JPA repository */
    @Autowired
    private UserRepository userRepository;

    /**
     * Used to fetch all the users from DB
     *
     * @return list of {@link User}
     */
    @GetMapping(value = "/all")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Used to find and return a user by name
     *
     * @param name refers to the name of the user
     * @return {@link User} object
     */
    @GetMapping(value = "/{name}")
    public User findByName(@PathVariable final String name){
        return userRepository.findByName(name);
    }

    /**
     * Used to create a User in the DB
     *
     * @param users refers to the User needs to be saved
     * @return the {@link User} created
     */
    @PostMapping(value = "/load")
    public User load(@RequestBody final User users) {
        userRepository.save(users);
        return userRepository.findByName(users.getName());

    }
}
