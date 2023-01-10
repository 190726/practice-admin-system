var newUser = {
		init : function(){
			var _this = this;
			$('.btn-user-add').on('click', function(){
				_this.newUser();
			});
		},
		newUser : function(){
			
			var data = {
					sno : $("#snoInput").val(),
					name : $("#nameInput").val(),
					dutyStep : $("#dutyStepSelect option:selected").val(),
					enterDate : $("#dateInput").val()
			};
			
			$.ajax({
				type: 'POST',
				url: '/api/user/add',
				dataType: 'json',
				contentType: 'application/json; charset=utf-8',
				data: JSON.stringify(data)
			}).done(function(){
				alert('등록되었습니다.');
				location.href = "/web/user/list";
			}).fail(function(error){
				alert(JSON.stringify(error))
			});
		}
}

newUser.init();