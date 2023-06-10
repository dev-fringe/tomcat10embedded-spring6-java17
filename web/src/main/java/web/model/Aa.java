package web.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "aa")
@Data
public class Aa {

	@Id
	@Column(name = "aa")
	private String aa;

	public Aa() {
		super();
	}

	public Aa(String aa) {
		this();
		this.aa = aa;
	}
	
}
