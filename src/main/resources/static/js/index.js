

function showToast(icon, title){
    const Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 3000,
        timerProgressBar: true,
        didOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
        }
    })
    Toast.fire({
        icon: icon,
        title: title
    })
}

function showToastModal(icon, title){
    const Toast = Swal.mixin({
        toast: true,
        position: 'top',
        showConfirmButton: false,
        timer: 3000,
        timerProgressBar: true,
        didOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
        }
    })
    Toast.fire({
        icon: icon,
        title: title
    })
}
$(document).ready(function(){
$("[foodId='click']").click(function() {
    var id = $(this).attr("id")
    var This = $(this)
    $.ajax({
        method: "GET",
        url: "http://localhost:8080/api/food/" + id,
        contentType: "application/json; charset=utf-8"
    }).done(function (result) {
        if (result.success) {
            $('#foodImg').attr('src','/img/'+ result.data.img)
            $('#foodImg').attr('foodId',result.data.id)
            $("#favor").attr('isactive', result.data.isFavor)
            $("#foodPrice").text("ADD ($" + result.data.price + ")")
            $("#foodPrice").attr('priceFood', result.data.price)
            var isactive = $("#favor").attr('isactive')
            if(isactive == 'true'){
                $("#favor").css("color", '#c60021')
            }else if(isactive == 'false'){
                $("#favor").css("color", 'black')
            }
            $('#foodName').text(result.data.name)
            var listFoodAdd = result.data.foodAddonEntities
            listFoodAdd.forEach(function (item, index) {
                $("#formFoodAdd .food-add").eq(index).text(item.name)
                $("#formFoodAdd .food-add").eq(index).attr('price', item.price)
            });

        } else {
            alert('Th???t b???i')
        }
    })
});

$("#foodPrice").click(function() {
    var items = []
    var idFood = $('#foodImg').attr('foodId')
    var nameFood = $('#foodName').text()
    var imgFood = $('#foodImg').attr('src')
    var priceFood = $("#foodPrice").attr('priceFood')
    var item = {
        'idFood' : idFood,
        'imgFood': imgFood,
        'nameFood': nameFood,
        'price': priceFood,
        'quality': 1
    }
    if(JSON.parse(localStorage.getItem('items')) === null){
        items.push(item)
        localStorage.setItem('items', JSON.stringify(items))
    }else{
        const localItems = JSON.parse(localStorage.getItem('items'))
        $.map(localItems, function(data){
            if(item.idFood == data.idFood){
                item.quality = data.quality + 1;
            }
            else{
                items.push(data)
            }
        })
        items.push(item)
        localStorage.setItem('items', JSON.stringify(items))
    }
});

    $(".foodCart").click(function() {
        $("#body-cart-modal").html("");
        let localItemsCart = JSON.parse(localStorage.getItem('items'))
        $('#totalItem').text('(' + localItemsCart.length + ' items)')
        localItemsCart.forEach(function (item, index) {
            $('#body-cart-modal').append(`<div class="d-flex align-items-center mb-3">
            <div class="mr-2"><img width="90" src="${item.imgFood}" class="img-fluid rounded"></div>
            <div class="small text-black-50">${item.quality} x</div>
            <div class="ml-2">
                <p class="mb-0 text-black">${item.nameFood}</p>
                <p class="mb-0 small">$${item.price}</p>
            </div>
            <a href="#" id="button-delete-food" so="${index}" class="ml-auto"><i class="btn btn-light text-danger mdi mdi-trash-can-outline rounded"></i></a>
        </div>`)
        });
        var totalCheckout = 0;
        $.map(localItemsCart, function(data){
            let totalPriceFood = data.quality * data.price;
            totalCheckout += totalPriceFood
        })
        $('#button-checkout').text('CHECKOUT ($' + totalCheckout + ')')
        $('#confirm-checkout').text('CONFIRM PAYMENT ($' + totalCheckout + ')')
    });
    $("#button-checkout").click(function() {
        $("#card-choose").addClass("disabledbutton");
        $("#add-card").addClass("disabledbutton")
    })
    $(document).on('click', '#button-delete-food', function(){
        let localItemsCart = JSON.parse(localStorage.getItem('items'))
        var This = $(this)
        let index = This.attr('so')
        localItemsCart.splice(index, 1)
        This.closest('div').remove()
        var totalCheckout = 0;
        $.map(localItemsCart, function(data){
            let totalPriceFood = data.quality * data.price;
            totalCheckout += totalPriceFood
        })
        $('#totalItem').text('(' + localItemsCart.length + ' items)')
        $('#button-checkout').text('CHECKOUT ($' + totalCheckout + ')')
        $('#confirm-checkout').text('CONFIRM PAYMENT ($' + totalCheckout + ')')
        localStorage.setItem('items', JSON.stringify(localItemsCart))
    })

    $("#confirm-checkout").click(function() {
        let optionsPay = $('input[name=options]:checked').attr('value')
        if(optionsPay != 'COD'){
            showToastModal('error', 'T??nh n??ng n??y ??ang ph??t tri???n')
        }else{
            data = localStorage.getItem('items')
            $.ajax({
            method: "POST",
            url: "http://localhost:8080/api/order",
            data: data,
            contentType: "application/json; charset=utf-8"
        }).done(function (result) {
            if (result.success) {
                localStorage.removeItem('items')
                showToastModal('success', 'Order th??nh c??ng')
                window.setTimeout(function(){
                    window.location.href = "/order";
                }, 2300)
            } else {
                alert('Th???t b???i')
            }
        }).fail(function (data){
            alert(500)
        })
        }
    });

    $("#favor").click(function() {
        var isFavor = $(this).attr("isactive")
        if(isFavor == 'true'){
            isFavor = 'false';
        }else if (isFavor == 'false'){
            isFavor = 'true'
        }
        var idFood = $('#foodImg').attr('foodId')
        var This = $(this)
        var data = {}
        data["idUser"] = 0
        data["idFood"] = idFood
        data["isFavor"] = isFavor
        $.ajax({
            method: "PUT",
            url: "http://localhost:8080/api/favor-toggle",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8"
        }).done(function (result) {
            console.log(result)
            if (result.success) {
                if(result.data){
                    This.attr("isactive", 'true')
                    This.css("color", '#c60021')
                    showToastModal('success', 'M??n ??n ???? ???????c th??m v??o favorite')
                }else {
                    This.attr("isactive", 'false')
                    This.css("color", 'black')
                    showToastModal('error', 'M??n ??n ???? ???????c lo???i kh???i favorite')
                }
            } else {
                alert('Th???t b???i')
            }
        }).fail(function (data){
            alert(500)
        })
    });

    $("#favorRes").click(function() {
        var isFavorRes = $(this).attr("isactive")
        if(isFavorRes == 'true'){
            isFavorRes = 'false';
        }else if (isFavorRes == 'false'){
            isFavorRes = 'true'
        }
        var idRestau = $(this).attr('idRestau')
        var This = $(this)
        var data = {}
        data["idUser"] = 0
        data["idRestaurant"] = idRestau
        data["isFavor"] = isFavorRes
        $.ajax({
            method: "PUT",
            url: "http://localhost:8080/api/restaurant-favor/" + idRestau,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8"
        }).done(function (result) {
            console.log(result)
            if (result.success) {
                if(result.data){
                    This.attr("isactive", 'true')
                    This.attr('style', 'color: red !important')
                    showToastModal('success', 'Nh?? h??ng ???? ???????c th??m v??o favorite')
                }else {
                    This.attr("isactive", 'false')
                    This.css("color", 'black')
                    showToastModal('error', 'Nh?? h??ng ???? ???????c lo???i kh???i favorite')
                }
            } else {
                alert('Th???t b???i')
            }
        }).fail(function (data){
            alert(500)
        })
    });

})
