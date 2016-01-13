package soap;



import com.sun.org.apache.xml.internal.security.c14n.CanonicalizationException;
import com.sun.org.apache.xml.internal.security.c14n.InvalidCanonicalizerException;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.text.ParseException;

/**
 * Created by Milord on 10.01.2016.
 */





/**
 * Created by Admin on 25.11.2015.
 */

public class Soap {
    String sName;
    String sCode;
    String sData;
    public Soap(String senderName, String senderCode, String senderData){
        sName = senderName;
        sCode = senderCode;
        sData= senderData;
    }
    public String create() throws ParseException, NoSuchAlgorithmException, CertificateException, NoSuchProviderException, KeyStoreException, IOException, UnrecoverableKeyException, ParserConfigurationException, SAXException, CanonicalizationException, InvalidCanonicalizerException, SignatureException, InvalidKeyException, org.apache.xml.security.c14n.CanonicalizationException, org.apache.xml.security.c14n.InvalidCanonicalizerException {

        Encrypt en = new Encrypt();
        en.initProvider();
        String certificate = en.getCertification();


        Document xml = DocumentHelper.createDocument();
        //create Envelop
        Element envelop = xml.addElement("soap:Envelope")
                .addNamespace("soap", "http://schemas.xmlsoap.org/soap/envelope/")
                .addNamespace("wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd")
                .addNamespace("ds", "http://www.w3.org/2000/09/xmldsig#")
                .addNamespace("atc", "http://at-sibir.ru/getDictionary")
                .addNamespace("smev", "http://smev.gosuslugi.ru/rev120315")
                .addNamespace("wsu","http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");
        //create body for computing hash
        Element body = createBody(envelop);

        String hash = en.getHash(body);
        envelop.remove(body);

        Header header = new Header(certificate,hash, en );
        header.generateHeader(envelop);
        createBody(envelop);
        return envelop.asXML();
    }

    private Element createBody(Element root) throws ParseException {
        Element body = root.addElement("soap:Body")
                .addNamespace("wsu", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd")
                .addAttribute("wsu:Id", "body");
        Element getDictionary = body.addElement("atc:getDictionary");
        Message message = new Message(sName, sCode);
        message.generateMessage(getDictionary);
        MessageData messageData = new MessageData(sData);
        messageData.generateMessageData(getDictionary);
        return body;
    }

}