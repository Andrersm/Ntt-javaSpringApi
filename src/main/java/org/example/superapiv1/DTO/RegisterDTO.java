package org.example.superapiv1.DTO;

import org.example.superapiv1.domain.client.ClienteRole;

public record RegisterDTO(String login, String password, ClienteRole role) {

}
