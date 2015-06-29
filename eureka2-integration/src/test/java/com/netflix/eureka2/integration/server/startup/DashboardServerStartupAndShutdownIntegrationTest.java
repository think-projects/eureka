package com.netflix.eureka2.integration.server.startup;

import com.netflix.eureka2.EurekaDashboardRunner;
import com.netflix.eureka2.config.EurekaDashboardConfig;
import com.netflix.eureka2.junit.categories.IntegrationTest;
import com.netflix.eureka2.junit.categories.LongRunningTest;
import com.netflix.eureka2.server.resolver.EurekaClusterResolvers.ResolverType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * @author Tomasz Bak
 */
@Category({IntegrationTest.class, LongRunningTest.class})
public class DashboardServerStartupAndShutdownIntegrationTest extends AbstractStartupAndShutdownIntegrationTest<EurekaDashboardRunner> {

    public static final String SERVER_NAME = "dashboard-server-startupAndShutdown";

    @Test(timeout = 60000)
    public void testStartsWithCommandLineParameters() throws Exception {
        EurekaDashboardConfig config = EurekaDashboardConfig.newBuilder()
                .withAppName(SERVER_NAME)
                .withResolverType(ResolverType.Fixed)
                .withWebAdminPort(0)
                .withShutDownPort(0)
                .withServerList(writeServerList)
                .build();
        EurekaDashboardRunner server = new EurekaDashboardRunner(config);
        executeAndVerifyLifecycle(server, SERVER_NAME);
    }

    @Test(timeout = 60000)
    public void testStartsWithFileBasedConfiguration() throws Exception {
        verifyThatStartsWithFileBasedConfiguration(SERVER_NAME, new EurekaDashboardRunner(SERVER_NAME));
    }
}