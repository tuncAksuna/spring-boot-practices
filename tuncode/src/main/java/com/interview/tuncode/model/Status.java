package com.interview.tuncode.model;

import com.interview.tuncode.utils.BaseEnum;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "STATUS")
@NoArgsConstructor
public class Status {

    public enum ENUM implements BaseEnum<Long> {
        ACTIVE(1L), PASSIVE(2L), LOGICAL_DELETED(3L);

        private Long value;

        ENUM(Long val) {
            value = val;
        }

        public Long getValue() {
            return value;
        }

        public String getLabel() {
            return name();
        }
    }

    @Id
    private Long id;

    public Status(Long id) {
        this.id = id;
    }

    public Status(ENUM e) {
        this.id = e.getValue();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActive() {
        return ENUM.ACTIVE.getValue().equals(this.getId());
    }

    public boolean isPassive() {
        return ENUM.ACTIVE.getValue().equals(this.getId());
    }

    public boolean isLogicalDeleted() {
        return ENUM.LOGICAL_DELETED.getValue().equals(this.getId());
    }

    public static Status getActiveObject() {
        Status status = new Status();
        status.setId(ENUM.ACTIVE.getValue());
        return status;
    }

    public static Status getPassiveObject() {
        Status status = new Status();
        status.setId(ENUM.PASSIVE.getValue());
        return status;
    }

    public static Status getLogicalDeletedObject() {
        Status status = new Status();
        status.setId(ENUM.LOGICAL_DELETED.getValue());
        return status;
    }

}
