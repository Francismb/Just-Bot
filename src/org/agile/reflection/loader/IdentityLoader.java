package org.agile.reflection.loader;

import org.agile.reflection.storage.IdentityStorage;
import org.agile.reflection.storage.type.ClassIdentity;
import org.agile.reflection.storage.type.FieldIdentity;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: Francis
 * Date: 26/03/13
 * Time: 4:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class IdentityLoader {

    public static void load() {
        System.out.println("^ - Requesting Class Identities");
        try {
            URL xmlURL = new URL("https://dl.dropboxusercontent.com/u/28468007/Identities.xml");
            InputStream xml = xmlURL.openStream();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(xml);
            doc.getDocumentElement().normalize();
            final NodeList classList = doc.getElementsByTagName("Class");
            for (int index = 0; index < classList.getLength(); index++) {
                Node node = classList.item(index);
                if (node instanceof Element) {
                    Element classElement = (Element) node;
                    String classID = classElement.getAttribute("IdentityName");
                    String className = classElement.getAttribute("ClassName");
                    final ClassIdentity identity = new ClassIdentity(classID, className);
                    NodeList getterElements = classElement.getElementsByTagName("Field");
                    if (getterElements != null && getterElements.getLength() > 0) {
                        for (int j = 0; j < getterElements.getLength(); ++j) {
                            Element getterNode = (Element) getterElements.item(j);
                            String fieldName = getterNode.getAttribute("FieldName");
                            String multiplier = getterNode.getAttribute("Multiplier");
                            String name = getterNode.getAttribute("Name");
                            String owner = getterNode.getAttribute("Owner");
                            if (multiplier.equals("")) {
                                identity.addIdentity(new FieldIdentity(name, fieldName, owner));
                            } else {
                                identity.addIdentity(new FieldIdentity(name, fieldName, owner, Integer.parseInt(multiplier)));
                            }
                        }
                    }
                    IdentityStorage.addIdentity(identity);
                }
            }
            xml.close();
        } catch (final Exception e) {
            e.printStackTrace();
        }
        System.out.println("^ - Loaded " + IdentityStorage.getSize() + " Class Identities");
        int fieldCount = 0;
        for (final ClassIdentity identity : IdentityStorage.toArray()) {
            fieldCount += identity.getIdentities().length;
        }
        System.out.println("^ - Loaded " + fieldCount + " Field Identities");
    }

}
