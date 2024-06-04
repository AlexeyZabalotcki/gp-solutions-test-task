package com.gpsolutions.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@Builder
@ToString
@Table(name = "address")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addressSeqGenerator")
    @SequenceGenerator(name = "addressSeqGenerator", sequenceName = "address_seq", allocationSize = 1)
    private Long id;
    private Integer houseNumber;
    private String street;
    private String city;
    private String country;
    private String postCode;

    @OneToOne(mappedBy = "address")
    private Hotel hotel;
}
