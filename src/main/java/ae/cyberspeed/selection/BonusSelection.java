package ae.cyberspeed.selection;

import lombok.Builder;

import java.util.Map;

public class BonusSelection extends Selection{

    @Builder
    public BonusSelection(Map<String, Double> selections) {
        super(selections);
    }

    @Override
    public String toString() {
        return "BonusSelection{selections=" + getSelections()+'}';
    }
}
