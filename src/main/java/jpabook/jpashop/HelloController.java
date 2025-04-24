package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!");
        return "hello"; // 리턴 화면 이름, 타임리프 디펜던시에 기본 세팅 다 잡혀서 가능 .html 뒤에 붙어서 templates에 매핑됨
    }

}
