var systemDetail = {
		init : function(){
			var _this = this;
			$('.btn-user-delete').on('click', function(){
				_this.deleteUser(this);
			});
			
			$('.btn-url-update').on('click', function(){
				$('#urlInfoTextarea').attr('readOnly', false);
			});
			
			$('.btn-server-update').on('click', function(){
				$('#serverInfoTextarea').attr('readOnly', false);
			});
			
			$('.btn-detail-save').on('click', function(){
				_this.detailSave();
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
		},
		
		detailSave : function(){
			
			var data = {
					id : $('#systemId').val(),
					urlInfo : $('#urlInfoTextarea').val(),
					serverInfo : $('#serverInfoTextarea').val()
			};
			
			$.ajax({
				type: 'POST',
				url: '/api/system/detail/save',
				dataType: 'json',
				contentType: 'application/json; charset=utf-8',
				data: JSON.stringify(data)
			}).done(function(){
				alert('저장되었습니다.');
			}).fail(function(error){
				alert(JSON.stringify(error))
			});
		}
		
}

systemDetail.init();