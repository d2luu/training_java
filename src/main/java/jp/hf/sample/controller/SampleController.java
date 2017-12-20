package jp.hf.sample.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jp.hf.sample.form.SampleForm;
import jp.hf.sample.model.SampleModel;

@Controller
public class SampleController {

    @ModelAttribute("sampleForm")
    public SampleForm setSampleForm() {
        return new SampleForm();
    }

    @Autowired
    SampleModel sampleModel;

    // @GetMapping("/sample")
    // public String hello(Model model) {
    // model.addAttribute("msg", sampleModel.holidayTest());
    // return "sample";
    // }

    @GetMapping("/dao")
    public String access(Model model) {
        model.addAttribute("views", sampleModel.findAll());
        model.addAttribute("msg", "Data Access Sample.");
        return "sampledao";
    }

    @GetMapping("/validate")
    public String validate(Model model) {
        model.addAttribute("msg", "入力内容の判定をします。");
        return "samplevalidate";
    }

    @PostMapping("/validate")
    public String validateExec(@Valid @ModelAttribute("sampleForm") SampleForm form,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "samplevalidate";
        }
        model.addAttribute("msg", "入力内容は正しいです。");
        return "samplevalidate";
    }
}
