package ro.msg.learning.shop.domain.key;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDetailId implements Serializable {

    @Column(name="product")
    private UUID productId;
    @Column(name="order")
    private UUID orderId;
    @Column(name="location")
    private UUID locationId;


    @Override
    public boolean equals(Object obj){
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        OrderDetailId stockObject=(OrderDetailId) obj;
        if (!stockObject.orderId.equals(this.orderId)){
            return false;
        }
        if (!stockObject.productId.equals(this.productId)){
            return false;
        }
        if (!stockObject.locationId.equals(this.locationId)){
            return false;
        }
        return true;
    }
    @Override
    public int hashCode(){
        return Objects.hash(productId,orderId,locationId);
    }

}
