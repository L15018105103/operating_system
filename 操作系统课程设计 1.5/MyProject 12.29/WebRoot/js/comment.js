// 绑定表情
$('.face-icon').SinaEmotion($('.text'));
// 测试本地解析
function out() {
	var inputText = $('.text').val();
	$('#info-show ul').append(reply(AnalyticEmotion(inputText)));
}

var html;
function reply(content) {
	html = '<li>';
	html += '<div class="head-face">';
	html += '<img src="images/portrait3.jpg" / >';
	html += '</div>';
	html += '<div class="reply-cont">';
	html += '<p class="username">年少的轻狂</p><br>';
	html += '<p class="comment-body">' + content + '</p>';
	html += '<p class="comment-footer">2017年4月7日　回复　点赞54　转发12</p>';
	html += '<p class="comment-blank">     </p>';
	html += '</div>';
	html += '</li>';
	return html;
}