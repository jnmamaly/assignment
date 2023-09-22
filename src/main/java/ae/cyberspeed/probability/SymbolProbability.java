package ae.cyberspeed.probability;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public abstract class SymbolProbability {
    private Map<String,Integer> symbols;

    @Override
    public String toString() {
        return "" +symbols;
    }
}
