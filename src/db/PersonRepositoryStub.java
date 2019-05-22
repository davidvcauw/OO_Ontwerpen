package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Person;
import domain.Role;

public class PersonRepositoryStub implements PersonRepository {
	private Map<String, Person> persons = new HashMap<String, Person>();
	
	public PersonRepositoryStub () {
		Person administrator = new Person("bib@ucll.be", "t", "Bib", "Liothekaris", Role.BIB);
		Person jan = new Person("jan@ucll.be", "t", "Jan", "Janssens", Role.LID);
		Person an = new Person("an@ucll.be", "t", "An", "Cornelissen", Role.LID);
		Person david = new Person("dvc@gmail.com", "t", "david", "vc", Role.LID);
		Person jef = new Person("jef@gmail.com", "t", "jef", "jefferson", Role.LID);
		Person brenda = new Person("brenda@gmail.com", "t", "brenda", "vc", Role.LID);
		Person hans = new Person("hans@gmail.com", "t", "hans", "c", Role.LID);

		add(administrator);
		add(jan);
		add(an);
		add(david);
		add(jef);
		add(brenda);
		add(hans);

		jan.addFriend(an);
		jan.addFriend(david);

		david.addFriend(jef);
		david.addFriend(brenda);

		an.addFriend(hans);

		brenda.addFriend(hans);

	}
	
	public Person get(String personId){
		if(personId == null){
			throw new IllegalArgumentException("No id given");
		}
		return persons.get(personId);
	}
	
	public List<Person> getAll(){
		return new ArrayList<Person>(persons.values());	
	}

	public void add(Person person){
		if(person == null){
			throw new IllegalArgumentException("No person given");
		}
		if (persons.containsKey(person.getUserId())) {
			throw new IllegalArgumentException("User already exists");
		}
		persons.put(person.getUserId(), person);
	}
	
	public void update(Person person){
		if(person == null){
			throw new IllegalArgumentException("No person given");
		}
		persons.put(person.getUserId(), person);
	}
	
	public void delete(String personId){
		if(personId == null){
			throw new IllegalArgumentException("No id given");
		}
		persons.remove(personId);
	}
	
	public Person getAuthenticatedUser(String email, String password) {
		Person person = get(email);
		
		if (person != null && person.isCorrectPassword(password)) {
			return person;
		}
		else {
			return null;
		}
	}
}
