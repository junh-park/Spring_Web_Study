package spittr.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Spitter {

	private long id;
	@NotNull
	@Size(min = 5, max = 16, message = "{username.size}")
	private String username;
	@NotNull
	@Size(min = 5, max = 25, message = "{password.size}")
	private String password;
	@NotNull
	@Size(min = 2, max = 30, message = "{firstName.size}")
	private String firstName;
	@NotNull
	@Size(min = 2, max = 30, message = "{lastName.size}")
	private String lastName;
	@NotNull
	@Email(message="{email.valid}")
	private String email;

	public Spitter() {	}
	
	public Spitter(String username, String password, String firstName, String lastName) {
		this(0, username, password, firstName, lastName);
	}

	public Spitter(long id, String username, String password, String firstName, String lastName) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "username", "lastName", "firstName");
	}

	@Override
	public boolean equals(Object that) {
		return EqualsBuilder.reflectionEquals(this, that, "username", "lastName", "firstName");
	}
	
	
}
