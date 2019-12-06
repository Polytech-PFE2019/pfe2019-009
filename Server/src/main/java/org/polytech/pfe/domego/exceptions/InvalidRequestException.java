package org.polytech.pfe.domego.exceptions;

public class InvalidRequestException extends Exception {

    public InvalidRequestException() {
        super("InvalidRequest : Read the protocol to create valid request ! Must be of type {\\\"request\\\":\\\"REQUEST_NAME\\'}\"");
    }
}
