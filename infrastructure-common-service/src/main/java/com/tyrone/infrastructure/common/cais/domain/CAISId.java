package com.tyrone.infrastructure.common.cais.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CAISId {

    @Column(name = "prefix", nullable = false, columnDefinition = "varchar(50) comment '前缀'")
    private String prefix;

    @Column(name = "segment_rule", nullable = false, columnDefinition = "varchar(50) comment '分断规则'")
    private String segmentRule;

}
