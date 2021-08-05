package com.stodop.demo.api;

import com.stodop.demo.model.JwtResponse;
import com.stodop.demo.model.LoginForm;
import com.stodop.demo.model.Users;
import com.stodop.demo.repositories.UserRepository;
import com.stodop.demo.service.UserService;
import com.stodop.demo.utils.Responses;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping(path="/api/v1/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    @Value("${jwt.secret}")
    private String secret;
    @PostMapping(path="/register")
    public ResponseEntity<Object> register(@RequestBody Users users){
        try{
            Users user= userRepository.findByEmail(users.getEmail());
            if(user!=null){
                throw new Exception("Email đã tồn tại");
            }
            else {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                List<String> projectId=new ArrayList<>();
                users.setProjectsId(projectId);
                users.setAvata("");
                users.setPasswordUpdateTime(LocalDateTime.now());
                users.setPassword(passwordEncoder.encode(users.getPassword()));
                userRepository.save(users);
                return Responses.generateResponse("Register Successfully!", HttpStatus.OK,users);
            }
        }catch (Exception e){
            return Responses.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
    @PostMapping(path="/login")
    public ResponseEntity<Object> login(@RequestBody LoginForm loginForm){
        try{
            Users users=userRepository.findByEmail(loginForm.getEmail());
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if(users==null||!passwordEncoder.matches(loginForm.getPassword(),users.getPassword())){
                throw new Exception("Email hoặc mật khẩu không chính xác");
            }else {
                Map<String, Object> claims = new HashMap<>();
                String token= Jwts.builder().setClaims(claims).setSubject(loginForm.getEmail()).setIssuedAt(new Date(System.currentTimeMillis()))
                        .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                        .signWith(SignatureAlgorithm.HS512, secret).compact();
                return Responses.generateResponse("Login Successfully!",HttpStatus.OK,new JwtResponse(token));
            }
        }catch (Exception e){
            return Responses.generateResponse(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }
}