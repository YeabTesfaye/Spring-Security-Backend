package com.yeab.services;

import java.util.HashSet;
import java.util.Set;


import com.yeab.dto.LoginRequestDTO;
import com.yeab.dto.RegistrationDTO;
import com.yeab.models.ApplicationUser;
import com.yeab.dto.LoginResponseDTO;
import com.yeab.models.Role;
import com.yeab.repository.RoleRepository;
import com.yeab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public String registerUser(RegistrationDTO registrationDTO){

        String encodedPassword = passwordEncoder.encode(registrationDTO.getPassword());
        Role userRole = roleRepository.findByAuthority("USER").get();

        Set<Role> authorities = new HashSet<>();

        authorities.add(userRole);
        String username = registrationDTO.getUsername();
        String email = registrationDTO.getEmail();
        userRepository.save(new ApplicationUser(0, username, encodedPassword,email, authorities));
         return "register Successfully ";
    }

    public LoginResponseDTO loginUser(LoginRequestDTO loginRequestDTO){
        String username = loginRequestDTO.getUsername();
        String password  = loginRequestDTO.getPassword();
        try{
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenService.generateJwt(auth);

            return new LoginResponseDTO(token);

        } catch(AuthenticationException e){
            return new LoginResponseDTO(null);
        }
    }

}
