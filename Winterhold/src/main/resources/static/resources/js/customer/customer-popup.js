(function(){
        addContactButtonListener();
        addCloseButtonListener();
    }())
 function addContactButtonListener(){
        $('.biodata-button').click(function(event){
            let membershipNumber = $(this).attr('data-membershipNumber');
            $.ajax({
                url : `/api/customer/biodata/${membershipNumber}`,
                success : function({membershipNumber,firstName,lastName,birthDate,gender,phone,address}){
                    $('.biodata-dialog .membershipNumber').text(membershipNumber);
                    $('.biodata-dialog .fullName').text(firstName+" "+lastName);
                    $('.biodata-dialog .birthDate').text(birthDate);
                    $('.biodata-dialog .gender').text(gender);
                    $('.biodata-dialog .phone').text(phone);
                    $('.biodata-dialog .address').text(address);
                    $('.modal-layer').addClass('modal-layer--opened');
                    $('.biodata-dialog').addClass('popup-dialog--opened');
                }
            })
        });
    }

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