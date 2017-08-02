window.onload = function(){
    var spanz = document.getElementsByTagName("span");
    for ( var i = 0; i < spanz.length; i++ )
        if ( spanz[i].title ) {
            var date = prettyDate(spanz[i].title);
            if ( date )
                spanz[i].innerHTML = date;
        }
};
$('.btn-tooltip').tooltip('show');