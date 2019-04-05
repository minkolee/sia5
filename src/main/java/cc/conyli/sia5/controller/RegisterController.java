package cc.conyli.sia5.controller;

import cc.conyli.sia5.entity.User;
import cc.conyli.sia5.entity.UserForConfirmPassword;
import cc.conyli.sia5.service.MyUserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/register")
public class RegisterController {

    private MyUserDetailService myUserDetailService;

    @Autowired
    public RegisterController(MyUserDetailService myUserDetailService) {
        this.myUserDetailService = myUserDetailService;
    }

    @GetMapping
    public String showForm(Model model) {
        model.addAttribute("user", new UserForConfirmPassword());
        return "registration";
    }

    @PostMapping
    public String processForm(@ModelAttribute("user") @Valid UserForConfirmPassword user, Errors errors) {
        if (errors.hasErrors() || !user.getPassword().equals(user.getConfirm_password())) {
            log.info(errors.toString());
            return "registration";
        }
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setFullname(user.getFullname());
        newUser.setCity(user.getCity());
        newUser.setState(user.getState());
        newUser.setStreet(user.getStreet());
        newUser.setZip(user.getZip());
        newUser.setPhonenumber(user.getPhonenumber());

        myUserDetailService.save(newUser);
        return "redirect:/login";
    }

    //初始化去掉两边的Trim
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }


}
