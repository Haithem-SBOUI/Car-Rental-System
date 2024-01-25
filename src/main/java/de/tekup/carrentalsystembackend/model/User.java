package de.tekup.carrentalsystembackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

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

    //    @Column(columnDefinition = "VARCHAR(255) DEFAULT 'https://www.freeiconspng.com/uploads/profile-icon-28.png'")
    private String imageUrl;


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
