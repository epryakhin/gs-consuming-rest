package com.example.consumingrest.clienttest;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.util.DigestFactory;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.encoders.Hex;

import java.math.BigInteger;

public class ABUnsignedHash {

    private static final String A_HEX = "0a";
    private static final String B_HEX = "bb";

    public static void main(String[] args) {
        final Digest sha3_512 = DigestFactory.createSHA3_512();

        appendHexToDigest(sha3_512, A_HEX);
        appendHexToDigest(sha3_512, B_HEX);

        final byte[] result = new byte[sha3_512.getDigestSize()];
        sha3_512.doFinal(result, 0);
        System.out.println(Hex.toHexString(result));
    }

    private static void appendHexToDigest(Digest digest, String hex) {
        byte[] bytes = BigIntegers.asUnsignedByteArray(new BigInteger(1, Hex.decode(hex)));
        digest.update(bytes, 0, bytes.length);
    }
}
