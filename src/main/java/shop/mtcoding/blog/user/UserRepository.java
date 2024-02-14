package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository // IoC에 new
public class UserRepository {

    // DB에 접근할 수 있는 매니저 객체
    // 스프링이 만들어서 IoC에 넣어둔다.
    // DI에서 꺼내 쓰기만 하면 된다.
    private EntityManager em;

    // 생성자 주입 (DI)
    public UserRepository(EntityManager em) {
        this.em = em;
    }

    @Transactional // DB에 write할 때는 필수
    public void save(UserRequest.JoinDTO joinDTO) {
        Query query = em.createNativeQuery("insert into user_tb(username, password, email, created_at) values(?,?,?,now())");
        query.setParameter(1, joinDTO.getUsername());
        query.setParameter(2, joinDTO.getPassword());
        query.setParameter(3, joinDTO.getEmail());
        query.executeUpdate();
    }

    public User findByUsernameAndPassword(UserRequest.LoginDTO loginDTO) {
        Query query = em.createNativeQuery("select * from user_tb where username=? and password=?", User.class);
        query.setParameter(1, loginDTO.getUsername());
        query.setParameter(2, loginDTO.getPassword());

        User user = (User) query.getSingleResult();
        return user;
    }
}
