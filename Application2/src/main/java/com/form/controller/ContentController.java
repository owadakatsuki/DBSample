package com.form.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.form.model.Content;
import com.form.model.ContentRepository;
import com.form.model.User;

@Controller
public class ContentController {

    @Autowired
    ContentRepository contentRepository;
    @RequestMapping("/menu")
    public String content(Locale locale, Model model) {
        System.out.println("[START] データベースに接続してデータを取得します。");

        List<Content> contentList = contentRepository.findAll();

        for (Content content : contentList) {
            System.out.println(content.getContent_id() + content.getContent_title());
        }

        User user_info = new User();
        user_info.setRole("role");
        model.addAttribute("user_info", user_info);
        model.addAttribute("contentList", contentList);

        return "menu"; // メニュー画面に遷移
    }

    @RequestMapping(value="/remove")
    public String remove(@RequestParam("contentId") int contentID, Locale locale, Model model) {
        System.out.println("[START] 削除を行います。" + contentID);

        contentRepository.delete(contentID);
        List<Content> contentList = contentRepository.findAll();

        for (Content content : contentList) {
            System.out.println(content.getContent_id() + content.getContent_title());
        }

        System.out.println("[END] 削除しました。");

        model.addAttribute("contentList", contentList);

        return "menu"; // 削除後、メニュー画面のまま
    }

    @RequestMapping("/qustion")
    public String question(Locale locale, Model model) {
        System.out.println("[START] 解答画面に遷移します。");

        List<Content> contentList = contentRepository.findAll();

        for (Content content : contentList) {
            System.out.println(content.getContent_id() + content.getContent_title());
        }

        model.addAttribute("contentList", contentList);

        return "menu";
    }

    @RequestMapping("/make_form")
    public String makeForm(Locale locale, Model model) {
        System.out.println("[START] フォーム編集画面に遷移します。");

        List<Content> contentList = contentRepository.findAll();

        for (Content content : contentList) {
            System.out.println(content.getContent_id() + content.getContent_title());
        }

        model.addAttribute("contentList", contentList);

        return "menu";
    }

}
