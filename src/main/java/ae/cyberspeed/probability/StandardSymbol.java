package ae.cyberspeed.probability;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter

public class StandardSymbol extends SymbolProbability{
    private int column;
    private int row;
    @Builder
    public StandardSymbol(Map<String, Integer> symbols, int column, int row) {
        super(symbols);
        this.column = column;
        this.row = row;
    }

    @Override
    public String toString() {
        return "{" +
                "column=" + column +
                ", row=" + row +
                ", symbols=" + getSymbols() +
                '}';
    }
}
