package com.sk.manage.service.user;

import com.sk.manage.domain.user.DutyStep;
import com.sk.manage.domain.user.User;
import com.sk.manage.domain.user.UserRepository;
import com.sk.manage.web.user.UserRequestDto;
import com.sk.manage.web.user.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;

    public List<UserResponseDto> allUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDto> results = users.stream().map(user
                        -> new UserResponseDto(user.getSno(),
                        user.getName(),
                        user.getDutyStep().getName(),
                        user.getEnterDate().format(DateTimeFormatter.BASIC_ISO_DATE),
                        (user.getRetireDate() != null ? user.getRetireDate().format(DateTimeFormatter.BASIC_ISO_DATE):"-"
                        )))
                .collect(Collectors.toList());
        return results;
    }

    @Transactional
	public Integer save(UserRequestDto requestDto) {
		User user = User.builder()
			.sno(requestDto.getSno())
			.name(requestDto.getName())
			.dutyStep(DutyStep.valueOf(requestDto.getDutyStep()))
			.enterDate(LocalDate.parse(requestDto.getEnterDate()).atTime(0, 0))
			.build();
		
		userValidator.validate(user);
		
		return userRepository.save(user).getSno();
	}

}