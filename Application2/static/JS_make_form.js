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
                            //addQuestion(form_id, data);
                        },
            error       : function(XMLHttpRequest, textStatus, errorThrown) {
                            error(XMLHttpRequest, textStatus, errorThrown);
                        } 
        });
    }); 
});


$(function() {
	// 解答欄増加
    $(".add_choice_btn").click(function() {
    	var content_id = this.form.id.split("_")[1];
    	var question_id = this.id;
    	//alert(this.parentElement);
    	var form_id = this.form.id;
    	var choice_container_id = "choice_container_" + form_id.split("_")[1]; 
    	//alert(choice_container_id);
    	/*
    	var content_id = document.getElementById(question_id).form.id; */
        $.ajax({
            type        : "POST",
            url         : "createNewChoice",
            dataType    : "json",
            data		: JSON.stringify({
            					"content_id": content_id,
            					"question_id": question_id}),
            contentType : "application/json", 
            success     : function(data) {
            				//addChoice(choice_container_id, data);
                        },
            error       : function(XMLHttpRequest, textStatus, errorThrown) {
                            error(XMLHttpRequest, textStatus, errorThrown);
                        } 
        });
    }); 
});

//Ajax通信成功時処理
function addQuestion(form_id, data) {
	var str = '<div class="question_container">' 
		+ '<span class="question"><input id="' 
		+ data.question_id + '" value="' 
		+ data.question + '" />無題の質問</span></div>'; 


	$("#" + form_id).append(str);

}

//Ajax通信成功時処理
function addChoice(choice_container_id, data) {
	var str = '<div class="question_container">' 
		+ '<span class="question"><input id="' 
		+ data.question_id + '" value="' 
		+ data.question + '" />無題の質問</span></div>'; 


	$("#" + form_id).append(str);

}
 
// Ajax通信失敗時処理
function error(XMLHttpRequest, textStatus, errorThrown) {
    alert("error:" + XMLHttpRequest);
    alert("status:" + textStatus);
    alert("errorThrown:" + errorThrown);
}

function addChoice() {
}

