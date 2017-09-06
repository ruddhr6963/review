<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%-- header.jsp를 불러와서 배치하는 코드 --%>
<%@ include file="/WEB-INF/view/template/header.jsp" %>  

<article>
<%-- 컨테이너 영역 --%>
<c:choose>
	<c:when test="${mode eq 'write'}">
		<h3>상세보기</h3>		        
	</c:when>
	<c:when test="${mode eq 'preview'}">
		<h3>미리보기</h3>
	</c:when>
</c:choose>
<div class="container">
	<table class="table table-striped">
	 	<thead></thead>
		<tbody>
			<tr>
				<td>${board.no}</td>
				<td>${board.title }</td> 
				<td>제목</td>
				<td>분류</td>
				<td>장르</td> 
				<td>작성일</td>
			</tr>
			<tr>
				<td>작성자</td>
			</tr>
			<tr>
				<td>
					<ul id="list-ul">
						<li>
							<div class="row form-inline">
	    						<div class="form-group area-20" >
	                      			<img src="http://placehold.it/100x100">
	                     		</div>
	                        	<div id="block">
	                            	<a href="#" id="block">제목</a>
	                          		<a href="#">지은이</a>
	                            	<p>내용</p>
	                      		</div>
	                 		</div>
	               		</li>
	       			</ul>
	    		</td>
	      	</tr>
	    	<tr>
	      		<td>
	     			<p>내용</p>
				</td>
	 		</tr>
	  		<tr>
	   			<td>좋아요</td>
				<td>싫어요</td>
	 		</tr>
	  		<tr>
	     		<td>
	                            댓글 공간
	  			</td>
	     	</tr>  
		</tbody>
	</table>            
	<hr/>

	<div class="align-right">
		<c:choose>
		    <c:when test="${mode eq 'write'}">
		        <button type="button" class="btn ">글쓰기</button>		        
		    </c:when>
		    <c:when test="${mode eq 'preview'}">
		        <button type="button" class="btn ">등록하기</button>
    			<button type="button" class="btn ">수정하기</button>
		    </c:when>
		</c:choose>
		
		<button type="button" class="btn ">목록보기</button>	
	</div>
    <div class="text-center">
		<ul class="pagination  justify-content-center">
			<li class="page-item"><a class="page-link" href="#">1</a></li>
			<li class="page-item"><a class="page-link" href="#">2</a></li>
			<li class="page-item"><a class="page-link" href="#">3</a></li>
        	<li class="page-item"><a class="page-link" href="#">4</a></li>
           	<li class="page-item"><a class="page-link" href="#">5</a></li>
  		</ul>
	</div>
</div>

</article>
      
<%-- footer.jsp를 불러와서 배치하는 코드 --%>
<%@ include file="/WEB-INF/view/template/footer.jsp" %> 
