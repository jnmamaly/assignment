package ae.cyberspeed.outcome;


import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class Loss extends Result{

    @Builder
    public Loss(String[][] matrix, double reward) {
        super(matrix, reward);
    }
}
