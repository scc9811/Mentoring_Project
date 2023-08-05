package com.teamProject.mentoring.controller;


import com.teamProject.mentoring.dto.UserDto;
import com.teamProject.mentoring.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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


        if(userService.save(userDto)) return "loginHome";
        else return "registerHome";
    }
    @GetMapping("user/login")
    public String login(){
        return "loginHome";
    }

    @PostMapping("user/login")
    public String login(@ModelAttribute UserDto userDto, HttpSession session){
        System.out.println("로그인 dto = "+userDto.toString());
        UserDto loginResult = userService.login(userDto);
        if(loginResult != null){
            // 로그인 성공
            // 앞으로 이 세션 정보를 이용.
            session.setAttribute("loginEmail", loginResult.getEmail());
            System.out.println("성공!");
            return "mainPage";
        }
        else{
            // 로그인 실패
            System.out.println("실패!");
            return "loginHome";
        }
    }




}
