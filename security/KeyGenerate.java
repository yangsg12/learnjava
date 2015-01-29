package security;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;

/**
 * Created by Yang on 2015/1/27.
 */
public class KeyGenerate {
    private static String publicKeyFile ="abc123public";
    private static String privateKeyFile = "abc 123 private";

    public static void main(String[] args) {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(1024);
            KeyPair key = keyGen.generateKeyPair();

            PrivateKey privateKey = key.getPrivate();
            PublicKey publicKey = key.getPublic();

            byte[] key1 = publicKey.getEncoded();
            FileOutputStream keyfos1= new FileOutputStream(publicKeyFile);
            keyfos1.write(key1);
            keyfos1.close();

            byte[] key2 = privateKey.getEncoded();
            FileOutputStream keyfos2 = new FileOutputStream(privateKeyFile);
            keyfos2.write(key2);
            keyfos2.close();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
