var balance = $("#balanceLbl").html();
$("#balanceLbl").html(balance);
if (balance >= 0) {
    $("#balanceLbl").attr("class","label label-success");
} else {
    $("#balanceLbl").attr("class","label label-danger");
}