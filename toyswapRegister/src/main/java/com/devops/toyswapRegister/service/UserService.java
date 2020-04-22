package com.devops.toyswapRegister.service;

import com.devops.toyswapRegister.model.User;
import com.devops.toyswapRegister.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service()
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;




    public User register(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());
        User u;
        if(existingUser == null) {

            String encryptedPass = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPass);
             u = userRepository.save(user);

            return u;
        }else{

            return null;
        }




    }

    public User update(User updatedUser) {

        return userRepository.save(updatedUser);
    }
}
