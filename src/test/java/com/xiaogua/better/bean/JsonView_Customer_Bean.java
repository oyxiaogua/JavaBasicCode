package com.xiaogua.better.bean;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

public class JsonView_Customer_Bean {
	public interface CustomerWithCard {
	}

	@JsonView({ CustomerWithCard.class, JsonView_Card_Bean.CardWithCustomer.class })
	private Long id;
	@JsonView(CustomerWithCard.class)
	private List<JsonView_Card_Bean> cardList;

	public JsonView_Customer_Bean() {
		super();
	}

	public JsonView_Customer_Bean(long l) {
		this.id = l;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<JsonView_Card_Bean> getCardList() {
		return cardList;
	}

	public void setCardList(List<JsonView_Card_Bean> cardList) {
		this.cardList = cardList;
	}

	public void addCard(JsonView_Card_Bean card1) {
		if (cardList == null)
			cardList = new ArrayList<JsonView_Card_Bean>();
		cardList.add(card1);
	}
}
