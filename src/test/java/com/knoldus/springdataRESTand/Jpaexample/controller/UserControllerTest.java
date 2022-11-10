package com.knoldus.springdataRESTand.Jpaexample.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knoldus.springdataRESTand.Jpaexample.entity.User;
import com.knoldus.springdataRESTand.Jpaexample.repository.UserRepository;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @Autowired
    private UserController userController;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link UserController#findAll()}
     */
    @Test
    void testFindAll() throws Exception {
        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/all");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link UserController#findAll()}
     */
    @Test
    void testFindAll2() throws Exception {
        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/users/all");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link UserController#findByName(String)}
     */
    @Test
    void testFindByName() throws Exception {
        User user = new User();
        user.setId(123L);
        user.setName("Name");
        user.setSalary(1);
        user.setTeamName("Team Name");
        when(userRepository.findByName((String) any())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/{name}", "Name");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":123,\"name\":\"Name\",\"teamName\":\"Team Name\",\"salary\":1}"));
    }

    /**
     * Method under test: {@link UserController#load(User)}
     */
    @Test
    void testLoad() throws Exception {
        User user = new User();
        user.setId(123L);
        user.setName("Name");
        user.setSalary(1);
        user.setTeamName("Team Name");

        User user1 = new User();
        user1.setId(123L);
        user1.setName("Name");
        user1.setSalary(1);
        user1.setTeamName("Team Name");
        when(userRepository.findByName((String) any())).thenReturn(user);
        when(userRepository.save((User) any())).thenReturn(user1);

        User user2 = new User();
        user2.setId(123L);
        user2.setName("Name");
        user2.setSalary(1);
        user2.setTeamName("Team Name");
        String content = (new ObjectMapper()).writeValueAsString(user2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/load")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":123,\"name\":\"Name\",\"teamName\":\"Team Name\",\"salary\":1}"));
    }
}

