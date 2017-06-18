package facebook.entities;

import java.util.Comparator;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Facebook {
	
	private String id;
	private long birthdate;
	private String firstname;
	private String lastname;
	private String occupation;
	private String gender;
	private City city;
	private String work;
	private Set<String> friends;
	private String school;
	private String location;
	private String relationship;
	private Set<Post> posts;
	
	public Facebook() {}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(long birthdate) {
		this.birthdate = birthdate;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	public Set<String> getFriends() {
		return friends;
	}
	public void setFriends(Set<String> friends) {
		this.friends = friends;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public Set<Post> getPosts() {
		return posts;
	}
	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}
	
	@Override
	public String toString() {
		return "Id: " + getId() + " name: " + getLastname() + " " + getFirstname();
	}
	
	public static Comparator<Facebook> firstnameComparator = new Comparator<Facebook>() {

		@Override
		public int compare(Facebook o1, Facebook o2) {
			String name1 = o1.getFirstname();
			String name2 = o2.getFirstname();
			return name1.compareTo(name2);
		}
	};
	
	public static Comparator<Facebook> lastnameComparator = new Comparator<Facebook>() {

		@Override
		public int compare(Facebook o1, Facebook o2) {
			String name1 = o1.getLastname();
			String name2 = o2.getLastname();
			return name1.compareTo(name2);
		}
	};

}
