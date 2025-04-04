package pti.sb_squash_mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pti.sb_squash_mvc.dto.AdminDto;
import pti.sb_squash_mvc.dto.GameDto;
import pti.sb_squash_mvc.dto.LoginUserDto;
import pti.sb_squash_mvc.dto.StatusDto;
import pti.sb_squash_mvc.service.AppService;

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
		
		LoginUserDto lud = service.logInUser(userName, password);
		
		if(lud == null) {
			StatusDto statusDto = new StatusDto(true);
			model.addAttribute("statusDto", statusDto);
			targetPage = "login.html";
		}
		else if (lud.isFirstLogin() == true) {
			
			// TODO Get ChangePwdDto
			targetPage = "changepwd.html";
		}
		else if (lud.getRole().equals("admin")) {
			
			AdminDto adminDto = service.getAdminData(lud.getUserId());
			model.addAttribute("adminDto", adminDto);
			targetPage = "admin.html";
		}
		else {
			
			GameDto gameDto = service.showResults(lud.getUserId());
			model.addAttribute("gameDto", gameDto);
			targetPage = "games.html";
		}
		
		
		
		return targetPage;

	}
	
	@GetMapping("/user/search/name")
	public String getGamesByFilteredName (
				Model model,
				@RequestParam("uid") int userId,
				@RequestParam("filtername") int filterNameId
			
			) {
		
		GameDto gameDto = service.getAllGamesByIds(userId, filterNameId, 0);
		
		model.addAttribute("gameDto", gameDto);
		
		return "games.html";
	}
	
	@GetMapping("/user/search/place")
	public String getGamesByFilteredPlace (
				Model model,
				@RequestParam("uid") int userId,
				@RequestParam("filterplace") int filterPlaceId
			
			) {
		
		GameDto gameDto = service.getAllGamesByIds(userId, 0, filterPlaceId);
		
		model.addAttribute("gameDto", gameDto);
		
		return "games.html";
	}
	
	@PostMapping("/admin/reg/place")
	public String registerNewPlace(
				Model model,
				@RequestParam("uid") int userId,
				@RequestParam("name") String newPlaceName,
				@RequestParam("price") int newPlacePrice,
				@RequestParam("address") String newPlaceAddress
			
			) {
		AdminDto adminDto = service.registerPlace(userId, newPlaceName, newPlacePrice, newPlaceAddress);
		
		model.addAttribute("adminDto", adminDto);
		
		return "admin.html";
	}
	
	@PostMapping("/admin/reg/user")
	public String registerNewUser (
				Model model,
				@RequestParam("uid") int userId,
				@RequestParam("uname") String userName,
				@RequestParam("urole") String userRole
			) {
		
		AdminDto adminDto = service.registerUser(userId, userName, userRole);
		
		model.addAttribute("adminDto", adminDto);

		return "admin.html";
	}

}





