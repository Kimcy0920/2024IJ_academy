package edu.du.sb1010.config;

import edu.du.sb1010.spring.Client;
import edu.du.sb1010.spring.MemberPrinter;
import edu.du.sb1010.spring.MemberSummaryPrinter;
import edu.du.sb1010.spring.VersionPrinter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
//@ComponentScan(basePackages = {"spring"}) // 패키지명을 풀네임으로 변경
@ComponentScan(basePackages = {"edu.du.sb1010.spring", "edu.du.sb1010.spring2"},
		excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,
				classes = {NoProduct.class, ManualBean.class})) // 이 애노테이션이 붙으면 스캔대상에서 제외.
public class AppCtx {

	@Bean
	@Qualifier("printer")
	public MemberPrinter memberPrinter1() {
		return new MemberPrinter();
	}
	
	@Bean
	@Qualifier("summaryPrinter")
	public MemberSummaryPrinter memberPrinter2() {
		return new MemberSummaryPrinter();
	}
	
	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(5);
		versionPrinter.setMinorVersion(0);
		return versionPrinter;
	}
}
