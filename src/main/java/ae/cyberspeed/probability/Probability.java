package ae.cyberspeed.probability;

import com.jayway.jsonpath.JsonPath;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@Slf4j
public class Probability {
    private List<StandardSymbol> standardSymbols;
    private BonusSymbol bonusSymbol;

    public Probability(String jsonContent) {
        try {
            standardSymbols = processStandardSymbols(jsonContent);
            bonusSymbol = processBonusSymbols(jsonContent);
        }catch (Exception e){
            log.warn(String.format("Failed to process probabilities: %s",e.getMessage()));
        }


    }

    private List<StandardSymbol> processStandardSymbols(String jsonContent){
        List<StandardSymbol> standardSymbolList = new ArrayList<>();

        List< Map<String, Object>> rawSymbols = JsonPath.read(jsonContent,"$.probabilities.standard_symbols");
        for ( Map<String, Object> data: rawSymbols) {
            int column = Integer.parseInt(data.get("column").toString());
            int row = Integer.parseInt(data.get("row").toString());
            @SuppressWarnings("unchecked")
            Map<String, Integer> symbols = (Map<String, Integer>) data.get("symbols");
            StandardSymbol standards = StandardSymbol
                    .builder()
                    .row(row)
                    .column(column)
                    .symbols(symbols).build();
            standardSymbolList.add(standards);
        }
        return standardSymbolList;
    }

    private BonusSymbol processBonusSymbols(String jsonContent){
        Map<String, Integer> bonusSymbols = JsonPath.read(jsonContent,"$.probabilities.bonus_symbols.symbols");
        return BonusSymbol.builder().symbols(bonusSymbols).build();
    }
}
