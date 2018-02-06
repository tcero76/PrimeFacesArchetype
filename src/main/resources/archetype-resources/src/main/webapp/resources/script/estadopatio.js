/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function handleMessage(data) {
    var e = eval("(" + data + ')');
    for (i = 0; i < data.length; i++) {
        $('.estatus' + e[i].nombre).text(ep[i].ocupado);
    }
}
