<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GEVS - Voter Dashboard</title>
    <link rel="stylesheet" href="dashboard.css">
</head>
<script>
    var hasVoted = false;
    var candidates = [];
    var constituency = "";

    function loadCandidates() {
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "/getCandidates?constituency=" + constituency, true);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                candidates = JSON.parse(xhr.responseText);
                displayCandidates();
            }
        };
        xhr.send();
    }

    function displayCandidates() {
        var container = document.getElementById('elections-container');
        container.innerHTML = '';
        for (var i = 0; i < candidates.length; i++) {
            var candidate = candidates[i];
            var candidateElement = document.createElement('div');
            candidateElement.innerHTML = '<h4>' + candidate.name + '</h4><button onclick="castVote(' + candidate.id + ')">Vote</button>';
            container.appendChild(candidateElement);
        }
    }

    window.onload = function() {
        constituency = document.getElementById('constituency').innerText;
        loadCandidates();
        checkIfVoted();
    }

    function checkIfVoted() {
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "/checkIfVoted", true);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                hasVoted = JSON.parse(xhr.responseText);
                if (hasVoted) {
                    alert("You have already cast your vote and cannot alter it.");
                }
            }
        };
        xhr.send();
    }


    function castVote(candidateId) {
        if (hasVoted) {
            alert("You have already cast your vote and cannot alter it.");
            return;
        }

        var confirmation = confirm("Are you sure you want to vote for candidate with ID: " + candidateId + "?");
        if (confirmation) {
            submitVote(candidateId);
            hasVoted = true;
            alert("Your vote has been cast successfully. Thank you for voting!");
        }
    }

    function submitVote(candidateId) {
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "/submitVote", true);
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    console.log("Vote submitted successfully");
                }
            };
            var data = JSON.stringify({"candidateId": candidateId});
            xhr.send(data);
    }
</script>

<body>
    <div class="dashboard-container">
        <h2>Welcome</h2>
        <p>ID: </p>
        <p>Constituency: </p>
        <h3>Elections</h3>
        <div id="elections-container">
            <h4 id="election-title"></h4>
            <p id="election-description"></p>
            <p id="election-start-date"></p>
            <p id="election-end-date"></p>
        </div>
        <button onclick="logout()">Logout</button>
    </div>
    <script src="dashboard.js"></script>
</body>
</html>
