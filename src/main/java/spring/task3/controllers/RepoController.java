package spring.task3.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.task3.domain.User;
import spring.task3.services.NotificationService;
import spring.task3.services.RepoService;

import java.util.List;

@RestController
@RequestMapping("/repo")
public class RepoController {

    @Autowired
    private RepoService repoService;
    @Autowired
    private NotificationService notificationService;


    @PostMapping("create-table")
    public void createTable(){
        repoService.createTable();
    }

    @GetMapping("/find-all")
    public List<User> findAll(Model model){
        List<User> users = repoService.findAll();

        model.addAttribute("users", users);
        return users;
    }


    @PostMapping("/user-create")
    public void createUser(@RequestBody User user){
        repoService.saveUser(user);
        notificationService.sendNotification("Пользователь "+ user.getName() + " добавлен");
    }

    @GetMapping("/user-delete/{name}")
    public void deleteUser(@PathVariable("name") String name){
        repoService.deleteByName(name);
        notificationService.sendNotification("Пользователь "+ name + " удален");;
    }

    @GetMapping("user-get/{name}")
    public User getOne(@PathVariable("name") String name, Model model){
        User user = repoService.getOne(name);
        model.addAttribute("user", user);
        return user;
    }
}
