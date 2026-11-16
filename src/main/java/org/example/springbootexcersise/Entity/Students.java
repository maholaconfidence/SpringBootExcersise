package org.example.springbootexcersise.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.AnyDiscriminatorImplicitValues;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table (name = "Students")
public class Students {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @Column (name ="firstName", nullable = false)
    private String firstName;

    @Column (name = "lastName", nullable = false)
    private  String lastName;

    @Column (name = "email" , nullable = false)
    private String email;

}
