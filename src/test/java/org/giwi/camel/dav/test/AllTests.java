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
@Suite.SuiteClasses({ DavBrowsableEndpointTest.class, DavChangedReadLockFastExistCheckTest.class, DavChangedReadLockTest.class, DavChangedReadLockTimeoutTest.class,
		DavChangedZeroLengthReadLockTest.class, DavConnectTimeoutTest.class, DavConsumerAbsolutePathTest.class, DavConsumerAsyncStressTest.class, DavConsumerBodyAsStringTest.class,
		DavConsumerDirectoriesNotMatchedTest.class, DavConsumerDoneFileNameFixedTest.class, DavConsumerDoneFileNameTest.class, DavConsumerDualDoneFileNameTest.class, DavConsumerExcludeNameTest.class,
		DavConsumerIdempotentRefTest.class, DavConsumerIdempotentTest.class, DavConsumerIncludeNameTest.class, DavConsumerLocalWorkDirectoryAsAbsolutePathTest.class,
		DavConsumerLocalWorkDirectoryDirectTest.class, DavConsumerLocalWorkDirectoryTest.class, DavConsumerLocalWorkDirectoryWorkOnPayloadTest.class, DavConsumerMaxMessagesPerPollTest.class,
		DavConsumerMoveExpressionTest.class, DavConsumerMultipleDirectoriesTest.class, DavConsumerNotEagerMaxMessagesPerPollTest.class, DavConsumerRelativeFileNameTest.class,
		DavConsumerSkipDotFilesTest.class, DavConsumerTemplateTest.class, DavConsumerWithNoFileOptionTest.class, DavEndpointURISanitizedTest.class, DavIllegalOptionsTest.class,
		DavLoginNoRetryTest.class, DavNoReconnectAttemptUnknownHostTest.class, DavPollingConsumerIdleMessageTest.class, DavPollingConsumerTest.class,
		DavProducerAllowNullBodyFileAlreadyExistTest.class, FileToDavTempFileNameTest.class, FromDavAsyncProcessTest.class, FromDavDeleteFileTest.class,
		FromDavDoNotDeleteFileIfProcessFailsTest.class, FromDavExclusiveReadNoneStrategyTest.class, FromDavFilterTest.class, FromDavKeepLastModifiedTest.class,
		FromDavMoveFileAbsoluteFolderRecursiveTest.class, FromDavMoveFilePostfixTest.class, FromDavMoveFilePrefixTest.class, FromDavMoveFileRecursiveTest.class, FromDavMoveFileTest.class,
		FromDavMoveFileToHiddenFolderRecursiveTest.class, FromDavNoEndpointPathRelativeMoveToAbsoluteTest.class, FromDavNoFilesTest.class, FromDavNoopTest.class, FromDavNotDownloadTest.class,
		FromDavPollFileOnlyTest.class, FromDavPreMoveDeleteTest.class, FromDavPreMoveFileExpressionTest.class, FromDavPreMoveFilePostfixTest.class, FromDavPreMoveFilePrefixTest.class,
		FromDavPreMoveNoopTest.class, FromDavRecursiveNoopTest.class, FromDavRegexPatternTest.class, FromDavRemoteFileFilterDirectoryTest.class, FromDavRemoteFileFilterTest.class,
		FromDavRemoteFileSortByExpressionTest.class, FromDavRemoteFileSortByIgnoreCaseExpressionTest.class, FromDavRemoteFileSortByNestedExpressionTest.class, FromDavRemoteFileSorterTest.class,
		FromDavSetNamesWithMultiDirectoriesTest.class, FromDavSimpleNoEndpointPathRelativeMoveToAbsoluteTest.class, FromDavSimpleNoEndpointPathRelativeMoveToRelativeTest.class,
		FromDavSimpleRelativeMoveToAbsoluteTest.class, FromDavSimpleRelativeMoveToRelativeTest.class, FromDavSimulateNetworkIssueRecoverTest.class, FromDavStartingDirAndFileNameClashTest.class,
		FromDavThirdPoolOkTest.class, FromDavToAsciiFileNoBodyConversionTest.class, FromDavToAsciiFileTest.class, FromDavToBinaryFilesTest.class, FromDavToBinaryFileTest.class,
		FromDavToBinarySampleTest.class, FromDavToFileNoFileNameHeaderTest.class, FromDavToMockTest.class, FromDavTwoSlashesIssueTest.class, FromFileToDavDefaultRootRenameStrategyTest.class,
		FromFileToDavDeleteTest.class, FromFileToDavTest.class, FromQueueThenConsumeDavToMockTest.class, DavProducerAllowNullBodyTest.class, PaddyRouteTest.class,
		RecipientListErrorHandlingIssueTest.class, })
public class AllTests {

}
