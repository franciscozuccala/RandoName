package com.github.franciscozuccala.randoname.domain;

import java.io.Serializable;
import java.util.List;

public class PersonsList implements Serializable {
	private List<Person> persons;

	public void setPersons(List<Person> persons){
		this.persons=persons;
	}
	public List<Person> getPersons(){
		return persons;
	}
}
