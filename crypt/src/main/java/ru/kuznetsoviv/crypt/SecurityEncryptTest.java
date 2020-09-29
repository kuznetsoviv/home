package ru.kuznetsoviv.crypt;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Collections;

import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.bouncycastle.cms.CMSAlgorithm;
import org.bouncycastle.cms.CMSEnvelopedData;
import org.bouncycastle.cms.CMSEnvelopedDataGenerator;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSTypedData;
import org.bouncycastle.cms.KeyTransRecipientInformation;
import org.bouncycastle.cms.RecipientInformation;
import org.bouncycastle.cms.jcajce.JceCMSContentEncryptorBuilder;
import org.bouncycastle.cms.jcajce.JceKeyTransAuthenticatedRecipient;
import org.bouncycastle.cms.jcajce.JceKeyTransRecipient;
import org.bouncycastle.cms.jcajce.JceKeyTransRecipientInfoGenerator;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.OutputEncryptor;
import org.w3c.dom.Document;

public class SecurityEncryptTest {

    static {
        Security.addProvider(new BouncyCastleProvider());
        Security.setProperty("crypto.policy", "unlimited");

    }

    public static void main(String[] args) throws Exception {
        createSignature();
        /*
        String secretMessage = "My passport is 123345";
        System.out.println("Original message: " + secretMessage);
        byte[] stringToEncrypt = secretMessage.getBytes();
        byte[] encryptedData = encryptData(stringToEncrypt, getCertificate());
        System.out.println("Encrypted Message : " + new String(encryptedData));
        byte[] rawData = decryptData(encryptedData, getPrivateKey());
        String decryptedMessage = new String(rawData);
        System.out.println("Decrypted Message : " + decryptedMessage);
         */
    }

    public static KeyPair generatePairKey() throws NoSuchProviderException, NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "BC");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
        keyGen.initialize(1024, random);
        return keyGen.generateKeyPair();
    }

    public static byte[] encryptData(byte[] data, X509Certificate encryptionCertificate)
            throws CertificateEncodingException, CMSException, IOException {
        if (data != null && encryptionCertificate != null) {
            CMSEnvelopedDataGenerator cmsEnvelopedDataGenerator = new CMSEnvelopedDataGenerator();
            JceKeyTransRecipientInfoGenerator jceKey = new JceKeyTransRecipientInfoGenerator(encryptionCertificate);
            cmsEnvelopedDataGenerator.addRecipientInfoGenerator(jceKey);
            CMSTypedData msg = new CMSProcessableByteArray(data);
            OutputEncryptor encryptor = new JceCMSContentEncryptorBuilder(CMSAlgorithm.AES256_CBC)
                    .setProvider("BC")
                    .build();
            CMSEnvelopedData cmsEnvelopedData = cmsEnvelopedDataGenerator.generate(msg, encryptor);
            return cmsEnvelopedData.getEncoded();
        }
        return null;
    }

    public static byte[] decryptData(byte[] encryptedData, PrivateKey decryptionKey) throws CMSException {
        if (encryptedData != null && decryptionKey != null) {
            CMSEnvelopedData envelopedData = new CMSEnvelopedData(encryptedData);
            Collection<RecipientInformation> recipients = envelopedData.getRecipientInfos().getRecipients();
            KeyTransRecipientInformation recipientInfo = (KeyTransRecipientInformation) recipients.iterator().next();
            JceKeyTransRecipient recipient = new JceKeyTransAuthenticatedRecipient(decryptionKey);
            return recipientInfo.getContent(recipient);
        }
        return null;
    }

    private static X509Certificate getCertificate() throws CertificateException, NoSuchProviderException {
        CertificateFactory certFactory = CertificateFactory.getInstance("X.509", "BC");
        InputStream certStream = SecurityEncryptTest.class.getClassLoader().getResourceAsStream("Baeldung.cer");
        return (X509Certificate) certFactory.generateCertificate(certStream);
    }

    private static PrivateKey getPrivateKey() throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException, UnrecoverableKeyException {
        KeyStore keystore = KeyStore.getInstance("PKCS12");
        InputStream privateStream = SecurityEncryptTest.class.getClassLoader().getResourceAsStream("Baeldung.p12");
        char[] keystorePassword = "password".toCharArray();
        char[] keyPassword = "password".toCharArray();
        keystore.load(privateStream, keystorePassword);
        return (PrivateKey) keystore.getKey("baeldung", keyPassword);
    }

    private static void createSignature() throws Exception {
        XMLSignatureFactory signatureFactory = XMLSignatureFactory.getInstance("DOM");
        DigestMethod digestMethod = signatureFactory.newDigestMethod(DigestMethod.SHA1, null);
        Reference reference = signatureFactory.newReference
                ("", signatureFactory.newDigestMethod(DigestMethod.SHA256, null),
                        Collections.singletonList(
                                signatureFactory.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null)),
                        null, null);

        SignedInfo signedInfo = signatureFactory.newSignedInfo(
                signatureFactory.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE_WITH_COMMENTS, (C14NMethodParameterSpec) null),
                signatureFactory.newSignatureMethod(SignatureMethod.DSA_SHA1, null),
                Collections.singletonList(reference));

        KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
        kpg.initialize(512);
        KeyPair keyPair = kpg.generateKeyPair();

        KeyInfoFactory keyInfoFactory = signatureFactory.getKeyInfoFactory();
        KeyValue keyValue = keyInfoFactory.newKeyValue(keyPair.getPublic());

        KeyInfo keyInfo = keyInfoFactory.newKeyInfo(Collections.singletonList(keyValue));

        XMLSignature signature = signatureFactory.newXMLSignature(signedInfo, keyInfo);

        InputStream docStream = SecurityEncryptTest.class.getClassLoader().getResourceAsStream("Document.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        Document doc = dbf.newDocumentBuilder().parse(docStream);

        DOMSignContext signContext = new DOMSignContext(keyPair.getPrivate(), doc.getDocumentElement());

        signature.sign(signContext);

        OutputStream os = System.out;

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer trans = tf.newTransformer();
        trans.transform(new DOMSource(doc), new StreamResult(os));
    }

}
