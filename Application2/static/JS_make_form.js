$(function() {
	// 問題増加
	$(document).on('click', '.add_question_btn', function() {
    	var content_id = this.id; 
        $.ajax({
            type        : "POST",
            url         : "createNewQuestion",
            dataType    : "json",
            data		:  JSON.stringify({
								"content_id": content_id,
								"question_id": null}),
            contentType : "application/json", 
            success     : function(data) {
                            addQuestion(content_id, data);
                        },
            error       : function(XMLHttpRequest, textStatus, errorThrown) {
                            error(XMLHttpRequest, textStatus, errorThrown);
                        } 
        });
    }); 
});


$(function() {
	// 選択肢増加
    $(document).on('click', '.add_choice_btn', function() {
    	var content_id = this.form.id.split("_")[1];
    	var id = this.id.split("_");
    	var question_id = id[0];
    	var choice_container_id = id[1];
        $.ajax({
            type        : "POST",
            url         : "createNewChoice",
            dataType    : "json",
            data		: JSON.stringify({
            					"content_id": content_id,
            					"question_id": question_id}),
            contentType : "application/json", 
            success     : function(data) {
            				addChoice(choice_container_id, data);
                        },
            error       : function(XMLHttpRequest, textStatus, errorThrown) {
                            error(XMLHttpRequest, textStatus, errorThrown);
                        } 
        });
    }); 
});


$(function() {
	// 選択肢削除
   	$(document).on('click', '.delete_choice_btn', function() {
    	var choice_id = this.id;
        $.ajax({
            type        : "POST",
            url         : "deleteChoice",
            dataType    : "json",
            data		: JSON.stringify({
            					"choice_id": choice_id}),
            contentType : "application/json", 
            success     : function() {            	
            				deleteChoice(choice_id);
                        },
            error       : function(XMLHttpRequest, textStatus, errorThrown) {
                            error(XMLHttpRequest, textStatus, errorThrown);
                        } 
        });
    }); 
});

$(function() {
	// 問題削除
   	$(document).on('click', '.delete_question_btn', function() {
    	var id = this.id.split("_");
    	var question_id = id[1];
        $.ajax({
            type        : "POST",
            url         : "deleteQuestion",
            dataType    : "json",
            data		: JSON.stringify({
            					"question_id": question_id}),
            contentType : "application/json", 
            success     : function() {            	
            				deleteQuestion(id[0]);
                        },
            error       : function(XMLHttpRequest, textStatus, errorThrown) {
                            error(XMLHttpRequest, textStatus, errorThrown);
                        } 
        });
    }); 
});


function deleteQuestion(question_id) {
	$(document.getElementById("qContainer_" + question_id)).remove();
}

function deleteChoice(choice_id) {
	$(document.getElementById("choice_" + choice_id)).remove();
}

//Ajax通信成功時処理
function addQuestion(form_id, data) {
	question_num++;
	var str = '<div class="question_container" id="qContainer_'+ question_num +'">' 
		+ '<span class="question">Q : <input id="questions' + question_num + '.question" name="questions[' + question_num + '].question" value="無題の質問" />'
		+ '<input type="hidden" id="questions' + question_num + '.question_id" name="questions[' + question_num + '].question_id" value="' + data.question_id + '" />'
		+ '<input type="hidden" id="questions' + question_num + '.content_id" name="questions[' + question_num + '].content_id" value="' + data.content_id + '" /></span>';
	
	str += '<input type="checkbox" id="questions'+ question_num+ '.required_flag1" name="questions['+question_num+'].required_flag" value="true"  /> '
		+ '<input type="hidden" name="_questions[' + question_num +'].required_flag" value="on">解答必須';
	
	str += '<input type="button" class="add_choice_btn" id="' + data.question_id + '_' + question_num + '" value="選択肢追加" />';
	
	str += '<input type="button" class="delete_question_btn" value="問題削除" id="' + question_num + '_' + data.question_id + '">';
	
	str += '<br /><span class="alert">※正解とする選択肢にチェックを入れてください。</span>';
	
	str += '<div class="choice_container" id="cContainer_' + question_num + '"></div>';

	str += "<\div>";


	$("#form_" + form_id).append(str);

}

//Ajax通信成功時処理
function addChoice(choice_container_id, data) {
	choice_num++;
	var str = '<div id="choice_'+ data.answer_id + '"><input type="hidden" id="choices' + choice_num + '.question_id" name="choices[' + choice_num + '].question_id" value="' + data.question_id + '" />'
			+ '<input type="hidden" id="choices' + choice_num + '.content_id" name="choices[' + choice_num + '].content_id" value="' + data.content_id + '" />'
			+ '<input type="hidden" id="choices' + choice_num + '.answer_id" name="choices[' + choice_num + '].answer_id" value="' + data.answer_id +'" />'
			+ '<input type="checkbox" id="choices' + choice_num + '.is_answer1" name="choices[' + choice_num + '].is_answer" value="true" />'
			+ '<input type="hidden" name="_choices[' + choice_num + '].is_answer" value="on" />'
			+ '<input id="choices' + choice_num + '.answer" name="choices['+ choice_num +'].answer" value="' + data.answer + '" />'
			+ '<input type="button" class="delete_choice_btn" value="削除" id="' + data.answer_id +'" />';
	str += '</div>'; 
	$("#cContainer_" + choice_container_id).append(str);

}
 
// Ajax通信失敗時処理
function error(XMLHttpRequest, textStatus, errorThrown) {
    alert("error:" + XMLHttpRequest);
    alert("status:" + textStatus);
    alert("errorThrown:" + errorThrown);
}
