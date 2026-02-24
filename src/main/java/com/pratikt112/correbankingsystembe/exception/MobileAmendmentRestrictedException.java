package com.pratikt112.correbankingsystembe.exception;

import com.pratikt112.correbankingsystembe.config.mobileConfig.amendConfig.MobileAmendCcntConfig;

public class MobileAmendmentRestrictedException extends BankingSystemException {


    public MobileAmendmentRestrictedException(String errorCode, String message, String errDesc, String userMessage) {
        super(errorCode, message, errDesc, userMessage);
    }

    public MobileAmendmentRestrictedException(String errorCode, String message, String userMessage, Throwable cause, String errDesc) {
        super(errorCode, message, userMessage, cause, errDesc);
    }
}
