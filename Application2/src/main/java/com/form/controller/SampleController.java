package com.form.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.form.model.MyData;
import com.form.model.MyDataRepository;
import com.form.model.Test;
import com.form.model.TestRepository;

@Controller
public class SampleController {

    @Autowired
    MyDataRepository repository;

    @RequestMapping("/helo")
    public String helo(Model model) {
        Iterable<MyData> list = repository.findAll();
        model.addAttribute("datas",list);
    return "login";
    }



    @Autowired
    TestRepository testRepository;
    @RequestMapping("/dbtest2")
    public String test() {
        System.out.println("[START] データベースに接続してデータを取得します。");
        Page<Test> configs = testRepository.findAll(new PageRequest(0, 10));

        for (Test config : configs) {
            System.out.println(config.getCol1() + " = " + config.getCol2());
        }
        System.out.println("[END  ] データベースに接続してデータを取得します。");
        return "index";
    }
}