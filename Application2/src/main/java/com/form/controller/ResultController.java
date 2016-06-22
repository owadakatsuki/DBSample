package com.form.controller;

import java.util.ArrayList;
import java.util.List;

import com.form.model.ChoicesEntity;
import com.form.model.Question;
import com.form.model.ResultEntity;
import com.form.model.UserAnswer;

public class ResultController {

	public String result(List<Question>  questionList,
									List<ChoicesEntity> choicesList,List<UserAnswer> userAnswerList){

		List<ResultEntity> result = new ArrayList<ResultEntity>();

		for(int i = 0 ; i < questionList.size() ; i++){

			ResultEntity resultEntity = new ResultEntity();

			resultEntity.setQuestion_id(questionList.get(i).getQuestion_id());
			resultEntity.setQuestion(questionList.get(i).getQuestion());
			resultEntity.setCommentary(questionList.get(i).getCommentary());

			for(ChoicesEntity choices : choicesList){
				if (choices.getQuestion_id()==questionList.get(i).getQuestion_id()
						&& choices.getIs_answer()) {
					resultEntity.setAnswer(choices.getAnswer());
				}
			}
//			resultEntity.setSelect_answer(select_answer);
			for(UserAnswer userAnswer : userAnswerList ){
				if(userAnswer.getQuestion_id()==questionList.get(i).getQuestion_id()){
					resultEntity.setSelect_answer(userAnswer.getSelect_answer());
				}
			}

			for(String answer : resultEntity.getAnswer()){
				if(resultEntity.getSelect_answer().contains(answer)){
					resultEntity.setMaruBatsu("〇");
				}else{
					resultEntity.setMaruBatsu("×");
				}
				result.add(resultEntity);
			}
		}
		return"Form/userAnswerResult";

	}
}
