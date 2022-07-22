package com.company.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends AEntity {

    @Column(name = "number")
    private Integer number;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "finish_date")
    private LocalDateTime finishDate;

    @Column(name = "rate")
    @Enumerated(EnumType.STRING)
    private Rate rate;

    @Column(name = "sum")
    private Double sum;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "scooter_id")
    private Long scooterId;

    public Order(Integer number, String rate, Long userId, Long scooterId) {
        this.number = number;
        this.rate = Rate.getRateByStr(rate);
        this.userId = userId;
        this.scooterId = scooterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        if (!super.equals(o)) return false;
        Order order = (Order) o;
        return Objects.equals(number, order.number) &&
                Objects.equals(startDate, order.startDate) &&
                Objects.equals(finishDate, order.finishDate) &&
                rate == order.rate &&
                Objects.equals(sum, order.sum) &&
                Objects.equals(userId, order.userId) &&
                Objects.equals(scooterId, order.scooterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), number, startDate, finishDate, rate, sum, userId, scooterId);
    }

}
