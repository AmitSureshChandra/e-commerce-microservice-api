package com.github.amitsureshchandra.accountservice.service;

import com.github.amitsureshchandra.accountservice.dto.UserCreateDto;
import com.github.amitsureshchandra.accountservice.dto.UserDto;
import com.github.amitsureshchandra.accountservice.dto.UserUpdateDto;
import com.github.amitsureshchandra.accountservice.entity.User;
import com.github.amitsureshchandra.accountservice.events.AccountTransactionEvent;
import com.github.amitsureshchandra.accountservice.repo.UserRepo;
import com.github.amitsureshchandra.commonfeature.service.feature.IBaseCRUService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Async
public class UserService implements IBaseCRUService<User, Long, UserCreateDto, UserUpdateDto> {
    final ApplicationEventPublisher eventPublisher;
    final ApplicationContext applicationContext;
    final UserRepo userRepo;

    public UserService(ApplicationEventPublisher eventPublisher, ApplicationContext applicationContext, UserRepo userRepo) {
        this.eventPublisher = eventPublisher;
        this.applicationContext = applicationContext;
        this.userRepo = userRepo;
    }

    public void payment(Long userId, Double amount, String transactionId) {
        transfer(userId, amount, transactionId);
    }

    public void withdraw(Long userId, Double amount, String transactionId) {
        transfer(userId, (-1) * amount, transactionId);
    }

    private void transfer(Long userId, Double amount, String transactionId){
        User user = findById(userId);
        user.setBalance(user.getBalance() + amount);
        userRepo.save(user);
        eventPublisher.publishEvent(new AccountTransactionEvent(transactionId, new UserDto(user)));
    }

    @Override
    public ApplicationContext getAppContext() {
        return applicationContext;
    }

    @Override
    public User beforeUpdate(User entity, UserUpdateDto userUpdateDto) {

        entity.setCity(userUpdateDto.getCity());
        entity.setEmail(userUpdateDto.getEmail());
        entity.setState(userUpdateDto.getState());
        entity.setCountry(userUpdateDto.getCountry());
        entity.setPincode(userUpdateDto.getPincode());

        return userRepo.save(entity);
    }

    @Override
    public JpaRepository<User, Long> getRepo() {
        return userRepo;
    }
}
