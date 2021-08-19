package com.stodop.demo.api;

import com.stodop.demo.component.JwtTokenProvider;
import com.stodop.demo.dao.UserDao;
import com.stodop.demo.model.JwtResponse;
import com.stodop.demo.model.LoginForm;
import com.stodop.demo.model.Users;
import com.stodop.demo.service.UserService;
import com.stodop.demo.utils.Responses;
import org.apache.tomcat.util.buf.UEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping(path = "/api/v1/user")
public class UserController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping(path = "/register")
    public ResponseEntity<Object> register(@RequestBody Users users) {
        System.out.println("register");
        try {
            List<Users> user = userDao.findByEmail(users.getEmail());
            if (!user.isEmpty()) {
                throw new Exception("Email đã tồn tại");
            } else {
                userDao.createNewUser(users);
                return Responses.generateResponse("Register Successfully!", HttpStatus.OK, null);
            }
        } catch (
                Exception e) {
            return Responses.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }

    }

    @PostMapping(path = "/login")
    public ResponseEntity<Object> login(@RequestBody LoginForm loginForm) {
        try {
            List<Users> users = userDao.findByEmail(loginForm.getEmail());
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (users == null || users.isEmpty()) {
                throw new Exception("Email không chính xác");
            } else {
                if (!passwordEncoder.matches(loginForm.getPassword(), users.get(0).getPassword())) {
                    throw new Exception("Mật khẩu không chính xác");
                } else {
                    String token = jwtTokenProvider.genarateJwtToken(loginForm.getEmail());
                    return Responses.generateResponse("Login Successfully!", HttpStatus.OK, new JwtResponse(token));
                }
            }
        } catch (Exception e) {
            return Responses.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PostMapping(path="/login/google")
    public ResponseEntity<Object> loginWithGoogle(@RequestBody Users users){
        System.out.println("google");
        try {
            List<Users> user = userDao.findByEmail(users.getEmail());
            if (user.isEmpty()){
                users.setPassword("1");
                userDao.createNewUser(users);
            }
            String token = jwtTokenProvider.genarateJwtToken(users.getEmail());
            return Responses.generateResponse("Login Successfully!", HttpStatus.OK, new JwtResponse(token));

        }catch (Exception e){
            return Responses.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS,null);
        }
    }

    @GetMapping("/me")
    public ResponseEntity<Object> getMyInfor() {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            List<Users> users = userDao.findByEmail(userDetails.getUsername());
            return Responses.generateResponse("Successfully!!!", HttpStatus.OK, users.get(0));
        } catch (Exception e) {
            return Responses.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }

    }
}