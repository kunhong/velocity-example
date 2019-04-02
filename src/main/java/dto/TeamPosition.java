package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Collections;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TeamPosition {
    private String id;
    private String _id;
    private int pos;
    private List<Double> startings = Collections.emptyList();
}
