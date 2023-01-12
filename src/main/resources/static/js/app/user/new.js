var newUser = {
		init : function(){
			var _this = this;
			$('.btn-user-add').on('click', function(){
				removeBorderDanger('snoInput','nameInput','dateInput');
				$('.alert').remove();
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
				
				console.log(error);
				let errInfo = error.responseJSON.errors[0];
				
				const alertPlaceholder = document.getElementById('errPlaceHolder');
				alertDiv(errInfo.defaultMessage, 'success', alertPlaceholder);
				  
				let errField = errInfo.field;
				
				if(errField=="sno"){
					borderDanger('snoInput');
				}else if(errField=="name"){
					borderDanger('nameInput');
				}
			});
		}
}

newUser.init();