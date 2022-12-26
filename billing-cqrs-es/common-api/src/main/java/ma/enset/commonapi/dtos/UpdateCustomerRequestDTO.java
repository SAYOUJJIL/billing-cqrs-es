package ma.enset.commonapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCustomerRequestDTO {
    private String id ;
    private String nom ;
    private String adresse ;
    private  String email  ;
    private String tel ;

}
