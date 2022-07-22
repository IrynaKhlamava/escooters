package com.company.model;

import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Scooter.class)
public abstract class Scooter_ extends com.company.model.AEntity_ {

	public static volatile SingularAttribute<Scooter, Double> wheels;
	public static volatile SingularAttribute<Scooter, Double> chargingTime;
	public static volatile SingularAttribute<Scooter, Double> price;
	public static volatile SingularAttribute<Scooter, Double> weight;
	public static volatile SingularAttribute<Scooter, String> model;
	public static volatile SingularAttribute<Scooter, Double> maxRange;
	public static volatile SingularAttribute<Scooter, Location> location;
	public static volatile SingularAttribute<Scooter, LocalDateTime> dateStartOfUse;
	public static volatile SingularAttribute<Scooter, Integer> maxSpeed;
	public static volatile SingularAttribute<Scooter, Double> mileage;

	public static final String WHEELS = "wheels";
	public static final String CHARGING_TIME = "chargingTime";
	public static final String PRICE = "price";
	public static final String WEIGHT = "weight";
	public static final String MODEL = "model";
	public static final String MAX_RANGE = "maxRange";
	public static final String LOCATION = "location";
	public static final String DATE_START_OF_USE = "dateStartOfUse";
	public static final String MAX_SPEED = "maxSpeed";
	public static final String MILEAGE = "mileage";

}

