package org.banta.huntsphere.web.error.exception.resource;

import org.banta.huntsphere.web.error.exception.HuntersLeagueException;

public class ResourceNotFoundException extends HuntersLeagueException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
