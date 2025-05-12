package eucl.tokengenerator.tokengeneratorapplication.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "purchased_tokens")
public class PurchasedToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meter_number_id", nullable = false)
    private Meter meterNumber;

    @NotBlank
    @Size(min = 16, max = 16)
    @Pattern(regexp = "^[0-9]{16}$")
    @Column(unique = true)
    private String token;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "token_status")
    private TokenStatus tokenStatus;

    @NotNull
    @Column(name = "token_value_days")
    private Integer tokenValueDays;

    @NotNull
    @Column(name = "purchased_date")
    private LocalDateTime purchasedDate;

    @NotNull
    @Column(name = "expiry_date")
    private LocalDateTime expiryDate;

    @NotNull
    @Column(precision = 10, scale = 2)
    private BigDecimal amount;

    public PurchasedToken(){}

    public PurchasedToken(Meter meterNumber, String token, TokenStatus tokenStatus, Integer tokenValueDays, LocalDateTime purchasedDate, LocalDateTime expiryDate, BigDecimal amount) {
        this.meterNumber = meterNumber;
        this.token = token;
        this.tokenStatus = tokenStatus;
        this.tokenValueDays = tokenValueDays;
        this.purchasedDate = purchasedDate;
        this.expiryDate = expiryDate;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Meter getMeterNumber() {
        return meterNumber;
    }

    public void setMeterNumber(Meter meterNumber) {
        this.meterNumber = meterNumber;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TokenStatus getTokenStatus() {
        return tokenStatus;
    }

    public void setTokenStatus(TokenStatus tokenStatus) {
        this.tokenStatus = tokenStatus;
    }

    public Integer getTokenValueDays() {
        return tokenValueDays;
    }

    public void setTokenValueDays(Integer tokenValueDays) {
        this.tokenValueDays = tokenValueDays;
    }

    public LocalDateTime getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(LocalDateTime purchasedDate) {
        this.purchasedDate = purchasedDate;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getFormattedToken() {
        if (token == null || token.length() != 16) {
            return token;
        }
        return token.substring(0, 4) + "_" + token.substring(4, 8) + "_" + token.substring(8, 12) + "_" + token.substring(12, 16);
    }
}
