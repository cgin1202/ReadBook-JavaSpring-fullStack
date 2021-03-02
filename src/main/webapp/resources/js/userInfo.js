// user create 시에 빈칸없는지 확인

function userCreateConfirm(){

    //  아이디 필수입력 확인
    if(document.createuser.id.value.length==0){
        swal("Notice","아이디는 필수사항입니다.");
        createuser.id.focus();
        return;
    }
	
	// 아이디 입력 길이 설정
    if(document.createuser.id.value.length<4 || document.createuser.id.value.length>20){
        swal("Notice","아이디는 최소 4글자 최대 20글자입니다.");
        createuser.id.focus();
        return;
    }

	// 패스워드 필수입력 확인
    if(document.createuser.pw.value.length==0){
        swal("Notice","패스워드는 필수사항입니다.");
        createuser.pw.focus();
        return;
    }
    
    // 패스워드 입력 길이 설정
    if(document.createuser.pw.value.length<4 || document.createuser.pw.value.length > 20){
        swal("Notice","패스워드는 최소 4글자 최대 20글자입니다.");
        createuser.pw.focus();
        return;
    }
    
	// 패스워드 확인 필수입력 확인
    if(document.createuser.pwcheck.value.length==0){
        swal("Notice","패스워드 확인은 필수사항입니다.");
        createuser.pwcheck.focus();
        return;
    }

    // 비밀번호와 비밀번호 확인 일치 여부 확인
    if(document.createuser.pw.value != document.createuser.pwcheck.value){
        swal("Notice","패스워드가 일치하지 않습니다.");
        createuser.pw.focus();
        return;
    }


    // 이름 필수입력 확인
    if(document.createuser.name.value.length==0){
        swal("Notice","이름은 필수사항입니다.");
        createuser.name.focus();
        return;
    }
    
    // 이름 입력 길이 설정
    if(document.createuser.name.value.length<2 || document.createuser.name.value.length > 10){
        swal("Notice","이름은 최소 2글자 최대 10글자입니다.");
        createuser.name.focus();
        return;
    }

	// 주소 필수입력 확인
    if(document.createuser.addr.value.length==0){
        swal("Notice","주소는 필수사항입니다.");
        createuser.addr.focus();
        return;
    }

	// 번호 필수입력 확인
    if(document.createuser.number.value.length==0){
        swal("Notice","번호는 필수사항입니다.");
        createuser.number.focus();
        return;
    }

	// 번호 규칙 확인
	
	var phoneExp = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;
	if(phoneExp.test(document.createuser.number.value)==false){
		swal("Notice","번호의 규칙은 01X-XXXX-XXXX 입니다.");
        createuser.number.focus();
        return;
	}
	
	// form action으로 이동
    document.createuser.submit();
}







// user login 시에 빈칸없는지 확인

function userLoginConfirm(){
    
    // 아이디 입력 필수 사항
    if(document.loginuser.id.value.length==0){
        swal("Notice","아이디는 필수사항입니다.");
        loginuser.id.focus();
        return;
    }
    
    
    // 아이디 입력 길이 설정
    if(document.loginuser.id.value.length<4 || document.loginuser.id.value.length>20){
        swal("Notice","아이디는 최소 4글자 최대 20글자입니다.");
        loginuser.id.focus();
        return;
    }


	// 패스워드 입력 필수 사항
    if(document.loginuser.pw.value.length==0){
        swal("Notice","패스워드는 필수사항입니다.");
        loginuser.pw.focus();
        return;
    }
    
    
    // 패스워드 입력 길이 설정
    if(document.loginuser.pw.value.length<4 || document.loginuser.pw.value.length > 20){
        swal("Notice","패스워드는 최소 4글자 최대 20글자입니다.");
        loginuser.pw.focus();
        return;
    }
    

    document.loginuser.submit();
}







// user update 시에 빈칸없는지 확인
function userUpdateConfirm(){

    //  아이디 필수입력 확인
    if(document.updateuser.id.value.length==0){
        swal("Notice","아이디는 필수사항입니다.");
        updateuser.id.focus();
        return;
    }
	
	// 아이디 입력 길이 설정
    if(document.updateuser.id.value.length<4 || document.updateuser.id.value.length>20){
        swal("Notice","아이디는 최소 4글자 최대 20글자입니다.");
        updateuser.id.focus();
        return;
    }

	// 패스워드 필수입력 확인
    if(document.updateuser.pw.value.length==0){
        swal("Notice","패스워드는 필수사항입니다.");
        updateuser.pw.focus();
        return;
    }
    
    // 패스워드 입력 길이 설정
    if(document.updateuser.pw.value.length<4 || document.updateuser.pw.value.length > 20){
        swal("Notice","패스워드는 최소 4글자 최대 20글자입니다.");
        updateuser.pw.focus();
        return;
    }
    
	// 패스워드 확인 필수입력 확인
    if(document.updateuser.pwcheck.value.length==0){
        swal("Notice","패스워드 확인은 필수사항입니다.");
        updateuser.pwcheck.focus();
        return;
    }

    // 비밀번호와 비밀번호 확인 일치 여부 확인
    if(document.updateuser.pw.value != document.updateuser.pwcheck.value){
        swal("Notice","패스워드가 일치하지 않습니다.");
        updateuser.pw.focus();
        return;
    }


    // 이름 필수입력 확인
    if(document.updateuser.name.value.length==0){
        swal("Notice","이름은 필수사항입니다.");
        updateuser.name.focus();
        return;
    }
    
    // 이름 입력 길이 설정
    if(document.updateuser.name.value.length<2 || document.updateuser.name.value.length > 10){
        swal("Notice","이름은 최소 2글자 최대 10글자입니다.");
        updateuser.name.focus();
        return;
    }

	// 주소 필수입력 확인
    if(document.updateuser.addr.value.length==0){
        swal("Notice","주소는 필수사항입니다.");
        updateuser.addr.focus();
        return;
    }

	// 번호 필수입력 확인
    if(document.updateuser.number.value.length==0){
        swal("Notice","번호는 필수사항입니다.");
        updateuser.number.focus();
        return;
    }

	// 번호 규칙 확인
	
	var phoneExp = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;
	if(phoneExp.test(document.updateuser.number.value)==false){
		swal("Notice","번호의 규칙은 01X-XXXX-XXXX 입니다.");
        updateuser.number.focus();
        return;
	}
	
	// form action으로 이동
    document.updateuser.submit();
}
