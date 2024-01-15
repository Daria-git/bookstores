 $(document).on("click", "#btn", function () {
    let inputs = form.getElementsByTagName('input');
    for (let input of inputs) {
        if (input.checked) {
            if (input.value == "sign") {
                var password = $("#pass").val();
                $.ajax({
                    type: 'POST',
                    url: "http://localhost:8082/Login",
                    data: {
                        "password": password
                    },
                    success: function (data) {
                        $(".answer").html(data);
                        location.href = "http://localhost:63342/Books/Books.main/FrontEnd/Admin.html"
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


