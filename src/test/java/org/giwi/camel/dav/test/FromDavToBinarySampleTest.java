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

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

/**
 * Unit test used for DAV wiki documentation.
 */
public class FromDavToBinarySampleTest extends CamelTestSupport {

    /**
     * Test dummy.
     * 
     * @throws Exception
     *             the exception
     */
    @Test
    public void testDummy() throws Exception {
	// this is a noop test
    }

    // START SNIPPET: e1
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
		// we use a delay of 60 minutes (eg. once pr. hour we poll the
		// DAV server
		long delay = 60 * 60 * 1000L;

		// from the given DAV server we poll (= download) all the files
		// from the public/reports folder as BINARY types and store this
		// as files
		// in a local directory. Camel will use the filenames from the
		// DAVServer

		// notice that the DAVConsumer properties must be prefixed with
		// "consumer." in the URL
		// the delay parameter is from the FileConsumer component so we
		// should use consumer.delay as
		// the URI parameter name. The DAV Component is an extension of
		// the File Component.
		from(
			"dav://tiger:scott@localhost/webdav/reports?consumer.delay="
				+ delay).to("file://tmpOut/test-reports");
	    }
	};
    }
    // END SNIPPET: e1
}