package com.financial.system.financial.system.model;

import com.financial.system.financial.system.dto.PersonCreateDTO;
import com.financial.system.financial.system.dto.PersonUpdateDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Table(name = "people")
@Entity(name = "Person")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private Boolean active;

    @Embedded
    private Address address;

    public Person(PersonCreateDTO data, String encryptedPassword){
        this.active = true;
        this.name = data.name();
        this.email = data.email();
        this.password = encryptedPassword;
        if (data.address() != null) {
            this.address = new Address(data.address());
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Boolean isActive() {
        return active;
    }

    public Address getAddress() {
        return address;
    }

    public void update(@Valid PersonUpdateDTO data){
        if(data.name() != null){
            this.name = data.name();
        }
        if (data.address() != null){
            this.address.update(data.address());
        }
    }

    public void delete() {
        this.active = false;
    }
}
