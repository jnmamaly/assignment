package ae.cyberspeed.outcome;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class Win extends Result{

    private Map<String, List<String>> applied_winning_combinations;
    private String applied_bonus_symbol;

    @Builder
    public Win(String[][] matrix, double reward, Map<String, List<String>> applied_winning_combinations, String applied_bonus_symbol) {
        super(matrix, reward);
        this.applied_winning_combinations = applied_winning_combinations;
        this.applied_bonus_symbol = applied_bonus_symbol;
    }
}
