package andrew.projects.reacon.controllers;

import andrew.projects.reacon.entities.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class MainController {
    @RequestMapping("/")
    public String main(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("userData", user);
        return "index";
    }
    @GetMapping("/messages")
    public String messages(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("idUser", user.getIdUser());
        model.addAttribute("name", user.getFullName());
        model.addAttribute("picture", user.getUserpic());
        return "messages";
    }
    @RequestMapping(value="/csrf-token", method= RequestMethod.GET)
    public @ResponseBody
    String getCsrfToken(HttpServletRequest request) {
        CsrfToken token = (CsrfToken)request.getAttribute(CsrfToken.class.getName());
        return token.getToken();
    }
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

}
