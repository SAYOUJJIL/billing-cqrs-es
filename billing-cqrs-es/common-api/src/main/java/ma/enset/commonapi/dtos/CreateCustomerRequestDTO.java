package ma.enset.commonapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerRequestDTO {

    private String nom ;
    private String adresse ;
    private  String email  ;
     private String tel ;

}
