package soap;

import com.sun.org.apache.xml.internal.security.c14n.CanonicalizationException;
import com.sun.org.apache.xml.internal.security.c14n.Canonicalizer;
import com.sun.org.apache.xml.internal.security.c14n.InvalidCanonicalizerException;
import org.apache.commons.codec.binary.Base64;
import org.dom4j.Element;
import org.xml.sax.SAXException;
import ru.infotecs.crypto.ViPNetProvider;

import javax.xml.bind.DatatypeConverter;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


/**
 * Created by Milord on 10.01.2016.
 */
public class Encrypt {


    KeyStore keyStore;
    String alias;
    char[] password;
    MessageDigest digestDriver;

    public String toCanonicalize(Element xmlElement) throws InvalidCanonicalizerException, IOException, ParserConfigurationException, SAXException, CanonicalizationException {
        Canonicalizer canon = Canonicalizer.getInstance(Canonicalizer.ALGO_ID_C14N_EXCL_OMIT_COMMENTS);
        byte[] canonBody = canon.canonicalize(xmlElement.asXML().getBytes("UTF-8"));
        String canonXML = new String(canonBody, "UTF-8");
        return canonXML;
    }



    public String getSignature(Element signedInfo) throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateEncodingException, NoSuchProviderException, InvalidKeyException, SignatureException, IOException, ParserConfigurationException, SAXException, CanonicalizationException, InvalidCanonicalizerException {
        Signature signatureDriver = Signature.getInstance(
                "GOST3411-94withGOST3410-2001",
                "ViPNet"
        );
        PrivateKey pKey = (PrivateKey) keyStore.getKey(alias, password);
        signatureDriver.initSign(pKey);
        String canonXML = toCanonicalize(signedInfo);
        byte[] nextDataChunk = canonXML.getBytes();
        signatureDriver.update(nextDataChunk);
        byte[] signatureValue = signatureDriver.sign();
        String sign = Base64.encodeBase64String(signatureValue);
        return sign;
    }

     public void initProvider() throws NoSuchProviderException, NoSuchAlgorithmException, KeyStoreException, IOException, CertificateException {

         com.sun.org.apache.xml.internal.security.Init.init();
         // регистрируем провайдер, предоставляющий реализации ГОСТ алгоритмов
         Security.addProvider(new ViPNetProvider());

         // получение хранилища для работы с одним контейнером в формате ViPNet
         digestDriver = MessageDigest.getInstance("GOST3411-94", "ViPNet");

         keyStore = KeyStore.getInstance("ViPNetContainer", "ViPNet");

         // сохранение хранилища
         keyStore.load(new FileInputStream("C:\\token"), null);

         // подготовка названия записи
         alias = "key";

         // подготовка пароля
         password = "1234567890".toCharArray();
     }



         public String getHash(Element xmlElement) throws IOException, ParserConfigurationException, SAXException, CanonicalizationException, InvalidCanonicalizerException {
             String canonXML = toCanonicalize(xmlElement);
             digestDriver.update(canonXML.getBytes());
             byte[] digestValue = digestDriver.digest();
             String hash = DatatypeConverter.printBase64Binary(digestValue);
             return hash;
         }
        public String getCertification() throws CertificateEncodingException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException {
            X509Certificate cert = (X509Certificate) keyStore.getCertificate(alias);
            String certificate = Base64.encodeBase64String(cert.getEncoded());
            return certificate;
        }

}

