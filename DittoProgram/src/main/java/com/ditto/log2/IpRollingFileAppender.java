package com.ditto.log2;/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache license, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the license for the specific language governing permissions and
 * limitations under the license.
 */

import com.mipt.fm.util.IpUtil;
import com.mipt.fm.util.ReadOuterFileUtils;
import com.mipt.fm.util.StatLogUtils;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractOutputStreamAppender;
import org.apache.logging.log4j.core.appender.rolling.DefaultRolloverStrategy;
import org.apache.logging.log4j.core.appender.rolling.RollingFileManager;
import org.apache.logging.log4j.core.appender.rolling.RolloverStrategy;
import org.apache.logging.log4j.core.appender.rolling.TriggeringPolicy;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.plugins.*;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.apache.logging.log4j.core.net.Advertiser;
import org.apache.logging.log4j.core.util.Booleans;
import org.apache.logging.log4j.core.util.Integers;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.zip.Deflater;


/**
 * An appender that writes to files and can roll over at intervals.
 */
@Plugin(name = IpRollingFileAppender.PLUGIN_NAME, category = "Core", elementType = Appender.ELEMENT_TYPE, printObject = true)
public final class IpRollingFileAppender extends AbstractOutputStreamAppender<RollingFileManager> {

    public static final String PLUGIN_NAME = "IpRollingFile";

