/**
 * Created by user on 5/8/2014.
 */

function checkForm(form) {

    var login = $("#login").val();
    var st = document.getElementById("login");
    var pass = $("#password").val();
    var st2 = document.getElementById("password");
    var pass2 = $("#repeatpassword").val();
    var st3 = document.getElementById("repeatpassword");

    var checkResult = true;

    if (login == "") {
        st.style.boxShadow = "0 0 10px red";
        checkResult =  false;
    }
    if (login != "") {
        st.style.boxShadow = "0 0 0px";
        checkResult = true;
    }
    if (pass == "") {
        st2.style.boxShadow = "0 0 10px red";
        checkResult =  false;
    }
    if (pass != "") {
        st2.style.boxShadow = "0 0 0px";
        checkResult = true;
    }
    if (pass2 == "") {
        st3.style.boxShadow = "0 0 10px red";
        checkResult =  false;
    }
    if (pass2 != "") {
        st3.style.boxShadow = "0 0 0px";
        checkResult = true;
    }
    if (pass != pass2) {
        alert("Error: Different passwords");
        checkResult =  false;
    }
    if (pass == login) {
        alert("Error: Password must be different from Username!");
        checkResult =  false;
    }

    return checkResult;
}