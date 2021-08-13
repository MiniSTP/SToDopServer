package com.stodop.demo.service;
import com.stodop.demo.model.CustomUserDetails;
import com.stodop.demo.dao.UserDao;
import com.stodop.demo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserDao userDao;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        List<Users> users = userDao.findByEmail(email);
        Users user = users.get(0);
        System.out.println(user.getUserName());
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        return new CustomUserDetails(user);
    }
}
