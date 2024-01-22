package com.tyrone.infrastructure.sdk.common.cais.domain;

public class SegmentRuleFactory {

    public static SegmentRule getNoneInstance(){
        return new SegmentRule.NoneSegmentRule();
    }

    public static SegmentRule getLocalDateTimeInstance(String pattern) {
        return new SegmentRule.LocalDateTimeSegmentRule(pattern);
    }

}
