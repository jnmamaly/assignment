package ae.cyberspeed;

import ae.cyberspeed.combination.Combination;
import ae.cyberspeed.combination.CountCombination;
import ae.cyberspeed.combination.CoveredAreaCombination;
import ae.cyberspeed.probability.Probability;
import ae.cyberspeed.symbol.*;
import com.jayway.jsonpath.JsonPath;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@Slf4j
public class Config {
    private int columns;
    private int rows;
    private Map<String, Symbol> symbols;
    private Probability probabilities;
    private Map<String, Combination> win_combinations;
    private String jsonContent;

    public Config(String jsonFilePath) {
        jsonContent = readConfigurationData(jsonFilePath);
        if(!jsonContent.equals("EXIT")){
            try {
                symbols = new LinkedHashMap<>();
                log.info("Processing Configuration File");
                processDimensions();
                processSymbols();
                probabilities = new Probability(jsonContent);
                processWinCombinations();
            }catch (Exception e){
                log.warn(String.format("Failed to process all configuration properties : %s",e.getMessage()));
            }
        }
    }

    private static String readConfigurationData(String fileName) {
        try {
            return new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            log.warn(String.format("Failed to process the configuration file: %s",e.getMessage()));
            return "EXIT";
        }
    }

    public void processSymbols(){
        symbols = new LinkedHashMap<>();
        Map<String, Object> rawSymbols = JsonPath.read(jsonContent,"$.symbols");
        for (Map.Entry<String, Object> entry: rawSymbols.entrySet()) {
            Map<String, Object> data = (Map<String, Object>) entry.getValue();
            String key =  entry.getKey();
            String type = (String) data.get("type");
            if(type.equals("standard")){
                Standard standard = new Standard(type,Double.parseDouble(data.get("reward_multiplier").toString()));
                symbols.put(key,standard);
            }else{
                String impact = data.get("impact").toString();

                switch (getImpact(impact)){
                    case MULTIPLY_REWARD:
                        double reward = Double.parseDouble(data.get("reward_multiplier").toString());
                        symbols.put(key, RewardBonus.builder().type(type).reward_multiplier(reward).impact(impact).build());
                        break;
                    case EXTRA_BONUS:
                        double extra = Double.parseDouble(data.get("extra").toString());
                        symbols.put(key, ExtraBonus.builder().extra(extra).type(type).impact(impact).build());
                        break;
                    case MISS:
                        symbols.put(key, None.builder().type(type).impact(impact).build());
                        break;
                }
            }

        }
    }
    public void processWinCombinations(){
        Map<String, Object> wins = JsonPath.read(jsonContent,"$.win_combinations");
        win_combinations = new LinkedHashMap<>();
        for (Map.Entry<String, Object> win: wins.entrySet() ) {
            Map<String, Object> data = (Map<String, Object>) win.getValue();
            double reward = Double.parseDouble(data.get("reward_multiplier").toString());
            String when = data.get("when").toString();
            String group = data.get("group").toString();
            if(data.containsKey("count")){
                int count = Integer.parseInt(data.get("count").toString());
                CountCombination combination = CountCombination.builder()
                        .reward_multiplier(reward)
                        .when(when)
                        .count(count)
                        .group(group)
                        .build();
                win_combinations.put(win.getKey(),combination);
            }else {
                List<List<String>> covered_area = (List<List<String>>) data.get("covered_areas");
                CoveredAreaCombination areaCombination = CoveredAreaCombination.builder()
                        .reward_multiplier(reward)
                        .when(when)
                        .group(group)
                        .covered_areas(covered_area)
                        .build();
                win_combinations.put(win.getKey(),areaCombination);
            }
        }

    }
    private static Impact getImpact(String impact){
        return impact.equals("multiply_reward") ? Impact.MULTIPLY_REWARD : impact.equals("extra_bonus") ? Impact.EXTRA_BONUS : Impact.MISS;
    }

    private void processDimensions(){
        rows = JsonPath.read(jsonContent,"$.rows");
        columns = JsonPath.read(jsonContent,"$.columns");
    }
}
