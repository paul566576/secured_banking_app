package com.banking.secured_banking_app.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cards
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "card_id")
	private long cardId;
	@Column(name="customer_id")
	private long customerId;
	@Column(name = "card_number")
	private String cardNumber;
	@Column(name = "card_type")
	private String cardType;
	@Column(name = "total_limit")
	private int totalLimit;
	@Column(name = "amount_used")
	private int amountUsed;
	@Column(name = "available_amount")
	private int availableAmount;
	@Column(name = "create_at")
	private Date createAt;
}