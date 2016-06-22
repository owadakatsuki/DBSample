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

    // contentメソッドの処理は、LoginControllerに移します。
    @RequestMapping("/menu")
    public String content(Locale locale, Model model) {
        System.out.println("[START] データベースに接続してデータを取得します。");

        // 大問一覧取得
        List<Content> contentList = contentRepository.findAll();

//        for (Content content : contentList) {
//            System.out.println(content.getContent_id() + content.getContent_title());
//        }

/** 管理者権限でログインしたときに、ボタンが非表示になるか確認するための処理
 *
        User user_info = new User();
        user_info.setRole("role");
        model.addAttribute("user_info", user_info);

*/
        // 大問一覧をセット
        model.addAttribute("contentList", contentList);

        return "menu";
    }

    @RequestMapping(value="/remove")
    public String remove(@RequestParam("contentId") int contentID, Locale locale, Model model) {
        System.out.println("[START] 削除を行います。" + contentID);

        // 問題の削除
        contentRepository.delete(contentID);

        System.out.println("[END] 削除しました。");

        List<Content> contentList = contentRepository.findAll();

        /** 管理者権限でログインしたときに、ボタンが非表示になるか確認するための処理
         *
                User user_info = new User();
                user_info.setRole("role");
                model.addAttribute("user_info", user_info);

<<<<<<< HEAD
        */
=======
        model.addAttribute("contentList", contentList);

        return "menu";
    }
/*
    @RequestMapping("/make_form")
    public String makeForm(Locale locale, Model model) {
        System.out.println("[START] フォーム編集画面に遷移します。");

        List<Content> contentList = contentRepository.findAll();

        for (Content content : contentList) {
            System.out.println(content.getContent_id() + content.getContent_title());
        }
>>>>>>> 22_makeform

        // 削除後の大問一覧をセット
        model.addAttribute("contentList", contentList);

        return "menu";
    }*/

//    @RequestMapping("/qustion")
//    public String question(Locale locale, Model model) {
//        System.out.println("[START] 解答画面に遷移します。");
//
//        List<Content> contentList = contentRepository.findAll();
//
//        for (Content content : contentList) {
//            System.out.println(content.getContent_id() + content.getContent_title());
//        }
//
//        model.addAttribute("contentList", contentList);
//
//        return "menu";
//    }
//
//    @RequestMapping("/make_form")
//    public String makeForm(Locale locale, Model model) {
//        System.out.println("[START] フォーム編集画面に遷移します。");
//
//        List<Content> contentList = contentRepository.findAll();
//
//        for (Content content : contentList) {
//            System.out.println(content.getContent_id() + content.getContent_title());
//        }
//
//        model.addAttribute("contentList", contentList);
//
//        return "menu";
//    }

}
