package cc.conyli.sia5.controller;

import cc.conyli.sia5.dao.TacoRepo;
import cc.conyli.sia5.entity.Order;
import cc.conyli.sia5.entity.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/cancel")
@SessionAttributes("order")
public class CancelController {

    private TacoRepo tacoRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public CancelController(TacoRepo tacoRepo, PasswordEncoder passwordEncoder) {
        this.tacoRepo = tacoRepo;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping
    public String cancelAll(@ModelAttribute("order") Order order, SessionStatus sessionStatus) {
        List<Taco> tacos = order.getTacos();
        for (Taco taco : tacos) {
            log.info("删除的Taco是：" + taco);
            tacoRepo.delete(taco);
        }
        sessionStatus.setComplete();
        log.info("Session 清除完毕");

        log.info("----------------------以下测试密码--------------------------");
        log.info(passwordEncoder.encode("fflym0709"));
        if (passwordEncoder.matches("fflym0709","$2a$04$TrqGLKU3fhuyjVfRJnzpUuiY1acHjkt9.bAKgFS/KBaUeEfc11qCy")) {
            log.info("$2a$04$TrqGLKU3fhuyjVfRJnzpUuiY1acHjkt9.bAKgFS/KBaUeEfc11qCy fflym0709 匹配成功");
        } else {
        log.info(" $2a$04$TrqGLKU3fhuyjVfRJnzpUuiY1acHjkt9.bAKgFS/KBaUeEfc11qCy fflym0709 匹配失败");
        }

        return "redirect:/";
    }
}
