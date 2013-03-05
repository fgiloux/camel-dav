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

import org.apache.camel.Exchange;
import org.junit.Test;

/**
 * @version
 */
public class DavProducerFileExistOverrideTwoUploadTest extends AbstractDavTest {

	protected String getDavUrl() {
		return DAV_URL + "/exist?tempPrefix=upload-&fileExist=Override&disconnect=true";
	}

	@Test
	public void testOverride() throws Exception {
		template.sendBodyAndHeader(getDavUrl(), "Hello World", Exchange.FILE_NAME, "hello.txt");

		// the 1st file should be stored
		File file = new File(DAV_ROOT_DIR + "/exist/hello.txt");
		assertTrue(file.exists());

		String body = context.getTypeConverter().convertTo(String.class, file);
		assertEquals("Hello World", body);

		// just wait a bit before upload 2nd file
		Thread.sleep(1000);

		template.sendBodyAndHeader(getDavUrl(), "Bye World", Exchange.FILE_NAME, "hello.txt");

		// the 2nd file should also exists as we stored with override
		file = new File(DAV_ROOT_DIR + "/exist/hello.txt");
		assertTrue(file.exists());

		body = context.getTypeConverter().convertTo(String.class, file);
		assertEquals("Bye World", body);
	}

	@Override
	public boolean isUseRouteBuilder() {
		return false;
	}
}