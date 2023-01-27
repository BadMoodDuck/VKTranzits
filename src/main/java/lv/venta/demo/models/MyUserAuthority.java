package lv.venta.demo.models;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "myuserauthority_table")
@Entity
public class MyUserAuthority {

		@Column(name = "IdA")
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Setter(value = AccessLevel.NONE)
		private int idA;

		@Column(name = "Title")
	    @NotNull(message = "Must not be empty")
	    @Pattern(regexp = "^ROLE_[A-Z0-9_]+$", message = "Roles have to start with 'ROLE_' and can include only uppercase A-Z letters, 0-9 numbers, and '_'")
		private String title;

		@ManyToMany
		@JoinTable(name = "users_authorities", joinColumns = @JoinColumn(name = "IdA"), inverseJoinColumns = @JoinColumn(name = "IdU"))
		private Collection<MyUser> myUsers = new ArrayList<>();
		
		public MyUserAuthority(String title) {
			this.title = title;
		}

		public void addUser(MyUser user) {
			myUsers.add(user);
		}
		public void deleteUser(MyUser user) {
			if(myUsers.contains(user)) {
			myUsers.remove(user);
			}
		}

}
