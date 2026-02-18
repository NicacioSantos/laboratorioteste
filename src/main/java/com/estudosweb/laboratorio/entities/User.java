package com.estudosweb.laboratorio.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Column(nullable = false)
    private String name;
    @Setter
    @Column(nullable = false, unique = true)
    private String email;
    @Setter
    private String phone;

    @Setter
    @Column(nullable = false)
    private String password;   @JsonIgnore
    @OneToMany(mappedBy = "client",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    public List<Order> getOrders() {
        return Collections.unmodifiableList(orders);
    }
    // 🔹 Controle da relação
    public void addOrder(Order order) {
        orders.add(order);
        order.setClient(this);
    }
    public void removeOrder(Order order) {
        orders.remove(order);
        order.setClient(null);
    }

}
