/**
 * Copyright (C) 2015 Michael Schnell. All rights reserved. 
 * http://www.fuin.org/
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or (at your option) any
 * later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library. If not, see http://www.gnu.org/licenses/.
 */
package org.fuin.smp;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.maven.plugin.MojoExecutionException;
import org.junit.Test;

/**
 * Test for {@link ReadSystemInformationMojo}.
 */
public class ReadSystemInformationMojoTest {

    // CHECKSTYLE:OFF Test

    @Test
    public void testSetGetPrefix() throws MojoExecutionException {

        // PREPARE
        final String genericPrefix = "a";
        final String unixPrefix = "b";
        final ReadSystemInformationMojo testee = new ReadSystemInformationMojo();

        // TEST
        testee.setGenericPrefix(genericPrefix);
        testee.setUnixPrefix(unixPrefix);

        // VERIFY
        assertThat(testee.getGenericPrefix()).isEqualTo(genericPrefix);
        assertThat(testee.getUnixPrefix()).isEqualTo(unixPrefix);

    }

    // CHECKSTYLE:ON

}
