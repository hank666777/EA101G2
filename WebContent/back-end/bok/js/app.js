$( function() {
	$("#datepicker").datepicker({ 
		minDate: 0, 
		maxDate: "+1M +10D",
		beforeShowDay: noMondays,
		dateFormat : "yy-mm-dd" 
	});	
});

function noMondays(a) {
	  a=a.getDay();
	  return[(a>1&&a<7)||a==0,""];
}

function checkdate(){
	if($("#datepicker").val()!=""){
		step1.submit();
	}else{
		alert("請選擇日期");
		$("#datepicker").focus();
	}
}
