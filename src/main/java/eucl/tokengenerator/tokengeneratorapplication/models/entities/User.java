package eucl.tokengenerator.tokengeneratorapplication.models.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.NotFound;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="users",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "email"),
            @UniqueConstraint(columnNames = "phone"),
            @UniqueConstraint(columnNames = "nationalId")
        })
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank(message = "Username is required")
        @Size(max = 100)
        private String name;

        @NotBlank(message = "Email is required")
        @Size(max = 100)
        @Email(message = "Email should be valid")
        private String email;

        @NotBlank
        @Size(min = 10, max = 12)
        @Pattern(regexp = "^(079|078|073|072)[0-9]{7}$", message = "Phone number should start with 079, 078, 073 or 072 and be 10 digits total")
        private String phone;

        @Column(name = "national_id")
        @NotBlank(message = "National ID is required")
        @Size(min = 16, max = 16, message = "National ID should be 16 digits")
        @Pattern(regexp = "^[1-2](180[0-9]|18[1-9][0-9]|19[0-9]{2}|200[0-7])[7-8][0-9]{7}[0-2][0-9]{2}$",
                message = "ID must be in format: X_XXXX_X_XXXXXXX_X_XX")
        private String nationalId;

        @NotBlank(message = "Password is required")
        @Size(min = 8, max = 12, message = "pssword should be atleast 8 characters")
        private String password;

        @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
        @CollectionTable(
                name = "user_roles",
                joinColumns = @JoinColumn(name = "user_id")  // Corrected: should reference user_id, not role_id
        )
        @Enumerated(EnumType.STRING)  // Stores enum values as strings (e.g., "ROLE_ADMIN")
        private Set<Role> roles = new HashSet<>();

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<Meter> meters;

        public User() {}

        public User(String name, String email, String phone, String nationalId, String password) {
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.nationalId = nationalId;
            this.password = password;
        }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Meter> getMeters() {
        return meters;
    }

    public void setMeters(List<Meter> meters) {
        this.meters = meters;
    }
}

