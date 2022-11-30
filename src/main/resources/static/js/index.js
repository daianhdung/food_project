$.ajax({
    method: "GET",
    url: "http://localhost:8080/api/signin",
    contentType: "application/json; charset=utf-8",
    data: JSON.stringify(data)
  }).done(function( data ) {
        if(data.success){
            
        }else{
            console.log(data);
        }
    }).fail(
        console.log(data)
);