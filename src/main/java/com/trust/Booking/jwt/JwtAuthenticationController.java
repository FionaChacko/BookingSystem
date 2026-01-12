package com.trust.Booking.jwt;

import com.trust.Booking.constants.AppConstants;
import com.trust.Booking.jwt.JwtTokenUtil;
import com.trust.Booking.jwt.JwtUserDetailsService;
import com.trust.Booking.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



import lombok.extern.slf4j.Slf4j;

/**
 * Controller class for jwt authentication
 *
 */
@Slf4j
@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    /**
     * Method for creating authentication token
     *
     * @param authenticationRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = AppConstants.AUTHENTICATE, method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtUserRequest authenticationRequest)
            throws Exception {
        log.info("JwtAuthenticationController createAuthenticationToken starts with {}", authenticationRequest);
        JwtUserResponse jwtUserResponse = new JwtUserResponse();

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        JwtResponse jwtResponse = new JwtResponse(token);

        UserResponse userResponse = userDetailsService.getUserResponse(authenticationRequest.getUsername());

        jwtUserResponse.setJwtResponse(jwtResponse);
        jwtUserResponse.setUserResponse(userResponse);
        log.info("JwtAuthenticationController createAuthenticationToken ends with {}", jwtUserResponse);
        return new ResponseEntity<>(jwtUserResponse, HttpStatus.OK);
    }

    /**
     * Method to authenticate given credentials
     *
     * @param username
     * @param password
     * @throws Exception
     */
    private void authenticate(String username, String password) throws Exception {
        log.info("JwtAuthenticationController authenticate method starts with {}", username);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception(AppConstants.USER_DISABLED, e);
        } catch (BadCredentialsException e) {
            throw new Exception(AppConstants.INVALID_CREDENTIALS, e);
        }
        log.info("JwtAuthenticationController authenticate method ends with {}", username);
    }
}