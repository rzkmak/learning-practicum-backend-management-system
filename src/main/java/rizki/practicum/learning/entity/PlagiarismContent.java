package rizki.practicum.learning.entity;
/*
    Created by : Rizki Maulana Akbar, On 02 - 2018 ;
*/

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Setter @Getter
public class PlagiarismContent {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @ManyToOne
    private Assignment assignment;

    @ManyToOne
    private Document document1;

    @ManyToOne
    private Document document2;

    private Double plagiasrism_rate;
}