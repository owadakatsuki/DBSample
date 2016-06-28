package com.form.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.form.dao.ChoicesRepository;
import com.form.dao.ContentRepository;
import com.form.dao.QuestionRepository;
import com.form.dao.UserAnswerRepository;
import com.form.dao.UserAnswerService;
import com.form.model.Content;
import com.form.model.ContentAnswer;
import com.form.model.UserInfo;

@Controller
@Transactional
@SessionAttributes("user_info")
public class ContentController {

    @Autowired
    ContentRepository contentRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    ChoicesRepository choicesRepository;

	@Autowired
	UserAnswerRepository user_answer_repository;

	@Autowired
	UserAnswerService user_answer_service;

	@Autowired
	private UserInfo user_info;

    // 解答結果画面から遷移
    @RequestMapping(value ="/menu")
    public String content(Model model) {
        System.out.println("[START] メニュー画面");

        // 直接アクセス拒否
        if (user_info.getUser_id() == null) {
			model.addAttribute("error", "ログイン処理をしてください");
        	return "error";
        }

        // 大問一覧取得
        List<Content> contentList = contentRepository.findAll();
		// ユーザ毎の解答結果取得
        List<Integer> contentIdList = user_answer_service.findByContentId(user_info.getUser_id());
        // 解答済問題判定用リスト
    	List<ContentAnswer> conAnswerList = new ArrayList<ContentAnswer>();

        // 一覧表示する問題をセットしていく
        for (Content c : contentList) {

        	ContentAnswer contentAnswer = new ContentAnswer();
        	contentAnswer.setContent_id(c.getContent_id());
        	contentAnswer.setContent_title(c.getContent_title());
        	contentAnswer.setAnswer_flg(contentIdList.contains(c.getContent_id()));
        	conAnswerList.add(contentAnswer);
        }

        // ユーザ情報、大問一覧をセット
	    model.addAttribute("user_info", user_info);
        model.addAttribute("contentList", conAnswerList);

        System.out.println("[END] セットしました");

        return "menu";
    }

    @RequestMapping(value="/remove")
    public String remove(@RequestParam("contentId") int contentID, Locale locale, Model model) {
        System.out.println("[START] 削除を行います。" + contentID);

        // 問題の削除
        user_answer_repository.deleteConUserAnswer(contentID);
        choicesRepository.deleteConChoice(contentID);
        questionRepository.deleteConQuestion(contentID);
        contentRepository.deleteContent(contentID);

        System.out.println("[END] 削除しました。");

        return "redirect:/menu";
    }

}
