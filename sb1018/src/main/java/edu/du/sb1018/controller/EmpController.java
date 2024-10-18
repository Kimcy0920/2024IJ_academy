package edu.du.sb1018.controller;

import edu.du.sb1018.entity.Emp;
import edu.du.sb1018.service.EmpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EmpController {

    final EmpService empService;

    @GetMapping("/")
    public String dept() {
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Emp> list = empService.find_Emp();
        model.addAttribute("datalist", list);
        return "list";
    }

    @GetMapping("/update")
    public String update(Model model) {

        return "update";
    }

    @PostMapping("/delete/{empno}")
    public String delete(@PathVariable Integer empno) {
        empService.remove_Emp(empno);
        return "redirect:/list";
    }

}
