package ae.cyberspeed.selection;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class StandardSelection extends Selection{

    private int row;
    private int column;

    @Builder
    public StandardSelection(Map<String, Double> selections, int row, int column) {
        super(selections);
        this.row = row;
        this.column = column;
    }

    @Override
    public String toString() {
        return "StandardSelection{" +
                "row=" + row +
                ", column=" + column +
                ",selections=" + getSelections()+
                '}';
    }
}
