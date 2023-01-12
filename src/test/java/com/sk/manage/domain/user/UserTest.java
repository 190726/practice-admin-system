package com.sk.manage.domain.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class UserTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("사용자 등록 테스트")
    void user_등록(){
        //given
        Integer sno = 190726;
        String name = "이상국";
        DutyStep dutyStep = DutyStep.MANAGER;
        LocalDateTime enterDate = LocalDateTime.of(2022, 12, 22, 0,0,0);
        User user = User.builder().sno(sno).name(name).dutyStep(dutyStep).enterDate(enterDate).build();

        //when
        userRepository.save(user);
        User findUser = userRepository.findBySno(190726).orElseThrow(() -> new RuntimeException("no user"));

        System.out.println(findUser);
        //then
        Assertions.assertThat(findUser.getName()).isEqualTo(user.getName());

    }

}