package egovframework.fusion.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.fusion.user.service.UserService;
import egovframework.fusion.user.vo.UserVO;


@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	/*
	 * 로그인 페이지 이동
	 * */
	@RequestMapping(value="/user/loginForm.do", method=RequestMethod.GET)
	public String loginForm() {
		return "views/user/loginForm";
	}
	
	/*로그인*/
	@ResponseBody
	@RequestMapping(value="/user/login.do", method=RequestMethod.POST)
	public String login(UserVO userVO, HttpServletRequest request) throws Exception {

		String inputId = userVO.getId();
		String userId = userVO.getNcnm();
		
		String msg = "ok";
	
		if(userService.checkId(inputId) == 0) {
			msg = "idCheck";
		}else if(userService.checkPwd(userVO) == 0) {
			msg = "pwdCheck";
		}
		
		HttpSession session = request.getSession(true);
		session.setAttribute("id", inputId);
		session.setAttribute("userId", userId);
			
		return msg;
	}
	
	/*
	 * 로그아웃
	 * */
	@RequestMapping(value="/user/logout.do", method=RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		 HttpSession session = request.getSession(false); 
		    if (session != null) {
		        session.invalidate();
		    }
		    return "redirect:/board/boardList.do?pageFilter=10";		
	}
	
	/*
	 * 회원가입 페이지 이동
	 * */
	@RequestMapping(value="/user/registerPage.do", method=RequestMethod.GET)
	public String registPage() {
		return "views/user/register";
	}
	
	/*
	 * 회원가입
	 * */
	@ResponseBody
	@RequestMapping(value="/user/regist.do", method=RequestMethod.POST)
	public String regist(UserVO userVO, Model model) throws Exception{

			userService.insUser(userVO);
			
		return "views/user/loginForm";
	}
	
	/*
	 *  아이디 중복 체크 
	 */
	@ResponseBody
	@RequestMapping(value="/user/idCheck.do", method=RequestMethod.GET)
	public String idCheck(Model model, @RequestParam(value="checkId") String checkId ) throws Exception{
		
			
			int result = userService.checkId(checkId);
			
			String msg = "";
			
			if(result == 0) {
				msg = "ok";
			}		
		return msg;
	}
	
	
}