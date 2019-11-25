package org.polytech.pfe.domego.exceptions;

import org.polytech.pfe.domego.protocol.RequestArgumentKey;
import org.polytech.pfe.domego.protocol.game.key.GameRequestKey;

public class MissArgumentToRequest extends RuntimeException {

    private RequestArgumentKey missKey;

    public MissArgumentToRequest(RequestArgumentKey missKey) {
        super("Error Request : Missing " + missKey.getKey() + " value for good request");
        this.missKey = missKey;
    }

    public RequestArgumentKey getMissKey() {
        return missKey;
    }
}
