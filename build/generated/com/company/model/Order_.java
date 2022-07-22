package com.company.model;

import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Order.class)
public abstract class Order_ extends com.company.model.AEntity_ {

	public static volatile SingularAttribute<Order, Integer> number;
	public static volatile SingularAttribute<Order, Rate> rate;
	public static volatile SingularAttribute<Order, LocalDateTime> finishDate;
	public static volatile SingularAttribute<Order, Double> sum;
	public static volatile SingularAttribute<Order, Long> userId;
	public static volatile SingularAttribute<Order, LocalDateTime> startDate;
	public static volatile SingularAttribute<Order, Long> scooterId;

	public static final String NUMBER = "number";
	public static final String RATE = "rate";
	public static final String FINISH_DATE = "finishDate";
	public static final String SUM = "sum";
	public static final String USER_ID = "userId";
	public static final String START_DATE = "startDate";
	public static final String SCOOTER_ID = "scooterId";

}

