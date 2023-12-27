(function(){
        addCloseButtonListener();
        addInsertButtonListener();
        addUpdateButtonListener();
        addSubmitFormListener();
        addSubmitUpdateFormListener();
    }())

    //function untuk close button formnya
    function addCloseButtonListener(){
        $('.close-button').click(function(event){
            $('.modal-layer').removeClass('modal-layer--opened');
            $('.popup-dialog').removeClass('popup-dialog--opened');
            $('.popup-dialog input').val("");
            $('.popup-dialog textarea').val("");
            $('.popup-dialog .validation-message').text("");

        });
    }

    //function untuk klik insert keluarin form
    function addInsertButtonListener(){

        $('.create-button').click(function(event){
            event.preventDefault();  //mencega pengiriman formulir keserver secaara default
            $('.modal-layer').addClass('modal-layer--opened');
            $('.form-insert-dialog').addClass('popup-dialog--opened');
        });
    }


    //update
    function addUpdateButtonListener(){
        $('.update-button').click(function(event){
            event.preventDefault();
            let name = $(this).attr('data-name');
            $.ajax({
                url :`/api/category/${name}`,
                success : function(response){
                    populateInputForm(response);
                    $('.modal-layer').addClass('modal-layer--opened');
                    $('.form-update-dialog').addClass('popup-dialog--opened');
                }
            })
        });
    }


    function populateInputForm({name,floor,isle,bay}){
         $('.form-update-dialog .name').val(name);
         $('.form-update-dialog .floor').val(floor);
         $('.form-update-dialog .isle').val(isle);
         $('.form-update-dialog .bay').val(bay);
    }

    //function untuk save new supplier
    function addSubmitFormListener(){
        $('.form-insert-dialog button').click(function(event){
            event.preventDefault(); //mencegah pengiriman
            let dto = collectInsertForm();
            let requestMethod = 'PUT';
            $.ajax({
                method : requestMethod,
                url : '/api/category',
                data : JSON.stringify(dto),
                contentType:'application/json',  //kalo salah disin error 415 : CONTENT TYPE NI HARUS DIBUAT
                success : function(response){
                    location.reload();
                },
                error: function({status, responseJSON}){
                    if(status=== 422){
                        writeValidationMessage(responseJSON);
                    }
                }
            });
        })
    }

     function addSubmitUpdateFormListener(){
            $('.form-update-dialog button').click(function(event){
                event.preventDefault(); //mencegah pengiriman
                let dto = collectUpdateForm();
                let requestMethod = 'PUT';
                $.ajax({
                    method : requestMethod,
                    url : '/api/category',
                    data : JSON.stringify(dto),
                    contentType:'application/json',  //kalo salah disin error 415 : CONTENT TYPE NI HARUS DIBUAT
                    success : function(response){
                        location.reload();
                    },
                    error: function({status, responseJSON}){
                        if(status=== 422){
                            writeValidationMessage(responseJSON);
                        }
                    }
                });
            })
        }

    function collectInsertForm(){
        let dto = {
            name:$('.form-insert-dialog .name').val(),
            floor :$('.form-insert-dialog .floor').val(),
            isle:$('.form-insert-dialog .isle').val(),
            bay :$('.form-insert-dialog .bay').val(),
        }
        return dto;
    }

      function collectUpdateForm(){
        let dto = {
            name:$('.form-update-dialog .name').val(),
            floor :$('.form-update-dialog .floor').val(),
            isle:$('.form-update-dialog .isle').val(),
            bay :$('.form-update-dialog .bay').val(),
        }
            return dto;
        }


    //keluarin error validasinya
    function writeValidationMessage(errorMessages){
        for(let error of errorMessages){
            let {field, message}=error;
            $(`.form-dialog [data-for=${field}]`).text(message);
        }
    }



