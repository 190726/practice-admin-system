var systemDetail = {
		init : function(){
			var _this = this;
			$('.btn-user-delete').on('click', function(){
				_this.deleteUser(this);
			});
		},
		deleteUser : function(clickBtn){
			
			var data = {
					systemUserId : $(clickBtn).val()
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

systemDetail.init();