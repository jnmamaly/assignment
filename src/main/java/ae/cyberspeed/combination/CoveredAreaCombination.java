package ae.cyberspeed.combination;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CoveredAreaCombination extends Combination{

    private List<List<String>> covered_areas;

    @Builder
    public CoveredAreaCombination(double reward_multiplier, String when, String group, List<List<String>> covered_areas) {
        super(reward_multiplier, when, group);
        this.covered_areas = covered_areas;
    }

    @Override
    public String toString() {
        return "{reward_multiplier=" + getReward_multiplier() +
                ", when=" + getWhen() +
                ", group=" + getGroup() +
                ", covered_areas=" + covered_areas +
                '}';
    }
}
