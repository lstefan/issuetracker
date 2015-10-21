$(document).ready(function() {
    $('#dropdown1').change(function() {
        var selectedValue = $(this).val();
        var servletUrl = 'dropdown2options?value=' + selectedValue;

        $.getJSON(servletUrl, function(options) {
            var dropdown2 = $('#dropdown2');
            $('>option', dropdown2).remove(); // Clean old options first.
            if (options) {
                $.each(opts, function(key, value) {
                    dropdown2.append($('<option/>').val(key).text(value));
                });
            } else {
                dropdown2.append($('<option/>').text("Please select dropdown1"));
            }
        });
    });
});