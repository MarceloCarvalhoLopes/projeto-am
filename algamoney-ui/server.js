const express = require('express');
const app = express();

// diretório dist é onde está o código compilado que será executado
// podemos alterar o diretório que será gerada a build -> angular.json / outputPath

app.use(express.static(__dirname + '/dist/algamoney-ui'));

// qualquer rota vai servir o index.html
app.get('/*', function(req, res){
  res.sendFile(__dirname + '/dist/algamoney-ui/index.html');
});

app.listen(4200);
