package org.polytech.pfe.domego.exceptions;

import org.polytech.pfe.domego.protocol.RequestArgumentKey;

public class MissArgumentToRequestException extends Exception{

    private final RequestArgumentKey missKey;

    public MissArgumentToRequestException(RequestArgumentKey missKey) {
        super("Error Request : Missing " + missKey.getKey() + " value for good request");
        this.missKey = missKey;
    }

    public RequestArgumentKey getMissKey() {
        return missKey;
    }
}
