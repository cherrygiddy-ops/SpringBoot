package com.morrisco.net.store.userRegistrationService;

import org.springframework.stereotype.Component;

@Component
public class UserService {
    private final UserRepository userRepository;
    private final NotificationService notificationService;

    public UserService(UserRepository userRepository, NotificationService notificationService) {
        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }

    public void  registerUser(User user){
        if (userRepository.findByEmail(user.getEmail()) !=null)
           throw  new IllegalArgumentException("user exists");
        userRepository.save(user);
        notificationService.send("succefully registred", user.getEmail());
    }
}
