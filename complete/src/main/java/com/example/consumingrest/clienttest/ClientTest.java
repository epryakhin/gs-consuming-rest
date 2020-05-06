package com.example.consumingrest.clienttest;

import com.example.consumingrest.ConsumingRestApplication;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.agreement.srp.SRP6StandardGroups;
import org.bouncycastle.crypto.util.DigestFactory;

import java.math.BigInteger;
import java.security.SecureRandom;

public class ClientTest {

    private static String aHex = "3e614401b26c47c76dd72332c48e58d5be3b34337453c6a72274a3ff6010efd7d61630be65d5b1689f5ff57c0ece69259b7f9704aa3ee212775c4710a22061b25c974dc46405ff47097a37a4b894d225f3f0a904552fe80d7a68c12120ed7e6a2ac22a6994459743981f94b92dbdb639dbc6cfb748bff89d5bcb723d76a1cf213b1d26a9d90ad0407b9f2d5f1c0e621a0e7ef22b9c2cc5f328b4ada99f9cb2d92f2234bb37b1eb306b44447f24ae788cd90bee82cd7d4357e1aa2a720b5b2e6b1c8c4c08826141ea479129e457aec3fdfed6f4dd45efb4361a6f55ed0b987f556a33bf1f125a1f050229741cec57009a27cef589737dc61dea95e6919b175d25";
    private static String AHex = "94f36d129658ce5649a78ea8007e84d003a597c9f9968261fbcd4f47dbc2303bf4afa78bf0ab941e4e8cd2238a7dcca0fa098e9c30a6f6574193ab2df22b4a576545b5a807256aaf6e3a0579eea42d46ff635ded40b4a25352a214488931ca7e461a1c5606d1546bcbf858d0ec470cf08ad01feda933bb4c8bb8083d6ab3a43d5b5fd5268967caffeea5c0c0a68bf2e2ad1468ed5ba3beab905620e59c47e2e079c19b3ecbffaf8caa2b82509511c521f2f07a768339bed12865c5a6e72d5e67f33d032b298332b0bca5d9199e4f57dae314668ff08843024a7d8fdaa91eca98f537e7dbcd2a847f7b715d938839c3f7dd54010be2c28082e18fca6b09351c73";
    private static String BHex = "2d80515ad5d354bc75366a0420547ed12b65f8354d7ca2bb05c2ec4f7186ceb7d1b69487cd44c9a411077fadc113da039c3621b73979620d67007c731775ab774bf29b85b096fd332ae7e0022bac31d3d92c57c991a09e9f1f23bcfcce259df0294a6656f1d695a4217c032e5e3e2ccd85e46f5b1016ddfefbceaca696ddda3061f78329c9b511aba2182e9b4bee800409574df112b808b2ddca08268abbd299e9296c5d97ab68ae098e7d57296449c6e38f5598421be919b765e7cf157bd65c96f211172ae7c919fb561b154f4dba7ec29e0aa7a10b518a7435a8b3f0b48c4600011c21f1f065239520bc9949cc51445fbcb0992c2a956f8b17c901aa145459";
    private static String saltHex = "2873ea5b4ce2220291127d9c410ab08e2a595ee8dd42519fbb5a8a351c9c55910d6e0330753d9227680049e7ca1cc6931d1dd31ea1a671e0087ae42457ba88cc";

    public static void main(String[] args) throws CryptoException {
        SRP6Client srp6Client = new SRP6Client();
        srp6Client.init(
                SRP6StandardGroups.rfc5054_2048,
                DigestFactory.createSHA3_512(),
                new SecureRandom(),
                ConsumingRestApplication.hextToBigInt(aHex),
                ConsumingRestApplication.hextToBigInt(AHex)
        );
        final BigInteger A = srp6Client.generateClientCredentials(
                ConsumingRestApplication.hextToBytes(saltHex),
                "test".getBytes(),
                "secret".getBytes());
        if (!A.equals(ConsumingRestApplication.hextToBigInt(AHex))) {
            throw new RuntimeException("Something goes wrong");
        }
        final BigInteger S = srp6Client.calculateSecret(ConsumingRestApplication.hextToBigInt(BHex));
        final BigInteger M1 = srp6Client.calculateClientEvidenceMessage();

        System.out.println("A: "+ ConsumingRestApplication.bigIntToHex(A));
        System.out.println("S: " + ConsumingRestApplication.bigIntToHex(S));
        System.out.println("M1: "+ ConsumingRestApplication.bigIntToHex(M1));
    }
}
