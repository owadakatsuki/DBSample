package com.form.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;

import com.form.model.ChoicesEntity;
import com.form.model.Question;
import com.form.model.ResultEntity;
import com.form.model.UserAnswer;


public class ResultController {

	public String result(Model model,List<Question>  questionList,
									List<ChoicesEntity> choicesList,List<UserAnswer> userAnswerList){

		//小問ごとの結果を入れるListを作成
		List<ResultEntity> resultList = new ArrayList<ResultEntity>();

		//questionListを要素の数ループさせる
		for(int i = 0 ; i < questionList.size() ; i++){
			ResultEntity resultEntity = new ResultEntity();

			//questionEntityの問題文、解説文をresultEntityに入れる
			resultEntity.setQuestion(questionList.get(i).getQuestion());
			resultEntity.setCommentary(questionList.get(i).getCommentary());

			//choicesListの要素を一つずつ検討
			for(ChoicesEntity choices : choicesList){
				//questionEntityとchoicesEntityの小問IDが一致かつis_answerがtrueの選択肢を正解としてresultEntityに入れる
				if (choices.getQuestion_id()==questionList.get(i).getQuestion_id()
						&& choices.getIs_answer()) {
					resultEntity.setAnswer(choices.getAnswer());
				}
			}

			//userAnswerListの要素を一つずつ検討
			for(UserAnswer userAnswer : userAnswerList ){
				//qustionEntityとuserAnswerEntityの小問IDが一致した解答をresultEntityに入れる
				if(userAnswer.getQuestion_id()==questionList.get(i).getQuestion_id()){
					resultEntity.setSelect_answer(userAnswer.getSelect_answer());
				}
			}

			boolean flg = true;

			//正解の数と解答の数が一致していなかったらfalse
			if (resultEntity.getAnswer().size() != resultEntity.getSelect_answer().size()) {
				flg = false;
			} else {
				//解答の中に一つでも正解がなければfalse
				for(String answer : resultEntity.getAnswer()){
					if(!resultEntity.getSelect_answer().contains(answer)){
						flg = false;
					}
				}
			}

			//flgの結果によって値をresultEntityに入れる
			//正解と解答の完全一致のみtrue
			if(flg){
				resultEntity.setMaruBatsu("〇");
			}else{
				resultEntity.setMaruBatsu("×");
			}

			//resultEntityをresultListに入れる
			resultList.add(resultEntity);

		}
		model.addAttribute("resultList",resultList);
		return"Form/userAnswerResult";

	}
}
