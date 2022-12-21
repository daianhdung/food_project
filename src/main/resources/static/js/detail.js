$(document).ready(function(){
    $("[type=file]").on("change", function(){
        // Name of file and placeholder
        var This = $(this)
        var file = this.files[0].name;
        var dflt = $(this).attr("placeholder");
        if($(this).val()!=""){
            $(this).next().text(file);
        } else {
            $(this).next().text(dflt);
        }
        $('#delete-img').on('click', function (){
            This.val().text('')
        })
    });

    $("#upload-button").click(function(){
        // $(':file').on('change', function () {
        //     var file = this.files[0];
        //
        //     if (file.size > 1024) {
        //         alert('max upload size is 1k');
        //     }
        //
        //     // Also see .name, .type
        // });
        var form = $('#profileEdit')[0]
        var data = new FormData(form);
        $.ajax({
            method: "POST",
            enctype: 'multipart/form-data',
            url: "http://localhost:8080/api/file/upload",
            contentType: "application/json; charset=utf-8",
            data: data,
        }).done(function( result ) {
            if(result.success){
                alert('success')
            }else{
                alert('fail')
            }
        }).fail(
            alert(500)
        );
    })



})