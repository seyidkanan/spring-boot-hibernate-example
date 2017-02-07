package az.kanan.usersecurity.db;

import az.kanan.usersecurity.pojo.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Kanan on 2/7/2017.
 */
@Transactional
public interface UserDao extends CrudRepository<User, Long> {

    User findByName(String name);

}
