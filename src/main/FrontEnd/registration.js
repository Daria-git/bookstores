 $(document).on("click", "#btn", function () {
    let inputs = form.getElementsByTagName('input');
    for (let input of inputs) {
        if (input.checked) {
            if (input.value == "signin") {
                var password = $("#pass").val();
                var login = $("#email").val();
                $.ajax({
                    type: 'POST',
                    url: "http://localhost:8082/SignIn",
                    data: {
                        "login": login,
                        "password": password
                    },
                    success: function (data) {
                        $(".answer").html(data);
                        location.href = "http://localhost:63342/Books/Books.main/FrontEnd/bookshop/Books.html";
                    },
                    error: function (data) {
                        console.log(data);
                        alert(data.responseJSON.message);
                    }
                });
                return false;

            } else if (input.value == "signup") {
                var password = $("#pass").val();
                var checkPassword = $("#repass").val();
                var login = $("#email").val();
                $.ajax({
                    type: 'POST',
                    url: "http://localhost:8082/SignUp",
                    data: {
                        "login": login,
                        "password": password,
                        "checkPassword": checkPassword
                    },
                    success: function (data) {
                        $(".answer").html(data);
                        location.href = "http://localhost:63342/Books/Books.main/FrontEnd/bookshop/Books.html";
                        console.log(data);
                    },
                    error: function (data) {
                        console.log(data);
                        alert(data.responseJSON.message);
                    }
                });
                return false;

            } else {
                var login = $("#email").val();
                $.ajax({
                    type: 'POST',
                    url: "http://localhost:8082/Resetpassword",
                    data: {
                        "login": login
                    },
                    success: function (data) {
                        $(".answer").html(data);
                        console.log(data);
                    },
                    error: function (data) {
                        console.log(data);
                        alert(data.responseJSON.message);
                    }
                });
                return false;
            }
        }
    }
});


