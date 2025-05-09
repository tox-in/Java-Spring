package eucl.tokengenerator.tokengeneratorapplication.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Set;

@Entity
@Table(name = "meter_numbers")
public class MeterNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 6, max = 6)
    @Pattern(regexp = "^[0-9a-zA-Z]{6}$")
    @Column(name = "meter_number", unique = true)
    private String meterNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "meterNumber", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PurchasedToken> tokens = new HashSet<>();

    @OneToMany(mappedBy = "meterNumber", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Notification> notifications = new HashSet<>();

    public MeterNumber() {
    }

    public MeterNumber(String meterNumber, User user) {
        this.meterNumber = meterNumber;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeterNumber() {
        return meterNumber;
    }

    public void setMeterNumber(String meterNumber) {
        this.meterNumber = meterNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<PurchasedToken> getTokens() {
        return tokens;
    }

    public void setTokens(Set<PurchasedToken> tokens) {
        this.tokens = tokens;
    }

    public Set<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }
}
