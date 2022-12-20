$(document).ready(function(){
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
        var form = $('#fileUploadForm')[0]

        $.ajax({
            method: "POST",
            enctype: 'multipart/form-data',
            url: "http://localhost:8080/api/file/upload",
            contentType: "application/json; charset=utf-8",
            data: data,
        }).done(function( result ) {
            if(result.success){

            }else{

            }
        }).fail(
            alert(500)
        );
    })

})