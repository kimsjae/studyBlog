package shop.mtcoding.blog.love;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Table(name="love_tb")
@Data
@Entity // 테이블 생성하기 위해 필요한 어노테이션
public class Love { // User 1 -> Board N
    @Id // PK 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment 전략
    private Integer id;
    private Integer boardId;
    private Integer userId;
    private LocalDateTime createdAt;
}