package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ra.model.dto.FeedbackDTO;
import ra.model.service.IFeedbackService;

@Controller
public class FeedbackController {
    @Autowired
    private IFeedbackService feedbackService;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("list",feedbackService.findAll());
        model.addAttribute("form",new FeedbackDTO());
        return "home";
    }

    @PostMapping("/")
    public String publish(@ModelAttribute FeedbackDTO feedbackDTO) {
        feedbackService.save(feedbackDTO);
        return "redirect:/";
    }

    @GetMapping("{id}")
    public String likes(@PathVariable Long id) {
        feedbackService.like(id);
        return "redirect:/";
    }
}
