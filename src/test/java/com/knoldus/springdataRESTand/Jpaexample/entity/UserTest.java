package com.knoldus.springdataRESTand.Jpaexample.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UserTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link User}
     */
    @Test
    void testConstructor() {
        User actualUser = new User();
        actualUser.setId(123L);
        actualUser.setName("Name");
        actualUser.setSalary(1);
        actualUser.setTeamName("Team Name");
        assertEquals(123L, actualUser.getId().longValue());
        assertEquals("Name", actualUser.getName());
        assertEquals(1, actualUser.getSalary().intValue());
        assertEquals("Team Name", actualUser.getTeamName());
    }
}

