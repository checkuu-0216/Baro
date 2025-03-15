package com.example.Baro.domain.user.entity;

import com.example.Baro.common.Timestamped;
import com.example.Baro.domain.user.enums.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;
    private String nickname;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public User(String username, String password, String nickname, UserRole userRole) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.userRole = userRole;
    }

    public void updateRole(UserRole newRole) {
        this.userRole = newRole;
    }
}
