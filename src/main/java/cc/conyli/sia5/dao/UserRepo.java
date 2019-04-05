package cc.conyli.sia5.dao;

import cc.conyli.sia5.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

    User getUserByUsername(String username);

}
