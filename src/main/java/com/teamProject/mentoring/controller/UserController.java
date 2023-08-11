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

    @GetMapping("user/sign_up_in")
    public String registerHome(){
        return "sign_up_in";
    }

    @PostMapping("user/sign_up")
    public String save(@ModelAttribute UserDto userDto){

        System.out.println(userDto.toString());


        // 회원가입 성공 시 로그인 페이지.
        if(userService.save(userDto)) return "sign_up_in";
        // 회원가입 실패 (이메일 중복) 시 알림창 띄우고 다시 회원가입 페이지
        else return "registerHome";
    }
//    @GetMapping("user/login")
//    public String login(){
//        return "loginHome";
//    }

    @PostMapping("user/sign_in")
    public String login(@ModelAttribute UserDto userDto, HttpSession session){
        System.out.println("로그인 dto = "+userDto.toString());
        UserDto loginResult = userService.login(userDto);
        if(loginResult != null){
            // 로그인 성공
            // 앞으로 이 세션 정보를 이용.
            session.setAttribute("userName", loginResult.getName());
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
