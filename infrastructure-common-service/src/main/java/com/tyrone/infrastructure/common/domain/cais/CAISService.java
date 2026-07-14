package com.tyrone.infrastructure.common.domain.cais;

import com.tyrone.infrastructure.sdk.common.domain.cais.SequenceType;
import com.tyrone.infrastructure.sdk.common.pl.GenCAISIdRequest;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Service
public class CAISService {

    private final Map<SequenceType, SequenceGenerator> generatorMap;

    public CAISService(List<SequenceGenerator> generators) {
        this.generatorMap = new EnumMap<>(SequenceType.class);
        for (SequenceGenerator generator : generators) {
            generatorMap.put(generator.getType(), generator);
        }
    }

    public String generate(GenCAISIdRequest cmd) {
        SequenceType type = cmd.getSequenceType() != null ? cmd.getSequenceType() : SequenceType.DATABASE;
        SequenceGenerator generator = generatorMap.get(type);
        if (generator == null) {
            throw new IllegalArgumentException("Unsupported sequence type: " + type);
        }
        return generator.generate(cmd);
    }

}