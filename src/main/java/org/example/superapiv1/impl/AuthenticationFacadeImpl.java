package org.example.superapiv1.impl;

import jakarta.persistence.EntityNotFoundException;
import org.aspectj.bridge.IMessage;
import org.example.superapiv1.DTO.*;
import org.example.superapiv1.domain.client.User;
import org.example.superapiv1.exception.CepNotFound;
import org.example.superapiv1.facade.AuthenticationFacade;
import org.example.superapiv1.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.security.auth.login.LoginException;
import java.util.List;

@Service
public class AuthenticationFacadeImpl implements AuthenticationFacade {


    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public ResponseDTO login(AuthenticationDTO body) throws LoginException {
        return authenticationService.login(body);
    }

    @Override
    public ResponseDTO register(RegisterDTO body) throws LoginException {
        AdressDTO adressDTO = getAddressFromCep(body.cep());
        return authenticationService.register(body, adressDTO);
    }

    @Override
    public String getCurrentUser(Authentication authentication) {
        return null;
    }

    @Override
    public UserDetailsDTO getUserById(String id) throws EntityNotFoundException {
        return authenticationService.getUserById(id);
    }

    @Override
    public List<UserDetailsDTO> findAllUsers() {
        return authenticationService.findAllUsers();
    }


    public AdressDTO getAddressFromCep(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<AdressDTO> responseEntity = restTemplate.getForEntity(
                String.format("https://viacep.com.br/ws/%s/json/", cep), AdressDTO.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            throw new CepNotFound();
        }
    }


}
