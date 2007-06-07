function switchDisplay(id){
	var elmtDisplay = document.getElementById(id).style.display;
	if(elmtDisplay=='none'){
		document.getElementById(id).style.display = "block";
	}else{
		document.getElementById(id).style.display = "none";
	}
}
function showMetrics(file){
	document.getElementById("Cache").style.visibility = "visible";
	document.getElementById("MetricsContent").style.visibility = "visible";
	document.getElementById("MetricsFrame").src=file;
}
function hideMetrics(){
	document.getElementById("Cache").style.visibility = "hidden";
	document.getElementById("MetricsContent").style.visibility = "hidden";
}