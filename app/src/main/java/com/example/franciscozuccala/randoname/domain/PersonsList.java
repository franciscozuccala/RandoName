package com.example.franciscozuccala.randoname.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by franciscozuccala on 11/06/16.
 */
public class PersonsList implements Serializable {
	private List<Person> persons;

	public void setPersons(List<Person> persons){
		this.persons=persons;
	}
	public List<Person> getPersons(){
		return persons;
	}
}
