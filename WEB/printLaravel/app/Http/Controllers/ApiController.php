<?php

namespace App\Http\Controllers;

use Request;
use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Auth;
Use Log;
use Hash;
use Illuminate\Support\Facades\Storage;
use App\Arquivo;

class ApiController extends Controller
{


    public function printView(){
      return view('print');
    }
    // vai retornar a id do usuário que enviou o arquivo que será impresso para o programa java.
    public function getIdUploader(){
        $arquivoObj = Arquivo::first();
        if($arquivoObj)
          return $arquivoObj->userID;        
        return 0;
    }

    public function addPrinterPages(){
      $idUser = Request::route('userId');
      dd($idUser);
      /**
       *OLHAR NO BANCO DO C4W, BASICAMENTE, PEGAR A ID DO USUÁRIO PARA CONTABILIZAR AS FOLHAS GASTAS
       */
    }

    public function print(){
      $req = Request::all();
      $fileUpload  = $req['arquivo'];
      $userID = "10";
      //dd($fileUpload );
      $nome = $fileUpload->getClientOriginalName(); // pegou o nome do arquivo
      //DEVE SER TIRADA UMA HASH BASEADA EM TEMPO PARA COLOCAR JUNTO COM O NOME DO ARQUIVO, PARA EVITAR 
      //SUBSTITUIÇÕES.
      $fileUpload->storeAs('arquivos',$nome); // AQUI ESTÁ COLOCANDO O ARQUIVO NO storage/app/arquivos
      //a userID está colocada como defaul para 10, apenas para testes.
      $query = DB::insert("insert into arquivo (nome, userID) values (?,?)",[$nome,$userID]); //salvando apenas o nome do arquivo

      return redirect('/print');
    }
    public function download(){

      $arquivoObj = Arquivo::first();
      if($arquivoObj){
        $nomeArquivo = $arquivoObj->nome; // pega o nome do arquivo
        $arquivoObj->delete();        // deleta o arquivo (AQUI TAMBÉM DEVE CONTAR AS PAGINAS QUE O CARA IMPRIMIU)
        $caminho = base_path('storage\app\arquivos\\');        
        return response()->download($caminho.$nomeArquivo)->deleteFileAfterSend(true);// FORÇA O DOWNLOAD E DELETA
      }
      //return redirect('/print');
    }

}