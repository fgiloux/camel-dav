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

import java.io.File;

import org.apache.camel.Exchange;
import org.apache.camel.builder.NotifyBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.junit.Test;

/**
 * The Class FileToDavTempFileNameTest.
 */
public class FileToDavTempFileNameTest extends AbstractDavTest {

    /**
     * Test file to dav.
     * 
     * @throws Exception
     *             the exception
     */
    @Test
    public void testFileToDav() throws Exception {
	NotifyBuilder notify = new NotifyBuilder(context).whenDone(1).create();

	template.sendBodyAndHeader("file:src/main/data", "Hello World",
		Exchange.FILE_NAME, "sub/hello.txt");

	assertTrue(notify.matchesMockWaitTime());
	Thread.sleep(1000);
	File file = new File(DAV_ROOT_DIR + "/sub/hello.txt");
	assertTrue("File should exists " + file, file.exists());
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
		from("file:src/main/data?recursive=true")
			.to(DAV_URL
				+ "?fileName=${file:name}&tempFileName=${file:onlyname}.part");
	    }
	};
    }
}
