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

        model.addAttribute("contentList", contentList);

        return "menu";
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

        return "menu";
    }
}
