package shop.mtcoding.blog._core.util;

import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

public class BCryptTest {

    @Test
    public void gensalt_test() {
        String salt = BCrypt.gensalt();
        System.out.println(salt);
    }

    @Test
    public void hashpw_test() {
        String rawPassword = "1234";
        String encPassword = BCrypt.hashpw(rawPassword, BCrypt.gensalt());
        System.out.println(encPassword);
    }
    //$2a$10$XxeKJAf2ec/bDs3hglwTR.6GkfvCuI1./Z7SBfk0nxwJkJmvKifN2
    //$2a$10$fqZTAtv8JgPmVB5tzBkmd.jBa9WDkFKcKj48OvfxOgbsEEeH1t.c.
}
