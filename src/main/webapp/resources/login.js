function checkForm() {

    var login = $("#login").val();
    var pass = $("#password").val();
    var st = document.getElementById("login");
    var st2 = document.getElementById("password");
    var checkResult = true;

    if (login.length < 1) {
        st.style.boxShadow = "0 0 10px red";
        checkResult =  false;
    }
    if (login.length >= 1) {
        st.style.boxShadow = "0 0 0px";
        checkResult =  true;
    }

    if (pass.length < 1) {
        st2.style.boxShadow = "0 0 10px red";
        checkResult =  false;
    }
    if (pass.length >= 1) {
        st2.style.boxShadow = "0 0 0px";
        checkResult =  true;
    }
    return checkResult;
}