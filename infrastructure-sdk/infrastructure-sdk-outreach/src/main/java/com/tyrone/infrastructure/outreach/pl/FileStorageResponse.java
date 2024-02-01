package com.tyrone.infrastructure.outreach.pl;

import com.tyrone.infrastructure.core.domain.AbstractResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileStorageResponse extends AbstractResponse {

    private String url;

}
