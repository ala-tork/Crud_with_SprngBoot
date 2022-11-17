package com.mycompany.Entitys;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="users")
@Getter
@Setter
@ToString
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false,unique = true,length = 45)
    private String email;
    @Column(length = 15,nullable = false)
    private String password;
    @Column(length = 45,nullable = false,name = "first_name")
    private String firstname;
    @Column(nullable = false,name = "last_name",length = 45)
    private String lastname;
    private boolean enable;
}
