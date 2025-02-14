package org.example.entities.generators;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;
import java.util.UUID;

public class UUIDGenerator implements IdentifierGenerator {

//     1. The '@Override' annotation ensures that this method is overriding a method from its superclass or an implemented interface.
//    @Override
//
//     2. Define a public method named 'generate' that returns an Object.
//    This method is likely used in an entity generator class in Hibernate.
//    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
//
//
//    3. Inside the method, generate a random UUID (Universally Unique Identifier).
//    The 'UUID.randomUUID()' method creates a 128-bit unique value.
//    The 'toString()' method converts it into a human-readable format.
//        return UUID.randomUUID().toString();
//    }
//
//    4. When this method is executed, it will return a random UUID.
//    Example output when the method runs
//        "550e8400-e29b-41d4-a716-446655440000"
//
//    This means that every time 'generate()' is called, a unique string-based ID will be returned.

    private static final KeyPair keyPair = generateKeyPair();  // Generate a key pair once

    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        try {
            String uuid = UUID.randomUUID().toString();
            return uuid + "-" + sign(uuid);
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            throw new RuntimeException("Error while signing UUID", e);
        }
    }

    private static KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            return keyGen.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating key pair", e);
        }
    }

    private String sign(String primaryKeyValue) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        PrivateKey privateKey = keyPair.getPrivate();
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(primaryKeyValue.getBytes(StandardCharsets.UTF_8));

        byte[] signedData = signature.sign();
        String signedString = Base64.getEncoder().encodeToString(signedData);

        // Truncate if longer than 100 characters
        return signedString.length() > 100 ? signedString.substring(0, 100) : signedString;
    }

}