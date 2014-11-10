/*
 * Copyright (C) 2014 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.  
 * For more information, contact <http://www.ciminc.com>
 */
package com.ciminc.metro_client;

import com.ciminc.test.Ping_Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;

/**
 *
 *
 * @author ben
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class PingClient {

    public static void main(String[] args) {
        try {

//            Ping_Service service = new com.ciminc.test.Ping_Service(new URL("http://localhost:8080/webapp/Ping"));            
            Ping_Service service = new com.ciminc.test.Ping_Service();
            com.ciminc.test.Ping port = service.getPingPort();
            ((BindingProvider) port).getRequestContext().put(BindingProvider.SESSION_MAINTAIN_PROPERTY, true);
            ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://localhost:8080/webapp/Ping");
            List<Handler> handlerChain = ((BindingProvider) port).getBinding().getHandlerChain();
            handlerChain.add(new SOAPLoggingHandler(true, System.out));
            ((BindingProvider) port).getBinding().setHandlerChain(handlerChain);
            port.hello("hello");
        } catch (Exception ex) {
            System.exit(1);
        }
        System.exit(0);
    }
}
