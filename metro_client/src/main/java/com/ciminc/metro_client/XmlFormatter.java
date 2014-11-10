/*
 * Copyright (C) 2013 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.  
 * For more information, contact <http://www.ciminc.com>
 */
package com.ciminc.metro_client;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.InputSource;

/**
 *
 * @author ben
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class XmlFormatter {

    public static String prettyPrintXml(String sourceXml) {
        try {
            Transformer serializer = SAXTransformerFactory.newInstance().newTransformer();

            serializer.setOutputProperty(OutputKeys.INDENT, "yes");

            // serializer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            // serializer.setOutputProperty("{http://xml.customer.org/xslt}indent-amount", "2");
            Source xmlSource = new SAXSource(new InputSource(new ByteArrayInputStream(sourceXml.getBytes())));
            StreamResult res = new StreamResult(new ByteArrayOutputStream());

            serializer.transform(xmlSource, res);

            return new String(((ByteArrayOutputStream) res.getOutputStream()).toByteArray());
        } catch (Exception e) {

            // TODO log error
            return sourceXml;
        }
    }
}
