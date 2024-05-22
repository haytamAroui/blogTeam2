package be.intecbrussel.blogteam2.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor @Setter @Getter @NoArgsConstructor
@Entity
public class User {
    @Id
    @Column(nullable = false, unique = true)
    private String userName;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;


    @Column(nullable = false, unique = true)
    private String email;

    private String street;
    private String home;
    private String city;
    private String zip;

    @Column(nullable = false)
    private String password;

    @Transient
    private String retypePassword;

    private String avatar;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(userName, user.userName) && Objects.equals(email, user.email) && Objects.equals(street, user.street) && Objects.equals(home, user.home) && Objects.equals(city, user.city) && Objects.equals(zip, user.zip) && Objects.equals(password, user.password) && Objects.equals(retypePassword, user.retypePassword) && Objects.equals(avatar, user.avatar) && Objects.equals(posts, user.posts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, userName, email, street, home, city, zip, password, retypePassword, avatar, posts);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", uniqueName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", street='" + street + '\'' +
                ", home='" + home + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                ", password='" + password + '\'' +
                ", retypePassword='" + retypePassword + '\'' +
                ", avatar='" + avatar + '\'' +
                ", posts=" + posts +
                '}';
    }
}
