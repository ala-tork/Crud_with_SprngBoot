package com.mycompany.Services;

import com.mycompany.Entitys.UserEntity;
import com.mycompany.Expectations.UserNotFoundExpectation;
import com.mycompany.Repositorys.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserServices {
    private UserRepository repo;

    public List<UserEntity> listAll(){
        return (List<UserEntity>) repo.findAll();
    }

    public void save(UserEntity user) {
        repo.save(user);
    }
    public UserEntity get(Integer id) throws UserNotFoundExpectation {
      Optional<UserEntity> result= repo.findById(id);
      if(result.isPresent()){
          return result.get();
      }
      throw new UserNotFoundExpectation("could not found aby user with ID " + id);
    }
    public void deletebyId(Integer id) throws UserNotFoundExpectation {
        Long count =repo.countById(id);
        if(count == null || count==0){
            throw new UserNotFoundExpectation("could not Found user with ID : "+id);
        }
        repo.deleteById(id);
    }
}
