$(function() {
	// 問題増加
    $(".add_question_btn").click(function() {
    	var content_id = this.id; 
    	var form_id = this.form.id
        $.ajax({
            type        : "POST",
            url         : "createNewQuestion",
            dataType    : "json",
            data		:  JSON.stringify({
								"content_id": content_id,
								"question_id": null}),
            contentType : "application/json", 
            success     : function(data) {
                            addQuestion(form_id, data);
                        },
            error       : function(XMLHttpRequest, textStatus, errorThrown) {
                            error(XMLHttpRequest, textStatus, errorThrown);
                        } 
        });
    }); 
});


$(function() {
	// 選択肢増加
    $(".add_choice_btn").click(function() {
    	var content_id = this.form.id;
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
    $(".delete_choice_btn").click(function() {
    	var choice_id = this.id;
        $.ajax({
            type        : "POST",
            url         : "deleteChoice",
            dataType    : "json",
            data		: JSON.stringify({
            					"choice_id": choice_id}),
            contentType : "application/json", 
            success     : function(data) {
            				deleteChoice(choice_container_id, data);
                        },
            error       : function(XMLHttpRequest, textStatus, errorThrown) {
                            error(XMLHttpRequest, textStatus, errorThrown);
                        } 
        });
    }); 
});

//Ajax通信成功時処理
function addQuestion(form_id, data) {
	question_num++;
	var str = '<div class="question_container" id="'+ question_num +'">' 
		+ '<span class="question"><input id="questions' + question_num + '.question" name="questions[' + question_num + '].question" value="無題の質問" />'
		+ '<input type="hidden" id="questions' + question_num + '.question_id" name="questions[' + question_num + '].question_id" value="' + data.question_id + '" />'
		+ '<input type="hidden" id="questions' + question_num + '.content_id" name="questions[' + question_num + '].content_id" value="' + data.content_id + '" />'
		+ '<input type="button" class="add_choice_btn" value="質問増えろ" id="' + data.question_id + '" /><br />	</span>';
	
	
	/*
	str += addChoice();*/
	str += "<\div>";


	$("#" + form_id).append(str);

}

//Ajax通信成功時処理
function addChoice(choice_container_id, data) {
	choice_num++;
	var str = '<div><input type="hidden" id="choices' + choice_num + '.question_id" name="choices[' + choice_num + '].question_id" value="' + data.question_id + '" />'
			+ '<input type="hidden" id="choices' + choice_num + '.content_id" name="choices[' + choice_num + '].content_id" value="' + data.content_id + '" />'
			+ '<input type="hidden" id="choices' + choice_num + '.answer_id" name="choices[' + choice_num + '].answer_id" value="' + data.answer_id +'" />'
			+ '<input type="checkbox" id="choices' + choice_num + '.is_answer' +  + '" name="choices[' + choice_num + '].is_answer" value="true" />'
			+ '<input type="hidden" name="_choices[' + choice_num + '].is_answer" value="on" /> <input id="choices' + choice_num + '.answer" name="choices['+ choice_num +'].answer" value="' + data.answer + '" />';
			
	str += '</div>'; 
	$("#cContainer_" + choice_container_id).append(str);

}
 
// Ajax通信失敗時処理
function error(XMLHttpRequest, textStatus, errorThrown) {
    alert("error:" + XMLHttpRequest);
    alert("status:" + textStatus);
    alert("errorThrown:" + errorThrown);
}
