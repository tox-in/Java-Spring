package eucl.tokengenerator.tokengeneratorapplication.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "purchased_tokens")
public class PurchasedToken {
    public enum TokenStatus {
        NEW, USED, EXPIRED
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meter_number_id", nullable = false)
    private Meter meterNumber;

    @NotBlank
    @Size(min = 16, max = 16, message = "Token must exactly be 16 digits")
    @Pattern(regexp = "^[0-9]{16}$", message = "Token must contain only digits")
    @Column(unique = true, nullable = false)
    private String token;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TokenStatus tokenStatus = TokenStatus.NEW;

    @NotNull
    @Column(name = "token_value_days", nullable = false)
    @Min(value = 1, message = "Token must be worth at least one day")
    private Integer tokenValueDays;

    @CreatedDate
    @Column(name = "purchased_date", nullable = false)
    private LocalDateTime purchasedDate;

    @Column(name = "expiry_date", nullable = false)
    private LocalDateTime expiryDate;
    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "100.00", message = "Minimum purchase amount is 100 RWF")
    @Column(precision = 10, scale = 2)
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public PurchasedToken(){}

    public PurchasedToken(Meter meterNumber, String token, TokenStatus tokenStatus, Integer tokenValueDays, LocalDateTime purchasedDate, LocalDateTime expiryDate, BigDecimal amount, User user) {
        this.meterNumber = meterNumber;
        this.token = token;
        this.tokenStatus = tokenStatus;
        this.tokenValueDays = tokenValueDays;
        this.purchasedDate = purchasedDate;
        this.expiryDate = expiryDate;
        this.amount = amount;
        this.user = user;
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

    @Transient
    public String getFormattedToken() {
        if (token == null || token.length() != 16) {
            return token;
        }
        return token.substring(0, 4) + "_" + token.substring(4, 8) + "_" + token.substring(8, 12) + "_" + token.substring(12, 16);
    }

    @Override
    public String toString() {
        return "PurchasedToken{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", tokenStatus=" + tokenStatus +
                ", tokenValueDays=" + tokenValueDays +
                ", purchasedDate=" + purchasedDate +
                ", expiryDate=" + expiryDate +
                ", amount=" + amount +
                '}';
    }

}
