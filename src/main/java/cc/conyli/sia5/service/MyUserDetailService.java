package cc.conyli.sia5.service;

import cc.conyli.sia5.dao.UserRepo;
import cc.conyli.sia5.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MyUserDetailService implements UserDetailsService {


    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public MyUserDetailService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("|***| USERNAME: " + username + " IS NOT FOUND |***|");
        }
        return user;
    }

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        log.info("加密保存后的user是：" + user);
        return user;
    }
}
