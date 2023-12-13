package de.tekup.carrentalsystembackend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "assurance")
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Assurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long idAssurance;


    @OneToOne( optional = false)
    @JoinColumn(name = "id_vehicle")
    private Vehicle vehicle;

}
