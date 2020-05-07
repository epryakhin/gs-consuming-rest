package com.example.consumingrest.clienttest;

import org.bouncycastle.util.encoders.Hex;

import java.math.BigInteger;

public class HexBigIntTest {

    private static final String HEX = "0d22c1c08ba07d10d37fe056c50b2fa07b25d86d60fee3709c3dc62090c82d8c586922f2b105324c1675ee269450dac7cbc0e8d9bc5a18fec57223c992a077244ba3824ad9ab4f6119438cfeb035b7ba5444011b99d713d054dab22994c99dda9cfe18ad194f43d1ee4ececc49723c0b7a39cc985e2aa3167051b915a2b7cac0378e45d1da58d6d0ca7b40f1496cf21f51d2a7c7327c0932182c76c5af810178a2d9cbe43f77f3bb67baca74c02722290fa46e9eb1b2a51672d6f62c47016e733bcc379155310bf7cd2250919311555438382a11f97a382bb2e58ba2d521037b1bcf2518a4452193b2cd888f645e86c5aa36337354ca15dc45ee766c59b5d324";

    public static void main(String[] args) {

        BigInteger bigInteger = new BigInteger(1, Hex.decode(HEX));
        System.out.println(bigInteger);
        String s = Hex.toHexString(bigInteger.toByteArray());

        System.out.println(s);

        if (!s.equalsIgnoreCase(HEX)) {
            throw new RuntimeException("Not equals");
        }

    }
}
