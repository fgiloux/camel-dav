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
import org.apache.camel.component.file.GenericFileExist;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Before;
import org.junit.Test;

/**
 * The Class DavProducerFileExistAppendTest.
 * 
 * @version
 */
public class DavProducerFileExistAppendTest extends AbstractDavTest {

    /** The Constant ON_WINDOWS. */
    private static final boolean ON_WINDOWS = System.getProperty("os.name")
	    .startsWith("Windows");

    /**
     * Gets the dav url.
     * 
     * @return the dav url
     */
    private String getDavUrl() {
	return DAV_URL + "/exist?delay=2000&noop=true&fileExist="
		+ GenericFileExist.Append;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.camel.test.junit4.CamelTestSupport#setUp()
     */
    @Override
    @Before
    public void setUp() throws Exception {
	super.setUp();
	deleteDirectory("tmpOut/exist");

	template.sendBodyAndHeader(getDavUrl(), "Hello World\n",
		Exchange.FILE_NAME, "hello.txt");
    }

    /**
     * Test append.
     * 
     * @throws Exception
     *             the exception
     */
    @Test
    public void testAppend() throws Exception {
	MockEndpoint mock = getMockEndpoint("mock:result");
	String expectBody = "Hello World\nBye World";
	if (ON_WINDOWS) {
	    expectBody = "Hello World\r\nBye World";
	}
	mock.expectedBodiesReceived(expectBody);
	mock.expectedFileExists(DAV_ROOT_DIR + "/exist/hello.txt", expectBody);
	template.sendBodyAndHeader(getDavUrl(), "Bye World",
		Exchange.FILE_NAME, "hello.txt");

	assertMockEndpointsSatisfied();
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
		from(getDavUrl()).to("mock:result");
	    }
	};
    }
}