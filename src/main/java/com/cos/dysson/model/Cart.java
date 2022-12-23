package com.cos.dysson.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Table(name="cart")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SequenceGenerator(
		name = "CART_SEQ_GENERATOR1"
		, sequenceName = "CART_SEQ1"
		, initialValue = 1
		, allocationSize = 1
)
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CART_SEQ_GENERATOR1")
	private int id;

	private int count;

	@OneToOne
	@JoinColumn(name = "userId")
	private Users users;

	@OneToMany(mappedBy = "cart")
	private List<CartItem> cartItem = new ArrayList<>();

	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private LocalDate createDate;

	@PrePersist
	public void createDate(){
		this.createDate = LocalDate.now();
	}

	public static Cart createCart(Users users) {
		Cart cart = new Cart();
		cart.setCount(0);
		cart.setUsers(users);
		return cart;
	}

}

