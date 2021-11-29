const path = require('path')
const dbConnection = require(path.join(__dirname, 'dbConnection.js'))

function actualizar(idUsuario, perfil, callback){
    dbConnection.query('call actualizarPerfil(?, ?, ?)', [idUsuario, perfil.imgRuta, perfil.bannerRuta], (err, rows, fields) =>{
        if(err){
            return callback(err)
        }
        else{
            callback(null, rows)
        }
    })
}

function obtenerPerfil(idUsuario, callback){
    dbConnection.query('call obtenerPerfil(?)', [idUsuario], (err, rows, fields)=>{
        if (err){
            return callback(err)
        }
        else {
            callback(null, rows[0][0])
        }
    })
}


module.exports = {actualizar, obtenerPerfil}