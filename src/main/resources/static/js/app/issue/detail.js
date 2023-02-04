var issueDetail = {
		init : function(){
			var _this = this;
			$('.btn-issue-update').on('click', function(){
				_this.updateIssue(this);
			});
			
			$('#contentEdit').hide();
			
			var contentBox = $('#contentBox');
			$(contentBox).on('dblclick', function(){
				$('#contentEdit').show();
				$('#contentBox').hide();
			});
		},
		updateIssue : function(clickBtn){
			
			var data = {
					issueId : $('#issueId').val(),
					content : $('#exampleFormControlTextarea1').val()
			};
			
			$.ajax({
				type: 'POST',
				url: '/api/issue/update',
				dataType: 'json',
				contentType: 'application/json; charset=utf-8',
				data: JSON.stringify(data)
			}).done(function(){
				alert('수정되었습니다.');
				location.reload();
			}).fail(function(error){
				alert(JSON.stringify(error))
			});
		}
}

issueDetail.init();