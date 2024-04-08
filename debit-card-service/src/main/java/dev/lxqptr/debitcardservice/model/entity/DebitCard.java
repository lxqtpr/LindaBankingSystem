package dev.lxqptr.debitcardservice.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "debit_card", name = "t_debit_cards")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DebitCard {

    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.VARCHAR)
    UUID id;

    @Column(name = "c_number")
    String number;

    @Column(name = "c_cardHolderName")
    String cardHolderName;

    @Column(name = "c_balance")
    Integer balance;

    @Column(name = "c_expiration_date")
    String expirationDate;

    @Column(name = "c_daily_limit")
    Integer dailyLimit;

    @Column(name = "c_cvv")
    String cvv;

    @Column(name = "c_customer_id")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    UUID customerId;

}
