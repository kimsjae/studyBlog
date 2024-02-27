package shop.mtcoding.blog.user;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "user_tb")
public class User {
    @Id // PRIMARY KEY
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    private int id;
    private String username;
    private String password;
    private String email;
    private LocalDateTime createdAt; // 카멜표기법으로 만들면 DB는 언더스코어기법으로 만들어진다.

}
