package ma.enset.blockchain.raquests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlockRequest implements Serializable {
    private static final long serialVersionUID = -4211697076270850848L;

    private String hashPrevious;
}
