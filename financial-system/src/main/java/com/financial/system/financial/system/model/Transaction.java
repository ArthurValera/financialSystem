package com.financial.system.financial.system.model;
import com.financial.system.financial.system.dto.TransactionCreateDTO;
import com.financial.system.financial.system.dto.TransactionUpdateDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private LocalDate dueDate;
    private LocalDate paymentDate;

    @Enumerated(EnumType.STRING)
    private TransactionType type;
    private String note;
    private BigDecimal amount;
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Transaction() {
    }

    public Transaction(TransactionCreateDTO data, Category category, Person person) {
        this.description = data.description();
        this.dueDate = data.dueDate();
        this.paymentDate = data.paymentDate();
        this.type = data.type();
        this.note = data.note();
        this.amount = data.amount();
        this.active = true;
        this.category = category;
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public TransactionType getType() {
        return type;
    }

    public String getNote() {
        return note;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public boolean isActive() {
        return active;
    }

    public Category getCategory() {
        return category;
    }

    public Person getPerson() {
        return person;
    }

    public void update(@Valid TransactionUpdateDTO data, Person person, Category category) {
        if (data.description() != null) {
            this.description = data.description();
        }
        if (data.amount() != null) {
            this.amount = data.amount();
        }
        if (data.dueDate() != null) {
            this.dueDate = data.dueDate();
        }
        if (data.paymentDate() != null) {
            this.paymentDate = data.paymentDate();
        }
        if (data.type() != null) {
            this.type = data.type();
        }
        if (data.note() != null) {
            this.note = data.note();
        }
        if (category != null) {
            this.category = category;
        }
        if (person != null) {
            this.person = person;
        }
    }

    public void delete() {
        this.active = false;
    }
}
