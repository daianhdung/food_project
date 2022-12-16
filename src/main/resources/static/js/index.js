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
            $('#foodName').text(result.data.name)
            console.log(result.data.img)
        } else {
            alert('Thất bại')
        }
    })
});

})