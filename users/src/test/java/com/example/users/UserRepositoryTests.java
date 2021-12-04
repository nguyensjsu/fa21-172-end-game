package com.example.endgame;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests 
{
    @Autowired
    private UserRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser()
    {
        User user = new User();
        user.setEmail("sam@sjsu.edu");
        user.setPassword("sam123");
        user.setUserName("sam123");

        User savedUser = repository.save(user);

        User existingUser = entityManager.find(User.class, savedUser.getId());

        assertThat(existingUser.getEmail()).isEqualTo(user.getEmail());
    }

}
