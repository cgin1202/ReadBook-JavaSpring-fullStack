// 검색창의 검색 키워드 검사

function searchKeywordCheck(){

    //  아이디 필수입력 확인
    if(document.searchword.keyword.value.length==0){
        swal("Notice","검색어는 필수사항입니다.");
        searchword.keyword.focus();
        return;
    }
	
	// 아이디 입력 길이 설정
    if(document.searchword.keyword.value.length<2 || document.searchword.keyword.value.length>20){
        swal("Notice","아이디는 최소 2글자 최대 20글자입니다.");
        searchword.keyword.focus();
        return;
    }
	
	// form action으로 이동
    document.searchword.submit();
}