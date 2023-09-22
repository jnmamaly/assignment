package ae.cyberspeed.outcome;

import ae.cyberspeed.Config;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Win.class, name = "win"),
        @JsonSubTypes.Type(value = Loss.class, name = "loss")
})
public abstract class Result {
    private String[][] matrix;
    private double reward;
}
