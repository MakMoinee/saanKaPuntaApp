package com.rizaltechnology.saankapuntaapp.Services;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class LocalHash {


    public LocalHash() {
    }

    public String makeHashPassword(String password) {
        String hashPass = "";
        hashPass = BCrypt.withDefaults().hashToString(16, password.toCharArray());
        return hashPass;
    }

    public Boolean verifyPassword(String password, String hashPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hashPassword);
        return result.verified;
    }


}
