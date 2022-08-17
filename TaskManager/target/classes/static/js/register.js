function validateForm() {
    let password = document.forms["myForm"]["password"].value;
    let confirm = document.forms["myForm"]["confirmPassword"].value;
    let button = document.querySelector("#button");
    let text;
    if (confirm != password) {
        text = "Confirm Password and Password not equal !";
        document.getElementById("checkError").innerHTML = text;
        button.disabled = true;
        return false;
    }
    button.disabled = false;

}
