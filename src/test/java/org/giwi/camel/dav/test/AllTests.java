/**
 * 
 */
package org.giwi.camel.dav.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author xavier
 * 
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({ 
	FileToDavTempFileNameTest.class,
	FromDavAsyncProcessTest.class,
	FromDavDeleteFileNotStepwiseTest.class,
	FromDavDeleteFileTest.class,
	FromDavDoNotDeleteFileIfProcessFailsTest.class,
	FromDavExclusiveReadNoneStrategyTest.class,
	FromDavFilterNotStepwiseTest.class,
	FromDavFilterTest.class,
	FromFileToDavDefaultRootRenameStrategyTest.class,
	FromFileToDavDeleteTest.class,
	FromFileToDavTest.class
	
	})
public class AllTests {

}
