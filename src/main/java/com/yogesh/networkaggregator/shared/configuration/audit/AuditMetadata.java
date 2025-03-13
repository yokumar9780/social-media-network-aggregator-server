package com.yogesh.networkaggregator.shared.configuration.audit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class AuditMetadata {

    @LastModifiedBy
    @JsonIgnore
    private String updatedBy;

    @CreatedBy
    @JsonIgnore
    private String createdBy;

    @CreatedDate
    @JsonIgnore
    private Instant createdDate;

    @LastModifiedDate
    @JsonIgnore
    private Instant lastModifiedDate;
}
