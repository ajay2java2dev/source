package com.vzwcorp.pricinglab.vo.key;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by menonka on 8/7/2016.
 */
@Embeddable
public class RefQoSPK implements Serializable{
    private static final long serialVersionUID = 1L;

    @Column
    private String name;

    @Column
    private String vispName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVispName() {
        return vispName;
    }

    public void setVispName(String vispName) {
        this.vispName = vispName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof RefQoSPK)) return false;

        RefQoSPK refQoSPK = (RefQoSPK) o;

        return new EqualsBuilder()
                .append(getName(), refQoSPK.getName())
                .append(getVispName(), refQoSPK.getVispName())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getName())
                .append(getVispName())
                .toHashCode();
    }
}
