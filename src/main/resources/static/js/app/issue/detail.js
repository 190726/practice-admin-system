var issueDetail = {
		init : function(){
			var _this = this;
			$('.btn-issue-update').on('click', function(){
				_this.updateIssue(this);
			});
			
		},
		updateIssue : function(clickBtn){
			
			var data = {
					issueId : $(clickBtn).val()
			};
			
			$.ajax({
				type: 'POST',
				url: '/api/system/user/delete',
				dataType: 'json',
				contentType: 'application/json; charset=utf-8',
				data: JSON.stringify(data)
			}).done(function(){
				alert('삭제되었습니다.');
				$(clickBtn).parent().parent().remove();
			}).fail(function(error){
				alert(JSON.stringify(error))
			});
		}
}

issueDetail.init();