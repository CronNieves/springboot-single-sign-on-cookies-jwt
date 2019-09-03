<!DOCTYPE html>
<html lang="en">
<head>
    <title>Authentication Service</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>
<body>

<div class="card" style="width: 500px; margin: auto;  margin-top: 10%;">
  <div class="card-header">
    Login
  </div>
  <div class="card-body">
		<form method="POST" action="/login?redirect=${RequestParameters.redirect!}">
		    <input class="form-control" name="username" type="text" placeholder="Username"
		           autofocus="true"/>
		    <br>
		    <input class="form-control" name="password" type="password" placeholder="Password"/>
		    <div>(try username=admin and password=admin)</div>
		    <div style="color: red">${error!}</div>
		    <br>
		    <button type="submit" class="btn btn-outline-primary">Log In</button>
		</form>
  </div>
</div>


</body>
</html>