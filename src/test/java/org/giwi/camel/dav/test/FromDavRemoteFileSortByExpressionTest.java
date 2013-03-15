/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.giwi.camel.dav.test;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test to verify remotefile sortby option.
 */
public class FromDavRemoteFileSortByExpressionTest extends AbstractDavTest {

    private String getDavUrl() {
	return DAV_URL + "/sortby?consumer.delay=5000";
    }

    @Override
    @Before
    public void setUp() throws Exception {
	super.setUp();
	prepareDavServer();
    }

    @Override
    public boolean isUseRouteBuilder() {
	return false;
    }

    @Test
    public void testSortFiles() throws Exception {
	context.addRoutes(new RouteBuilder() {
	    @Override
	    public void configure() throws Exception {
		from(getDavUrl() + "&sortBy=file:ext").to("mock:result");
	    }
	});
	context.start();

	MockEndpoint mock = getMockEndpoint("mock:result");
	mock.expectedBodiesReceived("Hello Paris", "Hello London",
		"Hello Copenhagen");

	assertMockEndpointsSatisfied();
    }

    @Test
    public void testSortFilesReverse() throws Exception {
	context.addRoutes(new RouteBuilder() {
	    @Override
	    public void configure() throws Exception {
		from(getDavUrl() + "&sortBy=reverse:file:ext").to(
			"mock:reverse");
	    }
	});
	context.start();

	MockEndpoint mock = getMockEndpoint("mock:reverse");
	mock.expectedBodiesReceived("Hello Copenhagen", "Hello London",
		"Hello Paris");

	assertMockEndpointsSatisfied();
    }

    private void prepareDavServer() throws Exception {
	// prepares the DAV Server by creating files on the server that we want
	// to unit
	// test that we can pool
	sendFile(getDavUrl(), "Hello Paris", "paris.dat");
	sendFile(getDavUrl(), "Hello London", "london.txt");
	sendFile(getDavUrl(), "Hello Copenhagen", "copenhagen.xml");
    }
}