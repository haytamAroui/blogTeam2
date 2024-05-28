package be.intecbrussel.blogteam2.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {
    @GetMapping()
    public String Home() {
        return "home"; // This should match the admin template name
    }
    @GetMapping("/admin/adminHome")
    public String adminHome() {
        return "adminHome"; // This should match the admin template name
    }

    @GetMapping("/userHome")
    public String userHome() {
        return "userHome"; // This should match the user template name
    }

    @GetMapping("/login")
    public String login(Authentication authentication) {
        if (authentication != null) {
            return "redirect:/";
        }
        return "login";
    }


}
