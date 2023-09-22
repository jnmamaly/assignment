package ae.cyberspeed.symbol;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
//@AllArgsConstructor
public class None extends Symbol{
    private String impact;

    @Builder
    public None(String type, String impact) {
        super(type);
        this.impact = impact;
    }

    @Override
    public String toString() {
        return String.format("{type=%s , impact=%s}",getType(),impact);
    }
}
