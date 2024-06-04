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
@Table(name = "contacts")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Contacts {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contactsSeqGenerator")
    @SequenceGenerator(name = "contactsSeqGenerator", sequenceName = "contacts_seq", allocationSize = 1)
    private Long id;
    private String phone;
    private String email;

    @OneToOne(mappedBy = "contacts")
    private Hotel hotel;
}
