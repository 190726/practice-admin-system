var listIssue = {
		init : function(){
			$('.tag').on('click', function(event){
				let text = $(event.target).text();
				$('#tagSearch').val(text);
			});
		}
}

listIssue.init();