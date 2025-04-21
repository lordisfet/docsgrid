import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocToCompanyId implements Serializable {
    private Long docId;
    private Long companyId;
}