async function listaFilmes(){

    const conexao = await fetch("http://localhost:8183/filmes");
    const conexaoConvertida = await conexao.json();

    console.log(conexaoConvertida);
}


listaFilmes();