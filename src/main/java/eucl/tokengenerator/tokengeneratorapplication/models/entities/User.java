package eucl.tokengenerator.tokengeneratorapplication.models.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.NotFound;

import java.util.HashSet;
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

        @NotBlank
        @Size(max = 100)
        private String name;

        @NotBlank
        @Size(max = 100)
        @Email
        private String email;

        @NotBlank
        @Size(min = 10, max = 12)
        @Pattern(regexp = "^[0-9]+$")
        private String phone;

        @NotBlank
        @Size(min = 16, max = 16)
        @Pattern(regexp = "^[0-9]+$")
        @Column(name = "national_id")
        private String nationalId;

        @NotBlank
        @Size(min = 8, max = 12)
        private String password;

        @ElementCollection(fetch = FetchType.EAGER)
        @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
        @Enumerated(EnumType.STRING)
        private Set<UserRole> roles = new HashSet<>();

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private Set<MeterNumber> meterNumbers = new HashSet<>();

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

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public Set<MeterNumber> getMeterNumbers() {
        return meterNumbers;
    }

    public void setMeterNumbers(Set<MeterNumber> meterNumbers) {
        this.meterNumbers = meterNumbers;
    }

    public void addRole(UserRole role) {
            this.roles.add(role);
    }
}
