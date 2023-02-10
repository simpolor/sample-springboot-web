package io.simpolor.websocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChatController {

    @GetMapping("/chat")
    public ModelAndView chat(ModelAndView mav) {

        mav.setViewName("chat");
        return mav;
    }
}
