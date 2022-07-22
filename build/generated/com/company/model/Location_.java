package com.company.model;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Location.class)
public abstract class Location_ extends com.company.model.AEntity_ {

	public static volatile SingularAttribute<Location, Integer> number;
	public static volatile SingularAttribute<Location, String> address;
	public static volatile SingularAttribute<Location, City> city;
	public static volatile ListAttribute<Location, Scooter> scooters;

	public static final String NUMBER = "number";
	public static final String ADDRESS = "address";
	public static final String CITY = "city";
	public static final String SCOOTERS = "scooters";

}

