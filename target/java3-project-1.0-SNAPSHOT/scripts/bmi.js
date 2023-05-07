(function() {
    $('.input-container input').focusout(function() {
        if ($(this).val() == '') {
            $(this).removeClass('filled');
        } else {
            $(this).addClass('filled');
        }
    });
})();