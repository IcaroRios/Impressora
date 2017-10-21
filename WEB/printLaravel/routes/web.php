<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/print', 'ApiController@printView');
Route::post('/print', 'ApiController@print');
//MELHORAR A PROTEÇÃO DAS ROTAS, CRIANDO UMA HASH E COLOCANDO COMO ROTA
Route::get('/print/userid','ApiController@getIdUploader');
Route::get('/down','ApiController@download');
Route::get('/print/count/{userId}', 'ApiController@addPrinterPages');