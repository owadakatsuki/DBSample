$(function() {
	var content_id = 4;
    // Ajax通信テスト ボタンクリック
    $("#add_question_btn").click(function() {
        $.ajax({
            type        : "POST",
            url         : "createNewQuestion",
            dataType    : "json",
            data		:  JSON.stringify({"id": content_id }),
            contentType : "application/json", 
            success     : function(data) {
                            addQuestion(data);
                        },
            error       : function(XMLHttpRequest, textStatus, errorThrown) {
                            error(XMLHttpRequest, textStatus, errorThrown);
                        } 
        });
    }); 
});


$(function() {
    // Ajax通信テスト ボタンクリック
	var content_id = 4;
    $("#add_choice_btn").click(function() {
        $.ajax({
            type        : "POST",
            url         : "createNewChoice",
            dataType    : "json",
            data		: JSON.stringify({"id": content_id}),
            contentType : "application/json", 
            success     : function(data) {alert("OK");
                        },
            error       : function(XMLHttpRequest, textStatus, errorThrown) {
                            error(XMLHttpRequest, textStatus, errorThrown);
                        } 
        });
    }); 
});
 
// Ajax通信成功時処理
function addQuestion(data) {
		var q_id = data.question_id;
		
 /*
    for (var cnt = 0; cnt < data.length; cnt++) {*/
    	 var str = '<div class="question_container">'
    		 	+ '<span class="question"><input id="questions'+ q_id +'.question" name="questions[' + q_id + '].question" value="" />無題の質問'
	+'<input type="hidden" id="questions'+ q_id+'.question_id" name="questions['+ q_id + '].question_id" value="'+ q_id +'" /></span></div>';
/*
    	    element.appendChild(addChoice("question"));
    		*/

        $("#form").append(str);/*
    }*/
}
 
// Ajax通信失敗時処理
function error(XMLHttpRequest, textStatus, errorThrown) {
    alert("error:" + XMLHttpRequest);
    alert("status:" + textStatus);
    alert("errorThrown:" + errorThrown);
}

function addChoice() {
	var str = '<div class="choice_container">' 
			+ '<input type="hidden" id="choices'+ c_id +'.question_id" name="choices['+c_id+'].question_id" value="1" />  '
			+'<input type="hidden" id="choices'+ c_id +'.answer_id" name="choices['+ c_id+'].answer_id" value="'+c_id+'" /> '
			+'<input type="checkbox" id="choices' + c_id +'.is_answer" name="choices[' + c_id +'].is_answer" value="true" checked="checked" />'
			+'<input type="hidden" name="_choices[' + c_id +'].is_answer" value="off" /> '
			+'<input id="choices'+ c_id + '.answer" name="choices[' + c_id + '].answer" value="" />'
			+'</div>';

    $("#question_").append(str);
}

