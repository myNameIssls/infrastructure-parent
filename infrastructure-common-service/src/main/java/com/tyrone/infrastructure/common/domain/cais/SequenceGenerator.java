package com.tyrone.infrastructure.common.domain.cais;

import com.tyrone.infrastructure.sdk.common.domain.cais.SequenceType;
import com.tyrone.infrastructure.sdk.common.pl.GenCAISIdRequest;

public interface SequenceGenerator {

    String generate(GenCAISIdRequest cmd);

    SequenceType getType();

}