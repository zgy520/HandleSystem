package com.zgy.handle.gateway.controller;

import com.zgy.handle.gateway.config.security.JwtResponse;
import com.zgy.handle.gateway.config.security.JwtTokenUtil;
import com.zgy.handle.gateway.config.security.JwtUserDetailsService;
import com.zgy.handle.gateway.model.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseCode<String> createAuthenticationToken(String username, String password) throws Exception {
        ResponseCode<String> responseCode = ResponseCode.sucess();
        authenticate(username,password);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        final String token = jwtTokenUtil.generateToken(userDetails);
        responseCode.setData(token);
        return responseCode;
    }

    @PostMapping(value = "logout")
    public ResponseCode<String> logout(){
        ResponseCode<String> responseCode = ResponseCode.sucess();
        responseCode.setData("sucess");
        return responseCode;
    }

    private void authenticate(String userName,String password) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,password));
        }catch (DisabledException ex){
            throw new Exception("USER_DISABLED", ex);
        }catch (BadCredentialsException ex){
            throw new Exception("INVALID_CREDENTIALS", ex);
        }
    }
}
