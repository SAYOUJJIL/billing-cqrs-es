package ma.enset.customerservice.query.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDTO {
    private String nom ;
    private String adresse ;
    private  String email  ;
    private String tel ;
}
