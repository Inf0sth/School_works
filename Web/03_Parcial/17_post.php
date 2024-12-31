<?php
	echo "<h1>POST test</h1>";
	
	if(isset( $_GET["color"]))
	{
		print ( "<p><b>By GET - Color:</b>".$_GET["color"]."</p>" );
	}

	if(isset( $_POST["color"]))
	{
		print ( "<p><b>By POST - Color:</b>".$_POST["color"]."</p>" );
	}
?>

<h2>GET</h2>
<form action="17_post.php" method="get">
	<label for="color">Ingresa color:</label>
	<input type="text" name="color" placeholder="color" />

	<input type="submit" value="Procesar" />
</form>

<h2>POST</h2>
<form action="17_post.php" method="post">
	<label for="color">Ingresa color:</label>
	<input type="text" name="color" placeholder="color" />

	<input type="submit" value="Procesar" />
</form>