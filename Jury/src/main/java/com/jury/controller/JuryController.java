package com.jury.controller;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.jury.model.CaseTypes;
import com.jury.model.SubCaseDetail;
import com.jury.model.SubCases;
import com.jury.model.User;
import com.jury.model.UserCase;
import com.jury.service.CaseService;
import com.jury.service.SubCaseDetailService;
import com.jury.service.SubCaseService;
import com.jury.service.UserCaseService;
import com.jury.service.UserService;
 import javax.mail.*;
 import javax.mail.internet.*;
 import javax.activation.*;
@Controller
public class JuryController {
	String message = "Welcome to Spring MVC!";
	private UserService userService;
	private CaseService caseService;
	private SubCaseService subCaseService;
	private SubCaseDetailService subCaseDetailService;
	private UserCaseService userCaseService;
	@Autowired(required=true)
	@Qualifier(value="userService")
	public void setUserService(UserService userService){
		this.userService = userService;
	}
	@Autowired(required=true)
	@Qualifier(value="caseService")
	public void setUserService(CaseService caseService){
		this.caseService = caseService;
	}
	@Autowired(required=true)
	@Qualifier(value="subCaseService")
	public void setUserService(SubCaseService subCaseService){
		this.subCaseService = subCaseService;
	}
	@Autowired(required=true)
	@Qualifier(value="subCaseDetailService")
	public void setUserService(SubCaseDetailService subCaseDetailService){
		this.subCaseDetailService = subCaseDetailService;
	}
	@Autowired(required=true)
	@Qualifier(value="userCaseService")
	public void setUserService(UserCaseService userCaseService){
		this.userCaseService = userCaseService;
	}
	@RequestMapping("/caseproceed")
	public ModelAndView caseProceed(
			@RequestParam(value = "subcasetype", required = false, defaultValue = "World") String subcasetype) {
		System.out.println("in subcasetype controller"+subcasetype);
		List<SubCaseDetail> scd = subCaseDetailService.getSubCaseDetail(subcasetype);
		ModelAndView mv = new ModelAndView("caseproceed");
		mv.addObject("casename", subcasetype);
		mv.addObject("scd", scd);
		return mv;
	}
	
	
	@RequestMapping("/logincheck")
	public ModelAndView loginCheck(@RequestParam(value = "username", required = false, defaultValue = "World") String name,@RequestParam(value = "password", required = false, defaultValue = "World") String password,HttpServletRequest request) {
		
		String usertype=(String)request.getParameter("usertype");
		User user = userService.findByUserName(name,password);
		if(user == null){
			User uu = userService.findByUser(name);
			if(uu != null){
				ModelAndView mv = new ModelAndView("redirect:login.jsp");
				mv.addObject("message", "password incorrect!");
				return mv;
			}
			ModelAndView mv = new ModelAndView("redirect:login.jsp");
			mv.addObject("message", "Invalid credentails");
			return mv;
		}
		System.out.println("in login controller"+usertype+"======usertypedata"+user.getUsertype()+!user.getUsertype().equals(usertype));
		if(!user.getUsertype().equals(usertype)){
			ModelAndView mv = new ModelAndView("redirect:login.jsp");
			mv.addObject("message", "user type is not correct!");
			return mv;
		}
		if(!user.getIsenabled().equals("Y")){
			ModelAndView mv = new ModelAndView("resetpassword");
			request.getSession().setAttribute("userLogin", user.getUsername());
			request.getSession().setAttribute("isAdmin", user.getIsAdmin());
			return mv;
		}
		ModelAndView mv = new ModelAndView("home");
		System.out.println("in login controller got it"+user+password+user.getIsAdmin());
		request.getSession().setAttribute("userLogin", user.getUsername());
		request.getSession().setAttribute("isAdmin", user.getIsAdmin());
		mv.addObject("name", name);
		return mv;
	}
	@RequestMapping("/userregistration")
	public ModelAndView userRegistration(@RequestParam(value = "username", required = false, defaultValue = "World") String name,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "lname", required = false) String lname,
			@RequestParam(value = "fname", required = false) String fname,
			@RequestParam(value = "mname", required = false) String mname,
			@RequestParam(value = "city", required = false) String city,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "address", required = false) String address,
			@RequestParam(value = "securityQuestion1", required = false) String securityQuestion1,
			@RequestParam(value = "sa1", required = false) String sa1,
			@RequestParam(value = "securityQuestion2", required = false) String securityQuestion2,
			@RequestParam(value = "sa2", required = false) String sa2,
			@RequestParam(value = "usertype", required = false) String usertype,
			@RequestParam(value = "gender", required = false) String gender,
			@RequestParam(value = "zipcode", required = false) String zipcode,
			
			HttpServletRequest request) {
		System.out.println("in register controller");
		User userCheck = userService.findByUser(name);
		if(userCheck != null){
			ModelAndView mv = new ModelAndView("redirect:register.jsp");
			mv.addObject("message", "UserId already exists");
			return mv;
		}
		java.util.Date date= new java.util.Date();
		System.out.println();
		User u = new User(name, password,  new Timestamp(date.getTime()), email, phone, address, city, fname, lname, mname,securityQuestion1,sa1,securityQuestion2,sa2,null,usertype,gender,zipcode,"Y");
		this.userService.addUser(u);
		ModelAndView mv = new ModelAndView("home");
		request.getSession().setAttribute("userLogin", u.getUsername());
		request.getSession().setAttribute("isAdmin", u.getIsAdmin());
		mv.addObject("name", name);
		return mv;
	}
	@RequestMapping("/newcase")
	public ModelAndView newcase() {
		ModelAndView mv = new ModelAndView("newcase");
		List<CaseTypes> caseTypes = caseService.getCaseTypes();
		mv.addObject("caseTypes", caseTypes);
		System.out.println(caseTypes.size());
	    return mv;
	}
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/login.jsp";
	}
