package teste.vr.server.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clients")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Clients {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "credit_limit", precision = 10, scale = 2)
    private BigDecimal creditLimit;
    @Min(1)
    @Max(31)
    @Column(name = "due_day")
    private Integer dueDay;
    @Column(name = "active")
    private Boolean active = Boolean.TRUE;
}
