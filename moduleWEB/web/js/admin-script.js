//On button click provides access to corresponded field(s)
$(':button').click(function (event) {
    //Alert just to show buttons work
    alert($(this).closest("tr").find(".subject").text());
    alert($(this).closest("tr").find(".currVal").text());
});