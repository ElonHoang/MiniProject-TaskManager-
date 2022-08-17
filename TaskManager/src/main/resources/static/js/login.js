
function validateForm() {
    let username = document.forms["myForm1"]["username"].value;
    let password = document.forms["myForm1"]["password"].value;
    let button = document.querySelector("#buttonLogin");
    let text;
    if (username.length == 0) {
        text = "Username not be empty !";
        document.getElementById("checkErrorUsername").innerHTML = text;
        button.disabled = true;
        return false;
    }
    if (password.length == 0) {
        text = "Password not be empty !";
        document.getElementById("checkErrorPassword").innerHTML = text;
        button.disabled = true;
        return false;
    }
    button.disabled = false;

}