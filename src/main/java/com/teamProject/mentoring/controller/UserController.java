package com.teamProject.mentoring.controller;


import com.teamProject.mentoring.dto.UserDto;
import com.teamProject.mentoring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("user/register")
    public String registerHome(){
        return "registerHome";
    }

    @PostMapping("user/register")
    public String save(@ModelAttribute UserDto userDto){

        System.out.println(userDto.toString());


        userService.save(userDto);
        return "registerHome";
    }
    @PostMapping("user/email-check")
    public @ResponseBody String emailCheck(@RequestParam("email") String email){

        return "OK";
    }



}
