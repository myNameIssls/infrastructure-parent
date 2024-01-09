package com.tyrone.infrastructure.core.domain;

import java.time.LocalDateTime;

public abstract class DomainEvent {

    private String eventId;

    private LocalDateTime occurrenceTime;

}
