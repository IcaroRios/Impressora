<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Arquivo extends Model
{

    protected $table = 'arquivo';  

    /**
     * The attributes that should be hidden for arrays.
     *
     * @var array
     */
    protected $hidden = [
        'arquivo', 'nome', 'userID'
    ];
    
}
