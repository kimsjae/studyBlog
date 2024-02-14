package shop.mtcoding.blog.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor // final이 붙은 것 생성자 만들어줌
public class UserController {

    private final UserRepository userRepository; // final 변수는 반드시 초기화 돼야함
    private final HttpSession session;

    @PostMapping("/join")
    public String join(UserRequest.JoinDTO joinDTO) {

        userRepository.save(joinDTO); // 모델에 위임하기

        return "redirect:/loginForm";
    }

    @PostMapping("/login") // 조회이지만 민감한 정보는 body로 보내기 때문에 로그인만 예외로 POST
    public String login(UserRequest.LoginDTO loginDTO) {

        if (loginDTO.getUsername().length() < 3) { // 유효성 검사
            return "error/400";
        }

        User user = userRepository.findByUsernameAndPassword(loginDTO);

        if (user == null) { // 조회 안 됨
            return "error/401";
        } else { // 조회 됨
            session.setAttribute("sessionUser", user); // 세션에 담는다
        }

        return "redirect:/"; // 컨트롤러가 존재하면 무조건 redirect
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }

    @GetMapping("/logout")
    public String logout() {

        session.invalidate();

        return "redirect:/";
    }
}
