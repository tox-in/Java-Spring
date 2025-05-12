package eucl.tokengenerator.tokengeneratorapplication.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "meters",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "meterNumber")
})
public class Meter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 6, max = 6, message = "Meter should ne exactly 6 characters")
    @Pattern(regexp = "^[0-9a-zA-Z]{6}$", message = "Meter number must be alphanumeric")
    @Column(name = "meter_number", unique = true, nullable = false)
    private String meterNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "meterNumber", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PurchasedToken> purchasedTokens;

    public Meter() {
    }

    public Meter(String meterNumber, User user) {
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

    public List<PurchasedToken> getPurchasedTokens() {return purchasedTokens;}

    public void setPurchasedTokens(List<PurchasedToken> purchasedTokens) {this.purchasedTokens = purchasedTokens;}
}
