package com.teamProject.mentoring.dto;


import com.teamProject.mentoring.entity.UserEntity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDto {
    private Long id;

    private String name;

    private String email;

    private String password;

    public static UserDto toUserDto(UserEntity userEntity){
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setName(userEntity.getName());
        userDto.setEmail(userEntity.getEmail());
        userDto.setPassword(userEntity.getPassword());

        return userDto;
    }





}
