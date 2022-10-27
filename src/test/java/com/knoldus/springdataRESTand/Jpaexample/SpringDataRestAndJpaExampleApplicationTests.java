package com.knoldus.springdataRESTand.Jpaexample;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knoldus.springdataRESTand.Jpaexample.controller.UserController;
import com.knoldus.springdataRESTand.Jpaexample.entity.User;
import com.knoldus.springdataRESTand.Jpaexample.repository.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import static java.lang.reflect.Array.get;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sun.nio.cs.Surrogate.is;


@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class SpringDataRestAndJpaExampleApplicationTests {
	private User user;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserRepository userRepository;

	@Before
	public void setUp()
	{
		user = new User();
		user.setId(1L);
		user.setName("Virat");
		user.setTeamName("RCB");
		user.setSalary(15000);
	}

	@Test
	public void userCreationTest() throws Exception {
		when(userRepository.findByName(user.getName())).thenReturn(user);
		ObjectMapper mapper = new ObjectMapper();
		String transactionString = mapper.writeValueAsString(user);

		MvcResult result = mockMvc.perform(post("/users/load").content(transactionString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String responseUser = result.getResponse().getContentAsString();

		JSONAssert.assertEquals(responseUser, result.getResponse().getContentAsString(), false);
	}


	@Test
	public void findUserByNameTest() throws Exception {
		when(userRepository.findByName(user.getName())).thenReturn(user);

		MvcResult result = mockMvc.perform((RequestBuilder) get("/users/{name}", Integer.parseInt("Virat"))).andExpect(status().isOk()).andReturn();

		JSONAssert.assertEquals(new ObjectMapper().writeValueAsString(user),
				result.getResponse().getContentAsString(), false);
	}

	@Test
	public void findUserByNameWithWrongNameTest() throws Exception {
		when(userRepository.findByName(user.getName())).thenReturn(user);

		MvcResult result = mockMvc.perform((RequestBuilder) get("/users/{name}", Integer.parseInt("Asif"))).andExpect(status().isOk()).andReturn();

		assertThat(String.valueOf(result.getResponse().getContentAsString().length()),is(0));
	}


}
