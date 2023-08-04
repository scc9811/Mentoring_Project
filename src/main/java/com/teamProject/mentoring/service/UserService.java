package com.teamProject.mentoring.service;

import com.teamProject.mentoring.dto.UserDto;
import com.teamProject.mentoring.entity.UserEntity;
import com.teamProject.mentoring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
// RequiredArgsConstructor : 생성자 주입.
public class UserService {
    private final UserRepository userRepository;
    public void save(UserDto userDto) {
        UserEntity userEntity = UserEntity.toUserEntity(userDto);
        Optional<UserEntity> byUserEmail = userRepository.findByEmail(userDto.getEmail());
        // 여기서의 save 메소드는 내가 생성할 필요X
        if(byUserEmail.isEmpty()) userRepository.save(userEntity);
        else System.out.println("exist _ email");



    }
}
