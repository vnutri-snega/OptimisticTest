package example.demo.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name="example")
public class ExampleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    long id;
    @Column(name = "TEXT")
    String text;
    @Column(name = "NUMBER")
    int number;
    @Column(name = "is_modified")
    boolean modified;

    @Version
    @Column(name = "version")
    long version;
}
