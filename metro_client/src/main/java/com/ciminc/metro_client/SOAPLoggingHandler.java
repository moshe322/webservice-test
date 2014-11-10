package com.ciminc.metro_client;

import java.io.PrintStream;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.xml.sax.InputSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;

/**
 *
 * @author ben
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class SOAPLoggingHandler implements SOAPHandler<SOAPMessageContext> {

    // change this to redirect output if desired
    private PrintStream out = System.out;
    private boolean prettyPrint = false;

    public SOAPLoggingHandler(boolean prettyPrint, PrintStream out) {
        this.prettyPrint = prettyPrint;
        this.out = out;
    }

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext smc) {
        logToSystemOut(smc);
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext smc) {
        logToSystemOut(smc);
        return true;
    }

    // nothing to clean up
    @Override
    public void close(MessageContext messageContext) {
    }

    /*
     * Check the MESSAGE_OUTBOUND_PROPERTY in the context
     * to see if this is an outgoing or incoming message.
     * Write a brief message to the print stream and
     * output the message. The writeTo() method can throw
     * SOAPException or IOException
     */
    private void logToSystemOut(SOAPMessageContext smc) {
        Boolean outboundProperty = (Boolean) smc.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        if (outboundProperty.booleanValue()) {
            out.println("\nOutbound message:");
        } else {
            out.println("\nInbound message:");
        }

        SOAPMessage message = smc.getMessage();
        try {
            if (prettyPrint) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                message.writeTo(baos);
                out.print(XmlFormatter.prettyPrintXml(baos.toString()));
            } else {
                message.writeTo(out);
            }

            out.println("");   // just to add a newline
        } catch (Exception e) {
            out.println("Exception in handler: " + e);
        }
    }
}