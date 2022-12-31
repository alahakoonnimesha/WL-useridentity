package com.nishiProductions.wluserIdentity.constants;

public interface ApplicationMessageConstants {
    public interface ServiceErrorMessages {
        String ERR_SAVE_USER = "err.save.user";
        String EX_SAVE_USER = "ex.save.user";
        String ERR_RETRIEVE_ALL_USERS = "err.retrieve.all.users";
        String EX_RETRIEVE_ALL_USERS = "ex.retrieve.all.users";
        String ERR_USER_NOT_AVAILABLE_BY_EMAIL = "err.user.not.available.for.email";
        String ERR_PASSWORD_INVALID_FOR_EMAIL = "err.password.invalid.for.email";
        String ERR_RETRIEVE_JWT_TOKEN = "err.retrieve.jwt.token";
        String EX_RETRIEVE_JWT_TOKEN = "ex.retrieve.jwt.token";
    }
}
