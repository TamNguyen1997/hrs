package hrs.parcel.management.api.orm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "guest")
public class GuestDb {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private UUID id;

        private String name;

        private String email;

        @Column(name = "checked_in", columnDefinition = "boolean default false")
        private Boolean checkedIn = false;

        @Column(name = "checked_out", columnDefinition = "boolean default false")
        private Boolean checkedOut = false;

        @CreatedDate
        @Column(name = "created_at")
        private LocalDateTime createdAt;

        @LastModifiedDate
        @Column(name = "updated_at")
        private LocalDateTime updatedAt;
}
