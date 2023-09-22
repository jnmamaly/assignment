package ae.cyberspeed;

import ae.cyberspeed.outcome.Outcome;
import ae.cyberspeed.probability.BonusSymbol;
import ae.cyberspeed.probability.StandardSymbol;
import ae.cyberspeed.selection.BonusSelection;
import ae.cyberspeed.selection.Selection;
import ae.cyberspeed.selection.StandardSelection;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class Game {

    private final Config config;
    private String[][] scratchGame;

    private  List<Selection> standardSelections;
    private  Selection bonusSelection;
    private  List<Map<Integer, Integer>> unusedDimensions;

    private  Map<String, Integer> winningSymbols;
    @Getter
    private  Outcome outcome;
    private String bonus;
    private double betAmount;

    public Game(String[][]scratchGame, String filePath, double betAmount,String bonus){
        config = new Config(filePath);
        this.scratchGame = scratchGame;
        standardSelections = computeProportionateSelections(1);
        bonusSelection = computeProportionateSelections(2).get(0);
        unusedDimensions = null;
        this.bonus = bonus;
        this.betAmount = betAmount;
        processWinningSymbols();
        outcome = new Outcome(config,scratchGame,winningSymbols,bonus,betAmount);

    }

    public Game(String filePath, double betAmount) {
        config = new Config(filePath);
        if(config.getJsonContent().equals("EXIT")){
            log.info(String.format("Please ensure that the configuration file  %s exists in the project root folder or  has a valid path",filePath));
            return;
        }
        this.betAmount = betAmount;
        scratchGame = new String[config.getRows()][config.getColumns()];
        standardSelections = computeProportionateSelections(1);
        bonusSelection = computeProportionateSelections(2).get(0);
        unusedDimensions = new ArrayList<>();
        for (int i = 0; i < scratchGame.length; i++) {
            for (int j = 0; j < scratchGame[i].length; j++) {
                unusedDimensions.add(Collections.singletonMap(i, j));
                scratchGame[i][j] = "S";
            }
        }
        fillScratchGameBoard();
        displayBoard();
        processWinningSymbols();
        outcome = new Outcome(config,scratchGame,winningSymbols,bonus,this.betAmount);
        try {
            String result = outcome.getOutcome();
            System.out.println(result.replace(result.substring(1,result.indexOf("matrix")-1),""));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String filePath;
        double betAmount;
        if(args.length==4) {
            try {
                betAmount = Double.parseDouble(args[3]);
            }catch (NumberFormatException e){
                log.warn(String.format("Bet Amount %s is invalid, please provide a valid number.", e.getMessage()));
                return;
            }

            filePath = args[1];
            new Game(filePath, betAmount);

        }else {
            log.warn("Invalid command line:");
            log.warn("Valid command line example: java -jar target\\assignment-1.0-shaded.jar  --config config.json --betting-amount 100");
        }


    }

    private void displayBoard() {
        System.out.println();
        for (String[] strings : scratchGame) {
            int columns = strings.length;
            for (int j = 0; j < columns; j++) {
                if (j < columns - 1)
                    System.out.printf("|%-5s", strings[j]);
                else
                    System.out.printf("|%-5s|", strings[j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    private List<Selection> computeProportionateSelections(int symbol) {

        List<Selection> selections = new ArrayList<>();

        switch (symbol) {
            case 1:
                for (StandardSymbol stdSymbol : config.getProbabilities().getStandardSymbols()) {
                    Map<String, Double> changes = computeSymbolChances(stdSymbol.getSymbols());
                    selections.add(
                            StandardSelection
                                    .builder()
                                    .row(stdSymbol.getRow())
                                    .column(stdSymbol.getColumn())
                                    .selections(changes)
                                    .build()
                    );
                }
                break;
            case 2:
                BonusSymbol bonusSymbol = config.getProbabilities().getBonusSymbol();
                Map<String, Double> changes = computeSymbolChances(bonusSymbol.getSymbols());
                selections.add(
                        BonusSelection.builder().selections(changes).build()
                );
                break;

        }
        return selections;
    }
    private Map<String, Double> computeSymbolChances(Map<String, Integer> symbols) {
        double previous_rate = 0.0;
        int population = symbols.values().stream().mapToInt(val -> val).sum();
        Map<String, Double> selections = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : symbols.entrySet()) {
            previous_rate += (double) entry.getValue() / population;
            selections.put(entry.getKey(), previous_rate);
        }
        return selections;
    }
    private String getSymbol(Selection selection) {
        double symbol = Math.random();
        return selection.getSelections().entrySet().stream().filter(entry -> entry.getValue() > symbol)
                .findFirst().get().getKey();
    }
    private void fillScratchGameBoard(){
        //Processing standard symbols with coordinates(row,column)
        for (Selection stdSelection: standardSelections) {
            StandardSelection selection = (StandardSelection) stdSelection;
            scratchGame[selection.getRow()][selection.getColumn()] = getSymbol(selection);
            Map<Integer, Integer> usedIndexes = Collections.singletonMap(selection.getRow(),selection.getColumn());
            unusedDimensions.remove(usedIndexes);
        }
        //Processing the bonus symbol ;
        Random random = new Random(System.currentTimeMillis());
        int bonusPosition = random.nextInt(unusedDimensions.size());
        Map<Integer, Integer> dimension = unusedDimensions.get(bonusPosition);
        Map.Entry<Integer, Integer> entry = dimension.entrySet().stream().findFirst().get();
        bonus = getSymbol(bonusSelection);
        scratchGame[entry.getKey()][entry.getValue()] = bonus;
        unusedDimensions.remove(dimension);
        //Processing standard symbols without coordinates(row,column) randomly
        for (Map<Integer, Integer> indexes: unusedDimensions) {
            entry = indexes.entrySet().stream().findFirst().get();
            int standardRandomChoice = random.nextInt(standardSelections.size());
            Selection selection = standardSelections.get(standardRandomChoice);
            scratchGame[entry.getKey()][entry.getValue()] = getSymbol(selection);
        }
    }
    private void processWinningSymbols(){
        Map<String, Integer> winSymbols = new LinkedHashMap<>();
        String key;
        for (String[] strings : scratchGame) {
            int columns = strings.length;
            for (int j = 0; j < columns; j++) {
                key = strings[j];
                winSymbols.compute(key, (Key, val) -> (val == null) ? 1 : ++val);
            }

        }
       winningSymbols = winSymbols.entrySet().stream().filter(entry-> entry.getValue()>=3).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    }
}
