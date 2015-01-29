$(document).ready(function () {
    $('#change-page-size-button').click(function(e){
        e.preventDefault();
        var value = $("selected-option").val();
        var href = this.href;
        console.log(href + value);
        var sep = (this.href.indexOf('?') != -1) ? '&' : '?';
        $(this).attr('href', href + sep + 'Hello=');
        this.click();
    });
});