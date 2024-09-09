 $(document).on("click", "#butt", function () {
    let inputs = form.getElementsByTagName('input');
    for (let input of inputs) {
        if (input.checked) {
            var author = $("#author").val();
            $.ajax({
                type: 'POST',
                url: "http://localhost:8082/GetBookByAuthor",
                data: {
                    "author": author
                },
                success: function (data) {
                    $(".answer").html(data);
                    console.log(data);
                    var html = '<div>';
                    $.each(data, function (index, value) {
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
                error: function (data) {
                    console.log(data);
                    alert(data.responseJSON.message);
                }
            });
            return false;
        }
    }
});






