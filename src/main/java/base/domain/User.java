package base.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usr")
@Data
public class User {


    @Id
    private String id;

    private String name;
    private String userPic;
    private String email;
    private String gender;
    private String locale;
    private String lastVisit;
}
