/*
 * Copyright (C) 2014 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.  
 * For more information, contact <http://www.ciminc.com>
 */
package com.ciminc.test;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author ben
 */
@WebService(serviceName = "Ping", name = "Ping")
public class Ping {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
}
