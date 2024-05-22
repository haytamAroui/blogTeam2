package be.intecbrussel.blogteam2;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/")
    public String home(Model model,
                       @AuthenticationPrincipal OAuth2User user) {
        if (user != null) {
            String name = user.getAttribute("name");
            String email = user.getAttribute("email");
            model.addAttribute("name", name);
            model.addAttribute("email", email);
        }
        return "index";  // Ensure you have an index.html template in the correct directory
    }

    @GetMapping("/secured")
    public String secured() {

        return "Sing_in_page";
    }
}
