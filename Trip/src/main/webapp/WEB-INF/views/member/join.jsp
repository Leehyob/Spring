<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Modal Signup Form</h2>

<button onclick="document.getElementById('id01').style.display='block'" style="width:auto;">Sign Up</button>

<div id="id01" class="modal">
  <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
  <form class="modal-content" action="/member/join" method="post">
    <div class="container">
      <h1>Sign Up</h1>
      <p>Please fill in this form to create an account.</p>
      <hr>
      <label for="email"><b>Email</b></label>
      <input type="text" placeholder="Enter Email" name="member_email" required>

      <label for="pwd"><b>Password</b></label>
      <input type="password" placeholder="Enter Password" name="pwd" required>

      <label for="psw-repeat"><b>Repeat Password</b></label>
      <input type="password" placeholder="Repeat Password" name="pwd-repeat" required>
      
      <label for="name"><b>Name</b></label>
      <input type="text" placeholder="Enter Username" name="name" required>
      
      <label for="phone"><b>Phone</b></label>
      <input type="text" placeholder="Enter Phone Number" name="phone" required>
      
      <p>By creating an account you agree to our <a href="#" style="color:dodgerblue">Terms & Privacy</a>.</p>

      <div class="clearfix">
        <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">Cancel</button>
        <button type="submit" class="signupbtn">Sign Up</button>
      </div>
    </div>
  </form>
</div>
</body>
</html>