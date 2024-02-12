package de.tekup.carrentalsystembackend.model;

import de.tekup.carrentalsystembackend.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, unique = true, nullable = false)
    private String username;

    @Column(length = 50, nullable = false)
    private String firstname;

    @Column(length = 50, nullable = false)
    private String lastname;

    @Column(length = 100, unique = true, nullable = false)
    private String email;

    @Column(length = 10, nullable = false)
    private String password;



    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

//    @OneToMany(mappedBy = "user")
//    @JsonManagedReference
//    private Set<Vehicle> vehicles = new HashSet<>();




    @CreationTimestamp
    private LocalDateTime createdOn;

    @UpdateTimestamp
    private LocalDateTime updatedOn;


}
