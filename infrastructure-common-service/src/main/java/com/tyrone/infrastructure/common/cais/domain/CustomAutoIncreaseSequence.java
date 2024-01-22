package com.tyrone.infrastructure.common.cais.domain;

import com.tyrone.infrastructure.sdk.common.cais.domain.SegmentRule;
import com.tyrone.infrastructure.sdk.common.cais.pl.GenCAISIdCommand;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.atomic.AtomicInteger;

@Table
@Entity
public class CustomAutoIncreaseSequence {

    @EmbeddedId
    private CAISId caisId;

    @Column(name = "initial_value", nullable = false, columnDefinition = "varchar(50) comment '序列初始值'")
    private AtomicInteger initialValue;

    @Column(name = "sequence", nullable = false, columnDefinition = "varchar(50) comment '序列'")
    private AtomicInteger sequence;

    @Column(name = "length", nullable = false, columnDefinition = "varchar(50) comment '序列长度'")
    private Integer length;
    @Column(name = "joiner", nullable = false, columnDefinition = "varchar(50) comment '连接符'")
    private String joiner;

    public CustomAutoIncreaseSequence(){}

    public CustomAutoIncreaseSequence(GenCAISIdCommand cmd){

        this.caisId = new CAISId(cmd.getPrefix(), cmd.getSegmentRule().getSegmentRuleValue());
        this.initialValue = new AtomicInteger(1);
        this.sequence = new AtomicInteger(1);
        this.length = cmd.getLength();
        this.joiner = cmd.getJoiner();

    }

    public void increase() {

        this.sequence.incrementAndGet();

    }

    public String format() {

        StringBuffer sb = new StringBuffer()
                .append(this.caisId.getPrefix())
                .append(this.joiner)
                .append(this.caisId.getSegmentRule())
                .append(this.joiner)
                .append(StringUtils.leftPad(String.valueOf(this.sequence), this.length, "0"));

        return sb.toString();

    }


    public static void main(String[] args) {
        SegmentRule segmentRule = new SegmentRule.LocalDateTimeSegmentRule();
        GenCAISIdCommand command = new GenCAISIdCommand();
        command.setSegmentRule(segmentRule);
        command.setPrefix("CZ");
        command.setJoiner("-");

        CustomAutoIncreaseSequence cais = new CustomAutoIncreaseSequence(command);
        String format = cais.format();
        System.out.println(format);
        cais.increase();
        format = cais.format();
        System.out.println(format);

    }

}
