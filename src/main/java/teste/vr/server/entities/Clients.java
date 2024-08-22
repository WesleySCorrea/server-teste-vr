package teste.vr.server.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "clients")
@RequiredArgsConstructor
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
    @Column(name = "due_date")
    private LocalDateTime dueDate;
}
