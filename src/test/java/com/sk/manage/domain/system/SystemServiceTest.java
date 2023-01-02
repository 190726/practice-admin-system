package com.sk.manage.domain.system;

import com.sk.manage.domain.user.DutyStep;
import com.sk.manage.domain.user.User;
import com.sk.manage.domain.user.UserRepository;
import com.sk.manage.service.system.SystemService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest
class SystemServiceTest {

    static System createSystem(){
        return new System("사이버창구", LocalDate.now());
    }

    static User createUser(){
        String sno1 = "190726";
        String name1 = "이상국";
        DutyStep dutyStep1 = DutyStep.MANAGER;
        LocalDateTime enterDate1 = LocalDateTime.of(2022, 12, 22, 0,0,0);
        User user1 = User.builder().sno(sno1).name(name1).dutyStep(dutyStep1).enterDate(enterDate1).build();
        return user1;
    }

    @Autowired
    SystemService systemService;

    @Autowired
    SystemRepository systemRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    @Test
    void enrolledSystemUser(){
        //given
        System system = createSystem();
        User user = createUser();
        System saveSystem = systemRepository.save(system);
        User saveUser = userRepository.save(user);

        //when
        System enrolledSystemUser = systemService.enrolledSystemUser(saveSystem.getSystemId(), saveUser.getSno());
        SystemUser systemUser = enrolledSystemUser.getSystemUsers().get(0);

        //then
        Assertions.assertThat(systemUser.getSystem().getSystemId()).isEqualTo(saveSystem.getSystemUsers().get(0).getSystem().getSystemId());
        Assertions.assertThat(systemUser.getUser().getSno()).isEqualTo(saveUser.getSno());
    }


}
