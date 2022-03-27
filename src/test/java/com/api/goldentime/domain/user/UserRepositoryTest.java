package com.api.goldentime.domain.user;

import com.api.goldentime.repository.UserRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @After
    public void cleanup()
    {
        userRepository.deleteAll();
    }

    @Test
    public void 회원가입()
    {
        //given
        String email = "hyewon981019@naver.com";
        String name = "seohyewon";
        String phoneNumber = "010-7502-2529";
        String gender = "F";


        userRepository.save(User.builder()
        .email(email)
        .name(name)
        .phoneNumber(phoneNumber)
        .gender(gender)
        .build());

        //when
        List<User> usersList = userRepository.findAll();

        //then
        User user = usersList.get(0); //첫번째 회원
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getName()).isEqualTo(name);


    }


}
