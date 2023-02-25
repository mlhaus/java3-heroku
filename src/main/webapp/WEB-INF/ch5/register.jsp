<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Map<String, String> results = (Map<String, String>)request.getAttribute("results");
    if(results == null) {
        results = new HashMap<>();
    }
    String firstName = results.containsKey("firstName") ? results.get("firstName") : "";
    String lastName = results.containsKey("lastName") ? results.get("lastName") : "";
    String email = results.containsKey("email") ? results.get("email") : "";
    String phone = results.containsKey("phone") ? results.get("phone") : "";
    String password1 = results.containsKey("password1") ? results.get("password1") : "";
    String password2 = results.containsKey("password2") ? results.get("password2") : "";
    String agreeChecked = results.containsKey("agreeToTerms") && results.get("agreeToTerms").equals("agree") ? "checked" : "";

    String firstNameError = results.containsKey("firstNameError") ? results.get("firstNameError") : "";
    String lastNameError = results.containsKey("lastNameError") ? results.get("lastNameError") : "";
    String emailError = results.containsKey("emailError") ? results.get("emailError") : "";
    String phoneError = results.containsKey("phoneError") ? results.get("phoneError") : "";
    String password1Error = results.containsKey("password1Error") ? results.get("password1Error") : "";
    String password2Error = results.containsKey("password2Error") ? results.get("password2Error") : "";
    String agreeError = results.containsKey("agreeError") ? results.get("agreeError") : "";

    String firstNameInvalid = results.containsKey("firstNameError") ? "is-invalid" : "";
    String lastNameInvalid = results.containsKey("lastNameError") ? "is-invalid" : "";
    String emailInvalid = results.containsKey("emailError") ? "is-invalid" : "";
    String phoneInvalid = results.containsKey("phoneError") ? "is-invalid" : "";
    String password1Invalid = results.containsKey("password1Error") ? "is-invalid" : "";
    String password2Invalid = results.containsKey("password2Error") ? "is-invalid" : "";
    String agreeInvalid = results.containsKey("agreeError") ? "is-invalid" : "";

    String userAddSuccess = results.containsKey("userAddSuccess") ? results.get("userAddSuccess") : "";
    String userAddFail = results.containsKey("userAddFail") ? results.get("userAddFail") : "";
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Register a User</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body class="bg-light">

<div class="container">
    <main>
        <div class="pt-4 pb-2 text-center">
            <h2>Register New User</h2>
            <p class="lead">Enter personal information below to create a new user account.</p>
        </div>

        <div class="row">
            <div class="col-md-8 mx-auto">
                <form method="post" action="register">
                    <div class="row">
                        <div class="col-sm-6 mb-2">
                            <label for="firstName" class="form-label">First name</label>
                            <input type="text" class="form-control <%= firstNameInvalid %>" id="firstName" name="firstName" value="<%= firstName %>">
                            <div class="invalid-feedback"><%= firstNameError %></div>
                        </div>

                        <div class="col-sm-6 mb-2">
                            <label for="lastName" class="form-label">Last name</label>
                            <input type="text" class="form-control <%= lastNameInvalid %>" id="lastName" name="lastName" value="<%= lastName %>">
                            <div class="invalid-feedback"><%= lastNameError %></div>
                        </div>

                        <div class="col-sm-6 mb-2">
                            <label for="email" class="form-label">Email</label>
                            <input type="text" class="form-control <%= emailInvalid %>" id="email" placeholder="you@example.com" name="email" value="<%= email %>">
                            <div class="invalid-feedback"><%= emailError %></div>
                        </div>

                        <div class="col-sm-6 mb-2">
                            <label for="phone" class="form-label">Phone Number</label>
                            <input type="text" class="form-control <%= phoneInvalid %>" id="phone" placeholder="123-456-7890" name="phone" value="<%= phone %>">
                            <div class="invalid-feedback"><%= phoneError %></div>
                        </div>

                        <div class="col-sm-6 mb-2">
                            <label for="password1" class="form-label">Password</label>
                            <input type="password" class="form-control <%= password1Invalid %>" id="password1" name="password1" value="<%= password1 %>">
                            <div class="invalid-feedback"><%= password1Error %></div>
                        </div>

                        <div class="col-sm-6 mb-2">
                            <label for="password2" class="form-label">Re-enter Password</label>
                            <input type="password" class="form-control <%= password2Invalid %>" id="password2" name="password2" value="<%= password2 %>">
                            <div class="invalid-feedback"><%= password2Error %></div>
                        </div>

                        <div class="col-12 mb-2">
                            <span class="text-muted">Minimum 8 characters with at least 1 uppercase letter, 1 lowercase letter, and 1 number</span>
                        </div>

                    </div>

                    <div class="col-12 form-check my-4">
                        <input type="checkbox" class="form-check-input <%= agreeInvalid %>" id="agree-to-terms" name="agree-to-terms" value="agree" <%= agreeChecked %>>
                        <label class="form-check-label" for="agree-to-terms">I agree to the <a href="#">terms and conditions</a>.</label>
                        <div class="invalid-feedback"><%= agreeError %></div>
                    </div>

                    <button class="w-100 btn btn-primary btn-lg" type="submit">Create new user</button>
                </form>
                <% if(!userAddSuccess.equals("")) { %>
                <div class="alert alert-success mb-2" role="alert">
                    <%= userAddSuccess %>
                </div>
                <% } else if(!userAddFail.equals("")) { %>
                <div class="alert alert-danger mb-2" role="alert">
                    <%= userAddFail %>
                </div>
                <% } %>
            </div>
        </div>
    </main>

    <footer class="my-5 pt-5 text-muted text-center text-small">
        <p class="mb-1">&copy; 2017-<script>document.write(new Date().getFullYear())</script> Company Name</p>
        <ul class="list-inline">
            <li class="list-inline-item"><a href="#">Privacy Policy</a></li>
            <li class="list-inline-item"><a href="#">Terms and Conditions</a></li>
            <li class="list-inline-item"><a href="#">Support</a></li>
        </ul>
    </footer>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>