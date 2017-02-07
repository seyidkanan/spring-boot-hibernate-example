package az.kanan.usersecurity.service;

import az.kanan.usersecurity.db.UserDao;
import az.kanan.usersecurity.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Kanan on 2/7/2017.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        for (User user : userDao.findAll()) {
            userList.add(user);
        }
        return userList;
    }

    public void save(User user) {
        userDao.save(user);
    }

    public boolean update(Long userId, String name, String pass) {
        User user = userDao.findOne(userId);

        if (user != null) {
            if (name != null)
                user.setName(name);
            if (pass != null)
                user.setPass(pass);

            userDao.save(user);
        } else
            return false;

        return true;
    }

    public void delete(Long id) {
        userDao.delete(id);
    }

    public User findById(Integer id) {
        return userDao.findOne((long) id);
    }

    public User findByName(String name) {
        return userDao.findByName(name);
    }

}
