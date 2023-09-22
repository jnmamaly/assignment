package ae.cyberspeed.selection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public abstract class Selection {
    private Map<String, Double> selections;

}
