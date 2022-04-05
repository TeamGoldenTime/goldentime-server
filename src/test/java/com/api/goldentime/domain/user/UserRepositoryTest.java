package com.api.goldentime.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.api.goldentime.repository.UserRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

        User user = User.builder()
            .email(email)
            .name(name)
            .phoneNumber(phoneNumber)
            .gender(gender)
            .build();
        userRepository.save(user);

        //when
        User foundedUser = userRepository.findById(user.getId()).orElse(null);

        //then
        assertThat(foundedUser).isNotNull();
        assertThat(foundedUser.getEmail()).isEqualTo(email);
        assertThat(foundedUser.getName()).isEqualTo(name);


    }


}
