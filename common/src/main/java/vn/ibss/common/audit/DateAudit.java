package vn.ibss.common.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdOn", "updatedOn"},
        allowGetters = true
)
public abstract class DateAudit implements Serializable {


    @CreatedDate
    @Column(name = "created_on")
    private Instant createdOn;

    @LastModifiedDate
    @Column(name = "updated_on")
    private Instant updatedOn;

    public Instant getCreatedOn() {
        return createdOn;
    }

    public void setCreatedAt(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public Instant getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedAt(Instant updatedOn) {
        this.updatedOn = updatedOn;
    }


}
