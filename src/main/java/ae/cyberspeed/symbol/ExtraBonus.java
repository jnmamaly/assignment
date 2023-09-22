package ae.cyberspeed.symbol;

import lombok.*;

@Setter
@Getter
//@AllArgsConstructor
//@NoArgsConstructor

public class ExtraBonus extends Symbol {

    private Double extra;
    private String impact;

    @Builder
    public ExtraBonus(String type, Double extra, String impact) {
        super(type);
        this.extra = extra;
        this.impact = impact;
    }

    @Override
    public String toString() {
        return "ExtraBonus{" +
                "extra=" + extra +
                ", type=" + getType() +
                ", impact='" + impact + '\'' +
                '}';
    }
}
