<html>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js" type="text/javascript"></script>
</head>

<body>
<h2>Hello World!</h2>
<div class="result"></div>
</body>
</html>

<!-- jquery get -->
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$.get("http://localhost:8080/home", function(data) {
			$(".result").html(data);
			//alert("Load was performed.");
		});
	});
</script>
