package com.tyrone.infrastructure.core.test;

import com.tyrone.infrastructure.core.extend.ToStringStyleExtend;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;

@NoArgsConstructor
@AllArgsConstructor
public class Test {

    private String id;

    private String name;

    @Override
    public String toString() {

        return ToStringBuilder.reflectionToString(this, ToStringStyleExtend.JSON_STYLE_EXTEND);

    }
}