/*	
	@RequestMapping(value = "/getcasetypes", method = RequestMethod.GET,headers = "Accept=application/json")
	public @ResponseBody List<CaseTypes> getSearchResultViaAjax(@RequestParam(value = "casetype", required = false, defaultValue = "World") String casetype) {
		System.out.println("in get cases conbtroller"+casetype);
		List<CaseTypes> casetypes = caseService.getCaseTypes();
		System.out.println("in get cases conbtroller"+casetypes.get(0).getCasedescription());
		//ModelAndView mv = new ModelAndView("home");
		return casetypes; 

	}
*/
	
	 @RequestMapping(value = "/checkUser")
		public ModelAndView checkUser(HttpServletRequest request) {
			ModelAndView mv = null;
			User user = userService.findByUser((String)request.getParameter("userId"));
			if(user != null){
				mv = new ModelAndView("resetpassword");
				mv.addObject("user", user);
			}else{
				mv = new ModelAndView("redirect:forgotpassword.jsp");
				mv.addObject("error", "Invalid UserLoginId");
				
			}
		    return mv;
		}
	 
				
			 @RequestMapping(value = "/checkUseremail")
				public ModelAndView checkUseremail(HttpServletRequest request) {
					ModelAndView mv = null;
					User user = userService.findByUser((String)request.getParameter("userId"));
					if(user != null){
						mv = new ModelAndView("emailpasswordcheck");
						mv.addObject("user", user);
					}else{
						mv = new ModelAndView("redirect:emailpassword.jsp");
						mv.addObject("error", "Invalid UserLoginId");
						
					}
				    return mv;
				}
			 
			 
			 
	  @RequestMapping(value = "/resetpassword")
		public ModelAndView resetPassword(HttpServletRequest request) {
			ModelAndView mv = null;
			String username = (String)request.getParameter("username");
			String count = (String)request.getParameter("count");
			int c = 0;
			if(count != null){
				c = Integer.parseInt(count);
			}
			String sa1 = (String)request.getParameter("sa1");
			User user = userService.findByUserAnswers(sa1,username);
			if(user != null){
				mv = new ModelAndView("changepassword");
				mv.addObject("user", user);
			}else{
				user = userService.findByUser(username);
				mv = new ModelAndView("resetpassword");
				mv.addObject("user", user);
				mv.addObject("error", "Your answers are incorrect!");
				c= c+1;
				count = String.valueOf(c);
				request.getSession().setAttribute("count",c);
				System.out.println(c);
				if(c > 2){
					request.getSession().removeAttribute("count");
					mv = new ModelAndView("resetpassword1");
					mv.addObject("user", user);
				}
			}
		   
			return mv;
		}
	  
	  
	  @RequestMapping(value = "/resetpasswordsec")
		public ModelAndView resetPasswordsec(HttpServletRequest request) {
			ModelAndView mv = null;
			String username = (String)request.getParameter("username");
			String sa2 = (String)request.getParameter("sa2");
			User user = userService.findByUserAnswers2(sa2,username);
			System.out.println(sa2);
			if(user != null){
				mv = new ModelAndView("changepassword");
				mv.addObject("user", user);
			}else{
				user = userService.findByUser(username);
				mv = new ModelAndView("resetpassword1");
				mv.addObject("user", user);
				mv.addObject("error", "Your answers are incorrect!");
			}
			return mv;
		}
	
	  @RequestMapping(value = "/changepassword")
		public ModelAndView changePassword(HttpServletRequest request) {
		  System.out.println("in cahnge");
		  ModelAndView mv = new ModelAndView("home");
		  String newpassword = (String)request.getParameter("newpassword");
		  String username = (String)request.getParameter("username");
		  String password = (String)request.getParameter("password");
		  User u = userService.findByUserName(username, password);
		  System.out.println(u);
		  if(u == null){
			  	mv = new ModelAndView("changepassword");
				mv.addObject("message", "old password incorrect");
				return mv;
		  }
		  userService.updateUserPassword(newpassword,username);
		  request.getSession().setAttribute("userLogin", username);
		  mv.addObject("message", "Your password has been changed succesfully");
		 
		  return mv;
		}
		@RequestMapping(value = "/getsubcasetypes", method = RequestMethod.GET,headers = "Accept=application/json")
		public @ResponseBody List<SubCases> getSearchResultViaAjax(@RequestParam(value = "casetype", required = false, defaultValue = "World") String casetype) {
			System.out.println("in get cases conbtroller"+casetype);
			List<SubCases> casetypes = subCaseService.getSubCaseTypes(casetype);
			System.out.println("in get cases conbtroller"+casetypes.get(0).getSubcase());
			//ModelAndView mv = new ModelAndView("home");
			return casetypes; 

		}
	
	   @RequestMapping(value = "/caseinfo",method=RequestMethod.POST)
	   public ModelAndView caseInfo(HttpServletRequest request) {
		  System.out.println("in cahnge");
		  ModelAndView mv = new ModelAndView("redirect:viewcaseinfo");
		  String accusedname = (String)request.getParameter("accusedname");
		  String accusednumber = (String)request.getParameter("accusednumber");
		  String cc = (String)request.getParameter("count");
		  String casename = (String)request.getParameter("casename");
		  String username = (String)request.getSession().getAttribute("userLogin");
		  System.out.println(cc);
		  int count = Integer.parseInt((String)request.getParameter("count"));
		  String casetype = "";
		  for(int i=1;i<=count-1;i++){
			  String ans = (String)request.getParameter("q"+i);
			  if(!ans.equals("no")){
				 // casetype = casetype+ans;
				  casetype = casetype+"<p>Result:</style></p>"+ans;  
			  }
		  }
		  java.util.Date date= new java.util.Date();
		  System.out.println("====uhhh====="+casename);
		  int nextId = userCaseService.getNextId();
		  UserCase uc = new UserCase(nextId+1, accusedname, accusednumber, casetype,casename,"",username,new Timestamp(date.getTime()));
		  this.userCaseService.addUserCase(uc);
		  //mv.addObject("usercase", uc);
		  //ra.addFlashAttribute("usercase", uc);
		  request.getSession().setAttribute("usercase", uc);
		  return mv;
		}
	   
	   @RequestMapping(value = "/viewcaseinfo", method = RequestMethod.GET)
	   public ModelAndView viewcaseinfo(HttpServletRequest request)
	   {
	       ModelAndView modelAndView = new ModelAndView("caseinfo");
	       UserCase uc = (UserCase)request.getSession().getAttribute("usercase");
	       modelAndView.addObject("usercase", uc);
	       return modelAndView;
	   }
	   @RequestMapping(value = "/viewcase", method = RequestMethod.GET)
	   public ModelAndView viewcase(HttpServletRequest request)
	   {
	       ModelAndView modelAndView = new ModelAndView("caseinfo");
	       System.out.println((String)request.getParameter("id"));
	       int id = Integer.parseInt((String)request.getParameter("id"));
	       String username = (String)request.getSession().getAttribute("userLogin");
	       UserCase uc = userCaseService.getUserCaseById(id,username);
	       modelAndView.addObject("usercase", uc);
	       return modelAndView;
	   }
	   
	   
		 @RequestMapping(value = "/viewpreviouscase")
			public ModelAndView viewPreviousCase(HttpServletRequest request) {
				ModelAndView mv = new ModelAndView("viewpreviouscase");
				
				String username = (String)request.getSession().getAttribute("userLogin");
				List<UserCase> usercase = userCaseService.listUserCases(username);
				System.out.println("connnn"+username);
				mv.addObject("usercase", usercase);
			    return mv;
			}
			@RequestMapping("/changecases")
			public ModelAndView changecases() {
				ModelAndView mv = new ModelAndView("changecases");
				List<CaseTypes> caseTypes = caseService.getCaseTypes();
				mv.addObject("caseTypes", caseTypes);
				System.out.println(caseTypes.size());
			    return mv;
			}
	   
			@RequestMapping("/editcaseinfo")
			public ModelAndView editcaseinfo(HttpServletRequest request) {
				ModelAndView mv = new ModelAndView("subcaseinfo");
				String subcasetype = (String)request.getParameter("subcasetype");
				System.out.println("in subcasetype controller"+subcasetype);
				List<SubCaseDetail> scd = subCaseDetailService.getSubCaseDetail(subcasetype);
				mv.addObject("casename", subcasetype);
				mv.addObject("scd", scd);
			    return mv;
			}
			
			@RequestMapping("/editquestioninfo")
			public ModelAndView editquestioninfo(HttpServletRequest request) {
				ModelAndView mv = new ModelAndView("editcaseinfo");
				int id = Integer.parseInt((String)request.getParameter("id"));
				System.out.println("in subcasetype controller"+id);
				SubCaseDetail scd = subCaseDetailService.getSubCaseById(id);
				mv.addObject("scd", scd);
			    return mv;
			}
			@RequestMapping("/updatecaseinfo")
			public ModelAndView updatecaseinfo(HttpServletRequest request) {
				ModelAndView mv = new ModelAndView("subcaseinfo");
				String q = (String)request.getParameter("q");
				String a = (String)request.getParameter("a");
				int id = Integer.parseInt((String)request.getParameter("id"));
				this.subCaseDetailService.updateCaseQA(q, a, id);
				String subcasetype = (String)request.getParameter("subcasetype");
				List<SubCaseDetail> scd = subCaseDetailService.getSubCaseDetail(subcasetype);
				mv.addObject("casename", subcasetype);
				mv.addObject("scd", scd);
			    return mv;
			}
			@RequestMapping("/deletecaseqa")
			public ModelAndView deletecaseqa(HttpServletRequest request) {
				ModelAndView mv = new ModelAndView("subcaseinfo");
				int id = Integer.parseInt((String)request.getParameter("id"));
				String subcasetype = (String)request.getParameter("subcasetype");
				System.out.println("in subcasetype controller"+id);
				SubCaseDetail sd = subCaseDetailService.getSubCaseById(id);
				if(sd == null){
					List<SubCaseDetail> scd = subCaseDetailService.getSubCaseDetail(subcasetype);
					mv.addObject("scd", scd);
					mv.addObject("casename", subcasetype);
					return mv;
				}
				this.subCaseDetailService.removeQA(id);
				
				List<SubCaseDetail> scd = subCaseDetailService.getSubCaseDetail(subcasetype);
				mv.addObject("scd", scd);
				mv.addObject("casename", subcasetype);
			    return mv;
			}
			@RequestMapping("/addcaseqa")
			public ModelAndView addcaseqa(HttpServletRequest request) {
				ModelAndView mv = new ModelAndView("addcaseinfo");
				String subcasetype = (String)request.getParameter("subcasetype");
				System.out.println("asdasdasd"+subcasetype);
				
				mv.addObject("subcasetype", subcasetype);
			    return mv;
			}
			@RequestMapping("/addnewcaseqa")
			public ModelAndView addnewcaseqa(HttpServletRequest request) {
				ModelAndView mv = new ModelAndView("redirect:newsubcaseinfo");
				String subcasetype = (String)request.getParameter("subcasetype");
				String casequestions = (String)request.getParameter("q");
				String verdict = (String)request.getParameter("a");
				int nextId = subCaseDetailService.getNextId();
				SubCases ss = subCaseService.getCaseTypes(subcasetype);
				String casename = ss.getCasename();
				System.out.println(casename);
				SubCaseDetail sc = new SubCaseDetail(nextId+1, casename, subcasetype, casequestions, verdict);
				subCaseDetailService.addsubcase(sc);
				List<SubCaseDetail> scd = subCaseDetailService.getSubCaseDetail(subcasetype);
				mv.addObject("scd", scd);
				mv.addObject("casename", scd.get(0).getSubcase());
				HttpSession session = request.getSession();
				session.setAttribute("scd", scd);
				session.setAttribute("casename", scd.get(0).getSubcase());
			    return mv;
			}
			
			@RequestMapping("/newsubcaseinfo")
			public ModelAndView newsubcaseinfo(HttpServletRequest request) {
				ModelAndView mv = new ModelAndView("subcaseinfo");
				HttpSession session = request.getSession();
				List<SubCaseDetail> scd = (List<SubCaseDetail>)session.getAttribute("scd");
				String casename = (String)session.getAttribute("casename");
				mv.addObject("scd", scd);
				mv.addObject("casename", casename);
			    return mv;
			}
			
			@RequestMapping(value = "/checkUserId", method = RequestMethod.GET,headers = "Accept=application/json")
			public @ResponseBody Map checkUserId(@RequestParam(value = "userLoginId", required = false, defaultValue = "World") String userLoginId) {
				System.out.println("in get cases conbtroller"+userLoginId);
				User user = userService.findByUser(userLoginId);
				System.out.println("in get cases conbtroller"+user);
				//ModelAndView mv = new ModelAndView("home");
				Map mp = new HashMap<String, String>();
				if(user==null){
					mp.put("value", "does not exists");
				}
				else{
					mp.put("value", "userId Already exists");
				}
				return mp; 

			}
			
			
			@RequestMapping("/addSubcase")
			public ModelAndView addSubcase(HttpServletRequest request) {
				ModelAndView mv = null;
				String subcasename = (String)request.getParameter("subcasename");
				String maincase = (String)request.getParameter("maincase");
				if(subCaseService.getCaseTypes(subcasename) != null){
					 mv = new ModelAndView("redirect:changecases");
					 mv.addObject("message", "Sub case already exists for that case type");
					 return mv;
				}
				mv = new ModelAndView("redirect:changecases");
				SubCases sc = new SubCases(maincase,subcasename);
				subCaseService.addSubCase(sc);
				 mv.addObject("message", "Added Successfully");
			    return mv;
			}
			
			@RequestMapping("/deleteCase")
			public ModelAndView deleteCase(HttpServletRequest request) {
				ModelAndView mv = new ModelAndView("redirect:viewpreviouscase");
				int id = Integer.parseInt((String)request.getParameter("id"));
				userCaseService.removeUserCase(id);
			    return mv;
			}
			@RequestMapping("/searchResults")
			public ModelAndView searchResults(HttpServletRequest request) {
				ModelAndView mv = new ModelAndView("searchResults");
				String search = (String)request.getParameter("search");
				//userCaseService.removeUserCase(id);
				List<SubCaseDetail> scds = subCaseDetailService.searchSubCaseDetail(search);
				mv.addObject("scds",scds);
			    return mv;
			}
			@RequestMapping("/changeuserpassword")
			public ModelAndView changeuserpassword(HttpServletRequest request) {
				ModelAndView mv = new ModelAndView("changepassword");
				String username = (String)request.getSession().getAttribute("userLogin");
				//userCaseService.removeUserCase(id);
				User u  = userService.findByUser(username);
				mv.addObject("user",u);
			    return mv;
			}
			@RequestMapping("/profile")
			public ModelAndView profile(HttpServletRequest request) {
				ModelAndView mv = new ModelAndView("profile");
				String username = (String)request.getSession().getAttribute("userLogin");
				//userCaseService.removeUserCase(id);
				User u  = userService.findByUser(username);
				System.out.println(u.getUsername());
				mv.addObject("user",u);
			    return mv;
			}
			@RequestMapping("/editProfile")
			public ModelAndView editProfile(@RequestParam(value = "username", required = false, defaultValue = "World") String name,
					@RequestParam(value = "password", required = false) String password,
					@RequestParam(value = "email", required = false) String email,
					@RequestParam(value = "lname", required = false) String lname,
					@RequestParam(value = "fname", required = false) String fname,
					@RequestParam(value = "mname", required = false) String mname,
					@RequestParam(value = "city", required = false) String city,
					@RequestParam(value = "phone", required = false) String phone,
					@RequestParam(value = "address", required = false) String address,
					@RequestParam(value = "securityQuestion1", required = false) String securityQuestion1,
					@RequestParam(value = "sa1", required = false) String sa1,
					@RequestParam(value = "securityQuestion2", required = false) String securityQuestion2,
					@RequestParam(value = "sa2", required = false) String sa2,
					@RequestParam(value = "usertype", required = false) String usertype,
					@RequestParam(value = "gender", required = false) String gender,
					@RequestParam(value = "zipcode", required = false) String zipcode,
					
					HttpServletRequest request) {
				ModelAndView mv = new ModelAndView("home");
				java.util.Date date= new java.util.Date();
				System.out.println(date);
				User u = new User(name, password,  new Timestamp(date.getTime()), email, phone, address, city, fname, lname, mname,securityQuestion1,sa1,securityQuestion2,sa2,null,usertype,gender,zipcode,"Y");
				System.out.println(u.getUsername());
				userService.updateUser(u);
			    return mv;
			}
			@RequestMapping("/recoverycheck")
			public ModelAndView recoverycheck(HttpServletRequest request) {
				ModelAndView mv = new ModelAndView("redirect:forgotpassword.jsp");
				String recoveryopt = (String)request.getParameter("recoveryopt");
				//userCaseService.removeUserCase(id);
				if(recoveryopt.equals("sendemail")){
					mv = new ModelAndView("redirect:emailpassword.jsp");
				}
			    return mv;
			}
			@RequestMapping("/sendemailpassword")
			public ModelAndView sendemailpassword(HttpServletRequest request) {
				ModelAndView mv = new ModelAndView("home");
				String emailTo = (String)request.getParameter("email");
				String username = (String)request.getParameter("userId");
				//userCaseService.removeUserCase(id);
				String emailFrom = "juryinfo@jurychargeselectionsystem.com";
				String host = "mail.jurychargeselectionsystem.com";
				Properties prop = new Properties();
				prop.put("mail.smtp.host", host);
				prop.put("mail.smtp.auth", "true");
				//prop.put("mail.smtp.ssl.enable", "true");
				Authenticator auth = new Authenticator(){
					protected PasswordAuthentication getPasswordAuthentication(){
						return new PasswordAuthentication("juryinfo@jurychargeselectionsystem.com","Test@123");
					}
				};
				Session session = Session.getDefaultInstance(prop,auth);
				Random rand = new Random();
				String rpasswd = String.format("%04d",rand.nextInt(10000));
				System.out.println(rpasswd);
				try{
					
					MimeMessage message = new MimeMessage(session);
					message.setFrom(new InternetAddress(emailFrom));
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
					message.setSubject("Please update the password jury");
					message.setText("You  password is:"+rpasswd);
					Transport.send(message);
					userService.updateUserPassword(rpasswd, username);
				}catch(Exception e){
					mv.addObject("message", e.getMessage());
					
				}
				
			    return mv;
			}
}