package com.ciberpath.edusec.services;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

public class ProbarTest extends CamelTestSupport {

    @Test
    public void testMock() throws Exception {
        getMockEndpoint("mock:result").expectedBodiesReceived("Hello World");

        template.sendBody("direct:start", "Hello World");
        MockEndpoint.assertIsSatisfied(context);
    }
    
    @Test
    public void testMock2() throws Exception {
        getMockEndpoint("mock:result").expectedBodiesReceived("Hello World");

        Object respuesta = template.requestBody("direct:start", "Hello World");
        String recibi = String.valueOf(respuesta).toString();
        System.out.println("esta es:" + recibi);
    }

    @Override
    protected RoutesBuilder createRouteBuilder() {
        return new RouteBuilder() {
            @Override
            public void configure() {
                from("direct:start").to("mock:result");
            }
        };
    }

}
