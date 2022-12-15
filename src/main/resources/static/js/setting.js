$(document).ready(function () {
    $('#btn-update-profile').click(function (){
        var data = {}
        var formEditProfile = $("#profileEdit").serializeArray()
        $.each(formEditProfile, function (i, v) {
            data[""+v.name+""] = v.value
        })
        $.ajax({
            method: "PUT",
            url: "http://localhost:8080/api/update-profile",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            dataType: 'json',
        }).done(function (data){
            console.log(data);
            showToast('success', 'Update job success')
        }).fail(function (data){
            showToast("error", 'Update job failed')
        })
    });
})