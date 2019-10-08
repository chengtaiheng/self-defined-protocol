package slef.defined.protocol.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import slef.defined.protocol.model.Vacation;

/**
 * @author: 程泰恒
 */

@Slf4j
@Controller
@RequestMapping("/vacation")
public class ProtocolController extends WebMvcConfigurerAdapter {

    @PostMapping(consumes = "application/x-chth")
    @ResponseBody
    public String getVocation(@RequestBody Vacation vacation) {
        log.debug("vacation = {}", vacation);
        return vacation.toString();
    }
}
