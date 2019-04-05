package cc.conyli.sia5.controller;

import cc.conyli.sia5.dao.OrderRepo;
import cc.conyli.sia5.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping("/order")
@SessionAttributes("order")
public class OrderController {

    private OrderRepo orderRepo;

    @Autowired
    public OrderController(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }


    @GetMapping("/form")
    public String showForm() {
        return "order";
    }

    @PostMapping("/process")
    public String processForm(@ModelAttribute("order")@Valid Order order, Errors errors, SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "order";
        }
        orderRepo.save(order);
        sessionStatus.setComplete();
        log.info("保存至数据库的Order是：" + order);
        return "redirect:/";
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }


}
