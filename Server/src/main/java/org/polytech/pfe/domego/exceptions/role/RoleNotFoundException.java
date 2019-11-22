package org.polytech.pfe.domego.exceptions.role;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException(int id) {
        super("Le role avec l'id " + id + " est INTROUVABLE.");
    }
}