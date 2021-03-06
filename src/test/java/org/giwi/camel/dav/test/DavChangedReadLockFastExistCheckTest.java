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

/**
 * The Class DavChangedReadLockFastExistCheckTest.
 */
public class DavChangedReadLockFastExistCheckTest extends
	DavChangedReadLockTest {

    /*
     * (non-Javadoc)
     * 
     * @see org.giwi.camel.dav.test.DavChangedReadLockTest#getDavUrl()
     */
    @Override
    protected String getDavUrl() {
	return DAV_URL
		+ "/changed?readLock=changed&readLockCheckInterval=1000&delete=true&fastExistsCheck=true";
    }

}
