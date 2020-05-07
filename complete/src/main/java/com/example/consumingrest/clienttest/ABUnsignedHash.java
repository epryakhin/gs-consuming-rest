package com.example.consumingrest.clienttest;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.util.DigestFactory;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.encoders.Hex;

import java.math.BigInteger;
import java.util.Arrays;

public class ABUnsignedHash {

    private static final String A_HEX = "0a";
    private static final String B_HEX = "bb";

    public static void main(String[] args) {
        final Digest sha3_512 = DigestFactory.createSHA3_512();

        System.out.println("\n---\nA\n---");
        appendHexToDigest(sha3_512, A_HEX);
        System.out.println("---\nB\n---");
        appendHexToDigest(sha3_512, B_HEX);

        final byte[] result = new byte[sha3_512.getDigestSize()];
        sha3_512.doFinal(result, 0);

        System.out.println();
        System.out.println(Hex.toHexString(result));
    }

    private static void appendHexToDigest(Digest digest, String hex) {
        byte[] bigIntBytes = Hex.decode(hex);
        System.out.println("bigIntBytes: " + Arrays.toString(bigIntBytes));
        BigInteger bigInt = new BigInteger(1, bigIntBytes);
        System.out.println("bigInt: " + bigInt);
        byte[] unsignedBytes = BigIntegers.asUnsignedByteArray(bigInt);
        System.out.println("unsigned unsignedBytes: " + Arrays.toString(unsignedBytes));
        digest.update(unsignedBytes, 0, unsignedBytes.length);
    }
}
