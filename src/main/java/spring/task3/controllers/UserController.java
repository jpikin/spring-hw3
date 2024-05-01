package spring.task3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.task3.domain.User;
import spring.task3.services.RegistrationService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private RegistrationService service;

    @GetMapping
    public List<User> userList() {
        return service.getDataProcessingService().getRepository().getUsers();
    }

    @PostMapping("/add/{name}/{age}/{email}")
    public String userAddFromParam(@PathVariable("name") String name,
                          @PathVariable("age") int age,
                          @PathVariable("email") String email)
    {
        service.processRegistration(name, age, email);
        return "User added from body!";
    }


}
