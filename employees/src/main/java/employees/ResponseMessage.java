package employees;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Kifelé menő, topicra menő
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage {

    private String content;
}