    /**
     * Builds FileAppender instances.
     *
     * @param <B>
     *            This builder class
     */
    public static class Builder<B extends Builder<B>> extends AbstractOutputStreamAppender.Builder<B>
            implements org.apache.logging.log4j.core.util.Builder<IpRollingFileAppender> {

        @PluginBuilderAttribute
        @Required
        private String fileName;

        @PluginBuilderAttribute
        @Required
        private String filePattern;

        @PluginBuilderAttribute
        private boolean append = true;

        @PluginBuilderAttribute
        private boolean locking;

        @PluginElement("Policy")
        @Required
        private TriggeringPolicy policy;

        @PluginElement("Strategy")
        private RolloverStrategy strategy;

        @PluginBuilderAttribute
        private boolean advertise;

        @PluginBuilderAttribute
        private String advertiseUri;

        @PluginBuilderAttribute
        private boolean createOnDemand;

        @PluginConfiguration
        private Configuration configuration;

        @Override
        public IpRollingFileAppender build() {
            // Even though some variables may be annotated with @Required, we must still perform validation here for
            // call sites that build builders programmatically.
            final boolean isBufferedIo = isBufferedIo();
            final int bufferSize = getBufferSize();
            if (getName() == null) {
                LOGGER.error("RollingFileAppender '{}': No name provided.", getName());
                return null;
            }

            if (!isBufferedIo && bufferSize > 0) {
                LOGGER.warn("RollingFileAppender '{}': The bufferSize is set to {} but bufferedIO is not true", getName(), bufferSize);
            }

            if (fileName == null) {
                LOGGER.error("RollingFileAppender '{}': No file name provided.", getName());
                return null;
            }

            if (filePattern == null) {
                LOGGER.error("RollingFileAppender '{}': No file name pattern provided.", getName());
                return null;
            }
            String taskId = ReadOuterFileUtils.getProperty(StatLogUtils.FM_STAT_TASK_ID);
            fileName = fileName.replaceAll("nodeIp", "taskId_" + taskId + "_node" + IpUtil.getIpSuffix());
            filePattern = filePattern.replaceAll("nodeIp", "taskId_" + taskId + "_node" + IpUtil.getIpSuffix());
            String tmpFileName = fileName.substring(fileName.lastIndexOf("/"));
            String tmpFilePattern = filePattern.substring(filePattern.lastIndexOf("/"));
            //重定义项目自定义配置文件路径
            String logFilePath = ReadOuterFileUtils.getProperty(StatLogUtils.FM_STAT_LOG_PATH) + "/taskId" + taskId + "/";
            fileName = logFilePath + tmpFileName;
            filePattern = tmpFilePattern + tmpFileName;
            if (policy == null) {
                LOGGER.error("RollingFileAppender '{}': No TriggeringPolicy provided.", getName());
                return null;
            }

            if (strategy == null) {
                strategy = DefaultRolloverStrategy.createStrategy(null, null, null,
                        String.valueOf(Deflater.DEFAULT_COMPRESSION), null, true, configuration);
            }

            if (strategy == null) {
                strategy = DefaultRolloverStrategy.createStrategy(null, null, null,
                        String.valueOf(Deflater.DEFAULT_COMPRESSION), null, true, configuration);
            }

            final Layout<? extends Serializable> layout = getOrCreateLayout();
            final RollingFileManager manager = RollingFileManager.getFileManager(fileName, filePattern, append,
                    isBufferedIo, policy, strategy, advertiseUri, layout, bufferSize, isImmediateFlush(),
                    createOnDemand, configuration);
            if (manager == null) {
                return null;
            }

            manager.initialize();

            return new IpRollingFileAppender(getName(), layout, getFilter(), manager, fileName, filePattern,
                    isIgnoreExceptions(), isImmediateFlush(), advertise ? configuration.getAdvertiser() : null);
        }

        public String getAdvertiseUri() {
            return advertiseUri;
        }

        public Configuration getConfiguration() {
            return configuration;
        }

        public String getFileName() {
            return fileName;
        }

        public boolean isAdvertise() {
            return advertise;
        }

        public boolean isAppend() {
            return append;
        }

        public boolean isCreateOnDemand() {
            return createOnDemand;
        }

        public boolean isLocking() {
            return locking;
        }

        public B withAdvertise(final boolean advertise) {
            this.advertise = advertise;
            return asBuilder();
        }

        public B withAdvertiseUri(final String advertiseUri) {
            this.advertiseUri = advertiseUri;
            return asBuilder();
        }

        public B withAppend(final boolean append) {
            this.append = append;
            return asBuilder();
        }

        public B withConfiguration(final Configuration config) {
            this.configuration = config;
            return asBuilder();
        }

        public B withFileName(final String fileName) {
            this.fileName = fileName;
            return asBuilder();
        }

        public B withCreateOnDemand(final boolean createOnDemand) {
            this.createOnDemand = createOnDemand;
            return asBuilder();
        }

        public B withLocking(final boolean locking) {
            this.locking = locking;
            return asBuilder();
        }

        public String getFilePattern() {
            return filePattern;
        }

        public TriggeringPolicy getPolicy() {
            return policy;
        }

        public RolloverStrategy getStrategy() {
            return strategy;
        }

        public B withFilePattern(final String filePattern) {
            this.filePattern = filePattern;
            return asBuilder();
        }

        public B withPolicy(final TriggeringPolicy policy) {
            this.policy = policy;
            return asBuilder();
        }

        public B withStrategy(final RolloverStrategy strategy) {
            this.strategy = strategy;
            return asBuilder();
        }

    }

    private static final int DEFAULT_BUFFER_SIZE = 8192;

    private final String fileName;
    private final String filePattern;
    private Object advertisement;
    private final Advertiser advertiser;

    private IpRollingFileAppender(final String name, final Layout<? extends Serializable> layout, final Filter filter,
                                  final RollingFileManager manager, final String fileName, final String filePattern,
                                  final boolean ignoreExceptions, final boolean immediateFlush, final Advertiser advertiser) {
        super(name, layout, filter, ignoreExceptions, immediateFlush, manager);
        if (advertiser != null) {
            final Map<String, String> configuration = new HashMap<>(layout.getContentFormat());
            configuration.put("contentType", layout.getContentType());
            configuration.put("name", name);
            advertisement = advertiser.advertise(configuration);
        }
        this.fileName = fileName;
        this.filePattern = filePattern;
        this.advertiser = advertiser;
    }

