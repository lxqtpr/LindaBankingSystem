package dev.lxqptr.accountservice.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "account", name = "t_accounts")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account {

    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.VARCHAR)
    UUID id;

    @Column(name = "c_number")
    String number;

    @Column(name = "c_holder")
    String holder;

    @CreationTimestamp
    @Column(name = "c_opened_date")
    LocalDateTime openedDate;

    @Column(name = "c_balance")
    Integer balance;

    @Column(name = "c_customer_id")
    UUID customerId;

}
