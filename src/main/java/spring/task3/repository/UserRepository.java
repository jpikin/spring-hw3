package spring.task3.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import spring.task3.domain.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private List<User> users = new ArrayList<>();

    public List<User> getUsers(){
        return users;
    }
    public void setUsers(List<User> users){
        this.users = users;
    }


    //Подключаем H2

    private final JdbcTemplate jdbc;

    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    public void createTable(){
        String sql = """
    CREATE TABLE userTable (
    name varchar(50) NOT NULL,
    age INT NOT NULL,
    email varchar(50) NOT NULL);\s
""";
    jdbc.update(sql);
    }



    public List<User> findAll() {
        String sql = "SELECT * FROM userTable";

        RowMapper<User> userRowMapper = (r, i) -> {
            User rowObject = new User();
            rowObject.setName(r.getString("name"));
            rowObject.setAge(r.getInt("age"));
            rowObject.setEmail(r.getString("email"));
            return rowObject;
        };

        return jdbc.query(sql, userRowMapper);
    }

    public User save(User user) {
        String sql = "INSERT INTO userTable (name, age, email) VALUES ( ?, ?, ?)";
        jdbc.update(sql, user.getName(), user.getAge(), user.getEmail());
        return user;
    }

    public void deleteByName(String name) {
        String sql = "DELETE FROM userTable WHERE name=" + name;
        jdbc.update(sql);
    }

    public User getOne(String name) {
        String sql = "SELECT * FROM userTable WHERE name = " + name;
        RowMapper<User> userRowMapper = ((rs, rowNum) -> {
            User rowObject = new User();
            rowObject.setAge(rs.getInt("age"));
            rowObject.setName(rs.getString("name"));
            rowObject.setEmail(rs.getString("email"));
            return rowObject;
        });
        List<User> users = jdbc.query(sql,userRowMapper);
        if (users.isEmpty()){
            return null;
        } else {
            return jdbc.query(sql, userRowMapper).get(0);
        }
    }


}
