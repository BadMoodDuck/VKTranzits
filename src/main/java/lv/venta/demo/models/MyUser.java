package lv.venta.demo.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "myuser_table")
@Entity
public class MyUser {

	@Column(name = "IdU")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private int idU;

	@Column(name = "Username")
	@Size(min = 6, max = 20)
	@Pattern(regexp = "[a-zA-Z0-9]+")
	private String username;

	@Column(name = "Password")
	@Size(min = 8, max = 60)
	@Pattern(regexp = "[a-zA-Z0-9\\W]+")
	private String password;

	@ManyToMany(mappedBy = "allUsers")
	private Collection<MyUserAuthority> allAuthorities = new ArrayList<>();

	public void addAuthority(MyUserAuthority authority) {
		allAuthorities.add(authority);
	}

	public void deleteAuthority(MyUserAuthority authority) {
		if (allAuthorities.contains(authority)) {
			allAuthorities.remove(authority);
		}
	}
}
