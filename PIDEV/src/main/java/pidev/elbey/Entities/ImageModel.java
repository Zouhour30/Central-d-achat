package pidev.elbey.Entities;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="image_model")
public class ImageModel  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long imageId;
    private String name;
    private String type;


    public ImageModel(String name, String type) {
        this.name = name;
        this.type = type;

    }

}
