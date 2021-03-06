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

import java.io.FileOutputStream;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Test;

/**
 * The Class DavChangedZeroLengthReadLockTest.
 */
public class DavChangedZeroLengthReadLockTest extends AbstractDavTest {

    /**
     * Gets the dav url.
     * 
     * @return the dav url
     */
    protected String getDavUrl() {
	return DAV_URL
		+ "/changed?readLock=changed&readLockCheckInterval=1000&readLockMinLength=0&delete=true";
    }

    /**
     * Test changed read lock.
     * 
     * @throws Exception
     *             the exception
     */
    @Test
    public void testChangedReadLock() throws Exception {
	MockEndpoint mock = getMockEndpoint("mock:result");
	mock.expectedMessageCount(1);
	mock.expectedFileExists("tmpOut/changed/out/zerofile.dat");

	writeZeroFile();

	assertMockEndpointsSatisfied();
    }

    /**
     * Write zero file.
     * 
     * @throws Exception
     *             the exception
     */
    private void writeZeroFile() throws Exception {
	createDirectory(DAV_ROOT_DIR + "/changed");
	FileOutputStream fos = new FileOutputStream(DAV_ROOT_DIR
		+ "/changed/zerofile.dat", true);
	fos.flush();
	fos.close();
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
		from(getDavUrl()).to("file:tmpOut/changed/out", "mock:result");
	    }
	};
    }

}
