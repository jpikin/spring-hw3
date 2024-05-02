package spring.task3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.task3.domain.User;
import spring.task3.repository.UserRepository;

import java.util.List;

@Service
public class RepoService {

    @Autowired
    private UserRepository repository;

    public UserRepository getRepository(){return repository;}

    public RepoService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll(){
        return repository.findAll();
    }

    public void saveUser(User user){
        repository.save(user);
    }

    public void deleteByName(String name){
        repository.deleteByName(name);
    }

    public User getOne(String name){
        return repository.getOne(name);
    }
    public void createTable(){
        repository.createTable();
    }





}
