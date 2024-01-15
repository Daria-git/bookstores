$(document).on("click", "#btn", function () {
    let inputs = form.getElementsByTagName('input');
    for (let input of inputs) {
        if (input.checked) {
            if (input.value == "buying") {
                var titlebook = $("#title").val();
                var fullname = $("#name").val();
                var toEmail = $("#toEmail").val();
                var address = $("#address").val();
                var quantity = $("#quantity").val();
                $.ajax({
                    type: 'POST',
                    url: "http://localhost:8082/BuyBook",
                    data: {
                        "titlebook": titlebook,
                        "fullname": fullname,
                        "toEmail": toEmail,
                        "address": address,
                        "quantity": quantity
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


