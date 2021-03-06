package soap;

import org.dom4j.Element;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Milord on 9.01.2016.
 */
public class Message {
    String sName;
    String sCode;
    SimpleDateFormat currentDate =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public String getCurrentDate() {
        return currentDate.format(new Date());
    }

    public Message(String senderName, String senderCode){
        sName = senderName;
        sCode = senderCode;

    }

    public void generateMessage(Element getDictionary){
        Element message = getDictionary.addElement("smev:Message");
        Element sender = message.addElement("smev:Sender");
        Element code = sender.addElement("smev:Code")
                .addText(sCode);
        Element name = sender.addElement("smev:Name")
                .addText(sName);
        Element recipient = message.addElement("smev:Recipient");
        code = recipient.addElement("smev:Code")
                .addText("000000541");
        name = recipient.addElement("smev:Name")
                .addText("ATC System");
        Element originator = message.addElement("smev:Originator");
        code = originator.addElement("smev:Code")
                .addText("IPGU01541");
        name = originator.addElement("smev:Name")
                .addText("EPGU");
        Element typeCode = message.addElement("smev:TypeCode")
                .addText("GSRV");
        Element status = message.addElement("smev:Status")
                .addText("REQUEST");
        Element date = message.addElement("smev:Date")
                .addText(getCurrentDate());
        Element exchangeType = message.addElement("smev:ExchangeType")
                .addText("1");
        Element serviceCode = message.addElement("smev:ServiceCode")
                .addText("5440100010000545045");
        Element caseNumber = message.addElement("smev:CaseNumber")
                .addText("70263950");
    }

}
