package be.intecbrussel.blogteam2.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor @Setter @Getter @NoArgsConstructor
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, unique = true)
    private String userName;

    @NotNull
    //@Column(nullable = false)
    private String firstName;
    @NotNull
            // @Column(nullable = false)
    private String lastName;


    @Column(nullable = false, unique = true)
    private String email;

    private String street;
    private String houseNo;
    private String city;
    private String zip;
    @NotNull
   // @Column(nullable = false)
    private String password;

    @Transient
    private String retypePassword;

    private String avatar;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();




}
