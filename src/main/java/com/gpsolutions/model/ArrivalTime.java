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

import java.time.LocalTime;

@Entity
@Setter
@Getter
@Builder
@ToString
@Table(name = "arrival_time")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ArrivalTime {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "arrivalTimeSeqGenerator")
    @SequenceGenerator(name = "arrivalTimeSeqGenerator", sequenceName = "arrival_time_seq", allocationSize = 1)
    private Long id;
    private LocalTime checkIn;
    private LocalTime checkOut;

    @OneToOne(mappedBy = "arrivalTime")
    private Hotel hotel;
}
