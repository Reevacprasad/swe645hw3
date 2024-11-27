package swe645.hw3.model;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="students")
public class Student {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="first_name", nullable=false)
	private String firstName;
	
	@Column(name="last_name", nullable=false)
	private String lastName;
	
	@Column(name="email", nullable=false)
	private String email;
	
	@Column(name="street_address", nullable=false)
	private String streetAddress;
	
	@Column(name="city", nullable=false)
	private String city;
	
	@Column(name="state", nullable=false)
	private String state;
	
	@Column(name="zip", nullable=false)
	private String zip;
	
	@Column(name="telephone_number", nullable=false)
	private String telephoneNumber;
	
	@Column(name = "date_of_survey", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfSurvey;

    @Column(name = "liked_most", nullable = false)
    private String likedMost;

    @Column(name = "interest_source", nullable = false)
    private String interestSource;

    @Column(name = "recommendation_likelihood", nullable = false)
    private String recommendationLikelihood;
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	public Date getDateOfSurvey() {
        return dateOfSurvey;
    }
	public void setDateOfSurvey(Date dateOfSurvey) {
        this.dateOfSurvey = dateOfSurvey;
    }
    public String getLikedMost() {
        return likedMost;
    }
    public void setLikedMost(String likedMost) {
        this.likedMost = likedMost;
    }
    public String getInterestSource() {
        return interestSource;
    }
    public void setInterestSource(String interestSource) {
        this.interestSource = interestSource;
    }
    public String getRecommendationLikelihood() {
        return recommendationLikelihood;
    }
    public void setRecommendationLikelihood(String recommendationLikelihood) {
        this.recommendationLikelihood = recommendationLikelihood;
    }
	
    @Override
    public String toString() {
        return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", streetAddress=" + streetAddress + ", city=" + city + ", state=" + state + ", zip=" + zip + ", telephoneNumber=" + telephoneNumber + ", email=" + email + ", dateOfSurvey=" + dateOfSurvey + ", likedMost=" + likedMost + ", interestSource=" + interestSource + ", recommendationLikelihood=" + recommendationLikelihood + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, dateOfSurvey, email, firstName, interestSource, id, lastName, likedMost, recommendationLikelihood, state, streetAddress, telephoneNumber, zip);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        return Objects.equals(city, other.city) && Objects.equals(dateOfSurvey, other.dateOfSurvey) && Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName) && Objects.equals(interestSource, other.interestSource) && id == other.id && Objects.equals(lastName, other.lastName) && Objects.equals(likedMost, other.likedMost) && Objects.equals(recommendationLikelihood, other.recommendationLikelihood) && Objects.equals(state, other.state) && Objects.equals(streetAddress, other.streetAddress) && Objects.equals(telephoneNumber, other.telephoneNumber) && Objects.equals(zip, other.zip);
    }
}