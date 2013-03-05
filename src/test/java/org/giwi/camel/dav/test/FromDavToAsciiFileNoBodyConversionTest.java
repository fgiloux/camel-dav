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

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Producer;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit testing a dav ASCII transfer that Camel provides the needed conversion
 * to String from the input stream.
 */
public class FromDavToAsciiFileNoBodyConversionTest extends AbstractDavTest {

	private String getDavUrl() {
		return DAV_URL + "/tmp5/camel";
	}

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		prepareDavServer();
	}

	@Test
	public void testFromDavToAsciiFileNoBodyConversion() throws Exception {
		MockEndpoint resultEndpoint = getMockEndpoint("mock:result");
		resultEndpoint.expectedMinimumMessageCount(1);
		resultEndpoint.expectedBodiesReceived("Hello ASCII from FTPServer");
	}

	private void prepareDavServer() throws Exception {
		// prepares the dav Server by creating a file on the server that we want
		// to unit
		// test that we can pool and store as a local file
		Endpoint endpoint = context.getEndpoint(getDavUrl());
		Exchange exchange = endpoint.createExchange();
		exchange.getIn().setBody("Hello ASCII from FTPServer");
		exchange.getIn().setHeader(Exchange.FILE_NAME, "ascii.txt");
		Producer producer = endpoint.createProducer();
		producer.start();
		producer.process(exchange);
		producer.stop();
	}

	@Override
	protected RouteBuilder createRouteBuilder() throws Exception {
		return new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				String fileUrl = "file:target/davtest/?fileExist=Override&noop=true";
				from(getDavUrl()).to(fileUrl, "mock:result");
			}
		};
	}
}