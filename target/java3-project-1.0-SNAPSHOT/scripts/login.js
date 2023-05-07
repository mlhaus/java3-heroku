$(function() {
    const rememberMeBtn = $("#remember-me");
    const emailFormInput = $("#email");
    const loginForm = $("#login-form");
    emailFormInput.val(localStorage.getItem("emailStored"));
    if(localStorage.getItem("rememberMe") === "true") {
        rememberMeBtn.prop('checked', true);
    } else {
        rememberMeBtn.prop('checked', false);
    }

    loginForm.on("submit", function(event) {
        event.preventDefault();
        // if (rememberMeBtn.prop('checked')) {
        if (rememberMeBtn.is(":checked")) {
            localStorage.setItem("emailStored", emailFormInput.val());
            localStorage.setItem("rememberMe", "true");
        } else {
            localStorage.removeItem("emailStored");
            localStorage.removeItem("rememberMe")
        }
        event.currentTarget.submit();
    });
});