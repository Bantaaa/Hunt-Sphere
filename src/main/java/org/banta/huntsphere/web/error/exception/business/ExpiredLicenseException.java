package org.banta.huntsphere.web.error.exception.business;

import org.banta.huntsphere.web.error.exception.HuntersLeagueException;

public class ExpiredLicenseException extends HuntersLeagueException {
    public ExpiredLicenseException(String message) {
        super(message);
    }
}
