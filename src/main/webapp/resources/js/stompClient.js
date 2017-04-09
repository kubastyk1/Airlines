function sendValues(){
	var fromAirport = document.getElementById("fromButton").innerText;
	var toAirport = document.getElementById("toButton").innerText;
	var fromDate = document.getElementById("fromDate").value;
	var toDate = document.getElementById("toDate").value;

	changeFromDescription('sendValues');

	stompClient.send("/app/sendValues", {}, JSON.stringify(
    		{'fromAirport': fromAirport, 'toAirport': toAirport, 'fromDate': fromDate, 'toDate': toDate}));
}

function deleteUser(id) {

    stompClient.send("/app/deleteUser", {}, JSON.stringify(
    		{'id': id}));
}

function showValues(message) {

}

function showUsersAfterDelete(message) {

}

function changeFromDescription(change) {
	var x = document.getElementById("fromButton");
	x.innerHTML = change;
}
function changeToDescription(change) {
	var x = document.getElementById("toButton");
	x.innerHTML = change;
}
