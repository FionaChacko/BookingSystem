package com.trust.Booking.jwt;

import com.trust.Booking.constants.AppConstants;
import com.trust.Booking.exception.UserNotFoundException;
import com.trust.Booking.model.Register;
import com.trust.Booking.repository.BookingRepository;
import com.trust.Booking.response.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private BookingRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     *Method to fetch the user with the given credentials
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("JwtUserDetailsService loadUserByUserName starts with {}",username);
        Optional<Register> userinfo = userRepository.findByName(username);

        if (userinfo.isPresent()) {
            Register userData = userinfo.get();
            log.info("JwtUserDetailsService loadUserByUserName ends with {}",username);
            return new User(userData.getName(), userData.getPassword(), new ArrayList<>());
        } else {
            log.info("JwtUserDetailsService loadUserByUserName ends with user not found exception");
            throw new UsernameNotFoundException(AppConstants.USER_NAME_NOT_FOUND + username);
        }
    }

    /**
     * Method to fetch the details of the user
     * @param username
     * @return
     */
    public UserResponse getUserResponse(String username) {
        log.info("JwtUserDetailsService getUserResponse starts with {}",username);
        Optional<Register> userinfo = userRepository.findByName(username);
        if (userinfo.isPresent()) {
            Register userData = userinfo.get();
            UserResponse userResponse = modelMapper.map(userData, UserResponse.class);
            log.info("JwtUserDetailsService loadUserByUserName ends with username {}",username);
            return userResponse;
        }

        else throw new UserNotFoundException(AppConstants.USER_DOES_NOT_FOUND);
    }


}