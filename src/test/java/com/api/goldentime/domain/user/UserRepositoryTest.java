package com.api.goldentime.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.api.goldentime.repository.UserRepository;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @AfterEach
    public void cleanup()
    {
        userRepository.deleteAll();
    }

    @Test
    public void 회원가입()
    {
        //given
        String identifier = UUID.randomUUID().toString();
        String email = "hyewon981019@naver.com";
        String name = "seohyewon";
        String phoneNumber = "010-7502-2529";
        String gender = "F";

        User user = User.builder()
            .identifier(identifier)
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
        assertThat(foundedUser.getIdentifier()).isEqualTo(identifier);
        assertThat(foundedUser.getEmail()).isEqualTo(email);
        assertThat(foundedUser.getName()).isEqualTo(name);
    }


}
