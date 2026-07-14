package com.tyrone.infrastructure.common.domain.cais;

import com.tyrone.infrastructure.sdk.common.domain.cais.SequenceType;
import com.tyrone.infrastructure.sdk.common.pl.GenCAISIdRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class SnowflakeSequenceGenerator implements SequenceGenerator {

    private final long workerId;
    private final long datacenterId = 1L;
    private final AtomicLong sequence = new AtomicLong(0L);

    private static final long START_TIMESTAMP = 1700000000000L;
    private static final long WORKER_ID_BITS = 5L;
    private static final long DATACENTER_ID_BITS = 5L;
    private static final long SEQUENCE_BITS = 12L;
    private static final long MAX_SEQUENCE = ~(-1L << SEQUENCE_BITS);
    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;
    private static final long DATACENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;
    private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATACENTER_ID_BITS;

    private long lastTimestamp = -1L;

    public SnowflakeSequenceGenerator() {
        this.workerId = generateWorkerId();
    }

    @Override
    public String generate(GenCAISIdRequest cmd) {
        long id = nextId();
        String segment = cmd.getSegmentRule().getSegmentRuleValue();
        String joiner = Objects.toString(cmd.getJoiner(), "");

        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotBlank(cmd.getPrefix())) {
            sb.append(cmd.getPrefix()).append(joiner);
        }
        if (StringUtils.isNotBlank(segment)) {
            sb.append(segment).append(joiner);
        }
        sb.append(StringUtils.leftPad(String.valueOf(id), cmd.getLength(), "0"));

        return sb.toString();
    }

    @Override
    public SequenceType getType() {
        return SequenceType.SNOWFLAKE;
    }

    private synchronized long nextId() {
        long timestamp = System.currentTimeMillis();

        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        if (lastTimestamp == timestamp) {
            long seq = sequence.incrementAndGet();
            if (seq > MAX_SEQUENCE) {
                timestamp = tilNextMillis(lastTimestamp);
                sequence.set(0L);
            }
        } else {
            sequence.set(0L);
        }

        lastTimestamp = timestamp;

        return ((timestamp - START_TIMESTAMP) << TIMESTAMP_LEFT_SHIFT)
                | (datacenterId << DATACENTER_ID_SHIFT)
                | (workerId << WORKER_ID_SHIFT)
                | sequence.get();
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    private long generateWorkerId() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            byte[] ipAddressByteArray = address.getAddress();
            return ((ipAddressByteArray[ipAddressByteArray.length - 2] & 0B11) << 3)
                    | (ipAddressByteArray[ipAddressByteArray.length - 1] & 0B111);
        } catch (UnknownHostException e) {
            return Math.abs(ManagementFactory.getRuntimeMXBean().getName().hashCode()) % 32;
        }
    }

}