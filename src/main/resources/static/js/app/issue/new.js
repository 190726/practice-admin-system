var newIssue = {
		init : function(){
			var _this = this;
			$('.btn-issue-add').on('click', function(){
				_this.registerIssue();
			});
		},
		registerIssue : function(){
			
			var data = {
					tagName : $("#tagInput").val(),
					title : $("#titleInput").val(),
					content : $("#contentInput").val()
			};
			
			$.ajax({
				type: 'POST',
				url: '/api/issue/save',
				dataType: 'json',
				contentType: 'application/json; charset=utf-8',
				data: JSON.stringify(data)
			}).done(function(){
				alert('등록되었습니다.');
				location.href = "/web/issue/list";
			}).fail(function(error){
				
				console.log(error);
				
				if(error.responseJSON.code){
					alert(error.responseJSON.message);
				}else if(error.responseJSON.errors){
					let errInfo = error.responseJSON.errors[0];
					
					const alertPlaceholder = document.getElementById('errPlaceHolder');
					alertDiv(errInfo.defaultMessage, 'success', alertPlaceholder);
					  
				}else{
					alert(error.responseJSON.message);
				}
			});
		}
}

newIssue.init();