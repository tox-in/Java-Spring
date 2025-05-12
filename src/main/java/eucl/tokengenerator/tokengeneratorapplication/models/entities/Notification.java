package eucl.tokengenerator.tokengeneratorapplication.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meter_number_id", nullable = false)
    private Meter meterNumber;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String message;

    @NotNull
    @Column(name = "issued_date")
    private LocalDateTime issuedDate;

    @Column(name = "sent")
    private boolean sent;

    public Notification(){}

    public Notification(Meter meterNumber, String message, LocalDateTime issuedDate) {
        this.meterNumber = meterNumber;
        this.message = message;
        this.issuedDate = issuedDate;
        this.sent = false;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(LocalDateTime issuedDate) {
        this.issuedDate = issuedDate;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }
}
