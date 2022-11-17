package com.mycompany.Repositorys;

import com.mycompany.Entitys.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository <UserEntity,Integer> {
    public long countById(Integer id);
}
