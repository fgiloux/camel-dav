/**
 *  Copyright 2013 Giwi Softwares (http://giwi.free.fr)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0 
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.giwi.camel.dav.test;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Test;

/**
 * The Class FromFileToDavTest.
 * 
 * @version
 */
public class FromFileToDavTest extends AbstractDavTest {

    /**
     * Test from file to dav.
     * 
     * @throws Exception
     *             the exception
     */
    @Test
    public void testFromFileToDav() throws Exception {
	MockEndpoint mock = getMockEndpoint("mock:result");
	mock.expectedMessageCount(2);
	template.sendBodyAndHeader("file:src/main/data", "Hello World",
		Exchange.FILE_NAME, "hello.txt");

	assertMockEndpointsSatisfied();
	Thread.sleep(1000);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.camel.test.junit4.CamelTestSupport#createRouteBuilder()
     */
    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
	return new RouteBuilder() {
	    @Override
	    public void configure() throws Exception {
		from(DAV_URL + "?consumer.initialDelay=3000").to("mock:result");
		from("file:src/main/data?noop=true&consumer.delay=3000").to(
			DAV_URL + "?consumer.initialDelay=3000");
	    }
	};
    }
}