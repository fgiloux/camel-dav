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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.camel.AsyncCallback;
import org.apache.camel.AsyncProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.junit.Test;

/**
 * The Class FromDavAsyncProcessTest.
 */
public class FromDavAsyncProcessTest extends AbstractDavTest {

    /**
     * Gets the dav url.
     * 
     * @return the dav url
     */
    protected String getDavUrl() {
	return DAV_URL + "/async?delete=true";
    }

    /**
     * Test dav async process.
     * 
     * @throws Exception
     *             the exception
     */
    @Test
    public void testDavAsyncProcess() throws Exception {
	template.sendBodyAndHeader(getDavUrl(), "Hello World",
		Exchange.FILE_NAME, "hello.txt");
	template.sendBodyAndHeader(getDavUrl(), "Bye World",
		Exchange.FILE_NAME, "bye.txt");
	getMockEndpoint("mock:result").expectedMessageCount(2);
	getMockEndpoint("mock:result").expectedHeaderReceived("foo", 123);
	context.startRoute("foo");

	assertMockEndpointsSatisfied();

	Thread.sleep(1000);

	File hello = new File(DAV_ROOT_DIR + "/async/hello.txt");
	assertFalse("File should not exist " + hello, hello.exists());

	File bye = new File(DAV_ROOT_DIR + "/async/bye.txt");
	assertFalse("File should not exist " + bye, bye.exists());
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
		from(getDavUrl()).routeId("foo").noAutoStartup()
			.process(new MyAsyncProcessor()).to("mock:result");
	    }
	};
    }

    /**
     * The Class MyAsyncProcessor.
     * 
     * @author xavier
     */
    private class MyAsyncProcessor implements AsyncProcessor {

	/** The executor. */
	private final ExecutorService executor = Executors
		.newSingleThreadExecutor();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.camel.AsyncProcessor#process(org.apache.camel.Exchange,
	 * org.apache.camel.AsyncCallback)
	 */
	@Override
	public boolean process(final Exchange exchange,
		final AsyncCallback callback) {
	    executor.submit(new Runnable() {
		@Override
		public void run() {
		    try {
			Thread.sleep(1000);
		    } catch (InterruptedException e) {
			// ignore
		    }
		    exchange.getIn().setHeader("foo", 123);
		    callback.done(false);
		}
	    });

	    return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
	    // noop
	}
    }
}
