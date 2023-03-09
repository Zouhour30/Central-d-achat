package pidev.elbey.Entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;




@Entity
@NoArgsConstructor
@Data
public class EmailDetails implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEmail;
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}
