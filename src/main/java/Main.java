import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.*;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.RSAKeyProvider;
import io.jsonwebtoken.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jcajce.provider.digest.SHA256;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.lang.JoseException;

import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {

        String secret = "MIGTAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBHkwdwIBAQQg247w7fZYc27Dr/cBJc5laTJFwLJLgs9jQaSeUfVU1kygCgYIKoZIzj0DAQehRANCAATOLyYZpBnAweJPU/FG4j0oA/z/qLTS7OJ5P839h9Rtngfs564at6azXK7udrYsTkRVZcpCJ7H5P8cPELcF7uUQ";

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.ES256;

        byte[] publicBytes = Base64.decodeBase64(secret);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(publicBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        PrivateKey prvKey = keyFactory.generatePrivate(keySpec);
        ECPrivateKey eckey = (ECPrivateKey)prvKey;

        long nowMillis = System.currentTimeMillis();
        long expiryMillis = 1503890102;
        Date now = new Date(nowMillis);
        Date expiry = new Date(nowMillis + 100000000);

        System.out.println("EXP: " + expiry.toString());
        System.out.println("NOW: " + now.toString());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder()
                .setIssuedAt(now)
                .setHeaderParam("alg","ES256")
                .setHeaderParam("kid","B93K9Z88NU")
                .setExpiration(expiry)
                .setIssuer("B68385H95A")
                .signWith(signatureAlgorithm, eckey);

        System.out.println("BUILDER: " + builder.compact());

    }



}