    @Override
    public boolean stop(final long timeout, final TimeUnit timeUnit) {
        setStopping();
        final boolean stopped = super.stop(timeout, timeUnit, false);
        if (advertiser != null) {
            advertiser.unadvertise(advertisement);
        }
        setStopped();
        return stopped;
    }

    /**
     * Writes the log entry rolling over the file when required.

     * @param event The LogEvent.
     */
    @Override
    public void append(final LogEvent event) {
        getManager().checkRollover(event);
        super.append(event);
    }

    /**
     * Returns the File name for the Appender.
     * @return The file name.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Returns the file pattern used when rolling over.
     * @return The file pattern.
     */
    public String getFilePattern() {
        return filePattern;
    }

    /**
     * Returns the triggering policy.
     * @param <T> TriggeringPolicy type
     * @return The TriggeringPolicy
     */
    public <T extends TriggeringPolicy> T getTriggeringPolicy() {
        return getManager().getTriggeringPolicy();
    }

    /**
     * Creates a RollingFileAppender.
     * @param fileName The name of the file that is actively written to. (required).
     * @param filePattern The pattern of the file name to use on rollover. (required).
     * @param append If true, events are appended to the file. If false, the file
     * is overwritten when opened. Defaults to "true"
     * @param name The name of the Appender (required).
     * @param bufferedIO When true, I/O will be buffered. Defaults to "true".
     * @param bufferSizeStr buffer size for buffered IO (default is 8192).
     * @param immediateFlush When true, events are immediately flushed. Defaults to "true".
     * @param policy The triggering policy. (required).
     * @param strategy The rollover strategy. Defaults to DefaultRolloverStrategy.
     * @param layout The layout to use (defaults to the default PatternLayout).
     * @param filter The Filter or null.
     * @param ignore If {@code "true"} (default) exceptions encountered when appending events are logged; otherwise
     *               they are propagated to the caller.
     * @param advertise "true" if the appender configuration should be advertised, "false" otherwise.
     * @param advertiseUri The advertised URI which can be used to retrieve the file contents.
     * @param config The Configuration.
     * @return A RollingFileAppender.
     * @deprecated Use {@link #newBuilder()}.
     */
    @Deprecated
    public static IpRollingFileAppender createAppender(
            // @formatter:off
            final String fileName,
            final String filePattern,
            final String append,
            final String name,
            final String bufferedIO,
            final String bufferSizeStr,
            final String immediateFlush,
            final TriggeringPolicy policy,
            final RolloverStrategy strategy,
            final Layout<? extends Serializable> layout,
            final Filter filter,
            final String ignore,
            final String advertise,
            final String advertiseUri,
            final Configuration config) {
        // @formatter:on
        final int bufferSize = Integers.parseInt(bufferSizeStr, DEFAULT_BUFFER_SIZE);
        // @formatter:off
        return newBuilder()
                .withAdvertise(Boolean.parseBoolean(advertise))
                .withAdvertiseUri(advertiseUri)
                .withAppend(Booleans.parseBoolean(append, true))
                .withBufferedIo(Booleans.parseBoolean(bufferedIO, true))
                .withBufferSize(bufferSize)
                .withConfiguration(config)
                .withFileName(fileName)
                .withFilePattern(filePattern)
                .withFilter(filter)
                .withIgnoreExceptions(Booleans.parseBoolean(ignore, true))
                .withImmediateFlush(Booleans.parseBoolean(immediateFlush, true))
                .withLayout(layout)
                .withCreateOnDemand(false)
                .withLocking(false)
                .withName(name)
                .withPolicy(policy)
                .withStrategy(strategy)
                .build();
        // @formatter:on
    }

    @PluginBuilderFactory
    public static <B extends Builder<B>> B newBuilder() {
        return new Builder<B>().asBuilder();
    }
}
