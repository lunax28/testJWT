import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import io.jsonwebtoken.*;

import java.security.NoSuchAlgorithmException;
import java.util.Date;


public class Main {


    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println("hi!");

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.ES256;

        long nowMillis = System.currentTimeMillis();

        long expiry = 1501944516000L;

        Key signKey = KeyGenerator.getInstance("MIGTAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBHkwdwIBAQQg247w7fZYc27Dr/cBJc5laTJFwLJLgs9jQaSeUfVU1kygCgYIKoZIzj0DAQehRANCAATOLyYZpBnAweJPU/FG4j0oA/z/qLTS7OJ5P839h9Rtngfs564at6azXK7udrYsTkRVZcpCJ7H5P8cPELcF7uUQ").generateKey();


        long expiryDate = 150194400;
        Date now = new Date(nowMillis);
        Date exp = new Date(expiryDate);

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder()
                .setIssuedAt(now)
                .setHeaderParam("alg","ES256")
                .setHeaderParam("kid","B93K9Z88NU")
                .setExpiration(exp)
                .setIssuer("B68385H95A")
                .signWith(signatureAlgorithm, signKey);


        System.out.println("BUILDER: " + builder.compact());


    }



}
