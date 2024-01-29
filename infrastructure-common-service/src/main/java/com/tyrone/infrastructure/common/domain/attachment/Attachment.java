package com.tyrone.infrastructure.common.domain.attachment;

import com.tyrone.infrastructure.core.domain.AbstractEntity;
import com.tyrone.infrastructure.sdk.common.domain.attachment.AttachmentSubType;
import com.tyrone.infrastructure.sdk.common.domain.attachment.AttachmentType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Table
@Entity
public class Attachment extends AbstractEntity {

    @Id
    private String attachmentId;
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, columnDefinition = "varchar(50) comment '附件类型'")
    private AttachmentType type;
    @Enumerated(EnumType.STRING)
    @Column(name = "sub_type", nullable = false, columnDefinition = "varchar(50) comment '附件子类型'")
    private AttachmentSubType subType;
    @Column(name = "name", nullable = false, columnDefinition = "varchar(50) comment '附件子名称'")
    private String name;
    @Column(name = "url", nullable = false, columnDefinition = "varchar(50) comment 'url'")
    private String url;

    @Column(updatable = false, nullable = false, columnDefinition = "datetime comment '创建时间'")
    @CreationTimestamp
    LocalDateTime createTime;

    @UpdateTimestamp
    LocalDateTime updateTime;

}
