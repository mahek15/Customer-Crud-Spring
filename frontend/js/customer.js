getAllCustomers();
function saveCustomer() {
    let firstName = $('#exampleFormControlInput2').val();
    let lastName = $('#exampleFormControlInput3').val();
    let street = $('#exampleFormControlInput4').val();
    let address = $('#exampleFormControlInput5').val();
    let city = $('#exampleFormControlInput6').val();
    let state = $('#exampleFormControlInput7').val();
    let email = $('#exampleFormControlInput8').val();
    let phone = $('#exampleFormControlInput9').val();

    $.ajax({
        method: "POST",
        contentType: "application/json",
        url: "http://localhost:9080/api/v1/customers/saveCustomer",
        async: true,
        data: JSON.stringify({
            "custID": "",
            "firstName": firstName,
            "lastName": lastName,
            "street": street,
            "address": address,
            "city": city,
            "state": state,
            "email": email,
            "phone": phone
        }),
        success: function (data) {
            alert("Saved");
            getAllCustomers();
        },
        error: function (xhr, exception) {
            alert("Error");
        }
    });
}
function updateCustomer(){
    let empID=$('#exampleFormControlInput1').val();
    let firstName = $('#exampleFormControlInput2').val();
    let lastName = $('#exampleFormControlInput3').val();
    let street = $('#exampleFormControlInput4').val();
    let address = $('#exampleFormControlInput5').val();
    let city = $('#exampleFormControlInput6').val();
    let state = $('#exampleFormControlInput7').val();
    let email = $('#exampleFormControlInput8').val();
    let phone = $('#exampleFormControlInput9').val();

    $.ajax({
        method:"PUT",
        contentType:"application/json",
        url:"http://localhost:9080/api/v1/customers/updateCustomer",
        async:true,
        data:JSON.stringify({
            "custID":custID,
            "firstName": firstName,
            "lastName": lastName,
            "street": street,
            "address": address,
            "city": city,
            "state": state,
            "email": email,
            "phone": phone
        }),
        success: function (data) {
            alert("Updated")
            getAllCustomers()
        },
        error: function (xhr, exception) {
            alert("Error")
        }
    })

}
function deleteCustomer() {
    let custID = $('#exampleFormControlInput1').val();

    $.ajax({
        method: "DELETE",
        url: "http://localhost:9080/api/v1/customers/deleteCustomer/" + custID,
        async: true,
        success: function (data) {
            alert("Deleted");
            getAllCustomers();
        },
        error: function (xhr, exception) {
            alert("Error");
        }
    });
}

function getAllCustomers() {
    $.ajax({
        method: "GET",
        url: "http://localhost:9080/api/v1/customers/getAllCustomers",
        async: true,
        success: function (data) {
            if (data.code === "00") {
                $('#custTable').empty();
                for (let customer of data.content) {
                    let custID = customer.custID;
                    let firstName = customer.firstName;
                    let lastName = customer.lastName;
                    let street = customer.street;
                    let address = customer.address;
                    let city = customer.city;
                    let state = customer.state;
                    let email = customer.email;
                    let phone = customer.phone;

                    var row = `<tr><td>${custID}</td><td>${firstName}</td><td>${lastName}</td><td>${street}</td><td>${address}</td><td>${city}</td><td>${state}</td><td>${email}</td><td>${phone}</td></tr>`;
                    $('#custTable').append(row);
                }
            }
        },
        error: function (xhr, exception) {
            alert("Error");
        }
    });
}

$(document).ready(function () {
    $(document).on('click', '#custTable tr', function () {
        var col0 = $(this).find('td:eq(0)').text();
        var col1 = $(this).find('td:eq(1)').text();
        var col2 = $(this).find('td:eq(2)').text();
        var col3 = $(this).find('td:eq(3)').text();
        var col4 = $(this).find('td:eq(4)').text();
        var col5 = $(this).find('td:eq(5)').text();
        var col6 = $(this).find('td:eq(6)').text();
        var col7 = $(this).find('td:eq(7)').text();
        var col8 = $(this).find('td:eq(8)').text();

        $('#exampleFormControlInput1').val(col0);
        $('#exampleFormControlInput2').val(col1);
        $('#exampleFormControlInput3').val(col2);
        $('#exampleFormControlInput4').val(col3);
        $('#exampleFormControlInput5').val(col4);
        $('#exampleFormControlInput6').val(col5);
        $('#exampleFormControlInput7').val(col6);
        $('#exampleFormControlInput8').val(col7);
        $('#exampleFormControlInput9').val(col8);
    });
});
