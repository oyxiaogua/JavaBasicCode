package com.xiaogua.better.bean;

import com.fasterxml.jackson.annotation.JsonView;

public class JsonView_Card_Bean {
	public interface CardWithCustomer {
	}

	@JsonView({ JsonView_Customer_Bean.CustomerWithCard.class, CardWithCustomer.class })
	private Long id;
	@JsonView(CardWithCustomer.class)
	private JsonView_Customer_Bean customer;

	public JsonView_Card_Bean() {
		super();
	}

	public JsonView_Card_Bean(long l, JsonView_Customer_Bean customer1) {
		this.id = l;
		this.customer = customer1;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public JsonView_Customer_Bean getCustomer() {
		return customer;
	}

	public void setCustomer(JsonView_Customer_Bean customer) {
		this.customer = customer;
	}

}