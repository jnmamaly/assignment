package ae.cyberspeed;

import ae.cyberspeed.outcome.Loss;
import ae.cyberspeed.outcome.Result;
import ae.cyberspeed.outcome.Win;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTest {
    private final String filePath = "src/test/resources/config.json";
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("same_symbol_3_times")
    void test_same_symbol_3_times() throws JsonProcessingException {
        String[][] expected_matrix = new String[][]{{"A", "B", "C"}, {"B", "A", "10x"}, {"F", "D", "B"}};
        Map<String, List<String>> expected_applied_winning_combinations = new LinkedHashMap<String, List<String>>() {{
            put("B", Arrays.asList("same_symbol_3_times"));
        }};
        double expected_reward = 25000.0;
        String expected_bonus = "10x";
        Game game = new Game(expected_matrix, filePath, 100, "10x");
        Win win = (Win) mapper.readValue(game.getOutcome().getOutcome(), Result.class);
        assertArrayEquals(expected_matrix, win.getMatrix());
        assertEquals(expected_bonus, win.getApplied_bonus_symbol());
        assertEquals(expected_reward, win.getReward());
        assertEquals(expected_applied_winning_combinations, win.getApplied_winning_combinations());
    }

    @Test
    @DisplayName("same_symbol_4_times")
    void test_same_symbol_4_times() throws JsonProcessingException {
        String[][] expected_matrix = new String[][]{{"A", "B", "B"}, {"B", "A", "+1000"}, {"F", "D", "B"}};
        Map<String, List<String>> expected_applied_winning_combinations = new LinkedHashMap<String, List<String>>() {{
            put("B", Arrays.asList("same_symbol_4_times"));
        }};
        double expected_reward = 4750.0;
        String expected_bonus = "+1000";
        Game game = new Game(expected_matrix, filePath, 100, "+1000");
        Win win = (Win) mapper.readValue(game.getOutcome().getOutcome(), Result.class);
        assertArrayEquals(expected_matrix, win.getMatrix());
        assertEquals(expected_bonus, win.getApplied_bonus_symbol());
        assertEquals(expected_reward, win.getReward());
        assertEquals(expected_applied_winning_combinations, win.getApplied_winning_combinations());
    }

    @Test
    @DisplayName("same_symbol_5_times")
    void test_same_symbol_5_times() throws JsonProcessingException {
        String[][] expected_matrix = new String[][]{{"A", "B", "C"}, {"B", "B", "10x"}, {"B", "D", "B"}};
        Map<String, List<String>> expected_applied_winning_combinations = new LinkedHashMap<String, List<String>>() {{
            put("B", Arrays.asList("same_symbol_5_times"));
        }};
        double expected_reward = 50000.0;
        String expected_bonus = "10x";
        Game game = new Game(expected_matrix, filePath, 100, "10x");
        Win win = (Win) mapper.readValue(game.getOutcome().getOutcome(), Result.class);
        assertArrayEquals(expected_matrix, win.getMatrix());
        assertEquals(expected_bonus, win.getApplied_bonus_symbol());
        assertEquals(expected_reward, win.getReward());
        assertEquals(expected_applied_winning_combinations, win.getApplied_winning_combinations());
    }

    @Test
    @DisplayName("same_symbol_6_times")
    void test_same_symbol_6_times() throws JsonProcessingException {
        String[][] expected_matrix = new String[][]{{"A", "A", "B"}, {"A", "A", "+1000"}, {"A", "A", "B"}};
        Map<String, List<String>> expected_applied_winning_combinations = new LinkedHashMap<String, List<String>>() {{
            put("A", Arrays.asList("same_symbol_6_times"));
        }};
        double expected_reward = 16000.0;
        String expected_bonus = "+1000";
        Game game = new Game(expected_matrix, filePath, 100, "+1000");
        Win win = (Win) mapper.readValue(game.getOutcome().getOutcome(), Result.class);
        assertArrayEquals(expected_matrix, win.getMatrix());
        assertEquals(expected_bonus, win.getApplied_bonus_symbol());
        assertEquals(expected_reward, win.getReward());
        assertEquals(expected_applied_winning_combinations, win.getApplied_winning_combinations());
    }

    @Test
    @DisplayName("same_symbol_7_times")
    void test_same_symbol_7_times() throws JsonProcessingException {
        String[][] expected_matrix = new String[][]{{"F", "F", "B"}, {"F", "F", "+500"}, {"F", "F", "F"}};
        Map<String, List<String>> expected_applied_winning_combinations = new LinkedHashMap<String, List<String>>() {{
            put("F", Arrays.asList("same_symbol_7_times"));
        }};
        double expected_reward = 1250.0;
        String expected_bonus = "+500";
        Game game = new Game(expected_matrix, filePath, 100, "+500");
        Win win = (Win) mapper.readValue(game.getOutcome().getOutcome(), Result.class);
        assertArrayEquals(expected_matrix, win.getMatrix());
        assertEquals(expected_bonus, win.getApplied_bonus_symbol());
        assertEquals(expected_reward, win.getReward());
        assertEquals(expected_applied_winning_combinations, win.getApplied_winning_combinations());
    }

    @Test
    @DisplayName("same_symbol_8_times")
    void test_same_symbol_8_times() throws JsonProcessingException {
        String[][] expected_matrix = new String[][]{{"D", "D", "D"}, {"D", "D", "+1000"}, {"D", "D", "D"}};
        Map<String, List<String>> expected_applied_winning_combinations = new LinkedHashMap<String, List<String>>() {{
            put("D", Arrays.asList("same_symbol_8_times"));
        }};
        double expected_reward = 6000.0;
        String expected_bonus = "+1000";
        Game game = new Game(expected_matrix, filePath, 100, "+1000");
        Win win = (Win) mapper.readValue(game.getOutcome().getOutcome(), Result.class);
        System.out.println(win.getApplied_winning_combinations());
        System.out.println(win.getApplied_bonus_symbol());
        System.out.println(win.getReward());
        assertArrayEquals(expected_matrix, win.getMatrix());
        assertEquals(expected_bonus, win.getApplied_bonus_symbol());
        assertEquals(expected_reward, win.getReward());
        assertEquals(expected_applied_winning_combinations, win.getApplied_winning_combinations());
    }

    @Test
    @DisplayName("same_symbol_9_times")
    void test_same_symbol_9_times() throws JsonProcessingException {
        String[][] expected_matrix = new String[][]{{"D", "D", "D"}, {"D", "D", "D"}, {"D", "D", "D"}};
        Map<String, List<String>> expected_applied_winning_combinations = new LinkedHashMap<String, List<String>>() {{
            put("D", Arrays.asList("same_symbol_9_times"));
        }};
        double expected_reward = 11000.0;
        String expected_bonus = "+1000";
        Game game = new Game(expected_matrix, filePath, 100, "+1000");
        Win win = (Win) mapper.readValue(game.getOutcome().getOutcome(), Result.class);
        System.out.println(win.getApplied_winning_combinations());
        System.out.println(win.getApplied_bonus_symbol());
        System.out.println(win.getReward());
        assertArrayEquals(expected_matrix, win.getMatrix());
        assertEquals(expected_bonus, win.getApplied_bonus_symbol());
        assertEquals(expected_reward, win.getReward());
        assertEquals(expected_applied_winning_combinations, win.getApplied_winning_combinations());
    }

    @Test
    @DisplayName("less_than_same_symbol_3_times")
    void test_less_than_same_symbol_3_times() throws JsonProcessingException {
        String[][] expected_matrix = new String[][]{{"D", "A", "E"}, {"F", "B", "+1000"}, {"B", "A", "D"}};
        double expected_reward = 0.0;
        Game game = new Game(expected_matrix, filePath, 100, "+1000");
        Loss loss = (Loss) mapper.readValue(game.getOutcome().getOutcome(), Result.class);
        assertArrayEquals(expected_matrix, loss.getMatrix());
        assertEquals(expected_reward, loss.getReward());
    }

    @Test
    @DisplayName("same_symbol_3_times_and_same_symbol_4_times_plus_1000_bonus")
    void test_same_symbol_3_times_and_same_symbol_4_times_plus_1000_bonus() throws JsonProcessingException {
        String[][] expected_matrix = new String[][]{{"D", "+1000", "D"}, {"A", "F", "A"}, {"D", "A", "A"}};
        Map<String, List<String>> expected_applied_winning_combinations =
                new LinkedHashMap<String, List<String>>() {
                    {
                     {put("D", Arrays.asList("same_symbol_3_times"));}
                     {put("A", Arrays.asList("same_symbol_4_times"));}
                    }
                };
        double expected_reward = 9000.0;
        String expected_bonus = "+1000";
        Game game = new Game(expected_matrix, filePath, 100, "+1000");
        Win win = (Win) mapper.readValue(game.getOutcome().getOutcome(), Result.class);
        assertArrayEquals(expected_matrix, win.getMatrix());
        assertEquals(expected_bonus, win.getApplied_bonus_symbol());
        assertEquals(expected_reward, win.getReward());
        assertEquals(expected_applied_winning_combinations, win.getApplied_winning_combinations());
    }

    @Test
    @DisplayName("same_symbol_3_times_and_same_symbol_4_times_plus_1000_bonus")
    void test_same_symbol_3_times_and_same_symbol_4_times_with_times_10_bonus() throws JsonProcessingException {
        String[][] expected_matrix = new String[][]{{"D", "10x", "D"}, {"A", "F", "A"}, {"D", "A", "A"}};
        Map<String, List<String>> expected_applied_winning_combinations =
                new LinkedHashMap<String, List<String>>() {
                    {
                        {put("D", Arrays.asList("same_symbol_3_times"));}
                        {put("A", Arrays.asList("same_symbol_4_times"));}
                    }
                };
        double expected_reward = 80000.0;
        String expected_bonus = "10x";
        Game game = new Game(expected_matrix, filePath, 100, "10x");
        Win win = (Win) mapper.readValue(game.getOutcome().getOutcome(), Result.class);
        assertArrayEquals(expected_matrix, win.getMatrix());
        assertEquals(expected_bonus, win.getApplied_bonus_symbol());
        assertEquals(expected_reward, win.getReward());
        assertEquals(expected_applied_winning_combinations, win.getApplied_winning_combinations());
    }

    @Test
    @DisplayName("same_symbol_3_times_and_same_symbol_4_times_miss_bonus")
    void test_same_symbol_3_times_and_same_symbol_4_times_with_miss_bonus() throws JsonProcessingException {
        String[][] expected_matrix = new String[][]{{"D", "MISS", "D"}, {"A", "F", "A"}, {"D", "A", "A"}};
        Map<String, List<String>> expected_applied_winning_combinations =
                new LinkedHashMap<String, List<String>>() {
                    {
                        {put("D", Arrays.asList("same_symbol_3_times"));}
                        {put("A", Arrays.asList("same_symbol_4_times"));}
                    }
                };
        double expected_reward = 8000.0;
        String expected_bonus = "MISS";
        Game game = new Game(expected_matrix, filePath, 100, "MISS");
        Win win = (Win) mapper.readValue(game.getOutcome().getOutcome(), Result.class);
        System.out.println(win.getApplied_winning_combinations());
        System.out.println(win.getApplied_bonus_symbol());
        System.out.println(win.getReward());
        assertArrayEquals(expected_matrix, win.getMatrix());
        assertEquals(expected_bonus, win.getApplied_bonus_symbol());
        assertEquals(expected_reward, win.getReward());
        assertEquals(expected_applied_winning_combinations, win.getApplied_winning_combinations());
    }


}
