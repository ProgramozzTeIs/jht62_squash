package pti.sb_squash_mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppController {
	
	private AppService service;

	@Autowired
	public AppController(AppService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/")
	public String loginPage() {
		
		return "login.html";
	}
	
	@PostMapping("/login")
	public String doLogin (
				Model model,
				@RequestParam("user") String userName,
				@RequestParam("password") String password
			
			) {
		
		String targetPage = "";
		
		StatusDto statusDto = service.logInUser(userName, password);
		
		if(statusDto != null && statusDto.getError() == true) {
			
			targetPage = "login.html";
		}
		
		model.addAttribute("statusDto", statusDto);
		
		return targetPage;

	}

}
