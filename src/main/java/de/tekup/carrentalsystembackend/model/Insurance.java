package de.tekup.carrentalsystembackend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "insurance")
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long idInsurance;


    @OneToOne( optional = false)
    @JoinColumn(name = "id_vehicle")
    private Vehicle vehicle;

}
