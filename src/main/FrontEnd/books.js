$(document).on("click", "#btn", function () {
    let inputs = boo.getElementsByTagName('input');
    for (let input of inputs) {
        if (input.checked) {
            if (input.value == "books") {
                $.ajax({
                    url: "http://localhost:8082/GetAllBooks",
                    type: "post",
                    data: {press: 'value'},
                    success: function (response) {
                        $(".answer").html(response);
                        console.log(response);
                        var html = '<div>';
                        $.each(response, function (index, value) {
                            html += '<h1>';
                            html += '<p>' + value.title + '</p>';
                            html += '<p>' + value.author + '</p>';
                            html += '<p>' + value.price + '</p>';
                            html += '<img src=' + value.image + 'style= width="250" height="385"/>';
                            html += '</h1>';
                            html += '</div>';
                            $('div').html(html);
                        });
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

})






