package slef.defined.protocol.model;

import lombok.*;
import slef.defined.protocol.Chth;

/**
 * @author: 程泰恒
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Vacation implements Chth {

    private Long length;

    private String content;

}
