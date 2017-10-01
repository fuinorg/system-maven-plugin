package org.fuin.smp.maven.test;

import static org.fest.assertions.Assertions.assertThat;

import java.io.IOException;

import org.junit.Test;

public class GetPropertiesIT {

    @Test
    public void testVerifyGenericProperties() throws IOException {

        assertThat(System.getProperty("xuser")).isNotEmpty();
        assertThat(System.getProperty("xuser")).isNotEqualTo("@{g.username}");
        
        assertThat(System.getProperty("xuid")).isNotEmpty();
        assertThat(System.getProperty("xuid")).isNotEqualTo("@{g.uid}");
        
        assertThat(System.getProperty("xgid")).isNotEmpty();
        assertThat(System.getProperty("xgid")).isNotEqualTo("@{g.gid}");
        
    }

}
