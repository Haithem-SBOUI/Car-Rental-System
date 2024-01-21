package de.tekup.carrentalsystembackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

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
    private Long id;


    @OneToOne( optional = false)
    private Vehicle vehicle;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private  Date endDate;
    @Column(nullable = false)
    private int price;




}

