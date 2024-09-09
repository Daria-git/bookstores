$(document).on("click", "#btn", function () {
    let inputs = form.getElementsByTagName('input');
    for (let input of inputs) {
        if (input.checked) {
            if (input.value == "admin") {
                var title = $("#title").val();
                var author = $("#author").val();
                var price = $("#price").val();
                var image = $("#image").val();
                $.ajax({
                    type: 'POST',
                    url: "http://localhost:8082/AddBook",
                    data: {
                        "title": title,
                        "author": author,
                        "price": price,
                        "image": image

                    },
                    success: function (response) {
                        $(".answer").html(response);
                        location.href = "http://localhost:63342/Books/Books.main/FrontEnd/bookshop/Books.html"
                        console.log(response);
                    },
                    error: function (response) {
                        console.log(response);
                        alert(data.responseJSON.message);
                    }
                });
                return false;
            }
        }
    }
});







