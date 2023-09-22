package ae.cyberspeed.combination;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@NoArgsConstructor
public class CountCombination extends Combination{
    private int count;

    @Builder
    public CountCombination(double reward_multiplier, String when, String group, int count) {
        super(reward_multiplier, when, group);
        this.count = count;
    }

    @Override
    public String toString() {
        return "{reward_multiplier=" + getReward_multiplier() +
                ", when=" + getWhen() +
                ", count=" + count +
                ", group=" + getGroup() +
                '}';
    }
}
