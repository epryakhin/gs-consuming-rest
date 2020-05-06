package com.example.consumingrest.clienttest;

import lombok.Data;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.params.SRP6GroupParameters;

import java.math.BigInteger;
import java.security.SecureRandom;

@Data
public class SRP6Client extends org.bouncycastle.crypto.agreement.srp.SRP6Client {

    private BigInteger predefined_A;
    private BigInteger predefined_a;

    public void init(SRP6GroupParameters group, Digest digest, SecureRandom random, BigInteger a, BigInteger A) {
        super.init(group, digest, random);
        this.predefined_a = a;
        this.predefined_A = A;
    }

    @Override
    public BigInteger generateClientCredentials(byte[] salt, byte[] identity, byte[] password) {
        super.generateClientCredentials(salt, identity, password);
        this.A = predefined_A;
        return A;
    }

    @Override
    protected BigInteger selectPrivateValue() {
        return predefined_a;
    }
}
