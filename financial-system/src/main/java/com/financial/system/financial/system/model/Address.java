package com.financial.system.financial.system.model;

import com.financial.system.financial.system.dto.AddressDTO;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String street;
    private String district;
    private String zipCode;
    private String number;

    @NotNull
    private String complement;

    private String city;
    private String state;

    public Address(AddressDTO data) {
        this.street = data.street();
        this.district = data.district();
        this.zipCode = data.zipCode();
        this.state = data.state();
        this.city = data.city();
        this.number = data.number();
        this.complement = data.complement();
    }

    public void update(AddressDTO data) {
        if (data.street() != null) {
            this.street = data.street();
        }
        if (data.district() != null) {
            this.district = data.district();
        }
        if (data.zipCode() != null) {
            this.zipCode = data.zipCode();
        }
        if (data.state() != null) {
            this.state = data.state();
        }
        if (data.city() != null) {
            this.city = data.city();
        }
        if (data.number() != null) {
            this.number = data.number();
        }
        if (data.complement() != null) {
            this.complement = data.complement();
        }
    }
}
