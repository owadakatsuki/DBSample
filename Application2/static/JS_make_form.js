jQuery(window).load(function() {
    // Ajax通信テスト ボタンクリック
    $("#ajax_btn").click(function() {
    	alert("来たよ");
        // outputDataを空に初期化
        $("#output_data").text("");
        $.ajax({
            type        : "GET",
            url         : "getTestData",
            dataType    : "json"
/*             success     : function(data) {
                            success(data);
                        },
            error       : function(XMLHttpRequest, textStatus, errorThrown) {
                            error(XMLHttpRequest, textStatus, errorThrown);
                        } */
        }).done(function(data) {        // Ajax通信が成功した時の処理
            alert("アップロードが完了しました。");
        }).fail(function(XMLHttpRequest, textStatus, errorThrown) { // Ajax通信が失敗した時の処理
            alert("アップロードが失敗しました。");
        });
    }); 
});
 
// Ajax通信成功時処理
function success(data) {
    alert("success:" + data);
 
    $("#output_data").text("");
    for (var cnt = 0; cnt < data.length; cnt++) {
        $("#output_data").append("data[" + cnt + "]：" + data[cnt] + "；");
    }
}
 
// Ajax通信失敗時処理
function error(XMLHttpRequest, textStatus, errorThrown) {
    alert("error:" + XMLHttpRequest);
    alert("status:" + textStatus);
    alert("errorThrown:" + errorThrown);
}






//----------------------------------------------------------------
q_id = 1;
c_id = 1;
function addChoice(str) {
    var element = document.createElement('div');
    element.className = "choice_container";
    element.innerHTML = '<input type="hidden" id="choices'+ c_id +'.question_id" name="choices['+c_id+'].question_id" value="1" />  '
    						+'<input type="hidden" id="choices'+ c_id +'.answer_id" name="choices['+ c_id+'].answer_id" value="'+c_id+'" /> '
    						+'<input type="checkbox" id="choices' + c_id +'.is_answer" name="choices[' + c_id +'].is_answer" value="true" checked="checked" />'
    						+'<input type="hidden" name="_choices[' + c_id +'].is_answer" value="off" /> '
    						+'<input id="choices'+ c_id + '.answer" name="choices[' + c_id + '].answer" value="" />';
  	c_id++;
  	if(str == "question"){
  	    return element;
  	} else {
  		var objQuestion = document.getElementById(str);
  		objQuestion.appendChild(element);
  	}
}

function addQuestion() {
    var element = document.createElement('div');
    element.className = "question_container";
    element.innerHTML = '<span class="question"><input id="questions'+ q_id +'.question" name="questions[' + q_id + '].question" value="" />無題の質問'
	+'<input type="hidden" id="questions'+ q_id+'.question_id" name="questions['+ q_id + '].question_id" value="'+ q_id +'" /></span>';


    element.appendChild(addChoice("question"));
	
	
	q_id++;
    
    var objForm = document.getElementById("form");
    objForm.appendChild(element);
}