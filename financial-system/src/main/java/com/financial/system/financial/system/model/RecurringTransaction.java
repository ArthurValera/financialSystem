package com.financial.system.financial.system.model;

import com.financial.system.financial.system.dto.RecurringTransactionCreateDTO;
import com.financial.system.financial.system.dto.RecurringTransactionUpdateDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "recurring_transactions")
@Entity(name = "RecurringTransaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class RecurringTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal amount;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private RecurrenceType recurrenceType;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType type;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    private Boolean active = true;

    public RecurringTransaction(RecurringTransactionCreateDTO data, Category category, Person person) {
        this.name = data.name();
        this.amount = data.amount();
        this.startDate = data.startDate();
        this.endDate = data.endDate();
        this.recurrenceType = data.recurrenceType();
        this.type = data.transactionType();
        this.category = category;
        this.person = person;
        this.active = true;
    }

    public void update(RecurringTransactionUpdateDTO data, Person person, Category category) {
        if (data.name() != null) this.name = data.name();
        if (data.amount() != null) this.amount = data.amount();
        if (data.startDate() != null) this.startDate = data.startDate();
        if (data.endDate() != null) this.endDate = data.endDate();
        if (data.recurrenceType() != null) this.recurrenceType = data.recurrenceType();
        if (data.transactionType() != null) this.type = data.transactionType();
        if (category != null) this.category = category;
        if (person != null) this.person = person;
    }

    public void delete() {
        this.active = false;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public RecurrenceType getRecurrenceType() {
        return recurrenceType;
    }

    public TransactionType getType() {
        return type;
    }

    public Category getCategory() {
        return category;
    }

    public Person getPerson() {
        return person;
    }

    public Boolean isActive() {
        return active;
    }
}
