package ae.cyberspeed.outcome;

import ae.cyberspeed.Config;
import ae.cyberspeed.combination.Combination;
import ae.cyberspeed.combination.CountCombination;
import ae.cyberspeed.probability.BonusSymbol;
import ae.cyberspeed.symbol.ExtraBonus;
import ae.cyberspeed.symbol.RewardBonus;
import ae.cyberspeed.symbol.Standard;
import ae.cyberspeed.symbol.Symbol;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@AllArgsConstructor
public class Outcome {

    private final Config config;
    private final String[][] scratchGame;
    private final Map<String, Integer> winningSymbols;

    private final String bonus;
    private final double bet_amount;

    public String getOutcome() throws JsonProcessingException {
        String win = new String("None");
        ObjectMapper mapper = new ObjectMapper();
        if (winningSymbols.size() == 0) {
            return mapper.writeValueAsString(Loss.builder().matrix(scratchGame).reward(0).build());
        } else {
            Map<String, List<String>> applied_winning_combinations = new LinkedHashMap<>();
            double win_amount = 0.0;
            Optional<Entry<String, Symbol>> bonus_symbol = getSymbol(bonus);
            for (Map.Entry<String, Integer> entry : winningSymbols.entrySet()) {
                Optional<Entry<String, Combination>> win_combination = getCombination(config.getWin_combinations(), entry.getValue());
                Optional<Entry<String, Symbol>> win_symbol = getSymbol(entry.getKey());
                if (win_combination.isPresent() && win_symbol.isPresent() && bonus_symbol.isPresent()) {
                    Standard standardSymbol = (Standard) win_symbol.get().getValue();
                    Double reward = bet_amount * win_combination.get().getValue().getReward_multiplier() * standardSymbol.getReward_multiplier();
                    win_amount += reward;
                    applied_winning_combinations.put(win_symbol.get().getKey(),Arrays.asList(win_combination.get().getKey()));
                }
            }
            if(bonus_symbol.isPresent())
                win_amount = addBonus(win_amount, bonus_symbol.get().getValue()) ;
            win = mapper.writeValueAsString(Win.builder()
                    .matrix(scratchGame)
                    .reward(win_amount)
                    .applied_bonus_symbol(bonus)
                    .applied_winning_combinations(applied_winning_combinations)
                    .build());

            return win;
        }
    }


    private Optional<Entry<String, Combination>> getCombination(Map<String, Combination> combinationMap, int count) {
        Optional<Entry<String, Combination>> combination = Optional.empty();
        return combinationMap.entrySet().stream()
                .filter(obj -> obj.getValue() instanceof CountCombination && ((CountCombination) obj.getValue()).getCount() == count)
                .findFirst();
    }

    private Optional<Entry<String, Symbol>> getSymbol(String key) {
        return config
                .getSymbols()
                .entrySet()
                .stream()
                .filter(symbol -> symbol.getKey().equals(key))
                .findFirst();
    }

    private double addBonus(double reward, Symbol symbol) {
        if (symbol instanceof RewardBonus) {
            reward *= ((RewardBonus) symbol).getReward_multiplier();
        } else if (symbol instanceof ExtraBonus) {
            reward += ((ExtraBonus) symbol).getExtra();
        }
        return reward;
    }
}
