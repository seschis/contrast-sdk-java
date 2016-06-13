package com.contrastsecurity;

import com.contrastsecurity.http.FilterForm;
import com.contrastsecurity.http.UrlBuilder;
import com.contrastsecurity.models.AgentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UrlBuilderTest {

    private static String applicationId;
    private static String organizationId;
    private static UrlBuilder urlBuilder;

    @BeforeClass
    public static void setUp() {
        applicationId = "test-app";
        organizationId = "test-org";

        urlBuilder = UrlBuilder.getInstance();
    }

    @Test
    public void testProfileOrganizationsUrl() {
        String expectedUrl = "/ng/profile/organizations";

        assertEquals(expectedUrl, urlBuilder.getProfileOrganizationsUrl());
    }

    @Test
    public void testApplicationUrl() {
        String expectedUrl = "/ng/test-org/applications/test-app";

        assertEquals(expectedUrl, urlBuilder.getApplicationUrl(organizationId, applicationId, null));
    }

    @Test
    public void testApplicationsUrl() {
        String expectedUrl = "/ng/test-org/applications?base=false";

        assertEquals(expectedUrl, urlBuilder.getApplicationsUrl(organizationId));
    }

    @Test
    public void testCoverageUrl() {
        String expectedUrl = "/ng/test-org/applications/test-app/coverage";

        assertEquals(expectedUrl, urlBuilder.getCoverageUrl(organizationId, applicationId));
    }

    @Test
    public void testLibrariesUrl() {
        String expectedUrl = "/ng/test-org/applications/test-app/libraries";

        String resultUrl = urlBuilder.getLibrariesUrl(organizationId, applicationId, null);

        assertEquals(expectedUrl, resultUrl);
    }

    @Test
    public void testTracesUrl() {
        String expectedUrl = "/ng/test-org/traces/test-app/filter/workflow/00001/search";

        assertEquals(expectedUrl, urlBuilder.getTracesUrl(organizationId, applicationId, null));
    }

    @Test
    public void testTracesWithFilterUrl() {
        String expectedUrl = "/ng/test-org/traces/test-app/filter/workflow/00001/search";

        assertEquals(expectedUrl, urlBuilder.getTracesWithFilterUrl(organizationId, applicationId, new FilterForm()));
    }

    @Test
    public void testAgentUrls() {
        String expectedJavaUrl = "/ng/test-org/agents/default/java?jvm=1_6";

        assertEquals(expectedJavaUrl, urlBuilder.getAgentUrl(AgentType.JAVA, organizationId, "default"));

        String expectedNodeWithCustomProfileUrl = "/ng/test-org/agents/customnodeprofile/node";

        assertEquals(expectedNodeWithCustomProfileUrl, urlBuilder.getAgentUrl(AgentType.NODE, organizationId, "customnodeprofile"));

        String expectedDotNetProfile = "/ng/test-org/agents/default/dotnet";

        assertEquals(expectedDotNetProfile, urlBuilder.getAgentUrl(AgentType.DOTNET, organizationId, "default"));
    }

}