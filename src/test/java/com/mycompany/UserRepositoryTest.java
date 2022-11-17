package com.mycompany;

import com.mycompany.Entitys.UserEntity;
import com.mycompany.Repositorys.UserRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
    @Autowired
    private UserRepository repo;
    @Test
    public void testAddNew(){
        UserEntity user=new UserEntity();
        user.setEmail("ala@gmail.com");
        user.setFirstname("Ala");
        user.setLastname("Torkhani");
        user.setPassword("1234567");
        UserEntity SavedUser=repo.save(user);
        Assertions.assertThat(SavedUser).isNotNull();
        Assertions.assertThat(SavedUser.getId()).isGreaterThan(0);
    }
    @Test
    public void testlistall(){
        Iterable<UserEntity> users=repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);
        for(UserEntity user :users){
            System.out.println(user);
        }
    }
    @Test
    public void testUpdate(){
        Integer userId=1;
        Optional<UserEntity> optionalUser=repo.findById(userId);
        UserEntity user =optionalUser.get();
        user.setLastname("Tork");
        repo.save(user);
        UserEntity updatedUser=repo.findById(userId).get();
        Assertions.assertThat(updatedUser.getLastname()).isEqualTo("Tork");
    }
    @Test
    public void testGet(){
        Integer useId=1;
        Optional<UserEntity> optionalUser=repo.findById(useId);
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());
    }
    @Test
    public void testdelet(){
        Integer userId=1;
        repo.deleteById(userId);
        Optional<UserEntity> optionalUser=repo.findById(userId);
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());
    }
}
