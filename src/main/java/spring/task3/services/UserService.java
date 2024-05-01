package spring.task3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.task3.domain.User;

@Service
public class UserService {

    @Autowired
    private NotificationService notificationService;

    public User createUser(String name, int age, String email) {

        User user = new User();
        user.setName(name);
        user.setAge(age);
        user.setEmail(email);

        return user;
    }
}
