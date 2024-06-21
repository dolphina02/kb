package com.kb.shop.controller;

import com.kb.shop.domain.User;
import com.kb.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PutMapping("/change/{id}")
    public String changePassword (@PathVariable Long id,@RequestParam String oldPassword,@RequestParam String newPassword) {
        String returnMsg = userService.checkIdAndChangePassword(id, oldPassword, newPassword);
        return returnMsg;
    }

    @GetMapping("/check/id")
    @ResponseBody // 만일 해당 어노테이션이 없으면 returnValue.html 을 찾으려고 함
    public String checkIdExist (@RequestParam Long id) {
        if (userService.checkIdExist(id)) {
            return "ID exist!";
        }
        else return "ID doesn't exist!";
    }

    @GetMapping("/check/username")
    @ResponseBody // 만일 해당 어노테이션이 없으면 returnValue.html 을 찾으려고 함
    public String checkIdExist (@RequestParam String username) {
        boolean exists = userService.checkUsernameExist(username);
        return exists ? "Username : "+ username+" exists!" : "Username : " + username +" doesn't exist!";
    }



//
//    @GetMapping("/check/Id")
//    public String checkIdExist(@RequestParam("id") Long id, Model model) {
//        boolean exists = userService.checkIdExist(id);
//        if (exists) {
//            model.addAttribute("message", "ID exists!");
//        } else {
//            model.addAttribute("message", "ID doesn't exist!");
//        }
//        return "result"; // 결과를 보여줄 템플릿 이름
//    }

    @PostMapping("/register")
    public String register(User user, RedirectAttributes redirectAttributes) {
        User registeredUser = userService.registerUser(user);
        if (registeredUser != null) {
            redirectAttributes.addFlashAttribute("message", "Registration successful!");
            redirectAttributes.addFlashAttribute("messageType", "success");
        } else {
            redirectAttributes.addFlashAttribute("message", "Registration failed!");
            redirectAttributes.addFlashAttribute("messageType", "danger");
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model) {
        if (error != null) {
            model.addAttribute("message", "Invalid username or password!");
            model.addAttribute("messageType", "danger");
        }
        if (logout != null) {
            model.addAttribute("message", "You have been logged out.");
            model.addAttribute("messageType", "success");
        }
        return "login";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "redirect:/login?logout";
    }
}
