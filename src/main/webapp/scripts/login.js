$(function() {
    const rememberMeBtn = $("#remember-me");
    const emailFormInput = $("#email");
    const loginForm = $("#login-form");
    console.log(rememberMeBtn);
    emailFormInput.val(localStorage.getItem("emailStored"));

    loginForm.on("submit", function(event) {
        if (rememberMeBtn.prop('checked')) {
            console.log("remember me");
            localStorage.setItem("emailStored", emailFormInput.val());
        } else {
            console.log("don't remember me");
            localStorage.removeItem("emailStored");
        }
    });
});