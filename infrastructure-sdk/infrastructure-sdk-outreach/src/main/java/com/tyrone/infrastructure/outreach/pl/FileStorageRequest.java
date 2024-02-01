package com.tyrone.infrastructure.outreach.pl;

import com.tyrone.infrastructure.core.domain.AbstractRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.InputStream;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileStorageRequest extends AbstractRequest {

    private InputStream inputStream;

}
