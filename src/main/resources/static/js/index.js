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
            var isactive = $("#favor").attr('isactive')
            if(isactive == 'true'){
                $("#favor").css("color", '#c60021')
            }else if(isactive == 'false'){
                $("#favor").css("color", 'black')
            }
            $('#foodName').text(result.data.name)
            console.log(result.data.img)
        } else {
            alert('Thất bại')
        }
    })
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
                    showToastModal('success', 'Món ăn đã được thêm vào favorite')
                }else {
                    This.attr("isactive", 'false')
                    This.css("color", 'black')
                    showToastModal('error', 'Món ăn đã được loại khỏi favorite')
                }
            } else {
                alert('Thất bại')
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
                    showToastModal('success', 'Nhà hàng đã được thêm vào favorite')
                }else {
                    This.attr("isactive", 'false')
                    This.css("color", 'black')
                    showToastModal('error', 'Nhà hàng đã được loại khỏi favorite')
                }
            } else {
                alert('Thất bại')
            }
        }).fail(function (data){
            alert(500)
        })
    });

})