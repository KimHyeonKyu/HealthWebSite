package com.example.healthwebsite.controller;

import com.example.healthwebsite.jdbc.IBuyerInformDAO;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class HeloController {

    @Autowired
    IBuyerInformDAO bDao;

    @PostMapping ("/test")
    public String test(@RequestBody Map<String, String> param, HttpSession session){
        System.out.println("testest");
        JsonObject jsonObject = new JsonObject();
        String test = "test";

        bDao.insertBuyerInform(param.get("goodsName"), Integer.parseInt(param.get("price"))
                ,(String)session.getAttribute("id"));
        return jsonObject.toString();
    }
}
