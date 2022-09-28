package com.example.healthwebsite.controller;

import com.example.healthwebsite.entity.BuyerInform;
import com.example.healthwebsite.entity.HealthMember;
import com.example.healthwebsite.jdbc.BuyerInformDTO;
import com.example.healthwebsite.jdbc.HealthMemberDTO;
import com.example.healthwebsite.jdbc.IBuyerInformDAO;
import com.example.healthwebsite.jdbc.IHealthMemberDAO;
import com.example.healthwebsite.repository.BuyerInformRepository;
import com.example.healthwebsite.repository.HealthMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MyController {
    @Autowired
    HealthMemberRepository healthMemberRepository;

    @Autowired
    BuyerInformRepository buyerInformRepository;

    @Autowired
    IHealthMemberDAO dao;

    @Autowired
    IBuyerInformDAO bDao;

    @PostConstruct
    public void memberInsert(){
        HealthMember healthMember = HealthMember.builder().phoneNumber("01029641746").email("ebs1746@naver.com").password("1746").id("ned1746").build();
        healthMemberRepository.save(healthMember);
    }

    //@PostConstruct
    public void buyerInsert(){
        BuyerInform buyerInform = BuyerInform.builder().goodsName("test2").price(10).build();
        buyerInformRepository.save(buyerInform);
    }

    @RequestMapping("/")
    public String initialize(){
        return "home";
    }

    @RequestMapping("/home")
    public String homePage(@RequestParam String checkedLog, HttpSession session, Model model){

        session.setAttribute("checkedLog", checkedLog);
        model.addAttribute("checkedLog", checkedLog);
        return "home";
    }

    @RequestMapping("/event")
    public String eventPage(HttpSession session, Model model){
        String checkedLog = (String)session.getAttribute("checkedLog");
        model.addAttribute("checkedLog", checkedLog);
        return "event";
    }

    @RequestMapping("/join")
    public String joinPage(HttpSession session, Model model){
        String checkedLog = (String)session.getAttribute("checkedLog");
        model.addAttribute("checkedLog", checkedLog);
        return "join";
    }

    @RequestMapping("/joinConfirm")
    public String joinConfirm(HttpSession session, Model model, HttpServletRequest request){
        String checkedLog = (String)session.getAttribute("checkedLog");
        model.addAttribute("checkedLog", checkedLog);

        String id = request.getParameter("id");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phoneNumber = request.getParameter("phoneNumber");

        List<HealthMemberDTO> memberID = new ArrayList<>();
        memberID = dao.selectIDList();

        int idCount = 0;
        for(HealthMemberDTO member : memberID){
            if(member.getId().equals(id)) {
                break;
            }else{
                idCount ++;
            }
        }

        if(idCount != memberID.size()){
            boolean isExistId = true;
            model.addAttribute("isExistId", isExistId);
            return "join";
        }

        dao.insert(id, email, password, phoneNumber);
        return "joinSuccess";
    }

    @RequestMapping("/joinSuccess")
    public String joinSuccessPage(){
        return "joinSuccess";
    }

    @RequestMapping("/logIn")
    public String logInPage(){
        return "logIn";
    }

    @RequestMapping("/logInConfirm")
    public String logInConfirm(HttpServletRequest request, Model model, HttpSession session){
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        List<HealthMemberDTO> memberID = new ArrayList<>();
        memberID = dao.selectIDList();

        int idCount = 0;
        for(HealthMemberDTO member : memberID){
            if(member.getId().equals(id)) {
                break;
            }else{
                idCount ++;
            }
        }

        if(idCount == memberID.size()){
            boolean isNotExistId = true;
            model.addAttribute("isNotExistId", isNotExistId);
            return "logIn";
        }

        String dbPassword = dao.selectPassword(id);

        if(!dbPassword.equals(password)){
            boolean isInCorrectPassword = true;
            model.addAttribute("isInCorrectPassword", isInCorrectPassword);
            return "logIn";
        }

        session.setAttribute("id", id);
        return "redirect:home?checkedLog=true";
    }

    @RequestMapping("/myPage")
    public String myPage(HttpSession session, Model model){
        String checkedLog = (String)session.getAttribute("checkedLog");
        String id = (String)session.getAttribute("id");
        model.addAttribute("checkedLog", checkedLog);
        model.addAttribute("id", id);

        List<BuyerInformDTO> buyerInform = new ArrayList<>();
        buyerInform = bDao.selectBuyerList(id);
        model.addAttribute("buyerInform", buyerInform);
        return "myPage";
    }

    @RequestMapping("/store")
    public String storePage(HttpSession session, Model model){
        String checkedLog = (String)session.getAttribute("checkedLog");
        model.addAttribute("checkedLog", checkedLog);

        System.out.println("상점들렷니?");
        return "store";
    }

    @RequestMapping("/trainer")
    public String trainerPage(HttpSession session, Model model){
        String checkedLog = (String)session.getAttribute("checkedLog");
        model.addAttribute("checkedLog", checkedLog);
        return "trainer";
    }
    @RequestMapping("/useInform")
    public String useInformPage(HttpSession session, Model model){
        String checkedLog = (String)session.getAttribute("checkedLog");
        model.addAttribute("checkedLog", checkedLog);
        return "useInform";
    }
}
