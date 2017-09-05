<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%-- header.jsp를 불러와서 배치하는 코드 --%>
<%@ include file="/WEB-INF/view/template/header.jsp" %>  

<article>
<%-- 컨테이너 영역 --%>
<h3>공연 게시판</h3>
<form action="#">
	<div class="row form-inline">
		<div class="form-group area-20">
			<label>카테고리</label>
		</div>
	<div class="form-group mx-sm-3">
		<select class="user-input" id="margin">  
            <option>선택</option>
        	<option value = "201">뮤지컬</option> 
            <option value = "202">음악회</option> 
            <option value = "203">축제</option> 
            <option value = "299">기타</option> 
       	</select>
	   </div>
	</div>
	<div class="row form-inline">
		<div class="form-group area-20">
			<label>제목</label>
		</div>
		<div class="form-group mx-sm-3">
			<input type="text" name="title" class="user-input area-90">
		</div>
	</div>
	<div class="row form-inline">
		<div class="form-group area-20" >
			<label>공연 검색</label>
		</div>
		<div class="form-group mx-sm-3">
			<input  id="margin" class="user-input" type="text" placeholder="검색할 공연 이름을 작성하세요">
		</div>
		<input type="button" value="검색">
	</div>
	<div class="row form-inline">
		<div class="form-group area-20" >
			<img src="http://placehold.it/120x120">
		</div>
		<div id="block">
			<label >공연 이름</label>
			<label>감독</label>
			<label>평점주기</label>
		</div>   
	</div>
   
	<textarea name="ir1" id="ir1" class="nse_content" style="width:100%; height:412px; min-width:610px; display:none;"></textarea>
	<script type="text/javascript">
   		var oEditors = [];
    	nhn.husky.EZCreator.createInIFrame({
	       	oAppRef: oEditors,
	       	elPlaceHolder: "ir1",
	       	sSkinURI: "/review/smarteditor/SmartEditor2Skin.html",
	       	fCreator: "createSEditor2"
       	});

    	function submitContents(elClickedObj) {
   			// 에디터의 내용이 textarea에 적용됩니다.
			oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
        	// 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("ir1").value를 이용해서 처리하면 됩니다. 
  			try {
   				elClickedObj.form.submit();
       		} catch(e) {}
    		}
	</script>
	<input type="submit" value="전송" onclick="submitContents(this)" />
</form>
</article>
      
<%-- footer.jsp를 불러와서 배치하는 코드 --%>
<%@ include file="/WEB-INF/view/template/footer.jsp" %> 
