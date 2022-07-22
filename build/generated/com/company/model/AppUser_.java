package com.company.model;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AppUser.class)
public abstract class AppUser_ extends com.company.model.AEntity_ {

	public static volatile SingularAttribute<AppUser, Integer> hours;
	public static volatile SingularAttribute<AppUser, String> password;
	public static volatile SingularAttribute<AppUser, UserStatus> userStatus;
	public static volatile SingularAttribute<AppUser, String> phone;
	public static volatile SetAttribute<AppUser, Role> roles;
	public static volatile SingularAttribute<AppUser, String> name;
	public static volatile SingularAttribute<AppUser, Integer> subHours;
	public static volatile SingularAttribute<AppUser, Integer> discount;
	public static volatile SingularAttribute<AppUser, String> login;

	public static final String HOURS = "hours";
	public static final String PASSWORD = "password";
	public static final String USER_STATUS = "userStatus";
	public static final String PHONE = "phone";
	public static final String ROLES = "roles";
	public static final String NAME = "name";
	public static final String SUB_HOURS = "subHours";
	public static final String DISCOUNT = "discount";
	public static final String LOGIN = "login";

}

