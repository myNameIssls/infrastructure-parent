package com.tyrone.infrastructure.sdk.common.domain.cais;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class SegmentRule {

    protected String pattern;

    public abstract String getSegmentRuleValue();

    static class NoneSegmentRule extends SegmentRule {

        @Override
        public String getSegmentRuleValue() {
            return StringUtils.EMPTY;
        }
    }

    public static class LocalDateTimeSegmentRule extends SegmentRule {

        public LocalDateTimeSegmentRule(){

        }
        public LocalDateTimeSegmentRule(String pattern) {
            super();
            this.pattern = pattern;
        }

        @Override
        public String getSegmentRuleValue() {

            DateTimeFormatter formatter = StringUtils.isBlank(this.pattern) ?
                    DateTimeFormatter.ofPattern("yyyyMMdd") : DateTimeFormatter.ofPattern(this.pattern);

            return LocalDateTime.now().format(formatter);
        }
    }

}
