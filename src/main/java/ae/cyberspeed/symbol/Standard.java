package ae.cyberspeed.symbol;


import lombok.*;

@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@SuperBuilder
public class Standard extends Symbol {
    private double reward_multiplier;
    //private String type;


    @Builder
    public Standard(String type, double reward_multiplier) {
        super(type);
        this.reward_multiplier = reward_multiplier;
    }

    @Override
    public String toString() {
        return  String.format("{reward_multiplier=%.2f , type=%s}",reward_multiplier,getType());
    }
}
