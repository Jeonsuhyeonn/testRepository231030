<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.outer table{border-color:white;}
</style>
</head>
<body>
	<%@ include file="/views/common/menubar.jsp" %>
	
	<div class="outer">
		<br>
		<h2 align="center">게시글 상세 보기</h2>
		<br>
		<table id="detail-area" align="center" border="1">
			<tr>
				<th width="70">카테고리</th>
				<td width="70">${b.category }</td>
				<th width="70">제목</th>
				<td width="350">${b.boardTitle }</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${b.boardWriter }</td>
				<th>작성일</th>
				<td>${b.createDate }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3">
					<p style="height:200px; white-space:pre;">${b.boardContent }</p>
						
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td colspan="3">
					<c:choose>
						<c:when test="${ empty at }">
							첨부파일이 없습니다.						
						</c:when>
						<c:otherwise>
							<a download="${at.originName }" href="${contextPath}${at.filePath}${at.changeName}">${at.originName }</a>						
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
		<br>
		<div align="center">
			<button onclick="location.href='${header.referer}'">목록가기</button>
			<!--현재 로그인된 유저의 아이디가 글 작성자와 동일하다면  -->
			    <button onclick="location.href='${contextPath}/update.bo?bno=${b.boardNo }'">수정하기</button>
			    <button onclick="location.href='${contextPath}/delete.bo?bno=${b.boardNo }'">삭제하기</button>
		</div>
		<br>
		
		<div id="reply-area">
			<table border="1" align="center">
				<thead>
					<tr>
						<th>댓글작성</th>
						<td>
							<textarea id="replyContent"rows="3" cols="50" style="resize:none"></textarea>
						</td>
						<td><button onclick="insertReply();">댓글 작성</button></td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>작성자</td>
						<td>내용</td>
						<td>작성일</td>
					</tr>
				</tbody>
			</table>
		</div>
		
		<script>
			$(function() {
				selectReplyList();
			});
		
			function selectReplyList() {  //댓글목록 조회 함수
				//목록 조회해와서 tbody에 tr 만들어넣기 mapping 주소 : replyList.bo
				
				$.ajax({ 
					url: "replyList.bo", // 댓글 목록 조회를 처리하는 서버의 엔드포인트 URL
			        data: {bno : ${b.boardNo}},
					success : function(result) {
						//console.log(result);
						var str = "";
						
						for (var i in result) {
							str += "<tr>"
								  +"<td>"+result[i].replyWriter+"</td>"
								  +"<td>"+result[i].replyContent+"</td>"
								  +"<td>"+result[i].createDate+"</td>"
								  +"</tr>";
						}
						
						$("#reply-area tbody").html(str);
					},
			        error: function() {
			            console.log("통신오류");
			        }
				});
			}
		
	
			function insertReply() {
				
				$.ajax({
					url : "insertReply.bo",
					data : {
						content:$("#replyContent").val(),
						bno : ${b.boardNo}
					},
					type : "post",
					success : function(result) {
						
						//console.log(result);
						if(result > 0) {  //성공
							alert("댓글 작성 성공");
							//추가된 댓글 목록 재조회
							selectReplyList();
						
						} else {  //실패
							alert("댓글 작성 실패");
						}
					},
					error : function() {
						console.log("통신오류");
					}
				});
			}
		</script>
		
		
		
		
		<br><br>
	</div>
</body>
</html>