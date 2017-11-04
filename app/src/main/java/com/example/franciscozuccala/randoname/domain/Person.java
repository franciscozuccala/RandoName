package com.example.franciscozuccala.randoname.domain;

import java.io.Serializable;

/**
 * Created by franciscozuccala on 10/06/16.
 */
public class Person implements Serializable {
	private String name;

	public Person(){}
	public Person(String name){
		this.name=name;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name=name;
	}
}
