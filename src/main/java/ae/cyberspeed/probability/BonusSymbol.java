package ae.cyberspeed.probability;

import lombok.Builder;

import java.util.Map;

public class BonusSymbol extends SymbolProbability{

    @Builder
    public BonusSymbol(Map<String, Integer> symbols) {
        super(symbols);
    }


}
