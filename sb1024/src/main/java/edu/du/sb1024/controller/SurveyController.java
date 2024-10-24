package edu.du.sb1024.controller;

import edu.du.sb1024.entity.Member;
import edu.du.sb1024.repository.MemberRepository;
import edu.du.sb1024.survey.AnsweredData;
import edu.du.sb1024.survey.Question;
import edu.du.sb1024.survey.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/survey")
public class SurveyController {

	@Autowired
	SurveyService surveyService;

	@Autowired
	private MemberRepository memberRepository;

	// member_id로 member 1명의 정보를 검색하고, answered_id가 null 인지 체크하고,
	// null이 아닌경우 if, null인 경우 else 실행.
	@GetMapping("/surveyForm")
	public String form(Member member, Model model) {
		if (memberRepository.findById(member.getId()) != null) {
			List<Question> questions = createQuestions();
			for (Question question : questions) {
				System.out.println(question);
			}
			model.addAttribute("questions", questions);
			return "/survey/surveyForm";
		} else {
			System.out.println("이미 설문조사를 완료하셨습니다.");
			return "redirect:/sample/all";
		}

	}

	private List<Question> createQuestions() {
		Question q1 = new Question("당신의 역할은 무엇입니까?",
				Arrays.asList("서버", "프론트", "풀스택"));
		Question q2 = new Question("많이 사용하는 개발도구는 무엇입니까?",
				Arrays.asList("이클립스", "인텔리J", "서브라임"));
		Question q3 = new Question("하고 싶은 말을 적어주세요.");
		return Arrays.asList(q1, q2, q3);
	}

	@PostMapping("/submitted")
	public String submit(@ModelAttribute("ansData") AnsweredData data) {
		surveyService.save(data);
		return "/survey/submitted";
	}

}
