var newSystemUser = {
		init : function(){
			var _this = this;
			$('.btn-user-add').on('click', function(){
				_this.addUser();
			});
		},
		addUser : function(){
			
			var data = {
					
					sno : $("#inputUserGroup option:selected").val(),
					systemId : $("#systemId").val()
			};
			
			$.ajax({
				type: 'POST',
				url: '/api/system/user/add',
				dataType: 'json',
				contentType: 'application/json; charset=utf-8',
				data: JSON.stringify(data)
			}).done(function(){
				alert('추가되었습니다.');
				location.href = "/system/detail/" + $("#systemId").val(); 
			}).fail(function(error){
				alert(JSON.stringify(error))
			});
		}
}

newSystemUser.init();