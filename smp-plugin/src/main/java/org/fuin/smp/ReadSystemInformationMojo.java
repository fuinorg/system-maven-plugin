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

import java.util.Properties;

import org.apache.commons.exec.OS;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.StaticLoggerBinder;

import com.sun.security.auth.module.UnixSystem;

/**
 * Reads the system properties.
 */
@SuppressWarnings("restriction")
@Mojo(name = "read-system-information", defaultPhase = LifecyclePhase.INITIALIZE, requiresProject = true, threadSafe = true)
public final class ReadSystemInformationMojo extends AbstractMojo {

    private static final Logger LOG = LoggerFactory
            .getLogger(ReadSystemInformationMojo.class);

    @Parameter(defaultValue = "${project}", readonly = true, required = true)
    private MavenProject project;

    /**
     * Prefix that will be added before name of each generic property. Defaults
     * to "org.fuin.smp.".
     */
    @Parameter(name = "generic-prefix", defaultValue = "org.fuin.smp.")
    private String genericPrefix = null;

    /**
     * Prefix that will be added before name of each unix property. Defaults to
     * "org.fuin.smp.unix.".
     */
    @Parameter(name = "unix-prefix", defaultValue = "org.fuin.smp.unix.")
    private String unixPrefix = null;

    /**
     * Returns the generic prefix.
     * 
     * @return Generic prefix.
     */
    public String getGenericPrefix() {
        return genericPrefix;
    }

    /**
     * Sets the generic prefix.
     * 
     * @param genericPrefix
     *            Prefix to set.
     */
    public void setGenericPrefix(final String genericPrefix) {
        this.genericPrefix = genericPrefix;
    }

    /**
     * Returns the Unix prefix.
     * 
     * @return Unix prefix.
     */
    public String getUnixPrefix() {
        return unixPrefix;
    }

    /**
     * Sets the Unix prefix.
     * 
     * @param unixPrefix
     *            Prefix to set.
     */
    public void setUnixPrefix(final String unixPrefix) {
        this.unixPrefix = unixPrefix;
    }

    @Override
    public final void execute() throws MojoExecutionException {
        StaticLoggerBinder.getSingleton().setMavenLog(getLog());
        LOG.info("generic-prefix={}", genericPrefix);
        LOG.info("unix-prefix={}", unixPrefix);

        // Supply variables that are OS dependent
        final String genericUid = genericPrefix + "uid";
        final String genericGid = genericPrefix + "gid";
        final String genericUsername = genericPrefix + "username";
        final Properties projectProperties = project.getProperties();

        if (OS.isFamilyUnix()) {
            final UnixSystem system = new UnixSystem();
            final String unixGid = unixPrefix + "gid";
            final String unixGroups = unixPrefix + "groups";
            final String unixUid = unixPrefix + "uid";
            final String unixUsername = unixPrefix + "username";

            projectProperties.put(genericUid, "" + system.getUid());
            projectProperties.put(genericGid, "" + system.getGid());
            projectProperties.put(genericUsername, system.getUsername());
            projectProperties.put(unixGid, "" + system.getGid());
            projectProperties.put(unixGroups, asString(system.getGroups()));
            projectProperties.put(unixUid, "" + system.getUid());
            projectProperties.put(unixUsername, system.getUsername());

            LOG.info(unixGid + "={}", projectProperties.get(unixGid));
            LOG.info(unixGroups + "={}", projectProperties.get(unixGroups));
            LOG.info(unixUid + "={}", projectProperties.get(unixUid));
            LOG.info(unixUsername + "={}", projectProperties.get(unixUsername));

        } else {
            throw new MojoExecutionException(
                    "Only Unix Family OS are supported");
        }

        LOG.info(genericUid + "={}", projectProperties.get(genericUid));
        LOG.info(genericGid + "={}", projectProperties.get(genericGid));
        LOG.info(genericUsername + "={}",
                projectProperties.get(genericUsername));

    }

    private String asString(final long[] groups) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < groups.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(groups[i]);
        }
        return sb.toString();
    }

}
