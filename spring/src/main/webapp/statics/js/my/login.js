$(function() {
	$('#loginOutButton').click(function(){
		var userName = $('#userName').val();
		window.location.href="loginOut.htm?userName="+userName;
	});
});