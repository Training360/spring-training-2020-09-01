package employees.hello.controller;

import employees.hello.service.HelloService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    private HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return helloService.sayHello().toUpperCase();
    }
}
