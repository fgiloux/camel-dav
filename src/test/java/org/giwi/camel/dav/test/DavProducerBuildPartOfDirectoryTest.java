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

import org.apache.camel.converter.IOConverter;
import org.junit.Test;

/**
 * Unit test to verify that Camel can build remote directory on DAV server if
 * missing (full or part of).
 */
public class DavProducerBuildPartOfDirectoryTest extends AbstractDavTest {

    /**
     * Gets the dav url.
     * 
     * @return the dav url
     */
    private String getDavUrl() {
	return DAV_URL + "/upload/user/claus";
    }

    /**
     * Test produce and build part of remot folder test.
     * 
     * @throws Exception
     *             the exception
     */
    @Test
    public void testProduceAndBuildPartOfRemotFolderTest() throws Exception {
	sendFile(getDavUrl(), "Bye World", "claus.txt");

	File file = new File(DAV_ROOT_DIR + "/upload/user/claus/claus.txt");
	assertTrue("The uploaded file should exists", file.exists());
	assertEquals("Bye World", IOConverter.toString(file, null));
    }
}