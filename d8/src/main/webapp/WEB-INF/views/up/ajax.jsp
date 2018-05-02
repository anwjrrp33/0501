<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<style>

.uploadUL li {
	
	list-style: none;
	
}


</style>
	<div>
		
		<img alt="" src="/temp1">
		<form id="uploadForm">
			<input type='file' id='upload' multiple>
		</form>
		<button id='btn'>click</button>
	</div>

	<div>
		<ul class="uploadUL">
		</ul>
	</div>
	
	<style>
	#wall {
	width: 100%;
	height:500px;
	border: 1px soild red;
	background-color: gray;
	position: absolute;
	display: none;
	z-index: 100;
	justify-content: center;
	align-items: center;
	}
	</style>
	
	<div id="wall" >
	
	</div>
	
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"
		integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
		crossorigin="anonymous"></script>

	<script>
		var uploadUL = $(".uploadUL");

		var uploadInput = $("#upload");
		
		var wall = $("#wall");

		$("#btn").on("click", function(e) {

					console.log("click...")
					var formData = new FormData();

					console.log(uploadInput[0].files);

					var files = uploadInput[0].files;

					for (var i = 0; i < files.length; i++) {

						formData.append("file", files[i]);
					}

					$.ajax({
						url : 'ajax',
						data : formData,
						processData : false,
						contentType : false,
						type : 'POST',
						success : function(data) {
							console.log(data);

							var str = ""

							for (var i = 0; i < data.length; i++) {
								str += "<li data-file='"+ data[i] + "'><img src='/display?file=s_" + data[i]
										+ "'></li>";
							}

							uploadUL.append(str);

							$("#uploadForm")[0].reset();
						}
					});
					
					$(".uploadUL").on("click", "li", function(e) {
						
						console.log("li clicked");
						//$(this).attr("data-file")
						var fileName = $(this).data("file");
						
						var str = "<img src='/display?file=" + fileName + "'>";
						
						wall.html(str);
						wall.show("slow");
					});
					
					wall.on("click", function(e) {
						
						wall.hide("slow");
					});
				});
	</script>
</body>
</html>