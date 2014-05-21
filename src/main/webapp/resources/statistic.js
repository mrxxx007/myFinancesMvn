/**
 * Created by user on 5/12/2014.
 */
function checkForm() {

    var fromdate = $("#fromdate").val();
    var todate = $("#todate").val();
    var st = document.getElementById("fromdate");
    var st2 = document.getElementById("todate");

    var checkResult = true;

    if (fromdate.length < 1) {
        st.style.boxShadow = "0 0 10px red";
        checkResult =  false;
    }
    if (fromdate.length >= 1) {
        st.style.boxShadow = "0 0 0px";
        checkResult =  true;
    }

    if (todate.length < 1) {
        st2.style.boxShadow = "0 0 10px red";
        checkResult =  false;
    }
    if (todate.length >= 1) {
        st2.style.boxShadow = "0 0 0px";
        checkResult =  true;
    }
    return checkResult;
}