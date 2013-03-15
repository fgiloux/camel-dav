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

import java.io.File;

import org.apache.camel.converter.IOConverter;
import org.junit.Test;

/**
 * Unit test to verify that Camel can build remote directory on DAV server if
 * missing (full or part of).
 */
public class DavProducerTempPrefixTest extends AbstractDavTest {

    @Override
    public boolean isUseRouteBuilder() {
	return false;
    }

    private String getDavUrl() {
	return DAV_URL + "/upload/user/claus?tempPrefix=.uploading";
    }

    @Test
    public void testProduceTempPrefixTest() throws Exception {
	sendFile(getDavUrl(), "Hello World", "claus.txt");

	File file = new File(DAV_ROOT_DIR + "/upload/user/claus/claus.txt");
	assertTrue("The uploaded file should exists", file.exists());
	assertEquals("Hello World", IOConverter.toString(file, null));
    }
}