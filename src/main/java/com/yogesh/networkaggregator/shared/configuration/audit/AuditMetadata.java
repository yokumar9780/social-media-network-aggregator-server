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

/**
 * Base class for MongoDB document auditing metadata.
 * This class provides common auditing fields for tracking document creation and
 * modification.
 * It can be extended by other document classes to inherit auditing
 * capabilities.
 * All audit fields are excluded from JSON serialization by default.
 */
@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class AuditMetadata {

    /**
     * Username of the user who last modified the document.
     * Automatically populated by Spring Data's auditing support.
     */
    @LastModifiedBy
    @JsonIgnore
    private String updatedBy;

    /**
     * Username of the user who created the document.
     * Automatically populated by Spring Data's auditing support.
     */
    @CreatedBy
    @JsonIgnore
    private String createdBy;

    /**
     * Timestamp when the document was created.
     * Automatically populated by Spring Data's auditing support.
     */
    @CreatedDate
    @JsonIgnore
    private Instant createdDate;

    /**
     * Timestamp when the document was last modified.
     * Automatically populated by Spring Data's auditing support.
     */
    @LastModifiedDate
    @JsonIgnore
    private Instant lastModifiedDate;
}
