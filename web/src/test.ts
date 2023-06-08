import $ from 'jquery';

export function change() {
    let request = $.ajax({
        type: 'GET',
        url: 'https://pokeapi.co/api/v2/pokemon/1',
        contentType: 'application/json'
    });
    request.done(function(res) {
        console.log(res);
        $('.title').text(res.species.name );
    });
    request.fail(function(jqXHR) {
        console.error(jqXHR)
    });
}