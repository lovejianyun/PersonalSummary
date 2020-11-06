package com.qijy.threads.threadlocals;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public enum SecureRandomTest {
    INSTANCE;
    final static ThreadLocal<SecureRandom> secure_random = new ThreadLocal(){
        SecureRandom secureRandom;
        @Override
        protected SecureRandom initialValue() {
            try {
                secureRandom = SecureRandom.getInstance("SHA1PRNG");
            } catch (NoSuchAlgorithmException e) {
                secureRandom = new SecureRandom();
                e.printStackTrace();
            }
            secureRandom.nextBytes(new byte[20]);
            return secureRandom;
        }
    };
    public int nextInt(int upperBound){
        SecureRandom secureRandom = secure_random.get();
        return secureRandom.nextInt(upperBound);
    }

    public void setSeed(long seed){
        SecureRandom secureRandom = secure_random.get();
        secureRandom.setSeed(seed);
    }

}
