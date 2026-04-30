<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>GEVS - Registration</title>
    <link rel="stylesheet" type="text/css" href="registration.css">
</head>
<script>
    function handleResponse(response) {
        if (response.status === 200) {
            alert("Registration successful!");
        } else if (response.status === 400) {
            alert("Registration failed: " + response.data);
        } else if (response.status === 409) {
            alert("Registration failed: " + response.data);
        } else {
            alert("An unexpected error occurred.");
        }
    }

    function registerUser() {
        var voterId = document.getElementById('voterId').value;
        var fullName = document.getElementById('fullName').value;
        var dob = document.getElementById('dob').value;
        var password = document.getElementById('password').value;
        var constituency = document.getElementById('constituency').value;
        var uvc = document.getElementById('uvc').value;

        var xhr = new XMLHttpRequest();
        xhr.open("POST", "/election/save", true);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                handleResponse(JSON.parse(xhr.responseText));
            }
        };
        var data = JSON.stringify({
            "voterId": voterId,
            "fullName": fullName,
            "dob": dob,
            "password": password,
            "constituency": constituency,
            "uvc": uvc
        });
        xhr.send(data);
    }

    function verifyUVC() {
        var uvc = document.getElementById('uvc').value;

        var xhr = new XMLHttpRequest();
        xhr.open("POST", "/election/verifyUVC", true);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    alert("UVC verification successful!");
                } else if (xhr.status === 400) {
                    alert("UVC verification failed: " + xhr.responseText);
                } else if (xhr.status === 409) {
                    alert("UVC verification failed: " + xhr.responseText);
                } else {
                    alert("An unexpected error occurred during UVC verification.");
                }
            }
        };
        xhr.send(JSON.stringify(uvc));
    }

    document.querySelector('form').addEventListener('submit', function(event) {
        event.preventDefault();
        verifyUVC();
        registerUser();
    });
</script>
<body>
    <div class="container">
        <h1>Register as a Voter</h1>
        <form>
            <label for="voterId">Email:</label>
            <input type="email" id="voterId" name="voterId" required>

            <label for="fullName">Name:</label>
            <input type="text" id="fullName" name="fullName" required>

            <label for="dob">Date of Birth:</label>
            <input type="date" id="dob" name="dob" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <label for="constituency">Constituency:</label>
            <input type="text" id="constituency" name="constituency" required>

            <label for="uvc">Unique Voter Code:</label>
            <input type="text" id="uvc" name="uvc" required>

            <input type="submit" value="Register">
        </form>
    </div>
</body>
</html>