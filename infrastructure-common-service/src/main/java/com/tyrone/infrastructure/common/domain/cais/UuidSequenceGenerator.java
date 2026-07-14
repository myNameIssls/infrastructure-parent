package com.tyrone.infrastructure.common.domain.cais;

import com.tyrone.infrastructure.sdk.common.domain.cais.SequenceType;
import com.tyrone.infrastructure.sdk.common.pl.GenCAISIdRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
public class UuidSequenceGenerator implements SequenceGenerator {

    @Override
    public String generate(GenCAISIdRequest cmd) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String segment = cmd.getSegmentRule().getSegmentRuleValue();
        String joiner = Objects.toString(cmd.getJoiner(), "");

        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotBlank(cmd.getPrefix())) {
            sb.append(cmd.getPrefix()).append(joiner);
        }
        if (StringUtils.isNotBlank(segment)) {
            sb.append(segment).append(joiner);
        }
        sb.append(uuid);

        return sb.toString();
    }

    @Override
    public SequenceType getType() {
        return SequenceType.UUID;
    }

}