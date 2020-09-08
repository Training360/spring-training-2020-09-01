package employees.messages.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Bejön, input
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageCommand {

    private String content;
}
