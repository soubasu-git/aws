package org.ecnanif.mf.exception;

import java.math.BigInteger;

public class MFNotFoundException extends Exception{
    BigInteger mfId;

    public BigInteger getMfId() {
        System.out.println("test");
        return mfId;
    }

    public void setMfId(BigInteger mfId) {
        this.mfId = mfId;
    }

    public MFNotFoundException(BigInteger mfId) {
        super(mfId +" not found");
    }
}
