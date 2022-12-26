//window.addEventListener('DOMContentLoaded', event => { //HTML이 모두 로드 외고, 외부 리소스는 아직 로드 안된 상태
                                                       //window.load는 브라우저 내 모든 리소스가 로드 되었을때
//});

$(document).ready(function() {
	
	var date = new Date();
	
	var dtCurDate = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
	$('#startDay').val(dtCurDate);
	$('#endDay').val(dtCurDate);
	
	/*1. datatable 생성 파라미터 객체 정의*/
	var dtObj = {
	        "processing": true,
	        "serverSide": true,
	        "pageLength": 10,
	        "searching": false,
	        "info" : true,

	        "ajax": {
	            "url": "/api/rule/logs",
	            "method":"POST",
	            "data":function(d){
	            	d.startDay = $('#startDay').val();
	            	d.endDay = $('#endDay').val();
	            	d.productCode = $('#productCode').val();
	            },
	            "dataSrc": function (response) {
	                var data = response.data; // your data list
	                var all = [];
	                for (var i = 0; i < data.length; i++) {

	                    var row = {
	                        rows: response.start + i + 1,
	                        id: data[i].id, // name ... ,
	                        productCode: data[i].productCode,
	                        createDate: data[i].createDate
	                    };
	                    all.push(row);
	                }
	                return all;
	            }
	        },
	        "columns": [
	            { "data": "id"},
	            { "data": "productCode"},
	            { "data": "createDate"}
	        ]
	    };
	/*2. 페이지 로딩 후, datatable 생성*/
    $('#mainTable').DataTable(dtObj);
    
    /*3. 검색 버튼 클릭시, datatables 재생성*/
    $('#btnSearch').on('click', function(){
    	$('#mainTable').DataTable().destroy();
    	$('#mainTable').DataTable(dtObj);
	});
});