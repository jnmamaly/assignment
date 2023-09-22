package ae.cyberspeed.symbol;

import lombok.*;

@Setter
@Getter
//@AllArgsConstructor
//@NoArgsConstructor
public class RewardBonus extends Symbol {
    private double reward_multiplier;
    private String impact;

    @Builder
    public RewardBonus(String type, double reward_multiplier, String impact) {
        super(type);
        this.reward_multiplier = reward_multiplier;
        this.impact = impact;
    }

    @Override
    public String toString() {
        return "{" +
                "reward_multiplier=" + reward_multiplier +
                ", type=" + getType() +
                ", impact='" + impact + '\'' +
                '}';
    }
}
